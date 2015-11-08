import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ReceiverBeacon extends Thread {

	private BeaconObject thisBeacon;
	public ReceiverBeacon(){
		
	}
	public ReceiverBeacon(BeaconObject thisBeacon){
		this.thisBeacon = thisBeacon;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		DatagramSocket serverSocket;
		try {
			serverSocket = new DatagramSocket(9900);
			byte[] receivedData = new byte[1024];
			while (true) {
				do {
					System.out.println("[state : Waiting for Connection]");
					receivedData = new byte[1024];
				} while (serverSocket.isConnected());
				
				DatagramPacket receivePacket = new DatagramPacket(receivedData,
						receivedData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
				//Receive Text
				System.out.print("[state : received from : " + receivePacket.getAddress()
						+ " : " + sentence + "] ");
				//Read Text
				decrypt(sentence, receivePacket.getAddress());
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void decrypt(String message, InetAddress sender) throws UnknownHostException{
		String[] tokens = message.split(" ");
		for(int i = 0; i < tokens.length; i++){
			tokens[i] = tokens[i].trim();
		}

		switch(tokens[0]){
			case "1" : // Parking Command
				// Check if Request Slot is match with this beacon
				if((Integer.parseInt(tokens[2]) == thisBeacon.getSlotNo()) && !thisBeacon.isHasCarPark()){
					thisBeacon.setHasCarPark(true);
					thisBeacon.setOwnBy(tokens[1]);
					thisBeacon.setMapAt(thisBeacon.getSlotNo(), true);
					thisBeacon.setParkingStartTime();
					//UPDATE HERE
					System.out.println("park at slot "+tokens[2]+"Successful");
					SenderBeacon ss1 = new SenderBeacon();
					ss1.broadcastOccupancy(thisBeacon.getSlotNo(), true);
				}
			break;
			case "2" : // Bye Command
				// Check if Request Slot is match with this beacon
				if(tokens[2].equals(""+thisBeacon.getSlotNo())){
					
					// remove a car from this slot
					thisBeacon.setHasCarPark(false);
					thisBeacon.setOwnBy("");
					thisBeacon.setMapAt(thisBeacon.getSlotNo(), false);
					
					// return time used in this slot
					SenderBeacon s1 = new SenderBeacon(sender);
					int usedTime = thisBeacon.getParkingTime();
					s1.packTime(usedTime);
					s1.start();
					//UPDATE HERE
					System.out.println("bye at slot "+tokens[2]+"Successful");
					SenderBeacon ss1 = new SenderBeacon();
					ss1.broadcastOccupancy(thisBeacon.getSlotNo(), false);
				}
			break;
			case "4" : // Request Map Command
				boolean map[] = thisBeacon.getMap();
				SenderBeacon s1 = new SenderBeacon(sender);
				s1.packMap(map);
				s1.start();
			break;
			
			case "6" :// Receive Occupancy Update 
				//TOKENS[1] = SlotNumber 	//TOKENS[2] = 1=occupied 0=not occupied
				if(!(tokens[1].equals(""+thisBeacon.getSlotNo()))){ // Valid if not its own broadcasting
					if(tokens[2].equals("1")){ //IF Reported Slot 1 is Occupied
						thisBeacon.setMapAt(Integer.parseInt(tokens[1]), true);
					}else{
						thisBeacon.setMapAt(Integer.parseInt(tokens[1]), false);
					}
				}else{
					System.out.println("received my own update from "+thisBeacon.getSlotNo());
				}
			break; 
		}	
	}
}
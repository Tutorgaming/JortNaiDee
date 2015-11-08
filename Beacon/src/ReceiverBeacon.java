import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

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
				//System.out.print("[state : received from : " + receivePacket.getAddress()
				//		+ " : " + sentence + "] ");
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
	
	void decrypt(String message, InetAddress sender){
		String[] tokens = message.split(" ");
		for(int i = 0; i < tokens.length; i++){
			tokens[i] = tokens[i].trim();
		}
		System.out.print(tokens[1]+" is request to ");

		switch(tokens[0].trim()){
			case "1" : // Parking Command
				// Check if Request Slot is match with this beacon
				System.out.println("edok "+tokens[2]);
				System.out.println(tokens[2].equals("1"));
				if((Integer.parseInt(tokens[2]) == thisBeacon.getSlotNo()) && !thisBeacon.isHasCarPark()){
					thisBeacon.setHasCarPark(true);
					thisBeacon.setOwnBy(tokens[1]);
					thisBeacon.setMapAt(thisBeacon.getSlotNo(), true);
					thisBeacon.setParkingStartTime();
					//UPDATE HERE
					System.out.println("park at slot "+tokens[2]+"Successful");
				}
			break;
			case "2" : // Bye Command
				// Check if Request Slot is match with this beacon
				System.out.println("bye at slot "+tokens[2]);
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
				}
			break;
			case "4" : // Request Map Command
				boolean map[] = thisBeacon.getMap();
				SenderBeacon s1 = new SenderBeacon(sender);
				s1.packMap(map);
				s1.start();
			break;
		}
		
	}

}

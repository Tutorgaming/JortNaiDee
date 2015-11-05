import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ReceiverCar extends Thread {

	private CarObject thisCarObject;

	public ReceiverCar(){
		
	}
	
	public ReceiverCar(CarObject thisCarObject){
		this.thisCarObject = thisCarObject;
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
				System.out.println("[state : received from : " + receivePacket.getAddress()
						+ " : " + sentence + "]");
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
		switch(tokens[0]){
			case "3": //Received Start parking timeStamp From Beacon
				thisCarObject.addBalance(Integer.parseInt(tokens[1]));
				break;
			case "5": //Received Map From Beacon
				boolean map[] = new boolean[tokens[1].length()];
				for(int i = 0; i < tokens[1].length(); i++){
					if(tokens[1].charAt(i) == '1'){
						map[i] = true;
					} else{
						map[i] = false;
					}
				}
				thisCarObject.setMap(map);
				
				break;
		}
		
	}

}

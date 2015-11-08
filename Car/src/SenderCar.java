import java.io.IOException;
import java.net.*;

public class SenderCar extends Thread {

	private String messageToSend;
	private InetAddress clientAddress;
	private CarObject thisCarObject;

	public SenderCar() throws UnknownHostException {
		this.setBroadcastAddress();
		this.messageToSend = "";
	}
	
	public SenderCar(CarObject thisCarObject) {
		this.setBroadcastAddress();
		this.thisCarObject = thisCarObject;
	}

	public SenderCar(InetAddress clientAddress) {
		this.clientAddress = clientAddress;
		this.messageToSend = "";
	}

	@Override
	public void run() {
		DatagramSocket clientSocket;
		try {
			clientSocket = new DatagramSocket();
			byte[] sendData = new byte[1024];
			sendData = this.messageToSend.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, this.clientAddress, 9900);
			clientSocket.send(sendPacket);
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendParkRequest(int slotNo){
		this.messageToSend = "1 " + thisCarObject.getCarPlate() + " " + slotNo;
	}
	
	public void sendByeRequest(){
		this.messageToSend = "2 " + thisCarObject.getCarPlate() + " " + thisCarObject.getCurrentParkingSlot();
	}
	
	public void sendMapRequest(){
		this.messageToSend = "5";
	}
	
	public void setBroadcastAddress(){
		try {
			this.clientAddress = InetAddress.getByName("192.168.0.255");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

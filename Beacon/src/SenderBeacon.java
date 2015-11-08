import java.io.IOException;
import java.net.*;

public class SenderBeacon extends Thread {

	private String messageToSend;
	private InetAddress clientAddress;

	public SenderBeacon() throws UnknownHostException {
		this.clientAddress = InetAddress.getByName("192.168.0.255");
		this.messageToSend = "";
	}

	public SenderBeacon(InetAddress clientAddress) {
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

	public void packMap(boolean[] map) {
		this.messageToSend = "5 ";
		for (int i = 0; i < map.length; i++) {
			if (map[i] == true) {
				this.messageToSend += "1";
			} else {
				this.messageToSend += "0";
			}
		}
	}

	public void packTime(int usedTime) {
		this.messageToSend = "3 " + usedTime;
	}
	
	public void broadcastOccupancy(int mySlotNumber , boolean isOccupied){
		this.messageToSend = "6 "+ mySlotNumber +" "+((isOccupied)? "1":"0") ;
		
	}
}

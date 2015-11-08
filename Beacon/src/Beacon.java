
public class Beacon {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Beacon Module Initialized~~! ");
		BeaconObject b1 = new BeaconObject(2);
		ReceiverBeacon r1 = new ReceiverBeacon(b1);
		r1.start();
	}
}

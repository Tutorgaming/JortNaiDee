import java.util.Scanner;


public class Car {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Car Module Initialized !! ");
		CarObject c1 = new CarObject("ABABC");
		CarGUI gui = new CarGUI(c1);
		ReceiverCar rc1 = new ReceiverCar(c1, gui);
		rc1.start();
	}

}

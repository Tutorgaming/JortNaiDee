import java.util.Scanner;


public class Car {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Car Module Initialized !! ");
		CarObject c1 = new CarObject("กอ159");
		ReceiverCar rc1 = new ReceiverCar(c1);
		rc1.start();
		
		Scanner kb = new Scanner(System.in);
		while(true){
			SenderCar sc1 = new SenderCar(c1);
			int input = kb.nextInt();
			if(input == 1){
				sc1.sendParkRequest(2);
			}else if(input ==2){
				sc1.sendByeRequest();
			}else if(input == 3){
				sc1.sendMapRequest();
			}
			sc1.start();
		}
	}

}

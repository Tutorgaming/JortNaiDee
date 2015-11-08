
public class CarObject {
	
	private String carPlate;
	private boolean[] map;
	private int balance;
	private int currentParkingSlot;
	
	public CarObject(){
		this.setCarPlate("");
		this.setBalance(0);
		this.setCurrentParkingSlot(0);
	}
	
	public CarObject(String carPlate){
		this.setCarPlate(carPlate);
		this.setBalance(0);
		this.setCurrentParkingSlot(0);
	}
	
	public void clearBalance(){
		this.balance = 0;  
	}
	
	public void addBalance(int parkedTime){
		this.balance += parkedTime;
	}

	public String getCarPlate() {
		return carPlate;
	}

	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}

	public boolean[] getMap() {
		return map;
	}

	public void setMap(boolean[] map) {
		this.map = map;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getCurrentParkingSlot() {
		return currentParkingSlot;
	}

	public void setCurrentParkingSlot(int currentParkingSlot) {
		this.currentParkingSlot = currentParkingSlot;
	}
	
	public boolean isParking(){
		return this.getCurrentParkingSlot() != 0;
	}
	
}

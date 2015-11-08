import java.util.Date;


public class BeaconObject {
	private boolean hasCarPark;
	private String ownBy;
	private int slotNo;
	private boolean[] map; //True if Occupied
	private Date parkingStartTime;
	
	// constructor
	public BeaconObject(){
		this.setHasCarPark(false);
		this.setOwnBy("");
		this.setSlotNo(0);
		this.setMap(new boolean[10]);
	}
	
	public BeaconObject(int slotNo){
		this.setSlotNo(slotNo);
		this.setOwnBy("");
		this.setHasCarPark(false);
		this.setMap(new boolean[10]);
	}

	public boolean isHasCarPark() {
		return hasCarPark;
	}

	public void setHasCarPark(boolean hasCarPark) {
		this.hasCarPark = hasCarPark;
	}

	public int getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}

	public String getOwnBy() {
		return ownBy;
	}

	public void setOwnBy(String ownBy) {
		this.ownBy = ownBy;
	}

	public boolean[] getMap() {
		return map;
	}
	
	public boolean getMapAt(int location) {
		return map[location];
	}


	public void setMap(boolean[] map) {
		this.map = map;
	}
	
	public void setMapAt(int location,boolean input){
		this.map[location] = input;
	}
	
	public void setParkingStartTime(){
		this.parkingStartTime = new Date();
	}
	
	public int getParkingTime(){
		long msDiff = (new Date()).getTime() - this.parkingStartTime.getTime();
		return (int)(msDiff / 1000);
	}
	
}

package models;

public class Space {
	private boolean available;
	
	public Space() {
		setAvailable(false);
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
}

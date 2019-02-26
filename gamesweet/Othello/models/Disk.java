package gamesweet.Othello.models;

public class Disk {
	private String[] colors = {"black", "white"};
	private String sideColor;
	private boolean isSandwiched;
	
	Disk(){
		
	}
	
	
	

	public String getSideColor() {
		return sideColor;
	}

	public void setSideColor(String sideColor) {
		this.sideColor = sideColor;
	}

	public boolean isSandwiched() {
		return isSandwiched;
	}

	public void setSandwiched(boolean isSandwiched) {
		this.isSandwiched = isSandwiched;
	}

	public String[] getColors() {
		return colors;
	}
	

}

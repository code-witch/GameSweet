package gamesweet.Othello.models;

public class Disk {
	private String[] colors = {"black", "white"};
	private String sideColor = "";

	public Disk() {
	}	

	public String getSideColor() {
		return sideColor;
	}

	public void setSideColor(String sideColor) {
		this.sideColor = sideColor;
	}

	public String[] getColors() {
		return colors;
	}
	


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Disk ").append(", sideColor=").append(sideColor);
		return builder.toString();
	}

}

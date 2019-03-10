package gamesweet.Othello.models;

public class Disk {
	private String[] colors = {"black", "white"};
	private String sideColor;
	private boolean isSandwiched;

	public Disk() {
	}


	public void flipDisk() {
		if(isSandwiched()) {
			if(sideColor.equals(colors[1])) {
				setSideColor(colors[0]);
			}else {
				setSideColor(colors[1]);
			}
		}
		
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
		flipDisk();
	}

	public String[] getColors() {
		return colors;
	}
	
	public String getColor(int index) {
		return colors[index];
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Disk ").append(", sideColor=").append(sideColor);
		return builder.toString();
	}

}

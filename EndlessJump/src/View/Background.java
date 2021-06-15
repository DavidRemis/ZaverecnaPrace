package View;

import javafx.scene.image.ImageView;

public class Background {
	private int index;
	private ImageView bg = new ImageView("View/Images/background.png");

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public ImageView getBg() {
		return bg;
	}
	public void setBg(ImageView bg) {
		this.bg = bg;
	}


}

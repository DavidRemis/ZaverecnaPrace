package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class View {

	private  int counter = 0;
	private  int counter10 = 0;

	private  final ImageView[] images = {
		new ImageView("View/Images/01.png"),
		new ImageView("View/Images/02.png"),
		new ImageView("View/Images/03.png"),
		new ImageView("View/Images/04.png")
	};

	public  ImageView changeImg(int speed){
		switch (speed) {
		case 1:
			counter++;
			break;
		case 2:
			counter += 2;
			break;
		case 3:
			counter += 4;
			break;

		}

		if (counter >= 10) {
			counter = 0;
			counter10++;
		}

		if (counter10 > images.length - 1) {
			counter10 = 0;
		}

		return images[counter10];
	}

}

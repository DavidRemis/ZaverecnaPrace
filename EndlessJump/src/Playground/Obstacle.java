package Playground;



import Global.Settings;
import Player.Player;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Obstacle {

	private double posX;
	private double posY;
	private double height;
	private double width;
	ImageView rc;
	private int index;
	public Sides lastSide;
	boolean passed = false;

	private double positionYTop;
	private double positionYBottom;
	private double positionXLeft;
	private double positionXRight;

	private double playerPositionYTop;
	private double playerPositionYBottom;

	public Obstacle(double posX, double posY) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.lastSide = Sides.LEFT;

		this.height = Settings.OBSTACLE_HEIGHT;
		this.width = Settings.OBSTACLE_WIDTH;
		rc = new ImageView("View/Images/obstacle.png");
		this.positionYTop = posY + height;
		this.positionYBottom = posY;



	}

	public void checkColision(Player p){




		playerPositionYTop = p.getPosY() + Settings.FIX_PLAYER_Y + Settings.IMAGE_HEIGHT;
		playerPositionYBottom = p.getPosY() + Settings.FIX_PLAYER_Y;
		positionXLeft = posX;
		positionXRight = posX + width;
		positionYTop = posY + height;
		positionYBottom = posY;



		if (matchYPos() && matchXPos()) {
			switch (lastSide) {
			case TOP:
				p.setPosY(positionYTop);
				p.setInAir(false);
				p.setVelocityY(0);

				break;

			/*case BOTTOM:
				p.setPosY(positionYBottom - Settings.IMAGE_HEIGHT + Settings.FIX_PLAYER_Y);
				p.setVelocityY(0);
				break;*/

			case LEFT:

				Settings.RUNNING = false;

				break;
			}

		}else if (!matchYPos() && !matchXPos() && lastSide == Sides.TOP) {
			if (inRange()) {
				p.setInAir(true);
			}


		}




		lastSide(p);

	}


	private void lastSide(Player p){
		if (!matchXPos() && matchYPos()) {
			lastSide = Sides.LEFT;
		}else if(matchXPos() && !matchYPos()){
			//if (p.getPosY() > positionYTop  - Settings.FIX_PLAYER_Y) {
				lastSide = Sides.TOP;
			/*}else {
				lastSide = Sides.BOTTOM;
			}*/
		}



	}

	public boolean matchXPos(){
		if ((Settings.PLAYER_PADDING_LEFT > positionXLeft && Settings.PLAYER_PADDING_LEFT < positionXRight) || (Settings.PLAYER_PADDING_LEFT + Settings.IMAGE_WIDTH -10 > positionXLeft && Settings.PLAYER_PADDING_LEFT + Settings.IMAGE_WIDTH - 10 < positionXRight)) {
			return true;
		}

		return false;
	}

	public boolean matchYPos(){
		if ((playerPositionYTop - Settings.FIX_PLAYER_Y < positionYTop && playerPositionYTop - Settings.FIX_PLAYER_Y  > positionYBottom) || (playerPositionYBottom < positionYTop && playerPositionYBottom > positionYBottom)) {
			return true;
		}

		return false;
	}

	public void move(double speed, StackPane sp){
			posX -= speed;
			sp.getChildren().get(index).setTranslateX(posX - Settings.WINDOW_WIDTH/2 + width/2);
	}

	public boolean inRange(){
		if (posX < Settings.PLAYER_PADDING_LEFT + Settings.IMAGE_WIDTH) {
			return true;
		}
		return false;
	}


	public String toString(){
		return "X: " + posX + ", Y: " + posY + ", YTop: "  + positionYTop + ", YBot: " + positionYBottom;
	}

	public ImageView getRc() {
		return rc;
	}

	public void setRc(ImageView rc) {
		this.rc = rc;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}

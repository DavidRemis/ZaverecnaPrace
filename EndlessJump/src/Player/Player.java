package Player;

import Controller.Controller;
import Controller.Keyboard;
import Global.Optimalizer;
import Global.Settings;
import View.View;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Player {
	private double posY = 100;
	private double velocityY = 0;
	private double velocityX = 0;
	private int index;
	public boolean isInAir = false;
	private View vi = new View();
	private Controller con;
	private ImageView img = new ImageView("View/Images/01.png");
	private StackPane sp;




	public Player(Keyboard kb, StackPane sp, Optimalizer op){
		con = new Controller(this, kb);
		this.sp = sp;

		index = op.getIndex();
		sp.getChildren().add(img);
	}

	public void update(){
		con.updateMovement();
		if (!isInAir) {
			if (velocityX == Settings.GAME_SPEED) {
				img = vi.changeImg(2);
			}else if(velocityX == Settings.GAME_SPEED*2){
				img = vi.changeImg(3);
			}else if(velocityX == Settings.GAME_SPEED/2){
				img = vi.changeImg(1);
			}
		}

		positions();


		sp.getChildren().set(index, getImg());
		sp.getChildren().get(index).setTranslateY(-posY - Settings.FIX_PLAYER_Y);
		sp.getChildren().get(index).setTranslateX(-Settings.FIX_PLAYER_X + Settings.PLAYER_PADDING_LEFT);
	}


	private void positions(){
		posY += velocityY;

	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double vbelocityY) {
		this.velocityY = vbelocityY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public boolean isInAir() {
		return isInAir;
	}

	public void setInAir(boolean isInAir) {
		this.isInAir = isInAir;
	}

	public ImageView getImg() {
		return img;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Controller getCon() {
		return con;
	}







}

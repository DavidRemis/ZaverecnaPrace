package Playground;

import java.util.ArrayList;
import java.util.Random;

import Global.Optimalizer;
import Global.Settings;
import Player.Player;
import javafx.scene.layout.StackPane;

public class Obstacles {

	StackPane sp;
	Optimalizer op;
	Player p;
	Random rd = new Random();
	public ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	public int useableObstacle;
	public int matchinObs = 0;
	Obstacle toReArange;
	Obstacle lastObstacle;

	public Obstacles(StackPane sp, Optimalizer op, Player p){
		this.sp = sp;
		this.op = op;
		this.p = p;
		firstStart();

	}

	public void update(){
		reArange(Settings.GAP);
		updateView();
		move(p.getVelocityX());
		checkColisions();

	}

	private Obstacle checkForUseable(){
		for (int i = 0; i < obstacles.size(); i++) {
			if (obstacles.get(i).getPosX() + 200 <= 0) {
				return obstacles.get(i);
			}
		}
		return null;

	}

	private void reArange(double gapX){
		if (checkForUseable() != null) {
			toReArange = checkForUseable();
			lastObstacle = lastObstacle();
			toReArange.setPosX(lastObstacle.getPosX() + Settings.OBSTACLE_WIDTH + gapX);
			toReArange.setPosY((rd.nextInt(100*Settings.DIFFICULTY/4) + 100*Settings.DIFFICULTY/4.0) - Settings.WINDOW_HEIGHT/2 + Settings.OBSTACLE_HEIGHT);
		}
	}

	private void updateView(){
		sp.getChildren().get(toReArange.getIndex()).setTranslateY(-toReArange.getPosY());
		sp.getChildren().get(toReArange.getIndex()).setTranslateX(toReArange.getPosX());
	}

	private Obstacle lastObstacle(){
		Obstacle last = obstacles.get(0);

		for (int i = 0; i < obstacles.size(); i++) {
			if (last.getPosX() < obstacles.get(i).getPosX()) {
				last = obstacles.get(i);
			}
		}
		return last;
	}


	private void firstStart(){
		obstacles.add(new Obstacle(300, 0));
		obstacles.get(0).setIndex(op.getIndex());
		for (int i = 1; i < 6; i++) {
			obstacles.add(new Obstacle(-400, 0));
			obstacles.get(i).setIndex(op.getIndex());
			reArange(200);
		}


		for (int i = 0; i < obstacles.size(); i++) {
			sp.getChildren().add(obstacles.get(i).getRc());
			sp.getChildren().get(obstacles.get(i).getIndex()).setTranslateY(-obstacles.get(i).getPosY());

		}
	}

	public void reset(){
		obstacles.set(0, replace(obstacles.get(0), 300));
		for (int i = 1; i < obstacles.size(); i++) {
			obstacles.set(i, replace(obstacles.get(i), -400));

		}

		for (int i = 1; i < obstacles.size(); i++) {
			reArange(200);
		}

		for (int i = 0; i < obstacles.size(); i++) {
			sp.getChildren().get(obstacles.get(i).getIndex()).setTranslateY(-obstacles.get(i).getPosY());

		}

	}

	public void move(double speed){
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).setPosX(obstacles.get(i).getPosX() - speed);
			sp.getChildren().get(obstacles.get(i).getIndex()).setTranslateX(obstacles.get(i).getPosX() - Settings.WINDOW_WIDTH/2 + obstacles.get(i).getWidth()/2);
		}


	}

	private Obstacle replace(Obstacle ob, int posX){
		Obstacle toReturn = new Obstacle(posX,0);
		toReturn.setIndex(ob.getIndex());
		return toReturn;
	}

	public void checkColisions(){
			for (int i = 0; i < obstacles.size(); i++) {

					obstacles.get(i).checkColision(p);


			}
	}


}

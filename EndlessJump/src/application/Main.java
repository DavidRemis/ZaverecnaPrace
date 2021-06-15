package application;

import View.BackgroundView;
import Controller.Keyboard;
import Global.Optimalizer;
import Global.Settings;
import Player.Player;
import Playground.Obstacle;
import Playground.Obstacles;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
	    Scene scene = new Scene(root, 1600, 800);
	    primaryStage.setTitle("Panda jump");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    primaryStage.setFullScreen(true);
	    Settings.WINDOW_HEIGHT = root.getHeight();
	    Settings.WINDOW_WIDTH = root.getWidth();
	    Settings.GROUND_HEIGHT = Settings.WINDOW_HEIGHT/2;
	    Settings.FIX_PLAYER_X = Settings.WINDOW_WIDTH/2 - Settings.IMAGE_WIDTH/2;

	    Optimalizer op = new Optimalizer(root);
	    BackgroundView bw = new BackgroundView(op, root);

	    Keyboard kb = new Keyboard(scene);
	    Player p = new Player(kb, root, op);

	    Obstacle ob = new Obstacle(0, p.getPosY() - Settings.OBSTACLE_HEIGHT);
	    ob.setIndex(op.getIndex());
	    root.getChildren().add(ob.getRc());
	    Obstacles os = new Obstacles(root, op, p);


	    Text t = new Text();
	    t.setFont(new Font(48));
	    t.setText("Press R to reset");
	    t.setVisible(false);
	    root.getChildren().add(t);

	    HBox hb = new HBox();
	    Text score = new Text();
	    score.setFont(new Font(48));
	    score.setText("Score: ");
	    Text hscore = new Text();
	    hscore.setFont(new Font(48));
	    hscore.setText("Highscore: ");

	    hb.getChildren().add(score);
	    hb.getChildren().add(hscore);



	    root.getChildren().add(hb);
	    root.getChildren().get(root.getChildren().size() -1).setTranslateX(10);
	    root.getChildren().get(root.getChildren().size() -1).setTranslateY(10);







	    startTimer(16, new Runnable() {
            @Override
            public void run() {
            	if (Settings.RUNNING) {
            		ob.checkColision(p);
            		ob.move(p.getVelocityX(), root);
            		bw.move(p.getVelocityX());
            		os.update();
	            	p.update();
	            	Settings.SCORE += p.getVelocityX()/2;
	            	score.setText("Score: " + Settings.SCORE + " ");
	            	hscore.setText("Highscore: " + Settings.HIGHSCORE + " ");

	            	if (Settings.SCORE >= 1000*Settings.DIFFICULTY && Settings.SCORE < 20000) {
	            		if (Settings.DIFFICULTY < 10) {
	            			Settings.DIFFICULTY++;
						}

	            		if (Settings.GAP < 400) {
							Settings.GAP += 50;
						}
	            		if (Settings.GAME_SPEED <  10) {
							Settings.GAME_SPEED += 0.5;
						}

					}
	            	if (Settings.SCORE > Settings.HIGHSCORE) {
	            		Settings.HIGHSCORE = Settings.SCORE;
					}



				}else if (kb.isR()) {
					Settings.RUNNING = true;
					t.setVisible(false);
					ob.setPosX(Settings.PLAYER_PADDING_LEFT);
					ob.move(p.getVelocityX(), root);
					Settings.GAP = 200;
					Settings.GAME_SPEED = 4;
					Settings.DIFFICULTY = 1;

					p.setPosY(110);
					p.setVelocityY(0);
					os.reset();
					Settings.SCORE = 0;

				}else if (!Settings.RUNNING) {
					t.setVisible(true);
				}



            }
        });









	}

	public static void main(String[] args) {
		launch(args);
	}

	private void startTimer(final int interval, final Runnable action) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException ex) {
                    }
                    Platform.runLater(action);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}

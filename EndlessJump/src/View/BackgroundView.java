package View;

import java.util.ArrayList;

import Global.Optimalizer;
import Global.Settings;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BackgroundView {
	ArrayList<Background> bgs = new ArrayList<Background>();
	private StackPane sp;
	private Optimalizer op;

    public BackgroundView(Optimalizer op , StackPane sp){
    	this.sp = sp;
    	this.op = op;
    	bgs.add(new Background());
    	bgs.add(new Background());
    	show();
    }

    public void duplicate(){
    	for (int i = 0; i < 2; i++) {
			if (checkRange(i)) {
					sp.getChildren().get(bgs.get(0).getIndex()).setTranslateX(0);
					sp.getChildren().get(bgs.get(1).getIndex()).setTranslateX(Settings.WINDOW_WIDTH);

			}
		}

    }

    private boolean checkRange(int i){
    	if (sp.getChildren().get(bgs.get(i).getIndex()).getTranslateX() + Settings.WINDOW_WIDTH < 0) {

			return true;
		}
    	return false;
    }

    private void show(){
    	for (int j = 0; j < bgs.size(); j++) {
			sp.getChildren().add(bgs.get(j).getBg());
			bgs.get(j).setIndex(op.getIndex());
		}
    	sp.getChildren().get(bgs.get(1).getIndex()).setTranslateX(Settings.WINDOW_WIDTH);

    }

    public void move(double speed){
    	speed /= 20;
    	for (int j = 0; j < bgs.size(); j++) {
			sp.getChildren().get(j).setTranslateX(sp.getChildren().get(j).getTranslateX() - speed);

		}
    	duplicate();

    }




}

package Global;

import javafx.scene.layout.StackPane;

public class Optimalizer {

	private int last_index;
	private StackPane root;

	public Optimalizer(StackPane sp){
		this.root = sp;
		this.last_index = -1;

	}

	public int getIndex(){
		last_index++;
		return last_index;
	}


}

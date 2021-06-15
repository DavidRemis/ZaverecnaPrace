package Controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {
	private Scene scene;
	private boolean A = false;
	private boolean SPACE = false;
	private boolean SHIFT = false;
	private boolean R = false;

	public Keyboard(Scene scene){
		this.scene = scene;
	}



	public void checkKeys(){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                updateState(true, event.getCode());
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                updateState(false, event.getCode());
            }
        });


	}

	private void updateState(boolean pressed, KeyCode keyCode) {
        switch (keyCode) {
            case A:
            	A = pressed;
            	break;

            case SPACE:
            	SPACE = pressed;
            	break;

            case SHIFT:
            	SHIFT = pressed;
            	break;
            case R:
            	R = pressed;
            	break;
        }
    }

	public boolean isA() {
		return A;
	}

	public boolean isR() {
		return R;
	}

	public boolean isSPACE() {
		return SPACE;
	}

	public boolean isSHIFT() {
		return SHIFT;
	}

}

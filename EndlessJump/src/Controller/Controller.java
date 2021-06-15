package Controller;

import Global.Settings;
import Player.Player;

public class Controller {
	Player p;
	Keyboard kb;

	public Controller(Player p, Keyboard kb){
		this.p = p;
		this.kb = kb;
	}

	public void updateMovement(){
		kb.checkKeys();
		if (!p.isInAir()) {
			if(kb.isSPACE()){
				jump();
				p.setInAir(true);
			}
		}else {
			fall();
		}


		if (kb.isA()) {
			slow();
		}else if (kb.isSHIFT()) {
			run();
		}else {
			normal();
		}


	}

	private void jump(){
		p.setVelocityY(Settings.JUMP_POWER);
	}

	private void slow(){
		p.setVelocityX(Settings.GAME_SPEED/2);
	}

	private void run(){
		p.setVelocityX(Settings.GAME_SPEED*2);
	}

	private void normal(){
		p.setVelocityX(Settings.GAME_SPEED);
	}

	private void fall(){
		if (p.getPosY() < -Settings.GROUND_HEIGHT) {
			Settings.RUNNING = false;
			/*p.setPosY(-Settings.GROUND_HEIGHT);
			p.setVelocityY(0);
			p.setInAir(false);*/
		}else {
			p.setVelocityY(p.getVelocityY() - Settings.GRAVITATION);
		}


	}
}

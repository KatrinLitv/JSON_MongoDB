package tel_ran.sportsman.implementation;

import tel_ran.sportsman.interfaces.ISportsman;

public class Runner implements ISportsman {
int speed=20;
	@Override
	public void action() {
		System.out.println("I'm running with speed "+speed+" km/h");
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Runner(int speed) {
		super();
		this.speed = speed;
	}
	public Runner(){};
}

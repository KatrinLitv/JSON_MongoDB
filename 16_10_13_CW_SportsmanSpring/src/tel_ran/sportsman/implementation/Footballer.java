package tel_ran.sportsman.implementation;

import tel_ran.sportsman.interfaces.ISportsman;

public class Footballer implements ISportsman {
String team = "SPARTAK";

	@Override
	public void action() {
		System.out.println("I'm playing football in "+team);
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Footballer(String team) {
		super();
		this.team = team;
	}
	public Footballer(){};
}

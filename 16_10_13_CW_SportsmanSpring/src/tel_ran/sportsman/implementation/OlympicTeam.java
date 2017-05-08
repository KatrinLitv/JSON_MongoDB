package tel_ran.sportsman.implementation;

import tel_ran.sportsman.interfaces.ISportsman;

public class OlympicTeam {
ISportsman [] team;

public OlympicTeam(ISportsman[] team) {
	super();
	this.team = team;
}

public void displayActions(){
	for (ISportsman sportsman : team){
		sportsman.action();
	}
}
}

package tel_ran.sportsman.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.sportsman.implementation.OlympicTeam;
import tel_ran.sportsman.interfaces.ISportsman;

public class SportsmanTestAppl {

	public static void main(String[] args) throws Exception {
			
		//неудобно конфигурировать свойства, т.к. надо добавлять условия
		//ISportsman sportsman = (ISportsman) Class.forName(args[0]).newInstance();
		
		//БИН автоматом вызывает конструктор и сеттер при необходимости
		
		//конструктор класса spring строит инф.контекст, 
		//который сост. из всех бинов, описанных в "beans.xml"	
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
		//ISportsman sportsman = (ISportsman) ctx.getBean("sportsman");
		//sportsman.action();
		OlympicTeam team = (OlympicTeam) ctx.getBean("team");
		team.displayActions();
		ctx.close();
	}

}

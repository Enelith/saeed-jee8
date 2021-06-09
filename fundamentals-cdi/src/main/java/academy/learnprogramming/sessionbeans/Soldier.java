package academy.learnprogramming.sessionbeans;

import java.text.MessageFormat;

import javax.ejb.Stateless;

import academy.learnprogramming.annotations.ServiceMan;
import academy.learnprogramming.interfaces.Salute;

@Stateless
@ServiceMan(value = ServiceMan.ServiceType.SOLDIER)
@academy.learnprogramming.annotations.Soldier
public class Soldier implements Salute {

    public String salute(String name) {
	return MessageFormat.format("Aye Aye Capt {0}", name);
    }
}

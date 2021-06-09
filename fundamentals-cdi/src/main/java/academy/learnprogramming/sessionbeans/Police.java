package academy.learnprogramming.sessionbeans;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.ejb.Stateful;

import academy.learnprogramming.annotations.ServiceMan;
import academy.learnprogramming.interfaces.Salute;

@Stateful
@ServiceMan(value = ServiceMan.ServiceType.POLICE)
@academy.learnprogramming.annotations.Police
public class Police implements Salute, Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String salute(String name) {
	return MessageFormat.format("Yes sir! {0}", name);
    }
}

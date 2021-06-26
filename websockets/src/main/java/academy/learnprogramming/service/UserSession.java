package academy.learnprogramming.service;

import java.io.Serializable;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;

@Stateful
public class UserSession implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String getCurrentUserName() {
	return "";
    }

    @PrePassivate
    private void passivate() {

    }

    @PostActivate
    private void active() {

    }
}

package academy.learnprogramming.scopes;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class SessionScope implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String getHashCode() {
	return this.hashCode() + " ";
    }
}

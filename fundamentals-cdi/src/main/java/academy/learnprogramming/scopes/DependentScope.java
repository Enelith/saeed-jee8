package academy.learnprogramming.scopes;

import java.io.Serializable;

public class DependentScope implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String getHashCode() {
	return this.hashCode() + " ";
    }
}

package academy.learnprogramming.sessionbeans;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import academy.learnprogramming.annotations.Logged;

@Stateless
public class AuditedService {

    @Inject
    private Logger logger;

    @PostConstruct
    private void init() {

    }

    // This method will only be called after the Logged Interceptor has returned ie
    // InvocationContext#proceed
    // This annotation could also be put on the class, making every method of the
    // class intercepted
    @Logged
    public void auditedMethod() {
	logger.log(Level.INFO, "OK so we are able to call this method after auditing took place");
    }
}

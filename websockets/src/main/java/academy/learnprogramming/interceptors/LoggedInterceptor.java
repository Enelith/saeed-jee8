/**
 * 
 */
package academy.learnprogramming.interceptors;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import academy.learnprogramming.annotations.Logged;

/**
 * @author Jonathan Vinh
 */
@Logged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LoggedInterceptor {
    @Inject
    private Logger logger;

    // This method will be called and passed invocation context by container
    @AroundInvoke
    public Object logMethodCall(InvocationContext context) throws Exception {
	// Log for example user who called method and time
	logger.log(Level.INFO,
		    "Method {0} invoked at {1}",
		    new Object[] {
			    context.getMethod().getName(),
			    LocalDate.now() });
	return context.proceed();
    }
}

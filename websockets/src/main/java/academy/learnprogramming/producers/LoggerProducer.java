/**
 * 
 */
package academy.learnprogramming.producers;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * @author Jonathan Vinh
 */
public class LoggerProducer {

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
	System.out.println("A : " + injectionPoint.getClass().getCanonicalName());
	System.out.println("B : " + injectionPoint.getMember().getDeclaringClass().getName());
	return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
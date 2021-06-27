package academy.learnprogramming.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import academy.learnprogramming.annotations.Authz;

/**
 * @author Jonathan Vinh
 *
 */
@Authz // SecurityFilter is binded to @Authz annotation
@Provider // SecurityFilter is now registered as a JAX-RS Component
@Priority(Priorities.AUTHENTICATION) // Highest priority : this filter will be run before the others filters
public class SecurityFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
	// TODO Auto-generated method stub
	
    }

}

package academy.learnprogramming.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
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
	// - Grab Token from the Header of the Request using the AUTHORIZATION constant
	// - Throw an Exception with a message if there's no Token
	// - Parse the Token
	// - If parsing succeed, proceed
	// - Otherwise, throw an Exception with message

	String authString = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
	if(authString == null || authString.isEmpty() || !authString.startsWith("Bearer")) {
	    // Beautify this Exception
	    throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
	}
    }
}

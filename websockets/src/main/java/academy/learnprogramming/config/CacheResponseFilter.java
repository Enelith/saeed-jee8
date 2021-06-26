package academy.learnprogramming.config;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;

// @Provider
public class CacheResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
		throws IOException {

	String method = requestContext.getMethod();
	String path = requestContext.getUriInfo().getPath();

	if (method.equalsIgnoreCase("GET")
		    && path.equalsIgnoreCase("departments")) {
	    CacheControl cacheControl = new CacheControl();
	    cacheControl.setMaxAge(100);
	    cacheControl.setPrivate(true);

	    responseContext.getHeaders().add("Cache-Control", cacheControl);
	    responseContext.getHeaders().add("MyMessage", "Amen! This works fine!!");
	}
    }
}

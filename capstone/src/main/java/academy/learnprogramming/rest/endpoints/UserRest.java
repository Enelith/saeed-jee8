package academy.learnprogramming.rest.endpoints;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Jonathan Vinh
 */
@Path("user")
public class UserRest {

    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@NotNull @FormParam("email") String email,
		@NotNull @FormParam("password") String password) {
	/*
	 * - Authenticate User
	 * - Generate Token
	 * - Return Token in Response Header to client
	 */
	
	return null;
    }
}

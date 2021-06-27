package academy.learnprogramming.rest;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import academy.learnprogramming.entity.User;
import academy.learnprogramming.security.SecurityUtil;
import academy.learnprogramming.service.TodoService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Jonathan Vinh
 */
@Path("user")
public class UserRest {

    @Inject
    private SecurityUtil securityUtil;

    @Inject
    private TodoService todoService; // <=> Persistence Service

    @Context
    private UriInfo uriInfo;

    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@NotNull @FormParam("email") String email,
		@NotNull @FormParam("password") String password) {
	/*
	 * - Authenticate User - Generate Token - Return Token in Response Header to
	 * client
	 */
	boolean isAuthenticated = securityUtil.authenticateUser(email, password);
	if (!isAuthenticated) {
	    throw new SecurityException("Email or password invalid");
	}

	String token = generateToken(email);

	return Response.ok()
		    .header(HttpHeaders.AUTHORIZATION, SecurityUtil.BEARER + " " + token)
		    .build();
    }

    private String generateToken(String email) {
	Key securityKey = securityUtil.getSecurityKey();

	return Jwts.builder()
		    .setSubject(email) // A METTRE EN RELATION AVEC SecurityFilter > filter > SecurityContext >
				       // Principal
		    .setIssuedAt(new Date())
		    .setIssuer(uriInfo.getBaseUri().toString())
		    .setAudience(uriInfo.getAbsolutePath().toString())
		    .setExpiration(securityUtil.toDate(LocalDateTime.now().plusMinutes(15)))
		    .signWith(SignatureAlgorithm.HS512, securityKey)
		    .compact();
    }

    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser(@NotNull User user) {
	todoService.saveUser(user);

	return Response.ok(user).build();
    }
}

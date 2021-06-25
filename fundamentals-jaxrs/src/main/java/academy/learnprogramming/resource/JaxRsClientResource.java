package academy.learnprogramming.resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("programmatic")
@Produces(MediaType.APPLICATION_JSON)
public class JaxRsClientResource {

    @Inject
    JaxRsClient jaxRsClient;

    @Path("breach/{email}")
    @GET
    public Response checkBreaches(@PathParam("email") @NotEmpty String email) {

	JsonArray breaches1 = jaxRsClient.getBreaches(email);

	List<JsonObject> jsonObjects = new ArrayList<>();

	@SuppressWarnings("unused")
	JsonArray jsonArray = Json.createArrayBuilder().build();

	if (breaches1.size() > 0) {
	    for (JsonValue jsonValue : breaches1) {
		JsonObject jsonObject = jsonValue.asJsonObject();

		jsonObjects.add(Json.createObjectBuilder()
			    .add("breach_domain", jsonObject.getString("Domain"))
			    .add("breach_date", jsonObject.getString("BreachDate"))
			    .build());

	    }

	    return Response.ok(jsonObjects).build();
	}
	return Response.ok("No breaches found for email " + email).build();
    }
}

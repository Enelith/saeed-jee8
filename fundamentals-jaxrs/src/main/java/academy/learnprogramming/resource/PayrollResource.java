package academy.learnprogramming.resource;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

import academy.learnprogramming.service.PayrollService;

@Path("payroll")
public class PayrollResource {

    @Resource
    ManagedExecutorService managedExecutorService;

    @Inject
    PayrollService payrollService;

    @POST
    @Path("run")
    public void run(@Suspended AsyncResponse asyncResponse) {
	final String currentThread = Thread.currentThread().getName();
	asyncResponse.setTimeout(5000, TimeUnit.MILLISECONDS);
	// 1. Implement timeout handler

	asyncResponse.setTimeoutHandler(asyncResponse1 -> {
	    asyncResponse1.resume(Response.status(Response.Status.REQUEST_TIMEOUT)
			.entity("Sorry, the request timed out. Please try again.")
			.build());
	});

	// 2. Register other callbacks
	asyncResponse.register(CompletionCallbackHandler.class);
	// 3. Pass long running task to MES and resume in there
	managedExecutorService.submit(() -> {
	    final String spawnedThreadName = Thread.currentThread().getName();
	    // Long running task
	    payrollService.computePayroll(); // Very expensive operation
	    asyncResponse.resume(Response.ok()
			.header("Original Thread", currentThread)
			.header("Spawned Thread", spawnedThreadName)
			.status(Response.Status.OK)
			.build());

	});

    }

    static class CompletionCallbackHandler implements CompletionCallback {

	@Override
	public void onComplete(Throwable throwable) {

	}
    }

    // Optional for implementations. Check with your provider!
    static class ConnectionCallbackHandler implements ConnectionCallback {

	@Override
	public void onDisconnect(AsyncResponse disconnected) {

	}
    }

    @POST
    @Path("run-cf")
    public void computePayrollCF(@Suspended AsyncResponse asyncResponse,
		@QueryParam("i") @DefaultValue("3") long number) {

	CompletableFuture.runAsync(() -> payrollService.fibonacci(number), managedExecutorService)

		    .thenApply((result) -> asyncResponse.resume(Response.ok(result).build()));
    }
}

package academy.learnprogramming.websocket.programmatic;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class ServerConfig implements ServerApplicationConfig {
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
	return new HashSet<ServerEndpointConfig>() {
	    /**
	     * 
	     */
	    private static final long serialVersionUID = 1L;

	    {
		add(ServerEndpointConfig.Builder
			    .create(MyProgrammaticEndPoint.class, "/pr")
			    .build());
	    }
	};
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> set) {
	return new HashSet<>(set);
    }
}

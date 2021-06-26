package academy.learnprogramming.websocket.annotation;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import academy.learnprogramming.annotations.Logged;

@Logged
@ServerEndpoint("/chat")
public class ChatEndPoint {

    private static final ConcurrentLinkedQueue<Session> peers = new ConcurrentLinkedQueue<>();

    @Inject
    private Logger logger;

    @OnOpen
    public void open(Session session) {
	logger.log(Level.INFO, "New session opened");
	peers.add(session);
    }

    @OnClose
    public void close(Session session, CloseReason closeReason) {
	logger.log(Level.INFO,
		    String.format("Session closed with reason %s",
				closeReason.getReasonPhrase()));
	peers.remove(session);
    }

    @OnMessage
    public void relayMessage(String message, Session session) throws IOException {
	for (Session peer : peers) {
	    if (!peer.equals(session)) {
		peer.getBasicRemote().sendText(message);
	    }
	}
    }
}
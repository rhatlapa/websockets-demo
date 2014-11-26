package org.websocket.demo;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author rhatlapa (rhatlapa@redhat.com)
 */
public class Demo {

    public static void main(String[] args) throws Exception {
        ChatClientEndpoint.latch = new CountDownLatch(1);
        Session session = connectToChatServerEndpoint(ChatClientEndpoint.class);
        Message message = new Message();
        message.setUsername("Joe");
        message.setMessage("Hello message");
        session.getBasicRemote().sendObject(message);
        ChatClientEndpoint.latch.await(5, TimeUnit.SECONDS);
        System.out.println("Message history = " + ChatClientEndpoint.messageHistory.toString());
    }

    public static Session connectToChatServerEndpoint(Class<?> endpoint) throws IOException, DeploymentException, URISyntaxException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        URI uri = new URI("ws://"
                + System.getProperty("host", "localhost")
                + ":"
                + "8080"
                + "/websocket-demo"
                + "/chat");
        return container.connectToServer(endpoint, uri);

    }
}

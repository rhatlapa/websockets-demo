package org.websocket.demo;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint(
        value = "/chat",
        encoders = {MessageEncoder.class},
        decoders = {MessageDecoder.class}
)
public class ChatEndpoint {
    @OnMessage
    public void message(Message message, Session client) throws IOException, EncodeException {
        System.out.println("message: " + message);
        for (Session peer : client.getOpenSessions()) {
            peer.getBasicRemote().sendObject(message);
        }
    }

    @OnClose
    public void onClose(Session client, CloseReason closeReason) {
        System.out.println("Closed session " + client.toString() + " with close reason: " + closeReason.toString());

    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error detected:");
        t.printStackTrace();
    }
}

package org.websocket.demo;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author rhatlapa (rhatlapa@redhat.com)
 */
@javax.websocket.ClientEndpoint(
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class
)
public class ChatClientEndpoint {
    public static List<Message> messageHistory = new ArrayList<Message>();
    public static CountDownLatch latch;

    @OnMessage
    public void onMessage(Message msg) {
        System.out.println("Message \""+ msg.getMessage() + "\" from " + msg.getUsername());
        messageHistory.add(msg);
        latch.countDown();
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connection opened");
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Session closed with " + closeReason.toString());
    }
}

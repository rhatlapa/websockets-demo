package org.websocket.demo;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @author rhatlapa (rhatlapa@redhat.com)
 */
public class MessageDecoder implements Decoder.Text<Message> {
    @Override
    public Message decode(String s) throws DecodeException {
        Gson gson = new Gson();
        return gson.fromJson(s, Message.class);
    }

    @Override
    public boolean willDecode(String s) {
        Gson gson = new Gson();
        try {
            Message message = gson.fromJson(s, Message.class);
            return true;
        } catch (JsonParseException ex) {
            return false;
        }
    }

    @Override
    public void init(EndpointConfig ec) {
        System.out.println("MessageDecoder -init method called");
    }

    @Override
    public void destroy() {
        System.out.println("MessageDecoder - destroy method called");
    }

}

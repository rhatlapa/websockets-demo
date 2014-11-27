package org.websocket.demo;

/**
 * @author rhatlapa (rhatlapa@redhat.com)
 */
public class Message {
    private String username;
    private String message;


    public Message() {
    }

    public Message(String username) {
        this.username = username;
    }

    public Message(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return username + ": " + message;
    }
}

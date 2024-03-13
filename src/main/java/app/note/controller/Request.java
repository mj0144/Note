package app.note.controller;


import lombok.Getter;

@Getter
public class Request {
    String mac;
    String message;

    public Request(String mac, String message) {
        this.mac = mac;
        this.message = message;
    }

}

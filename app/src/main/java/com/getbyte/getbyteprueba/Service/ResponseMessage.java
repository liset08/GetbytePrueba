package com.getbyte.getbyteprueba.Service;

/**
 * Created by LISET on 15/01/2019.
 */

public class ResponseMessage {
    private String type;

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", id=" + id +
                '}';
    }

    private String message;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}



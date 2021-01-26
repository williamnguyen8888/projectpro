package com.william.entity;

public class Response {
    private Object data;
    private int status;
    private String message;


    public Response() {
    }

    public Response(Object data, int status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
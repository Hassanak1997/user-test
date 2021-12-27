package com.example.user;

public class Rest {
    private String rest_time;
    private String status;
    private String request_time;
    private String description;

    public String getDate_rest() {
        return rest_time;
    }

    public void setDate_rest(String date_rest) {
        this.rest_time = date_rest;
    }

    public String getRest_status() {
        return status;
    }

    public void setRest_status(String rest_status) {
        this.status = rest_status;
    }

    public String getDate_created() {
        return request_time;
    }

    public void setDate_created(String date_created) {
        this.request_time = date_created;
    }

    public String getDec() {
        return description;
    }

    public void setDec(String dec) {
        this.description = dec;
    }
}

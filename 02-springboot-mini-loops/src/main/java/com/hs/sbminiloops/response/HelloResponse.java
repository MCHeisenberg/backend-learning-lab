package com.hs.sbminiloops.response;

public class HelloResponse {
    private String text;
    private String from;

    public HelloResponse(String text, String from){
        this.text = text;
        this.from = from;
    }

    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text=text;
    }

    public String getFrom(){
        return from;
    }
    public void setFrom(String from){
        this.from=from;
    }
}

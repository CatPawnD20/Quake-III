package com.ab.quake_iii;

public class Parser {

    private PingCreator pingCreator;

    public void createPingsFromString(){
        pingCreator = Creator.getObject("pingCreator");
        pingCreator.createPings();
    }

}

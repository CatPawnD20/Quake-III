package com.ab.quake_iii;

import java.util.HashMap;

public class Creator {

    private static HashMap<String,Object> hashMap = new HashMap<>();
    private static Parser parser;
    private static Container container;
    private static PingCreator pingCreator;
    private static WebListener webListener;
    private static MarkerCreator markerCreator;

    public void create() {
        parser = new Parser();
        container = new Container();
        pingCreator = new PingCreator();
        webListener = new WebListener();
        markerCreator = new MarkerCreator();
        staticSetUp();
    }

    private static void staticSetUp() {
        hashMap.put("parser", parser);
        hashMap.put("container", container);
        hashMap.put("pingCreator", pingCreator);
        hashMap.put("webListener", webListener);
        hashMap.put("markerCreator", markerCreator);
    }

    public static <T> T getObject(String s){
        return (T) hashMap.get(s);
    }
}

package com.ab.quake_iii;

import java.util.ArrayList;
import java.util.List;

public class PingCreator {

    private List<Ping> pingList = new ArrayList<>();
    private Container container;
    private List<List<String>> aqua;


    public void createPings(List<List<String>> aqua){
        container = Creator.getObject("container");
        this.aqua = aqua;
        container.setPingList(pingList);
    }
}

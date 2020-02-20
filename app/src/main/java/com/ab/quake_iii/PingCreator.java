package com.ab.quake_iii;

import java.util.ArrayList;
import java.util.List;

public class PingCreator {

    private List<Ping> pingList = new ArrayList<>();
    private Container container;


    public void createPings(){
        container = Creator.getObject("container");
        Ping ping1 = new Ping(35,34);
        Ping ping2 = new Ping(37,31);
        pingList.add(ping1);
        pingList.add(ping2);
        container.setPingList(pingList);
    }
}

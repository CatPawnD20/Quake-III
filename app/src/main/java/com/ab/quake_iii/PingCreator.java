package com.ab.quake_iii;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PingCreator {

    private List<Ping> pingList = new ArrayList<>();
    private Container container;
    private List<List<String>> aqua;

    public void createPings(List<List<String>> aqua){
        container = Creator.getObject("container");
        this.aqua = aqua;
        createPingList();
        container.setPingList(pingList);
    }

    private void createPingList(){
        for(List<String> lineEQ : aqua){
            LocalDate date = LocalDate.parse(lineEQ.get(0).replace(".", "-"));
            LocalTime time = LocalTime.parse(lineEQ.get(1));
            double y = Double.valueOf(lineEQ.get(2));
            double x = Double.valueOf(lineEQ.get(3));
            double depth = Double.valueOf(lineEQ.get(4));
            String magnitudeMD = lineEQ.get(5);
            double magnitudeML = Double.valueOf(lineEQ.get(6));
            String magnitudeMW = lineEQ.get(7);
            List<String> location = new ArrayList<>();
            location.add(lineEQ.get(8));
            int i = 0;
            while (!((lineEQ.get(9+i).startsWith("REV")) || (lineEQ.get(9+i).contains("lks")))){
                location.add(lineEQ.get(9+i));
                i++;
            }
            Ping ping = new Ping(date,time,y,x,depth,magnitudeMD,magnitudeML,magnitudeMW, location);
            pingList.add(ping);
        }
    }
}

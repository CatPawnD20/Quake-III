package com.ab.quake_iii;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PingCreator {

    private List<Ping> pingList = new ArrayList<>();
    private Container container;
    private List<List<String>> aqua;


    public void createPings(List<List<String>> aqua){
        container = Creator.getObject("container");
        this.aqua = aqua;
        initiliazeNewPings();
        container.setPingList(pingList);
    }

    private void initiliazeNewPings() {

        for(List<String> lineEQ : aqua){
            try {
                Date date = new SimpleDateFormat("yyyy.MM.dd").parse(lineEQ.get(0));
                DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                Date time = sdf.parse(lineEQ.get(1));
                double y = Double.valueOf(lineEQ.get(2));
                double x = Double.valueOf(lineEQ.get(3));
                double depth = Double.valueOf(lineEQ.get(4));
                //Magnitude deneme amaçlı dizi olarak alınmadı
                double magnitude = Double.valueOf(lineEQ.get(6));
                String location = lineEQ.get(8);
                Ping ping = new Ping(date,time,y,x,depth, magnitude, location);
                pingList.add(ping);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

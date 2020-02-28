package com.ab.quake_iii;

/*import java.time.LocalDate;
import java.time.LocalTime;*/

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class PingCreator {

    private List<Ping> pingList = new ArrayList<>();
    private Container container;

    //private ve unstatic olacak
    private List<List<String>> aqua;

    /*
    Değişiklikler
    inilitiazeNewPings location verisi düzeltildi
    Location artık düzgün alınıyor ilksel ve REV locationa eklenmiyor
    Location artık liste olarak tutuluyor
    Gerekli düzenlemeler ilgili sınıflarda yapıldı
    Ping.CLASS --
     */


    public void createPings(List<List<String>> aqua){
        container = Creator.getObject("container");
        this.aqua = aqua;
        crePingList();
        container.setPingList(pingList);
    }
    /*
    düzeltme lazım
     */
    private void crePingList(){

        for(List<String> lineEQ : aqua){
            LocalDate date = LocalDate.parse(lineEQ.get(0).replace(".", "-"));
            LocalTime time = LocalTime.parse(lineEQ.get(1));
            double y = Double.valueOf(lineEQ.get(2));
            double x = Double.valueOf(lineEQ.get(3));
            double depth = Double.valueOf(lineEQ.get(4));
            //MagnitudeMD ileriki kullanımlar için bıraktım
            //String magnitudeMD = lineEQ.get(5);
            double magnitudeML = Double.valueOf(lineEQ.get(6));
            //MagnitutdeMW ileriki kullanımlar için bıraktım
            //String magnitudeMW = lineEQ.get(7);
            List<String> location = new ArrayList<>();
            location.add(lineEQ.get(8));

            int i = 0;
            while (!((lineEQ.get(9+i).startsWith("REV")) || (lineEQ.get(9+i).contains("lks")))){
                location.add(lineEQ.get(9+i));
                i++;
            }

            Ping ping = new Ping(date,time,y,x,depth, magnitudeML, location);
            pingList.add(ping);
        }
    }
}

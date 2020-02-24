package com.ab.quake_iii;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PingCreator {

    private List<Ping> pingList = new ArrayList<>();
    private Container container;
    //private ve unstatic olacak
    public static List<List<String>> aqua;

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
    Pingleri Liste halinde veren bir fonksiyon eklendi
    NOT: Magnitutde Verileri STRİNG DOUBLE STRİNG seklinde ping objesine ekleniyor
     */

    public static List<Ping> createPingList(List<List<String>> lastList){
        List<Ping> temp = new ArrayList<>();
        for(List<String> lineEQ : lastList){
            try {
                Date date = new SimpleDateFormat("yyyy.MM.dd").parse(lineEQ.get(0));
                DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                Date time = sdf.parse(lineEQ.get(1));
                double y = Double.valueOf(lineEQ.get(2));
                double x = Double.valueOf(lineEQ.get(3));
                double depth = Double.valueOf(lineEQ.get(4));
                //Magnitude deneme amaçlı dizi olarak alınmadı
                String magnitudeMD = lineEQ.get(5);
                double magnitudeML = Double.valueOf(lineEQ.get(6));
                String magnitudeMW = lineEQ.get(7);
                List<String> location = new ArrayList<>();
                location.add(lineEQ.get(8));

//                while (!(lineEQ.get(9+i).startsWith("ilk"))){
//                    if(lineEQ.get(9+i).startsWith("REV"))continue;
//                    location.add(lineEQ.get(9+i));
//                    i++;
//                }
                for (int i = 0; i < lineEQ.size()-9 ; i++) {
                    String controller = lineEQ.get(9+i);
                    if (controller.contains("lk")){
                        break;
                    }
                    else if(controller.contains("REV")){
                        break;
                    }
                    else{
                        location.add(lineEQ.get(9+i));
                    }
                }

                Ping ping = new Ping(date,time,y,x,depth, magnitudeMD,magnitudeML,magnitudeMW, location);
                temp.add(ping);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    private void crePingList(){

        for(List<String> lineEQ : aqua){
            try {
                Date date = new SimpleDateFormat("yyyy.MM.dd").parse(lineEQ.get(0));
                DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                Date time = sdf.parse(lineEQ.get(1));
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

                /*for (int i = 0; i < lineEQ.size()-9 ; i++) {
                    String controller = lineEQ.get(9+i);
                    if (controller.contains("lk")){
                        break;
                    }
                    else if(controller.contains("REV")){
                        break;
                    }
                    else{
                        location.add(lineEQ.get(9+i));
                    }
                }*/

                Ping ping = new Ping(date,time,y,x,depth, magnitudeML, location);
                pingList.add(ping);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.ab.quake_iii;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private PingCreator pingCreator;
    private String[] lineString;
    private List<List<String>> aqua;

    public void createPingsFromString(String earthquakeString){
        lineString = firstParse(earthquakeString);
        aqua = secondParse(lineString);
        pingCreator = Creator.getObject("pingCreator");
        pingCreator.createPings(aqua);
    }

    public static String[] firstParse(String rawString){
        int ilk;
        ilk = rawString.indexOf("2");
        String temp;
        temp = rawString.substring(ilk);
        String[] line = temp.split("\n");
        return line;
    }

    /*
    Değişiklik
    sadece son iki elemanı siliyoruz
    ve parse işlemi burda bitiyor.
     */

    public static List<List<String>> secondParse(String[] lineString){

        List<String> lineList = new ArrayList<>();
        List<String> eachLineWordList;
        List<List<String>> lastList = new ArrayList<>();
        int i = 0;
        String[] temp;
        for (String eachline: lineString) {
            lineList.add(eachline);
            eachLineWordList = new ArrayList<>();
            temp = eachline.split(" ");
            for (String eachWord: temp) {
                if(!eachWord.equals("")){
                    eachLineWordList.add(eachWord);
                }
                i++;
            }
            lastList.add(eachLineWordList);
        }
        lastList.remove(501);
        lastList.remove(500);
        return lastList;
    }
}

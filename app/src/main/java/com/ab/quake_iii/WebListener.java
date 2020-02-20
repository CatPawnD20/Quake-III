package com.ab.quake_iii;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebListener extends AsyncTask<Void,Void,Void> {

    private static String webURL = "http://www.koeri.boun.edu.tr/scripts/lst6.asp";
    private String earthquakeString;
    private String s;

    private Parser parser;

    public void getDataFromWeb() {
        parser = Creator.getObject("parser");
        //program açılınca ilk veri çekilme süreci
        parser.createPingsFromString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            Document doc = Jsoup.connect(webURL).get();

            Elements deprem = doc.select("pre");

            earthquakeString = String.valueOf(deprem);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {


        //System.out.println(s);
        //System.out.println("adfsdf");
        Boolean b = s.contains("\n");
        //System.out.println(b);
        //System.out.println("sadfasdf");
        String[] line = s.split("\n");
        String[] deprem = line[7].split(" ");
        for (String s : deprem){
            System.out.println(s);
        }
    }

    public String getEarthquakeString() {
        return earthquakeString;
    }

}







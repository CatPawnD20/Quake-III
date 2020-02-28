package com.ab.quake_iii;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebListener extends AsyncTask<Void,Void,Void> {

    private static String webURL = "http://www.koeri.boun.edu.tr/scripts/lst6.asp";
    private String earthquakeString;
    private Parser parser;
    //Veri çekme sürecinin bittiğinde flag=true olarak değiştirilir.
    public static boolean flag = false;

    public void getDataFromWeb() {
        parser = Creator.getObject("parser");
        parser.createPingsFromString(earthquakeString);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        getData();
        flag = true;
        return null;
    }

    public Void getData(Void... voids) {

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
        super.onPostExecute(aVoid);
    }
}







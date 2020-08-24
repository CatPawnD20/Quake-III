package com.ab.quake_iii;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

public class WebListener extends AsyncTask<Void,Void,Void> {

    private static String[] webURL = {
            "http://www.koer.boun.edu.tr/scripts/lst0.asp",
            "http://www.koeri.boun.edu.tr/scripts/lst1.asp",
            "http://www.koeri.boun.edu.tr/scripts/lst2.asp",
            "http://www.koeri.boun.edu.tr/scripts/lst4.asp"};

    private static final String TAG = "WebListener";
    private String earthquakeString;
    private Parser parser;
    //Veri çekme sürecinin bittiğinde flag=true olarak değiştirilir.
    public static boolean flag = false;

    public void getDataFromWeb() {
        parser = Creator.getObject("parser");
        Log.i(TAG, "KendimeLog: Pinglerin yaratılması için parse işlemi başladı.");
        parser.createPingsFromString(earthquakeString);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //Uygulama tıklanarak açıldığında service çalışıyorsa onu sonlandırsın ve devam etsin.
    }

    @Override
    protected Void doInBackground(Void... voids) {
        getData();
        flag = true;
        return null;
    }

    //Bazen veri gelmiyor, internet bağlantısı veya boğaziçi ile alakalı olabilir. Bu durumlara çözüm bul.
    public Void getData(Void... voids) {

        int i = 0;
        earthquakeString = gettingDataBogazici(webURL[i],i);

        //şu anki çözümde 3 kere daha deniyor null olmayana kadar böylelikle uygulama hata vermez ama sınırı olmalı lol.
        while (earthquakeString == null){
            i++;
            if(i == 4){
                earthquakeString = getBackUpData();
                break;
            }
            earthquakeString = gettingDataBogazici(webURL[i], i);
        }

        //Eğer 3 denemeye rağmen gelmediyse uygulamayı kapat. Bu iyi bir yöntem değil ama en azından bildirimde etkilemez.
        //Buna yedeklemeyle çözüm bulunabilir. Son yedekteki verileri getir güncel olmasa da denebilir.
        //Buna bir çözüm bulunmalı, hem açılış için hem de bildirim Job çalışmaları için.
        if(earthquakeString == null){
            Log.i(TAG, "KendimeLog: earthquakeString null olduğu için killProcess çalıştı.");
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        setBackUpData(earthquakeString);

        return null;
    }

    private String gettingDataBogazici(String webURL, int i) {
        Document doc = null;

        Log.i(TAG, "KendimeLog: Veri çekme işlemi başladı." + i);

        try {
            //Veri çekmek için Connection Interface'i tipinde bir değişken ile işlem yapılıyor.
            Connection con = Jsoup.connect(webURL);
            //Bu Connection değişkenini kendim tutarak timeout süresini 5sn olarak değiştirdim. Normalde 30sn çok uzun.
            con.timeout(5000);
            //En son her şeyi ayarlandıktan sonra get işlemi yapılıyor.
            doc = con.get();

            //Eğer veri çekilemediyse, bir sonraki URL denenmesi için fonksiyon return edilir.
            if(doc == null){
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Elements deprem;

        try{
            deprem = doc.select("pre");
        }catch (Selector.SelectorParseException spe){
            return null;
        }

        Log.i(TAG, "KendimeLog: Veri çekme işlemi gerçekleşti." + i);

        return String.valueOf(deprem);
    }

    private void setBackUpData(String earthquakeString) {
        NotificationCreator.editor = NotificationCreator.sharedPref.edit();
        NotificationCreator.editor.remove("backUp");
        NotificationCreator.editor.putString("backUp", earthquakeString);
        NotificationCreator.editor.commit();
    }

    private String getBackUpData() {
        return NotificationCreator.sharedPref.getString("backUp", null);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}







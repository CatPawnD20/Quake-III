package com.ab.quake_iii;

public class WebListener {

    private Parser parser;

    public void getDataFromWeb() {
        parser = Creator.getObject("parser");
        //program açılınca ilk veri çekilme süreci
        parser.createPingsFromString();
    }

}

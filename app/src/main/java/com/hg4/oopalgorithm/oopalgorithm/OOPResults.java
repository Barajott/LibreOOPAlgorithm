package com.hg4.oopalgorithm.oopalgorithm;

import com.abbottdiabetescare.flashglucose.sensorabstractionservice.dataprocessing.GlucoseValue;
import com.abbottdiabetescare.flashglucose.sensorabstractionservice.TrendArrow;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

class HistoricBg {

    HistoricBg(GlucoseValue historicGlucose) {
        quality = historicGlucose.getDataQuality() == 0 ? 0 : 1; // TODO:
        time = historicGlucose.getId();
        bg = historicGlucose.getValue();
    }

    public int quality;
    public int time;
    public int bg;
}

public class OOPResults {
    double currentBg;
    int currentTime;
    int currenTrend;
    HistoricBg [] historicBg;
    long timestamp;
    String serialNumber;

    OOPResults(long timestamp, int currentBg, int currentTime, TrendArrow currenTrend) {

        this.currentBg = currentBg;
        this.currenTrend = 0;// Translate currenTrend TODO:
        this.timestamp = timestamp;
        this.currentTime = currentTime;
        serialNumber="";
   }
    void setHistoricBg(final List<GlucoseValue> historicGlucose) {
        if(historicGlucose == null) {
            return;
        }
        historicBg = new HistoricBg[historicGlucose.size()];
        for (int i = 0; i < historicGlucose.size(); i++) {
            historicBg[i] = new HistoricBg(historicGlucose.get(i));
        }
    }

    String toGson() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    static public void HandleData(String oopData) {
        //Log.i(TAG, "HandleData called with " + oopData);
        final Gson gson = new GsonBuilder().create();
        final OOPResults oOPResults = gson.fromJson(oopData, OOPResults.class);

    }

    public static String toGson(OOPResults[] oOPResultsArray) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(oOPResultsArray);
    }
}

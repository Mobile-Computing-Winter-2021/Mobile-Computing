package com.example.assignment5;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Plot extends AppCompatActivity {

    BarChart bChart;
    BarData bData;
    BarDataSet bDataSet;
    ArrayList bArrayList;
    private ArrayList<Integer> rssiarraylist = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot);

        bChart = findViewById(R.id.idBarChart);
        rssiarraylist = (ArrayList<Integer>) getIntent().getSerializableExtra("rssilist");
        arrayList = (ArrayList<String> ) getIntent().getSerializableExtra("aps");
        entriesforBar();


        bDataSet = new BarDataSet(bArrayList, "RSSI (Signal Strength) with  AP'S");

//        bData = new BarData(bDataSet);
        ArrayList<String> labels = new ArrayList<String>();
        for(int i=0;i<arrayList.size();i++){
            labels.add(arrayList.get(i));
        }

       bData = new BarData(bDataSet);
        bChart.setData(bData);
//        bData.setBarWidth(0.f);

       bChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        XAxis xAxis = bChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);


        YAxis yaLeft = bChart.getAxisLeft();
        YAxis yaRight = bChart.getAxisRight();
        yaLeft.setTextSize(14/*textSize*/);
        yaRight.setTextSize(14/*textSize*/);
      xAxis.setLabelRotationAngle(+90);

        bDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        bDataSet.setValueTextColor(Color.BLACK);

        bDataSet.setValueTextSize(12f);
        bChart.getDescription().setEnabled(false);
    }

    private void entriesforBar() {

        bArrayList = new ArrayList<>();
        for(int i=0;i<rssiarraylist.size();i++){
            bArrayList.add(new BarEntry(i, rssiarraylist.get(i)));
        }


    }
}
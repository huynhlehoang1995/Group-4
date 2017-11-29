package com.example.huynh.exchangerate.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.huynh.exchangerate.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    private GraphView graphView;

    public static ArrayList<Long> usd = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        graphView = findViewById(R.id.graph);

        DataPoint[] point = new DataPoint[usd.size()];

        for (int i = 0; i < point.length; i++) {
            point[i] = new DataPoint(i, usd.get(i));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(point);

        graphView.addSeries(series);

        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);
    }
}

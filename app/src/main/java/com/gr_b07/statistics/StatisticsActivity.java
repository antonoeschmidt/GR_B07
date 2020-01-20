package com.gr_b07.statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.gr_b07.R;
import com.gr_b07.logik.Meal;
import com.gr_b07.logik.Settings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {
    private LineChart lineChart;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private ArrayList<Double> data = new ArrayList<>();
    private ArrayList<Point> dataPoint = new ArrayList<>();
    private int labelCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        lineChart = findViewById(R.id.lineChart);

        lineChart.setDragEnabled(true);
        populateDateArray();
        addData();


    }

    public int longToIntDate(long time){
        Date date = new Date(time);
        String dateString = df.format(date);
        System.out.println(dateString);
        int x = Integer.parseInt(dateString.substring(0,2));
        return x;
    }

    public void addData() {
        mealsToData();

        List<Entry> entries = new ArrayList<>();
        for (Point data : dataPoint) {
            // turn your data into Entry objects
            if (data.y != 0) {
                entries.add(new Entry(data.x, data.y));
                labelCount++;
            }
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.parseColor("#1FBBA6"));
        dataSet.setFillAlpha(110);
        dataSet.setLineWidth(3f);
        dataSet.setValueTextSize(15f);
        //dataSet.setValueTextColor();

        //TODO: virker getDailyIntake her?
        LimitLine upperLimit = new LimitLine((int)Settings.getCurrentPupil().getDailyIntake(System.currentTimeMillis()).getCalories(), "Foreslåede indtag");
        upperLimit.setLineWidth(4f);
        upperLimit.enableDashedLine(10f, 10f, 0);
        upperLimit.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        upperLimit.setTextSize(15f);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.addLimitLine(upperLimit);
        yAxisLeft.enableGridDashedLine(10f, 10f, 0);
        yAxisLeft.setTextSize(15f);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(15f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) Math.round(value));
            }
        });
        xAxis.setLabelCount(labelCount,true);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();

    }

    private void mealsToData() {
        for (Meal meal :
                Settings.getCurrentPupil().getMeals() ) {
            for (Point point :
                    dataPoint) {
                if (longToIntDate(meal.getDate()) == point.x) {
                    point.y += (int)Math.round(meal.getCalories());
                }
            }
        }
    }

    public void populateDateArray() {
        dataPoint.clear();
        for (int i = 1; i <= 31; i++) {
            dataPoint.add(new Point(i,0));
        }
    }
}

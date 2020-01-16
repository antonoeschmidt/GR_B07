package com.gr_b07.statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
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
    private ArrayList<Double> month = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        lineChart = findViewById(R.id.lineChart);
        addData();


    }
    public void createDateArray() {
        for (int i = 0; i < 31; i++) {
            month.add(0.0);
        }
    }



    public void addDataToArray(int date, double cals) {
        month.set(date, month.get(date) + cals);

    }

    public int longToIntDate(long time){
        Date date = new Date(time);
        String dateString = df.format(date);
        System.out.println(dateString);
        int x = Integer.parseInt(dateString.substring(0,2));
        return x;
    }



    public void addData() {
        ArrayList<Point> mealsForTheDay = new ArrayList<Point>();
        for (Meal meal :
                Settings.getCurrentPupil().getMeals() ) {
            addDataToArray(longToIntDate(meal.getDate()),meal.getCalories());
        }

        int y = 0;
        int x = 0;

        Date now = new Date(System.currentTimeMillis());

        for (Point point :
                mealsForTheDay) {
            if (point.x == )
            y += point.y;
            x = point.x;
        }
        new Point(x,y);


        //Point[] dataObjects = {new Point(1,3), new Point(2, 4), new Point(3, 7), new Point(4, 8)};
        List<Entry> entries = new ArrayList<Entry>();
        for (Point data : mealsForTheDay) {
            // turn your data into Entry objects
            entries.add(new Entry(data.x, data.y));

        }

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.BLUE);
        dataSet.setFillAlpha(110);
        dataSet.setLineWidth(3f);
        dataSet.setValueTextSize(10f);
        //dataSet.setValueTextColor();

        LimitLine upperLimit = new LimitLine(7, "Daily");
        upperLimit.setLineWidth(4f);
        upperLimit.enableDashedLine(10f, 10f, 0);
        upperLimit.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        upperLimit.setTextSize(15f);

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.addLimitLine(upperLimit);
        yAxis.enableGridDashedLine(10f, 10f, 0);


        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();

    }
}

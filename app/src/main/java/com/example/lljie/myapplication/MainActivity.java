package com.example.lljie.myapplication;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart lineChart = (LineChart) findViewById(R.id.lineChart);
        //x轴长度
        lineChart.setxLength(800);
        //y轴长度
        lineChart.setyLength(500);
        //设置这个图形的左上角坐标
        lineChart.setStartPoint(new Point(130, 350));
        //y轴
        lineChart.setyArr(new int[]{0, 10, 20, 30, 40, 50});
        lineChart.setxArr(new String[]{"04-01", "04-06", "04-06", "04-07"});
        //背景条形颜色
        lineChart.setBarColor(Color.argb(90, 204, 232, 207));
        lineChart.setTextColor(Color.LTGRAY);
        lineChart.setTextSize(40);
        //折线数据
        lineChart.setDataArr(new double[]{35, 40, 42.265,12});//数据数组长度必须小于等于x轴数组长度，否则数组越界报错
        //标题文字
        lineChart.setTitle("近一周营收(元)");

    }
}

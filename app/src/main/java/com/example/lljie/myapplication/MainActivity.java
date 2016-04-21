package com.example.lljie.myapplication;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        LineChart lineChart = (LineChart) findViewById(R.id.lineChart);
        //x轴长度
        lineChart.setxLength(800);
        //y轴长度
        lineChart.setyLength(500);
        //设置这个图形的左上角坐标
        lineChart.setStartPoint(new Point(130, 350));
        //y轴
        lineChart.setyArr(new int[]{0, 1000, 2000, 3000, 4000, 5000});
        lineChart.setxArr(new String[]{"03-01", "03-06", "03-06", "03-06", "03-07"});
        //背景条形颜色
        lineChart.setBarColor(Color.argb(90, 204, 232, 207));
        lineChart.setTextColor(Color.LTGRAY);
        lineChart.setTextSize(40);
        //折线数据
        lineChart.setDataArr(new double[]{3500, 4000, 4200});//数据数组长度必须小于等于x轴数组长度，否则数组越界报错
        //标题文字
        lineChart.setTitle("近一周营收(元)");



        lineChart.setyArr(new int[]{0, 10, 20, 30, 40, 50});
        lineChart.setxArr(new String[]{"04-01", "04-06", "04-06", "04-06", "04-07"});
        lineChart.setDataArr(new double[]{35, 40, 42.265});


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();


    }


}

package com.example.lljie.myapplication;/**
 * Created by LLJie on 2016/3/21.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Author: LiLinjie
 * Date: 2016-03-21
 * Time: 17:19
 * FIXME
 */
public class LineChart extends View {
    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChart(Context context) {
        super(context);
    }

    //    y轴参数
    private int[] yArr;

    private String[] xArr;

    public void setyArr(int[] yArr) {
        this.yArr = yArr;
    }

    public int[] getyArr() {
        return yArr;
    }

    public void setxArr(String[] xArr) {
        this.xArr = xArr;
    }

    public String[] getxArr() {
        return xArr;
    }

    private int xLength;

    public int getyLength() {
        return yLength;
    }

    public void setyLength(int yLength) {
        this.yLength = yLength;
    }

    public int getxLength() {
        return xLength;
    }

    public void setxLength(int xLength) {
        this.xLength = xLength;
    }

    //横线末尾多余的部分的长度
    private float xRmargin = 30;


    public void setxRmargin(float xRmargin) {
        this.xRmargin = xRmargin;
    }


    private int yLength;

    //图形左上方起点
    private Point startPoint;

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    private int barColor;

    public void setBarColor(int barColor) {
        this.barColor = barColor;
    }

    //文字默认黑色
    private int textColor = Color.BLACK;

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    private double[] dataArr;

    public void setDataArr(double[] dataArr) {
        this.dataArr = dataArr;
    }

    public double[] getDataArr() {
        return dataArr;
    }

    //文字大小
    private int textSize = 40;

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    private int lineColor = Color.RED;

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    private int chartColor = Color.LTGRAY;

    public void setChartColor(int chartColor) {
        this.chartColor = chartColor;
    }

    //表格线的宽度

    public void setChartLineWidth(int chartLineWidth) {
        this.chartLineWidth = chartLineWidth;
    }

    private int chartLineWidth = 4;

    public void setPolyLineWidth(int polyLineWidth) {
        this.polyLineWidth = polyLineWidth;
    }

    //折线的宽度
    private int polyLineWidth = 6;

    //标题文字
    private String title = "";
    private int titleSize = 50;
    private int titleColor = Color.LTGRAY;

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(chartColor);
        //设置线的宽度
        paint.setStrokeWidth(chartLineWidth);

        Paint paint2 = new Paint();
        paint2.setColor(barColor);

        int[] Ys = {100, 200, 300, 400};

        Point originPoint = new Point(startPoint.x, startPoint.y + getyLength());//折线图坐标原点。0.0。
        Point YMAXPoint = new Point(startPoint.x, startPoint.y);//折线图 Y轴最大坐标
        Point XMAXPoint = new Point(startPoint.x + xLength, startPoint.y + getyLength());//折线图 X轴最大坐标

        //设置标题
        Paint titlePaint = new Paint();
        titlePaint.setColor(titleColor);
        titlePaint.setTextSize(titleSize);
        float titleLength = titlePaint.measureText(title);
        canvas.drawText(title, startPoint.x - (titleLength / 3), startPoint.y - (2 * titleSize), titlePaint);

        canvas.drawLine(startPoint.x, startPoint.y, startPoint.x, startPoint.y + getyLength(), paint);//y轴
        canvas.drawLine(startPoint.x, startPoint.y + getyLength(), startPoint.x + xLength + xRmargin, startPoint.y + getyLength(), paint);//x轴


        //画横线
        int yLineNum = yArr.length ;//y轴分格格数
        float itemYLength = yLength / yLineNum;//每格宽度
        //末端多余的线  xRmargin
        for (int i = 1; i < yArr.length + 1; i++) {
            canvas.drawLine(startPoint.x, startPoint.y + (itemYLength * i), startPoint.x + xLength + xRmargin, startPoint.y + (itemYLength * i), paint);//y轴
        }


        //画竖线
        int xLineNum = xArr.length;//x轴分格格数
        float itemXLength = xLength / xLineNum;//每格宽度
        for (int i = 0; i < xArr.length; i++) {
            canvas.drawLine(originPoint.x + (itemXLength * i), startPoint.y, originPoint.x + (itemXLength * i), originPoint.y, paint);//y轴

            //绘制背景的颜色条
            if (i % 2 == 1) {//奇数

                if (i == xArr.length - 1) {
                    canvas.drawRect(new RectF(startPoint.x + (itemXLength * i), startPoint.y, startPoint.x + xLength + xRmargin, originPoint.y), paint2);
                } else {
                    canvas.drawRect(new RectF(startPoint.x + (itemXLength * i), startPoint.y, startPoint.x + (itemXLength * (i + 1)), originPoint.y), paint2);
                }
            }


        }

        //画x轴文字
        Paint paintText = new Paint();
        paintText.setColor(textColor);
        paintText.setTextSize(textSize);
        for (int i = 0; i < xArr.length; i++) {
            float textLength = paintText.measureText(xArr[i]);
            canvas.drawText(xArr[i], startPoint.x + (itemXLength * i) - (textLength / 2), originPoint.y + (textLength / 2), paintText);
        }

        //画y轴文字
        for (int i = 0; i < yArr.length ; i++) {
            //测量字符串长度
            float normalTextLength = paintText.measureText(String.valueOf(yArr[yArr.length-1]));//最后一个元素一般最长，设为一般长度
            float perTextLength = paintText.measureText(String.valueOf(yArr[i]));//每个元素长度
            canvas.drawText(String.valueOf(yArr[i]), startPoint.x - perTextLength - (normalTextLength / 2), originPoint.y - (itemYLength * i) + (textSize / 2), paintText);
        }

        //画折线
        Paint linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(polyLineWidth);
        for (int i = 0; i < dataArr.length - 1; i++) {
            int itemNum = yArr[1] - yArr[0];//y轴一格代表的数值
            float perY = itemYLength / itemNum;//按数值，细分每格
            //起点
            float Y = perY * (float)dataArr[i];//数据的实际长度
            //终点
            float nextY = perY * (float)dataArr[i + 1];

            //终点x的坐标值
            Float nextXPosition = originPoint.x + (itemXLength * (i + 1));
            //终点y的坐标值
            Float nextYPosition = originPoint.y - nextY;

            canvas.drawLine(originPoint.x + (itemXLength * i), originPoint.y - Y, nextXPosition, nextYPosition, linePaint);//y轴




            if (i == dataArr.length - 2) {
                canvas.drawCircle(nextXPosition, nextYPosition, 15, linePaint);
                //画个白色圆形
                Paint circlePaint = new Paint();
                circlePaint.setColor(Color.WHITE);
                canvas.drawCircle(nextXPosition, nextYPosition, 8, circlePaint);

                Paint textPaint = new Paint();
                textPaint.setColor(Color.WHITE);
                textPaint.setTextSize(textSize);

                //转0.00格式
                String lastDataNum=new DecimalFormat("#.00").format(dataArr[i + 1]);

                float textLength = textPaint.measureText(lastDataNum);
                float padding = 10;
                RectF rectF = new RectF(nextXPosition - (textLength / 2) - padding, nextYPosition - textSize - textSize - padding, nextXPosition + (textLength / 2) + padding, nextYPosition - textSize + padding);
                canvas.drawRoundRect(rectF, 15, 15, linePaint);
//              canvas.drawRect(nextXPosition - (textLength / 2) - padding, nextYPosition - textSize - textSize - padding, nextXPosition + (textLength / 2) + padding, nextYPosition - textSize + padding, linePaint);


                //结束点的文字（白色）
                canvas.drawText(lastDataNum, nextXPosition - (textLength / 2), nextYPosition - textSize - padding, textPaint);

            }
        }
    }
}

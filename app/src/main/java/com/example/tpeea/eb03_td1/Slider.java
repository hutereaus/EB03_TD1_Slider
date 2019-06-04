package com.example.tpeea.eb03_td1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class Slider extends View {

    private double currentValue;
    private double minValue, maxValue;
    // private double width;
    private double heigth;
    // private double paddingTop, paddingBottom, paddingRight, paddingLeft;
    private double barWidth;
    private double barLength;
    private double barColor, valueColor, cursorColor;
    private double cursorDiameter;
    private int colorPrimary;
    private int colorPrimaryDark;
    private int colorAccent;

    Paint paintCursor = null;
    Paint paintValue = null;
    Paint paintBar = null;

    // Constants dimensions are in dp
    private final static float CURRENTVALUE = 0;
    private final static float MINVALUE = 0;
    private final static float MAXVALUE = 10;
    private final static float WIDTH = 5;
    private final static float HEIGTH = 5;
    private final static float PADDINGTOP = 0;
    private final static float PADDINGBOTTOM = 0;
    private final static float PADDINGRIGHT = 0;
    private final static float PADDINGLEFT = 0;
    private final static float BARWIDTH = 5;
    private final static float BARLENGTH = 5;
    private final static float BARCOLOR = 255;
    private final static float VALUECOLOR = 255;
    private final static float CURSORCOLOR = 255;
    private final static float CURSORDIAMETER = 1;


    public int getColorPrimary() {
        return colorPrimary;
    }

    public void setColorPrimary(int colorPrimary) {
        this.colorPrimary = colorPrimary;
    }

    public int getColorPrimaryDark() {
        return colorPrimaryDark;
    }

    public void setColorPrimaryDark(int colorPrimaryDark) {
        this.colorPrimaryDark = colorPrimaryDark;
    }

    public int getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(int colorAccent) {
        this.colorAccent = colorAccent;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    public double getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(double barWidth) {
        this.barWidth = barWidth;
    }

    public double getBarLength() {
        return barLength;
    }

    public void setBarLength(double barLength) {
        this.barLength = barLength;
    }

    public double getBarColor() {
        return barColor;
    }

    public void setBarColor(double barColor) {
        this.barColor = barColor;
    }

    public double getValueColor() {
        return valueColor;
    }

    public void setValueColor(double valueColor) {
        this.valueColor = valueColor;
    }

    public double getCursorColor() {
        return cursorColor;
    }

    public void setCursorColor(double cursorColor) {
        this.cursorColor = cursorColor;
    }

    public double getCursorDiameter() {
        return cursorDiameter;
    }

    public void setCursorDiameter(double cursorDiameter) {
        this.cursorDiameter = cursorDiameter;
    }

    public Slider(Context context){
        super(context); // utiliser le constructeur de View
        
    }
    public Slider(Context context, AttributeSet attrs){
        super(context); // utiliser le constructeur de View

    }

    /**
     * Qst 4-3
     * @return
     */

    public double valueToRatio(){
        double ratio;
        ratio = ( this.currentValue - this.minValue ) / (this.maxValue - this.minValue);
        return ratio;
    }
    public double ratioToValue(double ratio){
        double currentValue;
        currentValue = ratio * (this.maxValue - this.minValue) + this.minValue;
        return currentValue;
    }

    /**
     * Qst 4-4
     * @param value
     * @return
     */
    public Point toPos(double value){
        Point point = new Point();
        int x = (int)(this.getPaddingLeft() + this.cursorDiameter / 2);
        int y = (int)(this.getPaddingBottom() + valueToRatio() * this.barLength);
        point.set(x, y);
        return point;
    }

    public double toValue(Point point){
        double ratio = (point.y - this.getPaddingBottom()) / this.barLength;
        return ratioToValue(ratio);
    }

    /**
     * Qst 4-5
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs){
        // initialize attributes to default values
        currentValue = dpToPixel(CURRENTVALUE);
        minValue = dpToPixel(MINVALUE);
        maxValue = dpToPixel(MAXVALUE);
        heigth = dpToPixel(HEIGTH);
        barWidth = dpToPixel(BARWIDTH);
        barLength = dpToPixel(BARLENGTH);
        barColor = dpToPixel(BARCOLOR);
        valueColor = dpToPixel(VALUECOLOR);
        cursorColor = dpToPixel(CURSORCOLOR);
        cursorDiameter = dpToPixel(CURSORDIAMETER);

        // instantiate Paint for each element
        this.paintCursor = new Paint();
        this.paintValue = new Paint();
        this.paintBar = new Paint();

        // parameters of Paint objects
        this.paintCursor.setAntiAlias(true);
        this.paintValue.setAntiAlias(true);
        this.paintBar.setAntiAlias(true);

        // style of Paint objects
        this.paintCursor.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paintValue.setStyle(Paint.Style.STROKE);
        this.paintBar.setStyle(Paint.Style.STROKE);

        // specification des personalisation XML
        paintBar.setStrokeCap(Paint.Cap.ROUND);

        // Set minHeigth and minWidth
        float minHeight = (float)(super.getPaddingBottom() + this.barLength + super.getPaddingTop());
        setMinimumHeight((int)dpToPixel(minHeight));
        float minWidth = (float)(super.getPaddingRight() + this.cursorDiameter + super.getPaddingLeft());
        setMinimumWidth((int)dpToPixel(minWidth));

        // get colors from colors.xml
        colorAccent = ContextCompat.getColor(context,R.color.colorAccent);
        colorPrimary = ContextCompat.getColor(context,R.color.colorPrimary);
        colorPrimaryDark = ContextCompat.getColor(context,R.color.colorPrimaryDark);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * Convertit une valeur en dp en pixel
     *
     * @param valueInDp
     * @return : nombre de pixels correspondant
     */
    private float dpToPixel(float valueInDp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, getResources().getDisplayMetrics());
    }

}

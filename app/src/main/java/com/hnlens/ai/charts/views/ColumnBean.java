package com.hnlens.ai.charts.views;

public class ColumnBean {

    private int value;
    private int currentCycleFlag; //mCurrentCycleFlag
    private int index;

    private String x_coordinate;
    private float percent;
    private long timeStamp;

    public ColumnBean(int value, String x_coordinate, int maxRecyclerValue,int index,int currentCycleFlag) {
        this.value = value;
        this.x_coordinate = x_coordinate;
        this.percent = (float) (value * 1.0 / maxRecyclerValue);
        this.index = index;
        this.currentCycleFlag = currentCycleFlag;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(String x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getCurrentCycleFlag() {
        return currentCycleFlag;
    }

    public void setCurrentCycleFlag(int currentCycleFlag) {
        this.currentCycleFlag = currentCycleFlag;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

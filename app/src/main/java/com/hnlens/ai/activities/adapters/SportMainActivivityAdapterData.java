package com.hnlens.ai.activities.adapters;

public class SportMainActivivityAdapterData {
    private int id;
    private String title;
    private int iconId;
    private long data;
    private String date;

    public SportMainActivivityAdapterData(long data, String date, int iconId, int id, String title) {
        this.data = data;
        this.date = date;
        this.iconId = iconId;
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

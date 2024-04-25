package com.hnlens.ai.activities.adapters;

public class SportMainActivivityAdapterData {
    private int id;
    private String title;
    private String summary;
    private int iconId;

    public SportMainActivivityAdapterData(int id, String title, String summary, int iconId) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.iconId = iconId;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}

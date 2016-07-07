package com.frank.toolbardemo;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class ItemBean {
    public int IconID;
    public String colorID;

    public ItemBean(int iconID, String colorID) {
        IconID = iconID;
        this.colorID = colorID;
    }

    public int getIconID() {
        return IconID;
    }

    public void setIconID(int iconID) {
        IconID = iconID;
    }

    public String getColorID() {
        return colorID;
    }

    public void setColorID(String colorID) {
        this.colorID = colorID;
    }
}

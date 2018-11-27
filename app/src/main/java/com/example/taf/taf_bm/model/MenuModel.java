package com.example.taf.taf_bm.model;

public class MenuModel <T>{

    public String menuName;
    public boolean hasChildren, isGroup;
    public int icon;

    public  MenuModel(String menuName, boolean isGroup, boolean hasChildren, int icon) {
        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
        this.icon = icon;
    }
}
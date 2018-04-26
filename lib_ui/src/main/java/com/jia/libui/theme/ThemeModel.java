package com.jia.libui.theme;

/**
 * Description: 主题实体类
 * Created by jia on 2018/4/25.
 * 人之所以能，是相信能。
 */
public class ThemeModel {

    private String themeColor;
    private String themeName;
    private boolean isChoose;

    public ThemeModel(String themeColor, String themeName) {
        this.themeColor = themeColor;
        this.themeName = themeName;
    }

    public ThemeModel(String themeColor, String themeName, boolean isChoose) {
        this.themeColor = themeColor;
        this.themeName = themeName;
        this.isChoose = isChoose;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}

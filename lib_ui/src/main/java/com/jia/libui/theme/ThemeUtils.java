package com.jia.libui.theme;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 主题工具类
 * Created by jia on 2018/4/25.
 * 人之所以能，是相信能。
 */

public class ThemeUtils {

    private List<ThemeModel> themes;

    private static ThemeUtils instance;

    private ThemeUtils() {

    }

    public static ThemeUtils getInstance() {
        if (instance == null) {
            instance = new ThemeUtils();
        }
        return instance;
    }

    /**
     * 获取主题列表
     *
     * @return
     */
    public List<ThemeModel> getThemeList() {
        if (themes == null) {
            themes = new ArrayList<>();
            themes.add(new ThemeModel("#3F51B5", "blue",true));
            themes.add(new ThemeModel("#F44336", "pink"));
            themes.add(new ThemeModel("#E91E63", "red"));
            themes.add(new ThemeModel("#673AB7", "purple"));
            themes.add(new ThemeModel("#4CAF50", "green"));
            themes.add(new ThemeModel("#FFC107", "yellow"));
            themes.add(new ThemeModel("#FF5722", "orange"));
            themes.add(new ThemeModel("#9E9E9E", "gary"));
        }
        return themes;
    }
}

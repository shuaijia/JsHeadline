package com.jia.libui.theme;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jia.libui.CircularImage;
import com.jia.libui.R;
import com.jia.libui.utils.SPUtils;
import com.zhy.changeskin.SkinManager;

import java.util.List;

/**
 * Description:
 * Created by jia on 2018/4/25.
 * 人之所以能，是相信能。
 */

public class ThemeDialog extends Dialog implements View.OnClickListener {

    private RecyclerView rv_content;
    private TextView tv_cancel;
    private TextView tv_sure;

    private List<ThemeModel> list;

    private String theme = "blue";
    private String themeColor = "blue";

    public ThemeDialog(@NonNull Context context) {
        super(context);

        list = ThemeUtils.getInstance().getThemeList();

        init();
    }

    private void init() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_theme, null);

        setContentView(v);

        rv_content = v.findViewById(R.id.rv_content);
        tv_cancel = v.findViewById(R.id.tv_cancel);
        tv_sure = v.findViewById(R.id.tv_sure);

        tv_cancel.setOnClickListener(this);
        tv_sure.setOnClickListener(this);

        rv_content.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rv_content.setAdapter(new ThemeAdapter());
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_cancel) {
            dismiss();
        } else {
            SPUtils.saveData(getContext(), "theme", themeColor);
            // 切换主题
            SkinManager.getInstance().changeSkin(theme);
            dismiss();
        }
    }

    private class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeHolder> {

        @Override
        public ThemeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.item_theme_dialog, parent, false);
            return new ThemeHolder(v);
        }

        @Override
        public void onBindViewHolder(ThemeHolder holder, final int position) {
            holder.iv_theme.setBackgroundColor(Color.parseColor(list.get(position).getThemeColor()));

            holder.iv_choose.setVisibility(list.get(position).isChoose() ? View.VISIBLE : View.GONE);

            holder.iv_theme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < list.size(); i++) {
                        if (i == position) list.get(i).setChoose(true);
                        else list.get(i).setChoose(false);
                    }

                    notifyDataSetChanged();

                    theme = list.get(position).getThemeName();
                    themeColor = list.get(position).getThemeColor();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        class ThemeHolder extends RecyclerView.ViewHolder {

            CircularImage iv_theme;
            ImageView iv_choose;

            public ThemeHolder(View itemView) {
                super(itemView);
                iv_theme = itemView.findViewById(R.id.iv_theme);
                iv_choose = itemView.findViewById(R.id.iv_choose);
            }
        }
    }
}

package com.godliness.android.modulepdfdemo.controller;

import android.widget.TextView;

import com.godliness.android.modulepdf.BasePDFTitlebar;
import com.godliness.android.modulepdfdemo.R;

/**
 * Created by godliness on 2020-03-26.
 *
 * @author godliness
 */
public final class TitleBar extends BasePDFTitlebar {

    private TextView mTitle;

    @Override
    public int getBarLayoutId() {
        return R.layout.app_layout_title_bar;
    }

    @Override
    public void initBar() {
        mTitle = findViewById(R.id.tv_title);
    }

    @Override
    public void regEvent(boolean event) {
    }

    @Override
    public void setPDFTitle(String title) {
        mTitle.setText(title);
    }

}

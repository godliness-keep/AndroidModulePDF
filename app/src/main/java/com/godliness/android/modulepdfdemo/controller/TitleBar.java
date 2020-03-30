package com.godliness.android.modulepdfdemo.controller;

import com.godliness.android.modulepdf.BasePDFControllerBar;
import com.godliness.android.modulepdfdemo.R;

/**
 * Created by godliness on 2020-03-26.
 *
 * @author godliness
 */
public final class TitleBar extends BasePDFControllerBar {

    @Override
    public int getBarLayoutId() {
        return R.layout.app_layout_title_bar;
    }

    @Override
    public void initBar() {
    }

    @Override
    public void regEvent(boolean event) {
    }
}

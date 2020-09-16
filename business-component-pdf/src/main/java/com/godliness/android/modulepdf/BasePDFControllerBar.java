package com.godliness.android.modulepdf;

import com.godliness.android.base.controller.BaseControllerBar;
import com.godliness.android.modulepdf.options.BaseOptions;

/**
 * Created by godliness on 2020-03-30.
 *
 * @author godliness
 * <p>
 * PDF控制器控制栏
 */
public abstract class BasePDFControllerBar<Options extends BaseOptions> extends BaseControllerBar {

    /**
     * Page changed
     *
     * @param page      current page
     * @param pageCount total
     */
    public abstract void onPageChanged(int page, int pageCount);

    /**
     * Configuration from user custom
     *
     * @param options configuration of user custom
     */
    public void updateConfigurationOptions(Options options) {

    }
}

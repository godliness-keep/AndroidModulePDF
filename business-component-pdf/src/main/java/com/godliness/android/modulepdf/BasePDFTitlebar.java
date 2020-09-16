package com.godliness.android.modulepdf;

import com.godliness.android.modulepdf.options.BaseOptions;

/**
 * Created by godliness on 2020-04-08.
 *
 * @author godliness
 * PDF控制器标题栏
 */
public abstract class BasePDFTitlebar<Options extends BaseOptions> extends BasePDFControllerBar<Options> {

    /**
     * Title of current resource
     *
     * @param title resource title
     */
    public abstract void setPDFTitle(String title);

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void updateConfigurationOptions(Options options) {

    }
}

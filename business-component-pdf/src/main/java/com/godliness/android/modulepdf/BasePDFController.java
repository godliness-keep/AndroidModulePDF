package com.godliness.android.modulepdf;

import android.net.Uri;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.source.DocumentSource;
import com.godliness.android.base.controller.BaseStateBar;
import com.godliness.android.base.controller.BaseTemplateController;
import com.godliness.android.modulepdf.options.BaseOptions;

import java.io.File;
import java.io.InputStream;

/**
 * Created by godliness on 2020-03-27.
 *
 * @author godliness
 * <p>
 * Base controller of PDFView
 */
@SuppressWarnings({"unused", "unchecked", "WeakerAccess"})
public abstract class BasePDFController<TitleBar extends BasePDFTitlebar, BottomBar extends BasePDFControllerBar, StateBar extends BasePDFStateBar>
        extends BaseTemplateController<TitleBar, BottomBar, StateBar>
        implements OnErrorListener, OnLoadCompleteListener, OnPageChangeListener, OnRenderListener {

    private BaseOptions mOptions;
    private String mTtile;

    public BasePDFController(PDFView host) {
        super(host);
    }

    /**
     * More configuration
     */
    protected <Options extends BaseOptions> Options createOptions() {
        return null;
    }

    public final void setPDFTitle(String title) {
        final TitleBar titleBar = getTitleBar();
        if (titleBar != null) {
            titleBar.setPDFTitle(title);
        } else {
            this.mTtile = title;
        }
    }

    @Override
    protected final void initTitleBar() {
        super.initTitleBar();
        final TitleBar titleBar = getTitleBar();
        if (titleBar != null) {
            titleBar.updateConfigurationOptions(getOptions());
            titleBar.setPDFTitle(mTtile);
        }
    }

    @Override
    protected final void initBottomBar() {
        super.initBottomBar();
        final BottomBar bottomBar = getBottomBar();
        if (bottomBar != null) {
            bottomBar.updateConfigurationOptions(getOptions());
        }
    }

    @Override
    public final void onError(Throwable t) {
        final StateBar stateBar = getStateBar();
        if (stateBar != null) {
            if (!hasNetwork()) {
                stateBar.onError(BaseStateBar.Status.STATUS_NETWORK_ERROR);
                return;
            }
            stateBar.onError(0);
        }
    }

    @Override
    public final void loadComplete(int nbPages) {
        final StateBar stateBar = getStateBar();
        if (stateBar != null) {
            stateBar.onLoading(false);
        }
    }

    @Override
    public final void onPageChanged(int page, int pageCount) {
        final TitleBar titleBar = getTitleBar();
        if (titleBar != null) {
            titleBar.onPageChanged(page, pageCount);
        }
        final BottomBar bottomBar = getBottomBar();
        if (bottomBar != null) {
            bottomBar.onPageChanged(page, pageCount);
        }
    }

    public final PDFView.Configurator fromAsset(String assetName) {
        return initConfigurator(assetName);
    }

    public final PDFView.Configurator fromFile(File file) {
        return initConfigurator(file);
    }

    public final PDFView.Configurator fromUri(Uri uri) {
        return initConfigurator(uri);
    }

    public final PDFView.Configurator fromBytes(byte[] bytes) {
        return initConfigurator(bytes);
    }

    public final PDFView.Configurator fromStream(InputStream stream) {
        return initConfigurator(stream);
    }

    public final PDFView.Configurator fromSource(DocumentSource docSource) {
        return initConfigurator(docSource);
    }

    private PDFView getPDFView() {
        if (getHost() instanceof PDFView) {
            return getHost();
        }
        return null;
    }

    private BaseOptions getOptions() {
        if (mOptions == null) {
            mOptions = createOptions();
        }
        return mOptions;
    }

    private PDFView.Configurator initConfigurator(Object resource) {
        final PDFView pdfView = getPDFView();
        PDFView.Configurator configurator;
        if (pdfView != null) {
            if (resource instanceof String) {
                configurator = pdfView.fromAsset((String) resource);
            } else if (resource instanceof File) {
                configurator = pdfView.fromFile((File) resource);
            } else if (resource instanceof Uri) {
                configurator = pdfView.fromUri((Uri) resource);
            } else if (resource instanceof byte[]) {
                configurator = pdfView.fromBytes((byte[]) resource);
            } else if (resource instanceof InputStream) {
                configurator = pdfView.fromStream((InputStream) resource);
            } else if (resource instanceof DocumentSource) {
                configurator = pdfView.fromSource((DocumentSource) resource);
            } else {
                throw new IllegalArgumentException("Unknown resource type");
            }

            configurator.onError(this)
                    .onPageChange(this)
                    .onLoad(this)
                    .onRender(this);
            return configurator;
        }
        return null;
    }
}

package com.godliness.android.modulepdfdemo.controller;

import android.support.annotation.Nullable;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.godliness.android.modulepdf.BasePDFController;

/**
 * Created by godliness on 2020-03-26.
 *
 * @author godliness
 */
public final class PDFController extends BasePDFController<TitleBar, BottomBar, DefaultStateBar> implements DefaultStateBar.OnStateBarListener, BottomBar.OnBottomBarChangeListener {

    private final PDFView mPDFView;

    public PDFController(PDFView host) {
        super(host);
        this.mPDFView = host;
    }

    @Override
    public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
    }

    @Nullable
    @Override
    protected TitleBar createTitleBar() {
        return new TitleBar();
    }

    @Nullable
    @Override
    protected BottomBar createBottomBar() {
        return new BottomBar();
    }

    @Nullable
    @Override
    protected DefaultStateBar createStateBar() {
        return new DefaultStateBar();
    }

    /**
     * Initialization template
     */
    @Override
    protected void initTemplate() {

    }

    @Override
    protected void regEvent(boolean event) {
        getStateBar().setStateBarListener(event ? this : null);
        getBottomBar().setOnBottomBarChangeListener(event ? this : null);
    }

    @Override
    public void onRetry() {
        Toast.makeText(getContext(), "onRetry", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageChanged(int currentProgress) {
        mPDFView.jumpTo(currentProgress);
    }

    @Override
    public void onNext() {
        int currentPage = mPDFView.getCurrentPage();
        if (currentPage >= mPDFView.getPageCount() - 1) {
            return;
        }
        mPDFView.jumpTo(++currentPage, true);
    }

    @Override
    public void onPre() {
        int currentPage = mPDFView.getCurrentPage();
        if (currentPage <= 0) {
            return;
        }
        mPDFView.jumpTo(--currentPage, true);
    }
}
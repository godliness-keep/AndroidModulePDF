package com.godliness.android.modulepdfdemo.controller;

import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.godliness.android.modulepdf.BasePDFControllerBar;
import com.godliness.android.modulepdfdemo.R;

/**
 * Created by godliness on 2020-03-26.
 *
 * @author godliness
 */
public final class BottomBar extends BasePDFControllerBar implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private SeekBar mProgress;
    private TextView mTvProgress;

    private View mNext;
    private View mPre;

    private int mCurrentPage;

    private OnBottomBarChangeListener mCallback;

    public interface OnBottomBarChangeListener {

        void onPageChanged(int currentProgress);

        void onNext();

        void onPre();
    }

    void setOnBottomBarChangeListener(OnBottomBarChangeListener callback) {
        this.mCallback = callback;
    }

    @Override
    public int getBarLayoutId() {
        return R.layout.app_layout_bottom_bar;
    }

    @Override
    public void initBar() {
        mProgress = findViewById(R.id.modulestudy_pb_pdf);
        mTvProgress = findViewById(R.id.modulestudy_tv_progress_pdf);
        mNext = findViewById(R.id.modulestudy_ll_next_pdf);
        mPre = findViewById(R.id.modulestudy_ll_pre_pdf);
    }

    @Override
    public void regEvent(boolean event) {
        mProgress.setOnSeekBarChangeListener(event ? this : null);
        mNext.setOnClickListener(event ? this : null);
        mPre.setOnClickListener(event ? this : null);
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        mProgress.setMax(pageCount - 1);
        mProgress.setProgress(page);

        mTvProgress.setText(page + 1 + "/" + pageCount);
    }

    public void onInitially(int nbPages) {
        mTvProgress.setText("1/" + nbPages);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.modulestudy_ll_next_pdf) {
            if (mCallback != null) {
                mCallback.onNext();
            }
        } else if (id == R.id.modulestudy_ll_pre_pdf) {
            if (mCallback != null) {
                mCallback.onPre();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser) {
            return;
        }

        mCurrentPage = progress;
        mTvProgress.setText(progress + 1 + "/" + seekBar.getMax());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mCallback != null) {
            mCallback.onPageChanged(mCurrentPage);
        }
    }
}

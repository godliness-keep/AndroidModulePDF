package com.godliness.android.modulepdfdemo.controller;


import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.godliness.android.modulepdf.BasePDFStateBar;
import com.godliness.android.modulepdfdemo.R;


/**
 * Created by godliness on 2020-03-26.
 *
 * @author godliness
 */
public final class DefaultStateBar extends BasePDFStateBar {

    private View mProgress;
    private View mErrorView;
    private TextView mTvInfo;

    private OnStateBarListener mCallback;

    public interface OnStateBarListener {

        /**
         * Called when click retry
         */
        void onRetry();
    }

    @Override
    public int getBarLayoutId() {
        return R.layout.modulepdf_layout_default_state_bar;
    }

    @Override
    public void initBar() {
        mProgress = findViewById(R.id.progress_state_bar);
    }

    @Override
    public void regEvent(boolean event) {

    }

    @Override
    public void onError(int extra) {
        initErrorView();
        if (extra == -1) {
            mTvInfo.setText(getContext().getResources().getText(R.string.modulepdf_string_network_error));
        } else {
            mTvInfo.setText("加载失败了，请尝试重试");
        }

        mErrorView.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void onLoading(boolean loading) {
        mProgress.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (mCallback != null) {
            mCallback.onRetry();
            mErrorView.setVisibility(View.GONE);
            mProgress.setVisibility(View.VISIBLE);
        }
    }

    public void setStateBarListener(OnStateBarListener callback) {
        this.mCallback = callback;
    }

    private void initErrorView() {
        if (mErrorView == null) {
            final ViewStub errorStub = findViewById(R.id.vs_state_bar);
            mErrorView = errorStub.inflate();
            mTvInfo = findViewById(R.id.tv_info_state_bar);
            View retry = findViewById(R.id.tv_retry_state_bar);
            retry.setOnClickListener(this);
        }
    }
}

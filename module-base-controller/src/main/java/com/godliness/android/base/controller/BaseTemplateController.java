package com.godliness.android.base.controller;

import android.support.annotation.Nullable;
import android.view.ViewGroup;

/**
 * Created by godliness on 2020-03-30.
 *
 * @author godliness
 */
public abstract class BaseTemplateController<TitleBar extends BaseControllerBar, BottomBar extends BaseControllerBar, StateBar extends BaseStateBar> extends BaseController {

    private TitleBar mTitleBar;
    private BottomBar mBottomBar;
    private StateBar mStateBar;

    private boolean mShowing;

    protected BaseTemplateController(ViewGroup host) {
        super(host);
    }

    /**
     * Return controller title bar
     *
     * @return {@link BaseControllerBar}
     */
    @Nullable
    protected abstract TitleBar createTitleBar();

    /**
     * Return controller bottom bar
     *
     * @return {@link BaseControllerBar}
     */
    @Nullable
    protected abstract BottomBar createBottomBar();

    /**
     * Return controller state bar
     *
     * @return {@link BaseStateBar}
     */
    @Nullable
    protected abstract StateBar createStateBar();

    /**
     * Initialization template
     */
    protected abstract void initTemplate();

    /**
     * Register related events
     *
     * @param isClick register/unregister
     */
    @Override
    protected abstract void regEvent(boolean isClick);

    /**
     * Return controller layout id
     *
     * @return Layout resource id
     */
    @Override
    protected int getControllerLayoutId() {
        return R.layout.modulecontroller_base_layout;
    }

    public void show() {
        show(true);
    }

    public void hide() {
        hide(true);
    }

    @Override
    public final void show(boolean openAnim) {
        if (mTitleBar != null) {
            mTitleBar.show(openAnim);
        }
        if (mBottomBar != null) {
            mBottomBar.show(openAnim);
        }
        this.mShowing = true;
    }

    @Override
    public final void hide(boolean openAnim) {
        if (mTitleBar != null) {
            mTitleBar.hide(openAnim);
        }
        if (mBottomBar != null) {
            mBottomBar.hide(openAnim);
        }
        this.mShowing = false;
    }

    @Nullable
    protected final StateBar getStateBar() {
        return mStateBar;
    }

    @Nullable
    protected final TitleBar getTitleBar() {
        return mTitleBar;
    }

    @Nullable
    protected final BottomBar getBottomBar() {
        return mBottomBar;
    }

    @Override
    public final boolean isShowing() {
        return mShowing;
    }

    @Override
    protected final void initController() {
        initTitleBar();
        initBottomBar();
        initStateBar();
        initTemplate();
    }

    @Override
    public void release() {
        super.release();
        if (mTitleBar != null) {
            mTitleBar.release();
        }
        if (mBottomBar != null) {
            mBottomBar.release();
        }
        if (mStateBar != null) {
            mStateBar.release();
        }
    }

    private void initStateBar() {
        if (mStateBar == null) {
            mStateBar = createStateBar();
        }
        if (mStateBar != null) {
            mStateBar.attachBarToController(getControllerView());
        }
    }

    private void initTitleBar() {
        if (mTitleBar == null) {
            mTitleBar = createTitleBar();
        }
        if (mTitleBar != null) {
            mTitleBar.attachBarToController(getControllerView());
        }
    }

    private void initBottomBar() {
        if (mBottomBar == null) {
            mBottomBar = createBottomBar();
        }
        if (mBottomBar != null) {
            mBottomBar.attachBarToController(getControllerView());
        }
    }
}

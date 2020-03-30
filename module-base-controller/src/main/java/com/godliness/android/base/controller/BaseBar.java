package com.godliness.android.base.controller;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by godliness on 2020-03-26.
 *
 * @author godliness
 */
public abstract class BaseBar {

    private ViewGroup mParent;
    private View mBar;

    /**
     * Return layout id of current bar
     *
     * @return layout id
     */
    protected abstract int getBarLayoutId();

    /**
     * Initializes current bar resource
     */
    protected abstract void initBar();

    /**
     * Register event
     *
     * @param event state
     */
    public abstract void regEvent(boolean event);

    /**
     * @see LayoutInflater#inflate(int, ViewGroup, boolean)
     */
    protected boolean attachToRoot() {
        return false;
    }

    /**
     * Destroy lifecycle of bar
     */
    protected void release() {
        regEvent(false);
    }

    protected final Context getContext() {
        return mParent.getContext();
    }

    protected final Resources getResources() {
        return getContext().getResources();
    }

    protected final View getBarView() {
        return mBar;
    }

    protected final <T extends View> T findViewById(@IdRes int id) {
        return mBar.findViewById(id);
    }

    protected final void attachToController() {
        final ViewParent parent = mBar.getParent();
        if (parent == null) {
            mParent.addView(mBar);
        }
    }

    protected final void detachFromController() {
        final ViewParent parent = mBar.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(mBar);
        }
    }

    final void attachBarToController(ViewGroup parent) {
        this.mParent = parent;
        final boolean attachToRoot = attachToRoot();
        this.mBar = LayoutInflater.from(parent.getContext()).inflate(getBarLayoutId(), parent, attachToRoot);
        initBar();
        regEvent(true);
        if (!attachToRoot) {
            attachToController();
        }
    }
}

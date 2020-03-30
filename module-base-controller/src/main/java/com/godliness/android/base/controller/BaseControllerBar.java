package com.godliness.android.base.controller;

import android.view.View;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by godliness on 2020-03-26.
 *
 * @author godliness
 */
public abstract class BaseControllerBar extends BaseBar {

    private Animation mShowAnimation;
    private Animation mHideAnimation;
    private View.OnAttachStateChangeListener mStateChangeCallback;
    private Animation.AnimationListener mHideAnimationCallback;

    /**
     * Show bar
     */
    protected void show(boolean openAnimation) {
        if (openAnimation) {
            if (mStateChangeCallback == null) {
                getBarView().addOnAttachStateChangeListener(getStateChangeCallback());
            }
        }
        attachToController();
    }

    /**
     * Hide bar
     */
    protected void hide(boolean openAnimation) {
        if (openAnimation) {
            final ViewParent parent = getBarView().getParent();
            if (parent != null) {
                getBarView().startAnimation(getHideAnimation());
            }
        } else {
            detachFromController();
        }
    }

    /**
     * Return animation when show bar
     */
    protected Animation createShowAnimation() {
        final Animation showAnimation = new AlphaAnimation(1, 1);
        showAnimation.setDuration(300);
        return showAnimation;
    }

    /**
     * Return animation when hide bar
     */
    protected Animation createHideAnimation() {
        final Animation hideAnimation = new AlphaAnimation(1, 0);
        hideAnimation.setDuration(300);
        return hideAnimation;
    }

    @Override
    protected void release() {
        super.release();
        if (mStateChangeCallback != null) {
            getBarView().removeOnAttachStateChangeListener(mStateChangeCallback);
        }
    }

    private Animation getShowAnimation() {
        if (mShowAnimation == null) {
            mShowAnimation = createShowAnimation();
        }
        return mShowAnimation;
    }

    private Animation getHideAnimation() {
        if (mHideAnimation == null) {
            mHideAnimation = createHideAnimation();
            mHideAnimation.setAnimationListener(getHideAnimationCallback());
        }
        return mHideAnimation;
    }

    private View.OnAttachStateChangeListener getStateChangeCallback() {
        if (mStateChangeCallback == null) {
            mStateChangeCallback = new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View view) {
                    view.startAnimation(getShowAnimation());
                }

                @Override
                public void onViewDetachedFromWindow(View v) { }
            };
        }
        return mStateChangeCallback;
    }

    private Animation.AnimationListener getHideAnimationCallback() {
        if (mHideAnimationCallback == null) {
            mHideAnimationCallback = new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {
                    detachFromController();
                }

                @Override
                public void onAnimationRepeat(Animation animation) { }
            };
        }
        return mHideAnimationCallback;
    }
}

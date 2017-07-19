package com.fourway.cameraexam.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by 4 way on 29-06-2017.
 */

public class SlidingRelativeLayout extends SlidingUpPanelLayout {

    private View mDragView;

    public SlidingRelativeLayout(Context context) {
        super(context);
    }

    public SlidingRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setDragView(View dragView) {
        super.setDragView(dragView);
        if (dragView != null) {
            mDragView = dragView;
            mDragView.setOnClickListener(null);
            mDragView.setClickable(false);
        }
    }

    public void setDragViewClickable (boolean clickable) {
        if (mDragView != null) {
            mDragView.setClickable(clickable);
        }
    }
}

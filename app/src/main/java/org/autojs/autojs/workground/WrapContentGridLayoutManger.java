package org.autojs.autojs.workground;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WrapContentGridLayoutManger extends GridLayoutManager {

    private String mDebugInfo;

    public WrapContentGridLayoutManger(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public WrapContentGridLayoutManger(Context context, int spanCount) {
        super(context, spanCount);
    }

    public WrapContentGridLayoutManger(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    public void setDebugInfo(String debugInfo) {
        mDebugInfo = debugInfo;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {}
    }
}

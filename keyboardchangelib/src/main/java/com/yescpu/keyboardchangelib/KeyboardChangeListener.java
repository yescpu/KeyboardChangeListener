package com.yescpu.keyboardchangelib;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

/**
 * simple and powerful Keyboard show/hidden listener,view {@android.R.id.content} and {@ViewTreeObserver.OnGlobalLayoutListener}
 * Created by yes.cpu@gmail.com 2016/7/13.
 */
public class KeyboardChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG = "KeyboardChangeListener";
    public static final int MIN_KEYBOARD_HEIGHT = 300;
    private KeyboardListener mKeyboardListener;
    private boolean mShowFlag = false;
    private Window mWindow;
    private View mContentView;

    public interface KeyboardListener {
        /**
         * call back
         * @param isShow         true is show else hidden
         * @param keyboardHeight keyboard height
         */
        void onKeyboardChange(boolean isShow, int keyboardHeight);
    }

    public void setKeyboardListener(KeyboardListener keyboardListener) {
        this.mKeyboardListener = keyboardListener;
    }

    public static KeyboardChangeListener create(Activity activity){
        return new KeyboardChangeListener(activity);
    }

    public static KeyboardChangeListener create(Dialog dialog){
        return new KeyboardChangeListener(dialog);
    }

    private KeyboardChangeListener(Object contextObj) {
        if (contextObj == null) {
            Log.d(TAG, "contextObj is null");
            return;
        }
        if (contextObj instanceof Activity) {
            mContentView = findContentView((Activity) contextObj);
            mWindow = ((Activity) contextObj).getWindow();
        } else if (contextObj instanceof Dialog) {
            mContentView = findContentView((Dialog) contextObj);
            mWindow = ((Dialog) contextObj).getWindow();
        }
        if (mContentView != null && mWindow != null) {
            addContentTreeObserver();
        }

    }

    private View findContentView(Activity contextObj) {
        return contextObj.findViewById(android.R.id.content);
    }

    private View findContentView(Dialog contextObj) {
        return contextObj.findViewById(android.R.id.content);
    }

    private void addContentTreeObserver() {
        mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }


    @Override
    public void onGlobalLayout() {
        if (mContentView == null || mWindow == null) {
            return;
        }
        int currentViewHeight = mContentView.getHeight();
        if (currentViewHeight == 0) {
            Log.d(TAG, "currHeight is 0");
            return;
        }
        int screenHeight = getScreenHeight();
        int windowBottom;
        int keyboardHeight;

        Rect rect = new Rect();
        mWindow.getDecorView().getWindowVisibleDisplayFrame(rect);
        windowBottom = rect.bottom;

        keyboardHeight = screenHeight - windowBottom;

        Log.d(TAG, "onGlobalLayout() called " + " screenHeight " + screenHeight + " VisibleDisplayHeight " + windowBottom);

        if (mKeyboardListener != null) {
            boolean currentShow = keyboardHeight > MIN_KEYBOARD_HEIGHT;
            if (mShowFlag != currentShow) {
                mShowFlag = currentShow;
                mKeyboardListener.onKeyboardChange(currentShow, keyboardHeight);
            }
        }
    }

    private int getScreenHeight() {
        Display defaultDisplay = mWindow.getWindowManager().getDefaultDisplay();
        int screenHeight = 0;
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        screenHeight = point.y;
        return screenHeight;
    }


    public void destroy() {
        if (mContentView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }
}
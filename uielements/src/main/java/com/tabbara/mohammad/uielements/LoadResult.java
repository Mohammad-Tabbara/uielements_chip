package com.tabbara.mohammad.uielements;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * Created by Mohammad on 2/14/2018.
 */

public class LoadResult extends RelativeLayout {

    public final String TAG = getClass().getName();
    Drawable successDrawable;
    Drawable failDrawable;

    LayoutInflater mInflater;
    ProgressBar progress;
    ImageView result;
    OnLoadingListener onLoadingListener;

    private boolean pass;

    public LoadResult(Context context) {
        super(context);
        init(context,null);
    }

    public LoadResult(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public LoadResult(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray attributes = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LoadResult,
                0,0);
        try {
            successDrawable = attributes.getDrawable(R.styleable.LoadResult_successDrawable);
            failDrawable = attributes.getDrawable(R.styleable.LoadResult_failDrawable);
        }
        finally {
            attributes.recycle();
        }
        pass = false;
        mInflater = LayoutInflater.from(context);
        View v = mInflater.inflate(R.layout.load_result, this, true);
        progress = v.findViewById(R.id.progress);
        result = v.findViewById(R.id.result);
        reset();
        loading();
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean error) {
        this.pass = error;
    }

    public void reset(){
        progress.animate().alpha(1f).setDuration(400).start();
        result.animate().alpha(0f).setDuration(400).start();
    }
    public void loading(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(onLoadingListener != null) {
                    Looper.prepare();
                    onLoadingListener.onLoad();// Need To set Pass Inside here;
                }else{
                    Log.e(TAG,"No Listener Was implemented");
                }
                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress.animate().alpha(0f).setDuration(400).start();
                        result.animate().alpha(1f).setDuration(400).start();
                        if(pass){
                            if(successDrawable != null) {
                                result.setImageDrawable(successDrawable);
                            }else{
                                result.setImageResource(R.mipmap.checkmark_icon);
                            }
                        }else{
                            if(failDrawable != null) {
                                result.setImageDrawable(failDrawable);
                            }else {
                                result.setImageResource(R.mipmap.error_icon);
                            }
                        }
                    }
                });

            }
        });
    }

    public OnLoadingListener getOnLoadingListener() {
        return onLoadingListener;
    }

    public void setOnLoadingListener(OnLoadingListener onLoadingListener) {
        this.onLoadingListener = onLoadingListener;
    }
}

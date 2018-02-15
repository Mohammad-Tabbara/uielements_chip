package com.tabbara.mohammad.uielements;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * Created by Mohammad on 2/14/2018.
 */

public class LoadResult extends RelativeLayout {

    LayoutInflater mInflater;
    ProgressBar progress;
    ImageView result;

    private boolean pass;

    public LoadResult(Context context) {
        super(context);
        init(context);
    }

    public LoadResult(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadResult(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
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
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress.animate().alpha(0f).setDuration(400).start();
                        result.animate().alpha(1f).setDuration(400).start();
                        if(pass){
                            result.setImageResource(R.mipmap.checkmark_icon);
                        }else{
                            result.setImageResource(R.mipmap.error_icon);
                        }
                    }
                });

            }
        });
    }
}

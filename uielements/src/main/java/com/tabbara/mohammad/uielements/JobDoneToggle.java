package com.tabbara.mohammad.uielements;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import android.view.Gravity;
import android.view.View;
import android.widget.ToggleButton;

/**
 * Created by Mohammad on 2/14/2018.
 */

public class JobDoneToggle extends ToggleButton {
    public JobDoneToggle(Context context) {
        super(context);
        init();
    }

    public JobDoneToggle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JobDoneToggle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setGravity(Gravity.START|Gravity.CENTER_VERTICAL);
        setText(getResources().getString(R.string.incomplete));
        int padding = (int) getResources().getDimension(R.dimen.small_spacing);
        setPadding(padding,padding,padding,padding);
        setBackgroundResource(android.R.color.holo_red_light);
        setTextOff(getResources().getString(R.string.incomplete));
        setTextOn(getResources().getString(R.string.complete));
    }

    @Override
    public void toggle() {
        super.toggle();
        if(isChecked()){
            setBackgroundResource(android.R.color.holo_green_light);
        }else{
            setBackgroundResource(android.R.color.holo_red_light);
        }
    }
}

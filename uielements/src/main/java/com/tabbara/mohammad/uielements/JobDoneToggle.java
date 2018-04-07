package com.tabbara.mohammad.uielements;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import android.view.Gravity;
import android.widget.ToggleButton;

import java.util.HashMap;

/**
 * Created by Mohammad on 2/14/2018.
 */

public class JobDoneToggle extends ToggleButton {
    private static HashMap<Integer,Boolean> on = new HashMap<>();
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
        if(!on.containsKey(getId())){
            on.put(getId(),false);
        }
        setGravity(Gravity.START|Gravity.CENTER_VERTICAL);
        setText(getResources().getString(R.string.incomplete));
        int padding = (int) getResources().getDimension(R.dimen.small_spacing);
        setPadding(padding,padding,padding,padding);
        if(on.get(getId())){
            setBackgroundResource(android.R.color.holo_green_light);
        }else{
            setBackgroundResource(android.R.color.holo_red_light);
        }
        setTextOff(getResources().getString(R.string.incomplete));
        setTextOn(getResources().getString(R.string.complete));
    }

    @Override
    public void toggle() {
        super.toggle();
        if(isChecked()){
            on.put(getId(),true);
            setBackgroundResource(android.R.color.holo_green_light);
        }else{
            on.put(getId(),false);
            setBackgroundResource(android.R.color.holo_red_light);
        }
    }
}

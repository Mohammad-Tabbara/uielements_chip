package com.tabbara.mohammad.chiptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;

import com.tabbara.mohammad.uielements.JobDoneToggle;
import com.tabbara.mohammad.uielements.LoadResult;
import com.tabbara.mohammad.uielements.yahoo.rangeseekbar.RangeSeekBar;

public class MainActivity extends AppCompatActivity {

    private JobDoneToggle jobDoneToggle;
    private RangeSeekBar twoWaySeekBar;
    private LoadResult loadResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadResult = findViewById(R.id.load_result);
        jobDoneToggle = findViewById(R.id.job_done_toggle);
        twoWaySeekBar = findViewById(R.id.two_way_seek_bar);
        loadResult.setPass(jobDoneToggle.isChecked());
        jobDoneToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                loadResult.setPass(b);
                loadResult.reset();
                loadResult.loading();
            }
        });
    }
}

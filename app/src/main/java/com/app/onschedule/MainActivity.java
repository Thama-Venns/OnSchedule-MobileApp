package com.app.onschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button action_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        action_btn = (Button) findViewById(R.id.actions_button);
        action_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickAction();
            }
        });
    }

    public void pickAction() {
        Intent scheduleIntent = new Intent(this, ActionSchedule.class);
        startActivity(scheduleIntent);
    }

}

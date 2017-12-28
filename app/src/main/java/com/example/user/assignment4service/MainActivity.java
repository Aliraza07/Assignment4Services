package com.example.user.assignment4service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    Button startservice;
    Button stopservice;
    TextView percent;
    EditText limit;

    public void onEvent(Event event){
        String str = event.getValue();
        percent.setText(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startservice = findViewById(R.id.btnStartService);
        stopservice = findViewById(R.id.btnStopService);
        percent= findViewById(R.id.tvServiceProgress);
        limit = findViewById(R.id.etLimit);

        final Intent intent = new Intent(MainActivity.this, MyService.class);

        EventBus.getDefault().register(this);
        startservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numb = limit.getText().toString();
                try {
                    int number = Integer.parseInt(numb);
                    intent.putExtra("limit", number);
                    Toast.makeText(MainActivity.this, "Service Started", Toast.LENGTH_SHORT).show();
                    startService(intent);


                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Wrong Limit", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stopservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
            }
        });
    }
}

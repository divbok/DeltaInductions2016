package com.example.user.click;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private static int c = 0;
    private TextView t;
    private LinearLayout col;
    private static int j=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=(TextView)findViewById(R.id.t);
        t.setText(String.valueOf(c));
        col=(LinearLayout)findViewById(R.id.col);
        switch(j)
        {
            case 0:
                col.setBackgroundColor(Color.BLACK);
                break;
            case 1:
                col.setBackgroundColor(Color.YELLOW);
                break;
            case 2:
                col.setBackgroundColor(Color.GRAY);
                break;
            case 3:
                col.setBackgroundColor(Color.RED);
                break;
            case 4:
                col.setBackgroundColor(Color.GREEN);
                break;
            case 5:
                col.setBackgroundColor(Color.MAGENTA);
                break;
        }

    }

    public void hit(View view) {

        c++;
        t.setText(String.valueOf(c));

        if(j>6)
            j=0;
        switch(j)
        {
            case 0:
                col.setBackgroundColor(Color.BLACK);
                break;
            case 1:
                col.setBackgroundColor(Color.YELLOW);
                break;
            case 2:
                col.setBackgroundColor(Color.GRAY);
                break;
            case 3:
                col.setBackgroundColor(Color.RED);
                break;
            case 4:
            col.setBackgroundColor(Color.GREEN);
            break;
            case 5:
            col.setBackgroundColor(Color.MAGENTA);
            break;
        }
        j++;
    }

    public void Reset(View view) {
        c=0;
        t.setText(String.valueOf(c));
        col.setBackgroundColor(Color.BLACK);

    }
}

package com.example.user.delta2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public float posx;
    public float posy;
    public float screenx;
    public float screeny;
    public View circle;
public TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        circle=(View)findViewById(R.id.circle);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenx=metrics.heightPixels;
        screeny=metrics.widthPixels;
        t=(TextView)findViewById(R.id.txt);

    }



    public void micclick(View view) {
        if(view.getId()==R.id.mic)
        {promptSpeechInput();}
    }

    private void promptSpeechInput() {
        Intent i= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"SPEAK PLS");
        try{
        startActivityForResult(i,100);

    }
        catch (ActivityNotFoundException a)
        {
            Toast.makeText(MainActivity.this,"Sorry your device doesnt support",Toast.LENGTH_SHORT).show();
        }
}

    public void onActivityResult(int requestCode, int resultCode, Intent i)
    {
        super.onActivityResult(requestCode,resultCode,i);
        String voicetxt;
        switch (requestCode)
        {case 100:
            if(resultCode==RESULT_OK&&i!=null)
            {
                ArrayList<String> result=i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                voicetxt=result.get(0);
                t.setText(result.get(0));

                if(voicetxt.equals("left"))
                {
                    posx=circle.getX();
                    posx-=10;
                    if(circle.getLeft()<0)
                        circle.setX(posx+10);
                    else
                    circle.setX(posx);

                }
                if(voicetxt.equals("right"))
                {
                    posx=circle.getX();
                    posx+=10;
                    if(posx>screenx)
                        return;
                    else
                    circle.setX(posx);

                }
                if(voicetxt.equals("up"))
                {
                posy=circle.getY();
                posy-=10;
                if(circle.getTop()<0)
                    circle.setY(posy+10);
                else
                circle.setY(posy);
                }
                if(voicetxt.equals("down"))
                {
                posy=circle.getY();
                posy+=10;
                    if(posy>screeny)
                        return;
                    else
                circle.setY(posy);
                }
                if(voicetxt.equals("rectangle"))
                {
                    circle.setBackgroundResource(R.drawable.rectangle);



                }
                if(voicetxt.equals("circle"))
                {circle.setBackgroundResource(R.drawable.circle);

                }
                if(voicetxt.equals("star"))
                {
                   circle.setBackgroundResource(R.drawable.abc_ic_star_black_16dp);
                }



            }
            break;
    }
}}
package com.realwakka.organicpractice;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class IncorrectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incorrect);


        SharedPreferences preferences = getSharedPreferences("ORGANIC",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();




    }

}

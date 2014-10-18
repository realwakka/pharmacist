package com.realwakka.organicpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class OptionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.option_start:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();

                break;
        }
    }

}

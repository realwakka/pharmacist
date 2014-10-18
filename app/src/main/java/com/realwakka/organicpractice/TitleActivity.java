package com.realwakka.organicpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class TitleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

    }


   public void onClick(View v){
       switch(v.getId()){
           case R.id.title_start:
               Intent intent = new Intent(this,OptionActivity.class);
               startActivity(intent);
               break;
           case R.id.title_wrong:

               break;
       }
   }
}

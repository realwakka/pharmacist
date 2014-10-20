package com.realwakka.organicpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;


public class OptionActivity extends Activity {
    RadioGroup mProblemType;
    RadioGroup mOrderType;
    DataManager mDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        mProblemType = (RadioGroup) findViewById(R.id.option_problem);
        mOrderType = (RadioGroup) findViewById(R.id.option_order);
        mDataManager = new DataManager(this);
    }
    private boolean checkList(){
        if(mProblemType.getCheckedRadioButtonId()==R.id.option_problem_incorrect && mDataManager.getIncorrectList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.option_start:
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("PROBLEM_TYPE",mProblemType.getCheckedRadioButtonId());
                intent.putExtra("ORDER_TYPE",mOrderType.getCheckedRadioButtonId());

                if(checkList()){
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(this,"오답노트가 없습니다",Toast.LENGTH_LONG).show();
                }



                break;
        }
    }

}

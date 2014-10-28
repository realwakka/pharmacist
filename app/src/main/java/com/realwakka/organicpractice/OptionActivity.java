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
    RadioGroup mProblemGroup;

    IncorrectDataSource mDataSource;
    DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        mProblemType = (RadioGroup) findViewById(R.id.option_problem);
        mOrderType = (RadioGroup) findViewById(R.id.option_order);
        mProblemGroup = (RadioGroup) findViewById(R.id.option_group);


        try{
            mDataSource = new IncorrectDataSource(this);
            mDataSource.open();
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    private int getIndexOfGroup(RadioGroup radioButtonGroup){
        int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
        View radioButton = radioButtonGroup.findViewById(radioButtonID);
        int idx = radioButtonGroup.indexOfChild(radioButton);
        return idx;
    }

    private boolean checkList(){
        if(mProblemType.getCheckedRadioButtonId()==R.id.option_problem_incorrect
                && mDataSource.getIncorrectByGroup(getIndexOfGroup(mProblemGroup)).isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    private PracticeOption getPracticeOption(){
        PracticeOption option = new PracticeOption(getIndexOfGroup(mProblemGroup)
                ,getIndexOfGroup(mOrderType)==1,getIndexOfGroup(mProblemGroup)==1);
        return option;
    }



    public void onClick(View view){
        switch(view.getId()){
            case R.id.option_start:
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("PRACTICE_OPTION",getPracticeOption());

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

package com.realwakka.organicpractice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private final String JSON_KEY="PRACTICE_OPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        mProblemType = (RadioGroup) findViewById(R.id.option_problem);
        mOrderType = (RadioGroup) findViewById(R.id.option_order);
        mProblemGroup = (RadioGroup) findViewById(R.id.option_group);

        loadOption();

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
                ,getIndexOfGroup(mOrderType)==1,getIndexOfGroup(mProblemType)==1);
        return option;
    }

    private void saveOption(PracticeOption option){
        SharedPreferences pref = getSharedPreferences("PHARMACIST",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(JSON_KEY,option.toJson());
        editor.commit();
    }

    private void loadOption(){
        SharedPreferences pref = getSharedPreferences("PHARMACIST",MODE_PRIVATE);

        String json = pref.getString(JSON_KEY,null);

        if(json!=null){
            PracticeOption option = PracticeOption.fromJson(json);

            mapOption(option);
        }
    }

    private void mapOption(PracticeOption option){
        switch(option.getProblem_group()){
            case 0:
                mProblemGroup.check(R.id.option_group_organic);
                break;
            case 1:
                mProblemGroup.check(R.id.option_group_sang);
                break;
        }
        if(option.isShuffle()){
            mOrderType.check(R.id.option_order_shuffle);
        }else{
            mOrderType.check(R.id.option_order_default);
        }

        if(option.isIncorrect_list()){
            mProblemType.check(R.id.option_problem_incorrect);
        }else{
            mProblemType.check(R.id.option_problem_default);
        }
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

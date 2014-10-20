package com.realwakka.organicpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ImageView mImageView;
    private CheckBox mIncorrectBox;
    private enum STATE {PROBLEM,ANSWER}
    private STATE mSTATE=STATE.PROBLEM;
    private int mCurrentNumber;
    private QuizGenerator mGenerator;
    private DataManager mDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.main_image);
        mIncorrectBox = (CheckBox) findViewById(R.id.main_incorrect);

        mDataManager = new DataManager(this);

        Intent intent = getIntent();

        boolean shuffle = false,default_problem=true;

        if(intent.getIntExtra("ORDER_TYPE",1)==R.id.option_order_default){
            shuffle = false;
        }else{
            shuffle = true;
        }

        if(intent.getIntExtra("PROBLEM_TYPE",1)==R.id.option_problem_default){
            default_problem = true;
        }else{
            default_problem = false;
        }

        mGenerator = new QuizGenerator(this,shuffle,default_problem);

        mCurrentNumber = mGenerator.getNextNumber();
        setProblemNum(mCurrentNumber);

    }
    private void setProblemNum(int n){
        int resId = getResources().getIdentifier("problem_"+n, "drawable", getPackageName());
        mImageView.setImageResource(resId);

        mIncorrectBox.setChecked(mDataManager.isContains(mCurrentNumber));
    }

    private void setAnswerNum(int n){
        int resId = getResources().getIdentifier("answer_"+n, "drawable", getPackageName());
        mImageView.setImageResource(resId);

    }

    private void clickImage(){
        switch(mSTATE){
            case PROBLEM:
                setAnswerNum(mCurrentNumber);
                mSTATE = STATE.ANSWER;
                break;
            case ANSWER:
                if(mGenerator.isLast()) {
                    Toast.makeText(this,"마지막 문제입니다",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    mCurrentNumber = mGenerator.getNextNumber();
                    setProblemNum(mCurrentNumber);
                    mSTATE = STATE.PROBLEM;
                }
                break;
        }
    }
    private void clickIncorrect(){
        if(mIncorrectBox.isChecked()){
            Toast.makeText(this,"추가되었습니다",Toast.LENGTH_SHORT).show();
            mDataManager.addIncorrect(mCurrentNumber);
        }else{
            Toast.makeText(this,"제거되었습니다",Toast.LENGTH_SHORT).show();
            mDataManager.removeIncorrect(mCurrentNumber);
        }

    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.main_image:
                clickImage();
                break;
            case R.id.main_incorrect:
                clickIncorrect();
                break;
        }
    }
}

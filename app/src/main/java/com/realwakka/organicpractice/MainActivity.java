package com.realwakka.organicpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity {
    private ImageView mImageView;
    private enum STATE {PROBLEM,ANSWER}
    private STATE mSTATE=STATE.PROBLEM;
    private int mCurrentNumber;
    private QuizGenerator mGenerator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.main_image);

        mGenerator = new QuizGenerator(false);
        mCurrentNumber = mGenerator.getNextNumber();
        setProblemNum(mCurrentNumber);

    }
    private void setProblemNum(int n){
        int resId = getResources().getIdentifier("problem_"+n, "drawable", getPackageName());
        mImageView.setImageResource(resId);
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

                mCurrentNumber = mGenerator.getNextNumber();
                setProblemNum(mCurrentNumber);
                mSTATE = STATE.PROBLEM;
                break;
        }


    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.main_image:
                clickImage();
                break;
        }
    }

}

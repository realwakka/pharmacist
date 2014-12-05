package com.realwakka.organicpractice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.realwakka.organicpractice.data.ProblemInfoProvider;
import com.realwakka.organicpractice.data.ProblemSmallGroup;

import java.util.ArrayList;


public class OptionActivity extends Activity {
    RadioGroup mProblemType;
    RadioGroup mOrderType;
    RadioGroup mProblemGroup;
    Spinner mProblemSmallGroup;
    IncorrectDataSource mDataSource;

    ProblemInfoProvider mProblemInfoProvider;
    ArrayList<ProblemSmallGroup> mSmallList;
    private final String JSON_KEY="PRACTICE_OPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        mProblemType = (RadioGroup) findViewById(R.id.option_problem);
        mOrderType = (RadioGroup) findViewById(R.id.option_order);
        mProblemGroup = (RadioGroup) findViewById(R.id.option_group);
        mProblemSmallGroup = (Spinner) findViewById(R.id.option_small);
        mSmallList = new ArrayList<ProblemSmallGroup>();
        ArrayAdapter<ProblemSmallGroup> adapter = new ArrayAdapter<ProblemSmallGroup>(this,android.R.layout.simple_dropdown_item_1line,mSmallList);

        mProblemSmallGroup.setAdapter(adapter);


        mProblemInfoProvider = new ProblemInfoProvider(this);

        loadOption();

        try{
            mDataSource = new IncorrectDataSource(this);
            mDataSource.open();
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        saveOption(getPracticeOption());
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
                ,getIndexOfGroup(mOrderType)==1,getIndexOfGroup(mProblemType)==1,mProblemSmallGroup.getSelectedItemPosition());
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

        ArrayList<ProblemSmallGroup> list = mProblemInfoProvider.getProblemGroups().get(option.getProblem_group()).getSmallGroups();
        mSmallList.clear();
        mSmallList.addAll(list);

        ArrayAdapter<ProblemSmallGroup> adapter = (ArrayAdapter<ProblemSmallGroup>)mProblemSmallGroup.getAdapter();
        adapter.notifyDataSetChanged();

        mProblemSmallGroup.setSelection(option.getSmall_group());
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

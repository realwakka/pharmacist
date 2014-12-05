package com.realwakka.organicpractice;

import android.content.Context;
import android.util.Log;

import com.realwakka.organicpractice.data.ProblemGroup;
import com.realwakka.organicpractice.data.ProblemInfoProvider;
import com.realwakka.organicpractice.data.ProblemSmallGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by UCLAB_T60 on 2014-10-10.
 */
public class QuizGenerator {
    private final int TOTAL_QUIZ=79;

    private final int[] QUIZ_CNT= new int[]{79,126};
    private List<Incorrect> QuizList;
    private int CurrentOrder;
    private Context context;

    private PracticeOption mOption;
    private IncorrectDataSource mDataSource;


    public QuizGenerator(PracticeOption option,IncorrectDataSource source){
        mDataSource = source;
        mOption = option;
        QuizList = new ArrayList<Incorrect>();

        int group_no = mOption.getProblem_group();
        int small_no = mOption.getSmall_group();
        Log.d("QuizGenerator","group"+group_no);

        if(mOption.isIncorrect_list()){
            QuizList = source.getIncorrectByGroup(group_no);
            Log.d("QuizGenerator","load incorrect list");

        }else {
            ProblemInfoProvider problemInfoProvider = new ProblemInfoProvider(source.getContext());

            ArrayList<ProblemGroup> groups = problemInfoProvider.getProblemGroups();
            ProblemGroup group = groups.get(group_no);
            ProblemSmallGroup small = group.getSmallGroups().get(small_no);

            for (int i = small.getStart(); i <= small.getEnd(); i++) {
                QuizList.add(new Incorrect(0, group_no * 1000 + i, group_no));
                Log.d("QuizGenerator",group_no * 1000 + i+"");
            }

        }

        if(mOption.isShuffle()){
            shuffleList(QuizList);
        }

        CurrentOrder = -1;

    }

    private void shuffleList(List<Incorrect> list){

        List<Incorrect> tmp = new ArrayList<Incorrect>();
        tmp.addAll(list);

        list.clear();

        Random rand = new Random();
        while(!tmp.isEmpty()){
            list.add(tmp.remove(rand.nextInt(tmp.size())));
        }
    }



    public int getNextNumber(){
        if(CurrentOrder==QuizList.size()-1){
            return -1;
        }else {
            CurrentOrder++;
            return QuizList.get(CurrentOrder).getProblem();
        }
    }

    public int getCurrentOrder(){
        return CurrentOrder;
    }

    public boolean isBegin(){
        if(CurrentOrder==0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isLast(){
        if(CurrentOrder==QuizList.size()-1){
            return true;
        }else{
            return false;
        }
    }

}

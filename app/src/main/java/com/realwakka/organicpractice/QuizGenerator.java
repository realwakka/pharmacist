package com.realwakka.organicpractice;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by UCLAB_T60 on 2014-10-10.
 */
public class QuizGenerator {
    private final int TOTAL_QUIZ=79;

    private final int[] QUIZ_CNT= new int[]{79,126};
    private ArrayList<Integer> QuizList;
    private int CurrentOrder;
    private Context context;

    private PracticeOption mOption;


    public QuizGenerator(PracticeOption option){

        mOption = option;
        QuizList = new ArrayList<Integer>();

        int count = QUIZ_CNT[mOption.getProblem_group()];

        for(int i=1;i<=count;i++)
            QuizList.add(i);




    }
    private void shuffleList(List<Integer> list){

        List<Integer> tmp = new ArrayList<Integer>();
        tmp.addAll(list);

        list.clear();

        Random rand = new Random();
        while(!tmp.isEmpty()){
            list.add(tmp.remove(rand.nextInt(tmp.size())));
        }
    }
    public QuizGenerator(Context context, boolean shuffle,boolean default_problem){
        this.context = context;
        QuizList = new ArrayList<Integer>();
        ArrayList<Integer> list = null;

        if(default_problem){
            list = new ArrayList<Integer>();
            for(int i=1;i<=TOTAL_QUIZ;i++){
                list.add(i);
            }
        }else{
            DataManager manager = new DataManager(context);
            list = manager.getIncorrectList();
        }

        if(shuffle){
            Random rand = new Random();
            while(list.size()!=0){
                QuizList.add(list.remove(rand.nextInt(list.size())));
            }
        }else{
            QuizList.addAll(list);
        }

        CurrentOrder=-1;
    }
    public QuizGenerator(boolean shuffle){
        this(null,shuffle);
    }
    public QuizGenerator(ArrayList<Integer> list,boolean shuffle){
        QuizList = new ArrayList<Integer>();

        if(list==null){
            list = new ArrayList<Integer>();
            for(int i=1;i<=TOTAL_QUIZ;i++){
                list.add(i);
            }
        }

        if(shuffle){
            Random rand = new Random();
            while(list.size()!=0){
                QuizList.add(list.remove(rand.nextInt(list.size())));
            }
        }else{
            QuizList.addAll(list);
        }

        CurrentOrder=-1;
    }


    public int getNextNumber(){
        if(CurrentOrder==QuizList.size()-1){
            return -1;
        }else {
            CurrentOrder++;
            return QuizList.get(CurrentOrder);
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

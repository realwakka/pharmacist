package com.realwakka.organicpractice;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by UCLAB_T60 on 2014-10-10.
 */
public class QuizGenerator {
    private final int TOTAL_QUIZ=79;
    private ArrayList<Integer> QuizList;
    private int CurrentOrder;
    public QuizGenerator(boolean shuffle){
        this(null,shuffle);
    }
    public QuizGenerator(ArrayList<Integer> list,boolean shuffle){
        QuizList = new ArrayList<Integer>();

        if(list==null){
            list = new ArrayList<Integer>();
            for(int i=1;i<=79;i++){
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

}

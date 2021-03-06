package com.realwakka.organicpractice;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by UCLAB_T60 on 2014-10-19.
 */
public class PracticeOption implements Serializable{





    private int problem_group;

    private boolean shuffle;
    private boolean incorrect_list;
    private int small_group;

    public PracticeOption(int problem_group, boolean shuffle, boolean incorrect_list, int small_group) {
        this.problem_group = problem_group;
        this.shuffle = shuffle;
        this.incorrect_list = incorrect_list;
        this.small_group = small_group;
    }


    public int getSmall_group() {
        return small_group;
    }

    public void setSmall_group(int small_group) {
        this.small_group = small_group;
    }

    public int getProblem_group() {
        return problem_group;
    }

    public void setProblem_group(int problem_group) {
        this.problem_group = problem_group;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public boolean isIncorrect_list() {
        return incorrect_list;
    }

    public void setIncorrect_list(boolean incorrect_list) {
        this.incorrect_list = incorrect_list;
    }


    public String toJson(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("PROBLEM_GROUP", problem_group);
            obj.put("PROBLEM_SHUFFLE",shuffle);
            obj.put("INCORRECT_LIST",incorrect_list);
            obj.put("SMALL_GROUP",small_group);

        }catch(Exception e){
            e.printStackTrace();
        }
        return obj.toString();
    }

    public static PracticeOption fromJson(String json){
        JSONObject obj = null;
        PracticeOption option = null;
        try{
            obj = new JSONObject(json);
            option = new PracticeOption(obj.getInt("PROBLEM_GROUP"),obj.getBoolean("PROBLEM_SHUFFLE"),obj.getBoolean("INCORRECT_LIST"),obj.getInt("SMALL_GROUP"));

        }catch(Exception e){
            e.printStackTrace();
        }
        return option;

    }

}

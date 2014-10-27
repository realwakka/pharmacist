package com.realwakka.organicpractice;

import java.io.Serializable;

/**
 * Created by UCLAB_T60 on 2014-10-19.
 */
public class PracticeOption implements Serializable{
    private int problem_group;
    private boolean shuffle;
    private boolean incorrect_list;

    public PracticeOption(int problem_group, boolean shuffle, boolean incorrect_list) {
        this.problem_group = problem_group;
        this.shuffle = shuffle;
        this.incorrect_list = incorrect_list;

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
}

package com.realwakka.organicpractice;

/**
 * Created by realwakka on 14. 10. 27.
 */
public class Incorrect {

    private long id;
    private int problem;
    private int group;


    public Incorrect(long id, int problem, int group) {
        this.id = id;
        this.problem = problem;
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProblem() {
        return problem;
    }

    public void setProblem(int problem) {
        this.problem = problem;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}

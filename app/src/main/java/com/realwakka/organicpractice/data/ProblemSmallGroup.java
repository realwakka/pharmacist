package com.realwakka.organicpractice.data;

/**
 * Created by realwakka on 14. 12. 1.
 */
public class ProblemSmallGroup {

    private String name;
    private int start;
    private int end;


    public ProblemSmallGroup(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return name;
    }
}

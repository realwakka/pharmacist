package com.realwakka.organicpractice.data;

import java.util.ArrayList;

/**
 * Created by realwakka on 14. 12. 1.
 */
public class ProblemGroup {
    private String name;
    private ArrayList<ProblemSmallGroup> smallGroups;
    private int count;

    public ProblemGroup(String name, ArrayList<ProblemSmallGroup> smallGroups, int count) {
        this.name = name;
        this.smallGroups = smallGroups;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ProblemSmallGroup> getSmallGroups() {
        return smallGroups;
    }

    public void setSmallGroups(ArrayList<ProblemSmallGroup> smallGroups) {
        this.smallGroups = smallGroups;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

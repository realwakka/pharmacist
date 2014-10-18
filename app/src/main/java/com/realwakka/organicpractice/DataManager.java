package com.realwakka.organicpractice;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by realwakka on 14. 10. 16.
 */
public class DataManager {
    Context mContext;
    public DataManager(Context context){
       mContext = context;
    }


    public ArrayList<Integer> getIncorrectList(){
        SharedPreferences pref = mContext.getSharedPreferences("Organic", Context.MODE_PRIVATE);

        String ilist = pref.getString("INCORRECT_LIST",null);

        if(ilist!=null){
            return getArrayFromString(ilist);
        }else{
            return null;
        }

    }

    private ArrayList<Integer> getArrayFromString(String str){
        ArrayList<Integer> list = new ArrayList<Integer>();
        StringTokenizer tokenizer = new StringTokenizer(str);

        while(tokenizer.hasMoreTokens()){
            list.add(Integer.parseInt(tokenizer.nextToken()));
        }

        return list;
    }

    private String getStringFromArray(ArrayList<Integer> list){
        String ret="";
        for(Integer i : list){
            ret = ret+" "+i;
        }
        return ret;
    }



}

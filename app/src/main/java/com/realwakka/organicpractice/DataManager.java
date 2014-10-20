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

        SharedPreferences pref = mContext.getSharedPreferences("Organic", Context.MODE_PRIVATE);
        if(!pref.contains("INCORRECT_LIST")){
            pref.edit().putString("INCORRECT_LIST","").commit();
        }
    }

    public void removeIncorrect(int p) {
        ArrayList<Integer> list = getIncorrectList();
        list.remove(new Integer(p));
        setIncorrectList(list);
    }

    public void clearIncorrectList(){
        SharedPreferences pref = mContext.getSharedPreferences("Organic", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("INCORRECT_LIST","");
        editor.commit();
    }

    public boolean isContains(int i){
        return getIncorrectList().contains(new Integer(i));
    }

    public ArrayList<Integer> getIncorrectList(){
        SharedPreferences pref = mContext.getSharedPreferences("Organic", Context.MODE_PRIVATE);
        String list = pref.getString("INCORRECT_LIST",null);

        if(list!=null){
            return getArrayFromString(list);
        }else{
            return null;
        }
    }

    public void addIncorrect(int problem){
        ArrayList<Integer> list = getIncorrectList();
        list.add(problem);
        setIncorrectList(list);
    }

    public void setIncorrectList(ArrayList<Integer> list){
        SharedPreferences pref = mContext.getSharedPreferences("Organic", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        String str = getStringFromArray(list);
        editor.putString("INCORRECT_LIST",str);
        editor.commit();
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

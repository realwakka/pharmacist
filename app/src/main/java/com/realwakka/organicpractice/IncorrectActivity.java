package com.realwakka.organicpractice;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class IncorrectActivity extends Activity {
    ListView mListView;
    DataManager mDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incorrect);

        mListView = (ListView)findViewById(R.id.incorrect_list);
        mDataManager = new DataManager(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        loadIncorrectList();

    }
    private void loadIncorrectList(){
        IncorrectAdapter adapter = new IncorrectAdapter(this,mDataManager.getIncorrectList());
        mListView.setAdapter(adapter);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.incorrect_remove:
                mDataManager.clearIncorrectList();
                loadIncorrectList();
                break;
        }
    }
    
    private class IncorrectAdapter extends BaseAdapter{
        ArrayList<Integer> list;
        Context context;
        public IncorrectAdapter(Context context,ArrayList<Integer> list){
            this.context = context;
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return list.get(i);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
                Log.d("IncorrectActivity","not null");
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_incorrect, viewGroup, false);

                ImageView answer = (ImageView) v.findViewById(R.id.incorrect_answer);
                ImageView problem = (ImageView) v.findViewById(R.id.incorrect_problem);

                int p = list.get(i);
                int problem_resId = getResources().getIdentifier("problem_"+p, "drawable", getPackageName());
                problem.setImageResource(problem_resId);

                int answer_resId = getResources().getIdentifier("answer_"+p, "drawable", getPackageName());
                problem.setImageResource(problem_resId);
                answer.setImageResource(answer_resId);

                return v;

        }
    }
}

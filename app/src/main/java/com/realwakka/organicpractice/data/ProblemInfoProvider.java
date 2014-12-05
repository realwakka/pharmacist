package com.realwakka.organicpractice.data;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.realwakka.organicpractice.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

/**
 * Created by realwakka on 14. 12. 1.
 */
public class ProblemInfoProvider {
    private Context context;
    private ArrayList<ProblemGroup> problemGroups;

    public ProblemInfoProvider(Context context) {
        this.context = context;
        try {
            parseXML();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void parseXML() throws Exception{
        problemGroups = new ArrayList<ProblemGroup>();

        Resources resources = context.getResources();
        XmlResourceParser xpp = resources.getXml(R.xml.problem_info);
        int eventType = xpp.getEventType();
        int state = 0;
        xpp.next();


        ProblemGroup group=null;
        while (eventType != XmlResourceParser.END_DOCUMENT)
        {


            if(eventType == XmlResourceParser.START_DOCUMENT)
            {

            }
            else if(eventType == XmlResourceParser.START_TAG)
            {
                if(xpp.getName().equals("group")){
                    String name = xpp.getAttributeValue(null,"name");

                    int count = xpp.getAttributeIntValue(null,"count",0);
                    group = new ProblemGroup(name,new ArrayList<ProblemSmallGroup>(),count);

                }else if(xpp.getName().equals("small")){

                    String name = xpp.getAttributeValue(null,"name");

                    int start = xpp.getAttributeIntValue(null,"start",0);


                    int end = xpp.getAttributeIntValue(null,"end",0);
                    ProblemSmallGroup small = new ProblemSmallGroup(name,start,end);
                    group.getSmallGroups().add(small);
                }
            }
            else if(eventType == XmlResourceParser.END_TAG)
            {
                if(xpp.getName().equals("group")){
                    problemGroups.add(group);
                }
            }
            else if(eventType == XmlResourceParser.TEXT)
            {

            }
            eventType = xpp.next();
        }

    }

    public ArrayList<ProblemGroup> getProblemGroups() {
        return problemGroups;
    }

}

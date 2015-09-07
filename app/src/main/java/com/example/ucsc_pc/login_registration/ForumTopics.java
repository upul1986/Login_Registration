package com.example.ucsc_pc.login_registration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class ForumTopics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_topics);

        RecyclerView recList = (RecyclerView) findViewById(R.id.forumTopicList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        TopicAdapter ca = new TopicAdapter(createList(30));
        recList.setAdapter(ca);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forum_topics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<TopicInfo> createList(int size) {

        List<TopicInfo> result = new ArrayList<TopicInfo>();
        for (int i=1; i <= size; i++) {
            TopicInfo ci = new TopicInfo();
            ci.topic = TopicInfo.TOPIC_PREFIX + i;
            ci.name = TopicInfo.INITIATED_BY_PREFIX + i;
            ci.email = TopicInfo.EMAIL_PREFIX + i + "@test.com";
            ci.replies = TopicInfo.REPLIES_PREFIX + i;
            ci.view = TopicInfo.VIEW_PREFIX + i + " post";

            result.add(ci);

        }

        return result;
    }
}

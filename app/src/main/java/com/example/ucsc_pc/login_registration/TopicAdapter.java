package com.example.ucsc_pc.login_registration;

/**
 * Created by UCSC-PC on 8/20/2015.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private List<TopicInfo> topicList;

    public TopicAdapter(List<TopicInfo> topicList) {
        this.topicList = topicList;
    }


    @Override
    public int getItemCount() {
        return topicList.size();
    }

    @Override
    public void onBindViewHolder(TopicViewHolder topicViewHolder, int i) {
        TopicInfo ci = topicList.get(i);
        topicViewHolder.vTopic.setText(ci.topic);
        topicViewHolder.vName.setText(ci.name);
        topicViewHolder.vEmail.setText(ci.email);
        topicViewHolder.vReplies.setText(ci.replies);
        topicViewHolder.vInside.setText(ci.view);
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.topic_layout, viewGroup, false);

        return new TopicViewHolder(itemView);
    }

    public static class TopicViewHolder extends RecyclerView.ViewHolder {

        protected TextView vTopic;
        protected TextView vName;
        protected TextView vEmail;
        protected TextView vReplies;
        protected Button vInside;

        public TopicViewHolder(View v) {
            super(v);
            vTopic =  (TextView) v.findViewById(R.id.txtTopic);
            vName = (TextView)  v.findViewById(R.id.txtName);
            vEmail = (TextView)  v.findViewById(R.id.txtEmail);
            vReplies = (TextView) v.findViewById(R.id.txtReplies);
            vInside = (Button) v.findViewById(R.id.insideTopic);
        }
    }
}
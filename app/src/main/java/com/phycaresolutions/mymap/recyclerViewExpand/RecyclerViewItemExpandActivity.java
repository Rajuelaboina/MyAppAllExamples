package com.phycaresolutions.mymap.recyclerViewExpand;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.webkit.SafeBrowsingResponse;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phycaresolutions.mymap.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewItemExpandActivity extends AppCompatActivity {
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_item_expand);
        rv=findViewById(R.id.recyclerView5);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // add divider line between rows
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( rv.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.horizontal_divider));
         rv.addItemDecoration(dividerItemDecoration);
         rv.setItemAnimator(new MyItemAnimator());
        ItemAdapter adapter = new ItemAdapter( getListData());
        rv.setAdapter(adapter);

    }

    private List<UserDetails> getListData() {
        List<UserDetails> list = new ArrayList();
        list.add(new UserDetails("Android","1234567890","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));
        list.add(new UserDetails("Java","99999999999","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));
        list.add(new UserDetails("Kotlin","8888888888","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));
        list.add(new UserDetails("Angular","7777777777","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets,"));
        list.add(new UserDetails("Reactive","5555555555","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets,"));
        list.add(new UserDetails("Dot Net","3333333333","In Summary PendingIntents are a powerful mechanism for deferring actions and delegating them to other apps or system services. They are commonly used in notifications, alarms, widgets, and other scenarios where you need to trigger actions at a later time or from another context. I hope this explanation is helpful! Let me know if you have any other questions."));
        return list;
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{
        List<UserDetails> listData;

        public ItemAdapter(List<UserDetails> listData) {
            this.listData = listData;
        }

        @NonNull
       @Override
       public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_expand,parent,false));
       }

       @Override
       public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
           UserDetails details = listData.get(position);
           holder.tv1.setText(details.name);
           holder.tv2.setText(details.mobile);
           holder.tv3.setText(details.description);
           holder.itemView.setOnClickListener(v -> {
               boolean expanded = details.isExpanded();
               details.setExpanded(!expanded);
               notifyItemChanged(position);
           });
          // holder.linearLayout.setVisibility(details ? View.VISIBLE : View.GONE);
           if (details.isExpanded){
               holder.linearLayout.setVisibility(View.VISIBLE);
           }else {
               holder.linearLayout.setVisibility(View.GONE);
           }
       }

       @Override
       public int getItemCount() {
           return listData.size();
       }

       class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv1,tv2,tv3;
            LinearLayout linearLayout;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv1 = itemView.findViewById(R.id.textView11);
                tv2 = itemView.findViewById(R.id.textView12);
                tv3 = itemView.findViewById(R.id.textView13);
                linearLayout = itemView.findViewById(R.id.linearLayoutItem);
            }
        }
    }

    // model class
    private class UserDetails{
        String name;
        String mobile;
        String description;
        boolean isExpanded;

        public UserDetails(String name, String mobile, String description) {
            this.name = name;
            this.mobile = mobile;
            this.description = description;
        }

        public boolean isExpanded() {
            return isExpanded;
        }

        public void setExpanded(boolean expanded) {
            isExpanded = expanded;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private class MyItemAnimator extends DefaultItemAnimator {
        @Override
        public boolean animateAdd(RecyclerView.ViewHolder holder) {
            /*holder.itemView.getAlpha();
            holder.itemView.animate().alpha(1f).setDuration(500).start();
            return true;*/
            holder.itemView.setTranslationY(holder.itemView.getHeight());
            holder.itemView.animate()
                    .translationY(0f)
                    .alpha(1f)
                    .setDuration(500)
                    .setInterpolator(new OvershootInterpolator())
                    .start();
            return true;
        }
    }
}
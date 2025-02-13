package com.phycaresolutions.mymap.ordertrckingnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phycaresolutions.mymap.R;

import java.util.List;

public class TrackingAdapter extends RecyclerView.Adapter<TrackingAdapter.ViewHolder> {

    private List<OrderStatus> statusList;
   Context context;

    public TrackingAdapter(List<OrderStatus> statusList) {
        this.statusList = statusList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tracking_step, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderStatus status = statusList.get(position);
        holder.statusText.setText(status.getStatus());
        Animation animation;
       /* if (status.isCompleted()) {
            holder.statusIcon.setImageResource(R.drawable.check_circle_24); // Use a checkmark icon
        } else {
            holder.statusIcon.setImageResource(R.drawable.circle_24); // Use a pending icon
        }*/
        // Change circle color based on status
        if (status.isCompleted()) {
            holder.statusCircle.setBackgroundResource(R.drawable.check_circle_24);
            holder.verticalLine.setBackgroundColor(context.getResources().getColor(R.color.orderText));
            animation = AnimationUtils.loadAnimation(context,R.anim.blink);
          //  holder.statusCircle.startAnimation(animation);
            AlphaAnimation blinkAnimation = new AlphaAnimation(1.0f, 0.3f); // Fade from 100% to 30%
            blinkAnimation.setDuration(500); // Duration for each fade cycle
            blinkAnimation.setRepeatMode(Animation.REVERSE); // Reverse animation (fade in and out)
            blinkAnimation.setRepeatCount(10); // Infinite looping
            holder.statusCircle.startAnimation(blinkAnimation);
          //  holder.verticalLine.startAnimation(animation);

         /*   TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,holder.verticalLine.getHeight());
            translateAnimation.setDuration(500);
            translateAnimation.setFillAfter(true);
            holder.verticalLine.startAnimation(translateAnimation);*/
            if (position == statusList.size() - 1) {

                //holder.statusCircle.clearAnimation();
                //holder.statusCircle.setAnimation(null);
            }

        } else {
            holder.statusCircle.setBackgroundResource(R.drawable.circle_outline2);
        }
        // ✅ Corrected: Hide vertical line for last item
        if (position == statusList.size() - 1) {
            holder.verticalLine.setVisibility(View.INVISIBLE); // Use INVISIBLE instead of GONE to maintain alignment
        } else {
            holder.verticalLine.setVisibility(View.VISIBLE);
           // holder.verticalLine.startAnimation(animation);
           // holder.statusCircle.startAnimation(animation);
        }



        // Hide vertical line for last item
      // holder.verticalLine.setVisibility(position == statusList.size() - 1 ? View.INVISIBLE : View.VISIBLE);


    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView statusText;
        ImageView statusIcon;
        View verticalLine,statusCircle;
        public ViewHolder(View itemView) {
            super(itemView);
            statusText = itemView.findViewById(R.id.status_text);
           // statusIcon = itemView.findViewById(R.id.status_icon);
            verticalLine = itemView.findViewById(R.id.vertical_line);
            statusCircle = itemView.findViewById(R.id.status_circle);

        }
    }
}


/*import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrackingAdapter extends RecyclerView.Adapter<TrackingAdapter.ViewHolder> {
    private List<TrackingStep> trackingSteps;

    public TrackingAdapter(List<TrackingStep> trackingSteps) {
        this.trackingSteps = trackingSteps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tracking_step, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrackingStep step = trackingSteps.get(position);
        holder.statusTextView.setText(step.getStatus());

        if (step.isCompleted()) {
            holder.statusIcon.setImageResource(R.drawable.check_circle_24);
            holder.divider.setVisibility(View.VISIBLE);

            // Use a tick/check icon
        } else {
            holder.statusIcon.setImageResource(R.drawable.circle_24); // Use a gray dot or uncheck icon
            holder.divider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return trackingSteps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView statusTextView;
        ImageView statusIcon;
        View divider;

        public ViewHolder(View itemView) {
            super(itemView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            statusIcon = itemView.findViewById(R.id.statusIcon);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}*/

package com.phycaresolutions.mymap.odertracking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phycaresolutions.mymap.R;

import java.util.List;

public class OrderTrackingAdapter extends RecyclerView.Adapter<OrderTrackingAdapter.ViewHolder> {

    private List<OrderStep> orderSteps;
    private Context context;

    public OrderTrackingAdapter(Context context, List<OrderStep> orderSteps) {
        this.context = context;
        this.orderSteps = orderSteps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_status, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderStep step = orderSteps.get(position);

        holder.statusText.setText(step.getStatus());
        //holder.statusTimestamp.setText(step.getTimestamp());

        // Show completed/pending icons
        if (step.isCompleted()) {
            holder.statusIcon.setBackgroundResource(R.drawable.check_circle_24);  // Green checkmark
            holder.verticalLine.setBackgroundColor(context.getResources().getColor(R.color.orderText));
        } else {
            holder.statusIcon.setBackgroundResource(R.drawable.circle_outline2);  // Gray dot
           // holder.verticalLine.setBackgroundColor(context.getResources().getColor(R.color.bg));
        }

        // Hide vertical line for the last item
        if (position == orderSteps.size() - 1) {
            holder.verticalLine.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return orderSteps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View statusIcon;
        TextView statusText, statusTimestamp;
        View verticalLine;

        public ViewHolder(View itemView) {
            super(itemView);
            statusIcon = itemView.findViewById(R.id.status_circle);
            statusText = itemView.findViewById(R.id.status_text);
            //statusTimestamp = itemView.findViewById(R.id.status_timestamp);
            verticalLine = itemView.findViewById(R.id.vertical_line);
        }
    }
}


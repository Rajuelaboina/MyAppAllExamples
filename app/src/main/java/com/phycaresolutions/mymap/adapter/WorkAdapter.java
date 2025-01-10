package com.phycaresolutions.mymap.adapter;

import android.content.Context;

public class WorkAdapter /*extends RecyclerView.Adapter<WorkAdapter.MyViewHolder>*/ {
    Context mContext;
    /*List<WorkActivity.ItemDetails> list;
    int qty =0;
    *//*public WorkAdapter(Context mContext, List<WorkActivity.ItemDetails> list) {
     this.mContext = mContext;
     this.list = list;
    }*//*

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.work_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvItemName.setText(list.get(position).itemName);
        holder.tvItemCost.setText("Price = ₹ "+ String.format("%.2f", list.get(position).itemCost));
        holder.tvTotalCost.setText(String.format("%.2f", list.get(position).itemCost));
        List<Integer> listQun = new ArrayList<>();
        listQun.add(1);
        listQun.add(2);
        listQun.add(3);
        listQun.add(4);
        listQun.add(5);
        *//*ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item,listQun);
        holder.spinner.setAdapter(adapter);

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int sp_position, long id) {
                qty = Integer.parseInt(parent.getSelectedItem().toString());
                double total = (qty * list.get(position).itemCost);
                 holder.tvTotalCost.setText(String.format("%.2f", total));
                //holder.tvItemCost.setText(String.valueOf(total));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*//*
        ArrayAdapter Myadapter = new ArrayAdapter<>(mContext,android.R.layout.simple_spinner_item, listQun);
        Myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.autoCompleteTextView.setAdapter(Myadapter);
        holder.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  ItemClass string =(ItemClass) parent.getItemAtPosition(position);
                qty = Integer.parseInt( parent.getItemAtPosition(position).toString());
                double total = (qty * list.get(position).itemCost);
                holder.tvTotalCost.setText(String.format("%.2f", total));
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
       TextView tvItemName,tvItemCost,tvTotalCost;
       Spinner spinner;
       AutoCompleteTextView autoCompleteTextView;
       TextInputLayout textInputLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.textView2);
            tvTotalCost = itemView.findViewById(R.id.textView3);
            tvItemCost = itemView.findViewById(R.id.textViewCost);
            //spinner = itemView.findViewById(R.id.spinner);
            autoCompleteTextView = itemView.findViewById(R.id.autoCompleteTextView);
            textInputLayout = itemView.findViewById(R.id.textInputLayout);
        }
    }*/
}


package com.example.leading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerItemAdapter extends RecyclerView.Adapter<RecyclerItemAdapter.ViewHolder> {

    Context context;
    ArrayList<item>itemList;

    RecyclerItemAdapter(Context context, ArrayList<item>itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.image.setImageResource(itemList.get(position).img);
        holder.itemName.setText(itemList.get(position).name);
        holder.itemPrice.setText(itemList.get(position).price);
        holder.itemDescription.setText(itemList.get(position).description);


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName, itemPrice, itemDescription;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemDescription = itemView.findViewById(R.id.item_description);
            image = itemView.findViewById(R.id.image);
        }
    }
}
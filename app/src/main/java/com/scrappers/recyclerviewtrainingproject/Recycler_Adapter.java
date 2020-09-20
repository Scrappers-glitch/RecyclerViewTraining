package com.scrappers.recyclerviewtrainingproject;


import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import static com.scrappers.recyclerviewtrainingproject.MainActivity.prefs;
import static com.scrappers.recyclerviewtrainingproject.MainActivity.prefs2;


public class Recycler_Adapter extends Adapter<ViewHolderClass>  {

    private ArrayList<String> titles;

    Recycler_Adapter(ArrayList<String> titles){
        this.titles=titles;
    }

    //here we inflate the layout
    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_layout,parent,false));
    }

    //here we need to bind the ViewHolder class which is ViewHolderClass to our items and position of each row item
    @Override
    public void onBindViewHolder(ViewHolderClass holder, final int position) {

        holder.checkBoxOfViewHolder.setText(titles.get(position));

        holder.checkBoxOfViewHolder.setPaintFlags(prefs.getInt(""+position, Paint.LINEAR_TEXT_FLAG));
        holder.checkBoxOfViewHolder.setChecked(prefs2.getBoolean(""+position, false));


        holder.checkBoxOfViewHolder.setOnCheckedChangeListener(new MainActivity.checkBoxClass(this,position));

        holder.deleteBtnOfViewHolder.setOnClickListener(new MainActivity.deleteBtnClass(this,position));

    }



    @Override
    public int getItemCount() {
        return titles.size();
    }

}



class ViewHolderClass extends ViewHolder {
    CheckBox checkBoxOfViewHolder;
    ImageButton deleteBtnOfViewHolder;
    ViewHolderClass(View itemView) {
        super(itemView);
        checkBoxOfViewHolder=itemView.findViewById(R.id.adaptercheckbox);
        deleteBtnOfViewHolder=itemView.findViewById(R.id.deleteBtn);

    }
}

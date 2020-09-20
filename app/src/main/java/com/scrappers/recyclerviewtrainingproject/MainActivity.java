package com.scrappers.recyclerviewtrainingproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> Titles=new ArrayList<>();
    static SharedPreferences prefs;
    static SharedPreferences prefs2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configure_Recycle("Hi");
        configure_Recycle("Recycle example");
        configure_Recycle("What a tiring day !");

        prefs= getSharedPreferences("textstate", Context.MODE_PRIVATE);
        prefs2= getSharedPreferences("checkstate", Context.MODE_PRIVATE);

    }

    public void configure_Recycle(String text){
        Titles.add(text);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new Recycler_Adapter(Titles));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    static class deleteBtnClass implements View.OnClickListener{


        RecyclerView.Adapter adapter;
        int position;

        deleteBtnClass(RecyclerView.Adapter adapter, int position){
            this.adapter=adapter;
            this.position=position;
        }
        @Override
        public void onClick(View v) {
            Titles.remove(position);
            adapter.notifyDataSetChanged();

        }
    }


    static class checkBoxClass implements CompoundButton.OnCheckedChangeListener {


        RecyclerView.Adapter adapter;
        int position;

        checkBoxClass(RecyclerView.Adapter adapter, int position){
            this.adapter=adapter;
            this.position=position;
        }
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            //changing the SharedPreferences configuration
            SharedPreferences.Editor editor=prefs.edit();
            SharedPreferences.Editor editor2=prefs2.edit();

            if(!isChecked){
                buttonView.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
                buttonView.setChecked(false);

                editor.putInt(""+position,Paint.LINEAR_TEXT_FLAG);
                editor.apply();


                editor2.putBoolean(""+position,false);
                    editor2.apply();

            }else {
                buttonView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                buttonView.setChecked(true);

                editor.putInt(""+position,Paint.STRIKE_THRU_TEXT_FLAG);
                editor.apply();

                editor2.putBoolean(""+position,true);
                editor2.apply();
            }
            adapter.notifyDataSetChanged();

        }

    }



}

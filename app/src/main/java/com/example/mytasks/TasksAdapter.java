package com.example.mytasks;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyTasks> myTasks;
    public TasksAdapter(Context c, ArrayList<MyTasks> p){
        context = c;
        myTasks = p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_does,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.titledoes.setText(myTasks.get(position).getTitledoes());
    holder.descdoes.setText(myTasks.get(position).getDescdoes());
    holder.datedoes.setText(myTasks.get(position).getDatedoes());

    final String getTitleTask = myTasks.get(position).getTitledoes();
    final String getDescTask = myTasks.get(position).getDescdoes();
    final String getDataTask = myTasks.get(position).getDatedoes();
    final String getKeyTask = myTasks.get(position).getKeydoes();


    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentt = new Intent(context,EditTaskDesk.class);
            intentt.putExtra("titledoes",getTitleTask);
            intentt.putExtra("descdoes",getDescTask);
            intentt.putExtra("datedoes",getDataTask);
            intentt.putExtra("keydoes",getKeyTask);
            context.startActivity(intentt);
        }
    });
    }



    @Override
    public int getItemCount() {
        return myTasks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titledoes,descdoes,datedoes, keydoes;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titledoes = (TextView) itemView.findViewById(R.id.titledoes);
            descdoes = (TextView) itemView.findViewById(R.id.descdoes);
            datedoes = (TextView) itemView.findViewById(R.id.datedoes);



        }
    }
}

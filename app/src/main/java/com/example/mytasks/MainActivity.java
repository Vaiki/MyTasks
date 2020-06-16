package com.example.mytasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView titlePage, subtitlePage, endPage;
    Button btnAddNew;
     DatabaseReference reference;
     RecyclerView ourTasks;
     ArrayList<MyTasks> list;
     TasksAdapter tasksAdapter;


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        titlePage = findViewById(R.id.title_page);
        subtitlePage = findViewById(R.id.subtitle_page);
        endPage = findViewById(R.id.endpage);

        btnAddNew = findViewById(R.id.btnAdd_task);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,NewTaskAct.class);
                startActivity(a);
            }
        });

//работа с БД
        ourTasks = findViewById(R.id.our_tasks);
        ourTasks.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyTasks>();
//получить данные из firebase
        reference = FirebaseDatabase.getInstance().getReference().child("MyTasks");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    MyTasks p = dataSnapshot1.getValue(MyTasks.class);
                    list.add(p);
                }
                tasksAdapter = new TasksAdapter(MainActivity.this, list);
                ourTasks.setAdapter(tasksAdapter);
                tasksAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

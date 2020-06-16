package com.example.mytasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskAct extends AppCompatActivity {

    TextView titlePage, addTitle, addDesc, addDate;
    EditText titleTask, descTask,dataTask;
    Button btnSaveTask, btnCancel;
    DatabaseReference reference;
    Integer taskNum = new Random().nextInt();
    String keydoes = Integer.toString(taskNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        addDate = findViewById(R.id.add_date);
        addDesc = findViewById(R.id.add_desc);
        descTask = findViewById(R.id.descdoes);
        dataTask = findViewById(R.id.datedoes);
        titlePage = findViewById(R.id.title_page);
        addTitle = findViewById(R.id.add_title);
        titleTask = findViewById(R.id.titledoes);
        btnCancel = findViewById(R.id.btn_Cansel);
        btnSaveTask = findViewById(R.id.btn_Save_Task);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //добавление задачи в БД
                reference = FirebaseDatabase.getInstance().getReference().child("MyTasks").
                        child("Task"+taskNum);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   dataSnapshot.getRef().child("titledoes").setValue(titleTask.getText().toString());
                   dataSnapshot.getRef().child("descdoes").setValue(descTask.getText().toString());
                   dataSnapshot.getRef().child("datedoes").setValue(dataTask.getText().toString());
                   dataSnapshot.getRef().child("keydoes").setValue(keydoes);
                        Intent intent = new Intent(NewTaskAct.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }
}

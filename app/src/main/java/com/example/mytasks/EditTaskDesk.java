package com.example.mytasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskDesk extends AppCompatActivity {
    EditText titleTask,descTask,dataTask;
    Button btnSaveUpdate, btnDelete;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);

        titleTask = findViewById(R.id.titledoes);
        descTask = findViewById(R.id.descdoes);
        dataTask = findViewById(R.id.datedoes);

        btnSaveUpdate = findViewById(R.id.btn_Save_Update);
        btnDelete = findViewById(R.id.btn_Delete);

        titleTask.setText(getIntent().getStringExtra("titledoes"));
        descTask.setText(getIntent().getStringExtra("descdoes"));
        dataTask.setText(getIntent().getStringExtra("datedoes"));

        final String keykeyDoes = getIntent().getStringExtra("keydoes");
        reference = FirebaseDatabase.getInstance().getReference().child("MyTasks").
                child("Task"+keykeyDoes);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(EditTaskDesk.this,MainActivity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titledoes").setValue(titleTask.getText().toString());
                        dataSnapshot.getRef().child("descdoes").setValue(descTask.getText().toString());
                        dataSnapshot.getRef().child("datedoes").setValue(dataTask.getText().toString());
                        dataSnapshot.getRef().child("keydoes").setValue(keykeyDoes);
                        Intent intent = new Intent(EditTaskDesk.this,MainActivity.class);
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

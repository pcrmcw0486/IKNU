package com.example.iknu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NewWrite_Forum extends AppCompatActivity {

    EditText title;
    EditText name;
    EditText text;
    String UID;
    String Collcetions = "AcademyForum";
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    Map<String, Object> input_text = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_write__forum);

        title = (EditText)findViewById(R.id.Write_Title);
        name = (EditText)findViewById(R.id.Write_Name);
        text = (EditText)findViewById(R.id.Write_Text);

        Spinner spinner = (Spinner)findViewById(R.id.Forum_Type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.forum_type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Collcetions = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
     /*   spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Collcetion = parent.getItemAtPosition(position).toString();
            }
        });*/
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        UID = mAuth.getCurrentUser().getUid();
    }

    public void onMove(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.Forum_btn:
                break;
            case R.id.Setting_btn:
                intent = new Intent(NewWrite_Forum.this, Setting.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Home_btn:
                intent = new Intent(NewWrite_Forum.this, Home.class);
                startActivity(intent);
                finish();
            default:
                return;
        }
    }

    public void SendText(View view) {
        input_text.put("UID",UID);
        input_text.put("context", text.getText().toString());
        input_text.put("name",name.getText().toString());
        input_text.put("title", title.getText().toString());
        db.collection(Collcetions)
                .add(input_text)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(NewWrite_Forum.this, "Write Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document with ",e);
                    }
                });

    }
}

package com.example.iknu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NewWrite_Forum extends AppCompatActivity {

    TextView title;
    TextView name;
    TextView text;
    String UID;
    String Collcetion = "AcademyForum";
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    Map<String, Object> input_text = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_write__forum);

        Spinner spinner = (Spinner)findViewById(R.id.Forum_Type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.forum_type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Collcetion = parent.getItemAtPosition(position).toString();
            }
        });
        mAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        UID = mAuth.getCurrentUser().getUid();
    }

    public void onMove(View view) {
    }

    public void SendText(View view) {
        input_text.put("UID",UID);
        input_text.put("context", text.getText().toString());
        input_text.put("name",name.getText().toString());
        input_text.put("title", title.getText().toString());
        db.collection(Collcetion)
                .add(input_text)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG","Document snapshot addded with ID : " + documentReference.getId());
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

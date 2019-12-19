package com.example.iknu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Forum_View extends AppCompatActivity {

    Intent intent;
    String documentID;
    String CollectionName;
    TextView title;
    TextView writer;
    TextView context;
    TextView type;
    FirebaseFirestore db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this.getIntent());
        setContentView(R.layout.activity_forum__view);
        documentID = intent.getStringExtra("documentID");
        CollectionName = intent.getStringExtra("documentName");
        title = (TextView)findViewById(R.id.Text_Title);
        writer = (TextView)findViewById(R.id.Text_writer);
        context = (TextView)findViewById(R.id.Text_context);
        type = (TextView)findViewById(R.id.Forum_Type);
        type.setText(CollectionName);
        db = FirebaseFirestore.getInstance();
        db.collection(CollectionName).document(documentID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    title.setText(task.getResult().getData().get("title").toString());
                    context.setText(task.getResult().getData().get("context").toString());
                    writer.setText(task.getResult().getData().get("name").toString());
                }
                else
                {
                    Toast.makeText(Forum_View.this,"Can not load Text",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    public void onMove(View view) {
        Intent intent = null;
        switch (view.getId())
        {
            case R.id.Forum_btn :
                break;
            case R.id.Setting_btn :
                intent = new Intent(Forum_View.this, Setting.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Home_btn :
                intent = new Intent(Forum_View.this, Home.class);
                startActivity(intent);
                finish();

            default:
                return;
        }
    }
}

package com.example.iknu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Forum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
    }

    public void onMove(View view) {
    }

    public void MoveForum(View view) {
        Intent intent;
        switch (view.getId())
        {
            case R.id.academy_forum_btn:
                intent = new Intent(Forum.this, Academy_Forum.class);
                startActivity(intent);
                break;
            case R.id.internship_forum_btn :
                intent = new Intent(Forum.this, InternShip_Forum.class);
                startActivity(intent);
                break;
            case R.id.seminar_forum_btn :
                intent = new Intent(Forum.this, SEMINAR_Forum.class);
                startActivity(intent);
                break;
            case R.id.cultural_forum_btn :
                intent = new Intent(Forum.this, Cultural_Forum.class);
                startActivity(intent);
                break;
            case R.id.club_activity_forum_btn :
                intent = new Intent(Forum.this, ClubActivity_Forum.class);
                startActivity(intent);
                break;
            case R.id.news_forum_btn :
                intent = new Intent(Forum.this, News_Forum.class);
                startActivity(intent);
                break;
        }
    }


}

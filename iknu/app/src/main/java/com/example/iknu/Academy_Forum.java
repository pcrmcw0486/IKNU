package com.example.iknu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Academy_Forum extends AppCompatActivity {

    FirebaseFirestore mdb ;
    ListView listView;
    ArrayList<Text> list = new ArrayList<Text>();
    ArrayList<String> textID = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy__forum);

        mdb = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        mdb.setFirestoreSettings(settings);
        mdb = FirebaseFirestore.getInstance();
        /*
        *  db.collection("Location").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document : task.getResult())
                    {
                        items.add(document.getId());
                        Lat.add(Float.valueOf(document.getData().get("Lat").toString()));
                        Long.add(Float.valueOf(document.getData().get("Long").toString()));
                        Log.i("TAG", document.getId() + " => " + document.getData().get("Long").toString());
                    }
                }
            }
        });*/

        mdb.collection("AcademyForum").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document : task.getResult())
                    {
                        list.add(new Text(document.getData().get("title").toString(), document.getData().get("name").toString()));
                        textID.add(document.getId());
                    }
                    ArrayList<HashMap<String, String>>mapList = new ArrayList<HashMap<String, String>>();
                    for(Text t : list)
                    {
                        HashMap map = new HashMap();
                        map.put("title", t.title);
                        map.put("name",t.name);
                        mapList.add(map);
                    }
                    listView = (ListView)findViewById(R.id.forum_listView);
                    SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), mapList, android.R.layout.simple_list_item_2, new String[]{"title","name"}, new int[]{android.R.id.text1,android.R.id.text2});
                    listView.setAdapter(adapter);
                }
                else
                    Toast.makeText(Academy_Forum.this, "?!?!?!", Toast.LENGTH_SHORT).show();
            }
        });
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent = new Intent(Academy_Forum.this, Forum_View.class);
              intent.putExtra("documentID", textID.get(position));
              intent.putExtra("documentName", "AcademyForum");
              startActivity(intent);
          }
      });
    }

    public void onMove(View view) {
    }

    class Text
    {
        String title;
        String name;
        public Text(String title, String name)
        {
            this.title = title;
            this.name = name;
        }
    }
}

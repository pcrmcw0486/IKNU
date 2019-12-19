package com.example.iknu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.io.LineReader;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//https://github.com/rkswlrbduf/RecyclerViewFilter/blob/master/app/src/main/java/com/example/recyclerviewfilter/MainActivity.java#L31 Recyclerview 참조
//파이어 베이스에 미리 저장해놓은 각각의 건물 위치 정보에 대하여 가져온 다음 버튼이 클릭시 editText에 존재하는 해당 건물의 위치 정보를 띄움
// Map의 초기값은 initial 자신의 포지션으로 설정함.
//네비게이션은 구현하지 않고 위치 정보만 하는걸로 합시다.
public class Map_Navi extends AppCompatActivity implements TextWatcher, OnMapReadyCallback {

    RecyclerView recyclerView;
    EditText editText;
    RecycleViewAdapter adapter;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<Float> Lat = new ArrayList<>();
    ArrayList<Float>Long = new ArrayList<>();
    FirebaseFirestore db ;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private static final int REQUEST_CODE_LOCATION = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__navi);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        editText = (EditText)findViewById(R.id.editText);
        editText.addTextChangedListener(this);

        db = FirebaseFirestore.getInstance();
        db.collection("Location").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document : task.getResult())
                    {
                        items.add(document.getId());
                        Lat.add(Float.valueOf(document.getData().get("Lat").toString()));
                        Long.add(Float.valueOf(document.getData().get("Lang").toString()));
                        //Log.i("TAG", document.getId() + " => " + document.getData().get("Lang").toString());
                    }
                }
            }
        });

        adapter = new RecycleViewAdapter(getApplicationContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                editText.setText(adapter.filteredlist.get(position).toString());
            }
        });
        SupportMapFragment mapFragment =(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
        recyclerView.bringToFront();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MarkerOptions markerOptions = new MarkerOptions();
        LatLng SEOUL = new LatLng(37.56,126.97);
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        recyclerView.bringToFront();

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.getFilter().filter(s);
        recyclerView.bringToFront();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void Search(View view) {
        String building;
        building = editText.getText().toString();
        Float Select_Lat = Lat.get(items.indexOf(building));
        Float Select_Long = Long.get(items.indexOf(building));
        LatLng POSITION = new LatLng(Select_Lat, Select_Long);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(POSITION));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    public void MyPosition(View view) {
        LatLng userPosition;
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location userLocation = getMyLocation();
        if(userLocation != null)
        {
            userPosition = new LatLng(userLocation.getLatitude(),userLocation.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(userPosition));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
        recyclerView.bringToFront();
    }

    private Location getMyLocation()
    {
        Location currentLocation = null;
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(Map_Navi.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Map_Navi.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("////////////사용자에게 권한을 요청해야함");
            ActivityCompat.requestPermissions(Map_Navi.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_CODE_LOCATION);
            getMyLocation(); //이건 써도되고 안써도 되지만, 전 권한 승인하면 즉시 위치값 받아오려고 썼습니다!
        }
        else {
           // System.out.println("////////////권한요청 안해도됨");

            // 수동으로 위치 구하기
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                double lng = currentLocation.getLongitude();
                double lat = currentLocation.getLatitude();
            }
        }
        return currentLocation;


       // 출처: https://vvh-avv.tistory.com/138 [정리잘하고싶다]
    }

    public void onMove(View view) {
    }
}

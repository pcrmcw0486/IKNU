package com.example.iknu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.common.io.LineReader;

import java.util.ArrayList;

//https://github.com/rkswlrbduf/RecyclerViewFilter/blob/master/app/src/main/java/com/example/recyclerviewfilter/MainActivity.java#L31 Recyclerview 참조
//파이어 베이스에 미리 저장해놓은 각각의 건물 위치 정보에 대하여 가져온 다음 버튼이 클릭시 editText에 존재하는 해당 건물의 위치 정보를 띄움
// Map의 초기값은 initial 자신의 포지션으로 설정함.
//네비게이션은 구현하지 않고 위치 정보만 하는걸로 합시다.
public class Map_Navi extends AppCompatActivity implements TextWatcher, OnMapReadyCallback {

    RecyclerView recyclerView;
    EditText editText;
    RecycleViewAdapter adapter;
    ArrayList<String> items = new ArrayList<>();
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__navi);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        editText = (EditText)findViewById(R.id.editText);
        editText.addTextChangedListener(this);

        items.add("김씨");
        items.add("이씨");

        adapter = new RecycleViewAdapter(getApplicationContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                editText.setText(items.get(position).toString());
                Toast.makeText(Map_Navi.this,"클릭 이름" + items.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });
        SupportMapFragment mapFragment =(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
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
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void Search(View view) {
    }
}

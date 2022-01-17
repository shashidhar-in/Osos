package com.example.osos;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mMapView;
    private RecyclerView recyclerView;
    private SnapHelper snapHelper;
    private LinearLayoutManager linearLayoutManager;

    Double latitude=0.0,longitude=0.0;
    String markername;
    private GoogleMap map;
    String url="https://jsonplaceholder.typicode.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.getMapAsync(this);



        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);



        listingdata();





    }


    private void listingdata() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Userdata_api userdata_api=retrofit.create(Userdata_api.class);

        Call<List<User>>call=userdata_api.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                List<User> data=response.body();
                RecyclerAdapter recycleadapter=new RecyclerAdapter(MainActivity.this,data);
                recyclerView.setAdapter(recycleadapter);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                            View centerView = snapHelper.findSnapView(linearLayoutManager);
                            int pos = linearLayoutManager.getPosition(centerView);
                            changemarker(pos);
                        }
                    }

                    private void changemarker(int pos) {
                        markername=markername+data.get(pos).getName();
                         latitude=Double.valueOf(data.get(pos).getAddress().getGeo().getLat());
                         longitude=Double.valueOf(data.get(pos).getAddress().getGeo().getLng());

                         LatLng latLng=new LatLng(latitude,longitude);
                        Log.e("double data",""+latitude+","+longitude+","+markername);
                        map.clear();
                        map.addMarker(new MarkerOptions().position(new LatLng(latitude,latitude)).title(markername));
                        map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        map.setMapType(map.MAP_TYPE_HYBRID);

                    }
                });


            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map=map;

            map.clear();
            Log.e("double data",""+latitude+","+longitude+","+markername);
            map.addMarker(new MarkerOptions().position(new LatLng(latitude,latitude)).title(markername));
            map.setMapType(map.MAP_TYPE_HYBRID);


    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }


    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        private Context context;
        private List<User> users;
        private User user;
        public RecyclerAdapter(Context context, List<User> users) {
            this.context=context;
            this.users=users;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view= LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            user=users.get(i);
            viewHolder.Name.setText(user.getName());
            viewHolder.Username.setText(user.getUsername());
            viewHolder.Email.setText(user.getEmail());
            viewHolder.Street.setText(user.getAddress().getStreet());
            viewHolder.City.setText(user.getAddress().getCity());
            viewHolder.Zip.setText(user.getAddress().getZipcode());

        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView Name,Username,Email,Street,City,Zip;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                Name=(TextView)itemView.findViewById(R.id.name);
                Username=(TextView)itemView.findViewById(R.id.username);
                Email=(TextView)itemView.findViewById(R.id.Email);
                Street=(TextView)itemView.findViewById(R.id.street);
                City=(TextView)itemView.findViewById(R.id.city);
                Zip=(TextView)itemView.findViewById(R.id.zipcode);

            }
        }
    }

}
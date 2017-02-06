package student.cosmostaban.ilovezappos.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import retrofit2.Call;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.io.IOException;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import student.cosmostaban.ilovezappos.Adapter.AdapterRecyclerView;
import student.cosmostaban.ilovezappos.R;
import student.cosmostaban.ilovezappos.Service.IService;
import student.cosmostaban.ilovezappos.Models.Results;
//import student.cosmostaban.ilovezappos.databinding.ActivityMainBinding;
//import android.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity
{
    LinearLayoutManager linearLayoutManager;

    public MainActivity() throws IOException
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //final ActivityMainBinding mainActivityBinding = DataBindingUtil
        setContentView(R.layout.activity_main);
        getData();
        //final ActivityMainBinding mainActivityBinding
    }

    private void getData()
    {
        //Check network/wifi for data processing
        if (isNetworkAvailable())
        {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(IService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            IService service = retrofit.create(IService.class);
            Call<Results> call = service.productList("nike", IService.API_KEY);
            call.enqueue(new Callback<Results>()
            {

                @Override
                public void onResponse(Call<Results> call, Response<Results> response)
                {
                    try
                    {
                        if (response.isSuccessful())
                        {
                            Results responseData = response.body();
                            //int results = response.body().returnProducts().size();
                            linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            AdapterRecyclerView adapterRecyclerView = new AdapterRecyclerView(responseData);
                            recyclerView.setAdapter(adapterRecyclerView);
                            //mainActivityBinding.setProducts(responseData.returnProducts().get(p));
                            //System.out.print(responseData.returnProducts().get(p));
                        }

                    } catch (JsonIOException e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t)
                {
                    Log.v(MainActivity.class.getSimpleName(), "Test" + t.getMessage());
                }
            });

        } else
        {
            alertAboutERROR();
        }
    }

    //Check if device has network
    private boolean isNetworkAvailable()
    {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) isAvailable = true;
        return isAvailable;
    }

    //Display error
    private void alertAboutERROR()
    {
        DialogAlert alertFrag = new DialogAlert();
        alertFrag.show(getFragmentManager(), "error_dial");
    }

}



































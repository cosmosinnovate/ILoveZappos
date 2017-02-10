package student.cosmostaban.ilovezappos.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import retrofit2.Call;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.squareup.picasso.Picasso;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import student.cosmostaban.ilovezappos.Adapter.AdapterRecyclerView;
import student.cosmostaban.ilovezappos.R;
import student.cosmostaban.ilovezappos.Service.IService;
import student.cosmostaban.ilovezappos.Models.Results;

public class MainActivity extends AppCompatActivity
{

    private RecyclerView binding;
    private FloatingActionButton fab;
    String searchView;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Floating action button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                FloatActionAnim alertFrag = new FloatActionAnim();
                alertFrag.show(getFragmentManager(), "display");            }
        });

        Init();//Start data upload and network connection

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.main_menu_view, menu);
        this.menu = menu;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

            SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();

            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query)
                {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String search) {
                    searchView = search;

                    if (isNetworkAvailable()) // Check for internet
                    {
                        getData(searchView); //Use this later with searching data
                    } else
                    {
                        alertAboutERROR();
                    }
                    return true;
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Init()
    {
        binding = (RecyclerView) findViewById(R.id.recycler); // Get the recycler view
        binding.setLayoutManager(new LinearLayoutManager(this));
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

        if (isNetworkAvailable()) // Check for internet
        {
            getData(searchView); //Use this later with searching data
        } else
        {
            alertAboutERROR();
        }
    }

    private void getData(String search)
    {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        IService service = retrofit.create(IService.class);
        Call<Results> call = service.productList(search, IService.API_KEY);
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
                        AdapterRecyclerView adapterRecyclerView = new AdapterRecyclerView(responseData);
                        binding.setAdapter(adapterRecyclerView);
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
    }

    /* Check if device has network */
    private boolean isNetworkAvailable()
    {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) isAvailable = true;
        return isAvailable;
    }

    /* Display error */
    private void alertAboutERROR()
    {
        DialogAlert alertFrag = new DialogAlert();
        alertFrag.show(getFragmentManager(), "error_dial");
    }

}



































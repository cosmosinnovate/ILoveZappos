package student.cosmostaban.ilovezappos;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import student.cosmostaban.ilovezappos.Service.IService;
import student.cosmostaban.ilovezappos.Service.Results;
import student.cosmostaban.ilovezappos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActivityMainBinding mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Check network/wifi for data processing
        if (isNetworkAvailable()) {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(IService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            IService service = retrofit.create(IService.class);
            Call<Results> call = service.productList("nike", IService.API_KEY);
            call.enqueue(new Callback<Results>() {

                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {

                    try {
                        if (response.isSuccessful()) {
                            Results results = response.body();
                            //mainActivityBinding.setProducts(results.returnProducts().get(0));
                            for (int p = 0; p < results.returnProducts().size(); p++) {
                                mainActivityBinding.setProducts(results.returnProducts().get(p));

                                System.out.print(
                                        "\nProductId " + results.returnProducts().get(p).getProductId()
                                                + "\nBrand Name " + results.returnProducts().get(p).getBrandName()
                                                         + "\nOriginalPrice " + results.returnProducts().get(p).getOriginalPrice()
                                        + "\nProductName " + results.returnProducts().get(p).getProductName()
                                        + "\nThumbnailImageUrl " + results.returnProducts().get(p).getThumbnailImageUrl());

//                                Log.v(MainActivity.class.getSimpleName(),  + "" + p);
//                                Log.v(MainActivity.class.getSimpleName(), ;
//                                Log.v(MainActivity.class.getSimpleName(), );
//                                Log.v(MainActivity.class.getSimpleName(), );
//                                Log.v(MainActivity.class.getSimpleName(), );
                            }
                        }

                    } catch (JsonIOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t) {
                    Log.v(MainActivity.class.getSimpleName(), "Test" + t.getMessage());
                }
            });

        } else {
            //Toast.makeText(this, "Network unavailable", Toast.LENGTH_LONG).show();
            alertAboutERROR();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) isAvailable = true;
        return isAvailable;
    }

    private void alertAboutERROR() {
        DialogAlert alertFrag = new DialogAlert();
        alertFrag.show(getFragmentManager(), "error_dial");
    }

}



































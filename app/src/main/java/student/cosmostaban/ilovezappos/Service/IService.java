package student.cosmostaban.ilovezappos.Service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import student.cosmostaban.ilovezappos.Models.Results;

/**
 *
 * Created by cosmos on 2/3/17.
 *
 */

public interface IService
{
    String BASE_URL = "https://api.zappos.com/";
    String API_KEY = "b743e26728e16b81da139182bb2094357c31d331";

    //returns the search asked
    @GET("Search")
    Call<Results> productList(@Query("term") String term, @Query("key") String key);
}

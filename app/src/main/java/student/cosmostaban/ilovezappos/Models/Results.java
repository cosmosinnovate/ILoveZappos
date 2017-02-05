package student.cosmostaban.ilovezappos.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by cosmost on 2/3/17.
 */
public class Results {

    @SerializedName("results")
    @Expose
    private ArrayList<Products> products = new ArrayList<>();

    public ArrayList<Products> returnProducts() {
        return products;
    }
}

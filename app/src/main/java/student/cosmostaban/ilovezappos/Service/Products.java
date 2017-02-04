package student.cosmostaban.ilovezappos.Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by cosmost on 2/3/17.
 */

public class Products {
    //Products variable:
    @SerializedName("brandName")
    @Expose
    private String brandName;

    @SerializedName("thumbnailImageUrl")
    @Expose
    private String thumbnailImageUrl;

    @SerializedName("productId")
    @Expose
    private String productId;

    @SerializedName("originalPrice")
    @Expose
    private String originalPrice;

    @SerializedName("styleId")
    @Expose
    private String styleId;

    @SerializedName("colorId")
    @Expose
    private String colorId;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("percentOff")
    @Expose
    private String percentOff;

    @SerializedName("productUrl")
    @Expose
    private String productUrl;

    @SerializedName("productName")
    @Expose
    private String productName;

    public String getBrandName() {
        return this.brandName;
    }


    public String getThumbnailImageUrl() {
        return this.thumbnailImageUrl;
    }


    public String getProductId() {
        return this.productId;
    }


    public String getOriginalPrice() {
        return this.originalPrice;
    }


    public String getStyleId() {
        return this.styleId;
    }


    public String getColorId() {
        return this.colorId;
    }


    public String getPrice() {
        return this.price;
    }


    public String getPercentOff() {
        return this.percentOff;
    }


    public String getProductUrl() {
        return this.productUrl;
    }


    public String getProductName() {
        return this.productName;
    }



}

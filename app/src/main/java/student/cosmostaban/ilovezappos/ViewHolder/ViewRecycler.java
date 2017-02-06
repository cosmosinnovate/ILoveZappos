package student.cosmostaban.ilovezappos.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import student.cosmostaban.ilovezappos.R;

/**
 *
 * Created by cosmos on 2/5/17.
 *
 */

public class ViewRecycler extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView productName, brandName, productPrice;
    public ImageView thumbUrl;


    public ViewRecycler(View view)
    {
        super(view);
        view.setOnClickListener(this); //Set your view ready.
        //Bind what you want to display on the list.
        productName = (TextView) view.findViewById(R.id.productName); //bind data here
        brandName = (TextView) view.findViewById(R.id.brandName); //bind data here
        thumbUrl = (ImageView) view.findViewById(R.id.imageView);
        productPrice = (TextView) view.findViewById(R.id.productPrice);

    }

    @Override
    public void onClick(View v)
    {

    }
}

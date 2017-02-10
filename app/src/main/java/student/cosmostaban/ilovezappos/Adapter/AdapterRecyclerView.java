package student.cosmostaban.ilovezappos.Adapter;

/**
 *
 * Created by cosmos on 2/5/17.
 *
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import student.cosmostaban.ilovezappos.BR;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import student.cosmostaban.ilovezappos.Models.Products;
import student.cosmostaban.ilovezappos.Models.Results;
import student.cosmostaban.ilovezappos.R;
import student.cosmostaban.ilovezappos.ViewHolder.ViewRecycler;

import static com.android.databinding.library.baseAdapters.BR._all;


public class AdapterRecyclerView extends RecyclerView.Adapter<ViewRecycler>
{

    //This data comes from the server
    private Results results;

    public AdapterRecyclerView(Results results)
    {
        this.results = results;
    }


    @Override
    public ViewRecycler onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row_xml, null);
        ViewRecycler viewRecycler = new ViewRecycler(view); //This inflates the view
        return viewRecycler; // Return the object created.
    }

    @Override
    public void onBindViewHolder(ViewRecycler holder, int position)
    {
        final Products products = results.returnProducts().get(position);
        holder.getBinding().setVariable(BR.products, products);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() //Get the count of the data
    {
        return this.results.returnProducts().size(); //Returns the data count
    }
}

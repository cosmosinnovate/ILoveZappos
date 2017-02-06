package student.cosmostaban.ilovezappos.Adapter;

/**
 *
 * Created by cosmos on 2/5/17.
 *
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import student.cosmostaban.ilovezappos.Models.Results;
import student.cosmostaban.ilovezappos.R;
import student.cosmostaban.ilovezappos.ViewHolder.ViewRecycler;


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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_xml, null);
        ViewRecycler viewRecycler = new ViewRecycler(view); //This inflates the view
        return viewRecycler; // Return the object created.
    }

    @Override
    public void onBindViewHolder(ViewRecycler holder, int position)
    {
        holder.brandName.setText(results.returnProducts().get(position).getBrandName());
        holder.productName.setText(results.returnProducts().get(position).getProductName());

    }

    @Override
    public int getItemCount() //Get the count of the data
    {
        return this.results.returnProducts().size(); //Returns the data count
    }
}

package student.cosmostaban.ilovezappos.ViewHolder;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.tool.Binding;
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

public class ViewRecycler extends RecyclerView.ViewHolder
{
    private ViewDataBinding binding; //View binder

    public ViewRecycler(View view)
    {
        super(view);
        binding = DataBindingUtil.bind(view);
    }

    public ViewDataBinding getBinding()
    {
        return binding;
    }
}

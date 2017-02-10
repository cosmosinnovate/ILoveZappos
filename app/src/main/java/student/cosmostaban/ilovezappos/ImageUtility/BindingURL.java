package student.cosmostaban.ilovezappos.ImageUtility;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import student.cosmostaban.ilovezappos.R;

/**
 * Created by cosmost on 2/9/17.
 */

public class BindingURL
{
    @BindingAdapter("app:imageUrl")
    public static void imageUrl(ImageView view, String url)
    {
        Picasso.with(view.getContext())
                .load(url).error(R.mipmap.ic_launcher)
                .into(view);
    }
}

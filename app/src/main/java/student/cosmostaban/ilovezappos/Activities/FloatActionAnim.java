package student.cosmostaban.ilovezappos.Activities;

/**
 * Created by cosmost on 2/10/17.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import student.cosmostaban.ilovezappos.R;

public class FloatActionAnim extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("ILoveZappos Store")
                .setMessage("Browse our great products. You are awesome!")
                .setPositiveButton(R.string.error_ok, null);

        return builder.create();
    }
}


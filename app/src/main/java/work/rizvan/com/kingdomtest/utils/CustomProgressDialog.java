package work.rizvan.com.kingdomtest.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import work.rizvan.com.kingdomtest.R;



public class CustomProgressDialog
{
    private static ProgressBar progressbar;
    public static AlertDialog alertdialog;




    public static void showDialog(final Context context, String text)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.custom_progress,null);
        builder.setView(view);

        progressbar= (ProgressBar) view.findViewById(R.id.progressbar_id);
        TextView progressText= (TextView) view.findViewById(R.id.progress_textview);

        progressbar.setProgress(0);
        progressbar.setVisibility(View.VISIBLE);
        progressText.setText(text);

        alertdialog=builder.create();
        alertdialog.show();

    }

    public static void hideDialog()
    {
        alertdialog.dismiss();

    }


}

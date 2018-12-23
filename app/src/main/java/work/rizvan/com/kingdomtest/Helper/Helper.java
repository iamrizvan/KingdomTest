package work.rizvan.com.kingdomtest.Helper;

import android.support.design.widget.Snackbar;
import android.view.View;


public class Helper
{
    public static void showSnack(View view, String text)
    {
        Snackbar snackbar = Snackbar
                .make(view, text, Snackbar.LENGTH_SHORT);

        snackbar.show();
    }
}

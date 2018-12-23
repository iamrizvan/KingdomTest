package work.rizvan.com.kingdomtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import work.rizvan.com.kingdomtest.R;

public class ToDoDescriptionActivity extends AppCompatActivity {

    TextView title, desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_description);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<small>"+"Details"+"</small>"));

        String ttl = getIntent().getStringExtra("TITLE");
        String desciption = getIntent().getStringExtra("DESC");

        title = findViewById(R.id.title_desc_textview);
        desc = findViewById(R.id.desc_desc_textview);

        if (ttl != null)
        {
            title.setText(ttl);
        }
        if (desciption != null)
        {
            desc.setText(desciption);
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}

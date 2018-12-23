package work.rizvan.com.kingdomtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import work.rizvan.com.kingdomtest.Helper.Helper;
import work.rizvan.com.kingdomtest.R;
import work.rizvan.com.kingdomtest.table.ToDoTable;

public class ToDoEditActivity extends AppCompatActivity {

    EditText titleEdt, descriptionEdt;
    Button updateBtn;
    ToDoTable toDoTable;
    String keyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<small>"+"Update"+"</small>"));

        toDoTable = new ToDoTable(ToDoEditActivity.this);

        final String ttl = getIntent().getStringExtra("TITLE");
        final String desciption = getIntent().getStringExtra("DESC");
        keyId = getIntent().getStringExtra("KEYID");


        titleEdt = findViewById(R.id.update_title_edittext);
        descriptionEdt = findViewById(R.id.update_description_edittext);

        if (ttl != null)
        {
            titleEdt.setText(ttl);
        }
        if (desciption != null)
        {
            descriptionEdt.setText(desciption);
        }

        updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (titleEdt.getText().toString().equalsIgnoreCase(ttl)
                        && descriptionEdt.getText().toString().equalsIgnoreCase(desciption))
                {
                    Helper.showSnack(updateBtn,"No change found to update.");
                }
                else
                {
                    toDoTable.updateData(keyId,titleEdt.getText().toString(),descriptionEdt.getText().toString());
                    Helper.showSnack(updateBtn,"Update success.");
                }

            }
        });




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

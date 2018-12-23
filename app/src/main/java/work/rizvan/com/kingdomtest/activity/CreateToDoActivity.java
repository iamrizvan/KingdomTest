package work.rizvan.com.kingdomtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import work.rizvan.com.kingdomtest.Helper.Helper;
import work.rizvan.com.kingdomtest.R;
import work.rizvan.com.kingdomtest.model.ToDoModel;
import work.rizvan.com.kingdomtest.table.ToDoTable;

public class CreateToDoActivity extends AppCompatActivity {

    EditText title_edt,description_edt;
    Button create_btn;
    DatabaseReference databaseReferences;
    ToDoTable toDoTable;
    List<ToDoModel> toDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_to_do);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<small>"+"Create"+"</small>"));

        title_edt = findViewById(R.id.title_edittext);
        description_edt = findViewById(R.id.description_edittext);
        create_btn = findViewById(R.id.create_btn);

        toDoTable = new ToDoTable(CreateToDoActivity.this);
        toDoList = new ArrayList<>();
        toDoList = toDoTable.getToDoData();


        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title_edt.getText().toString().equalsIgnoreCase(""))
                {
                    Helper.showSnack(create_btn,"Please enter title.");
                }
                else
                {
                    DateFormat df = DateFormat.getTimeInstance();
                    df.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
                    df.format(new Date(System.currentTimeMillis()));
                    toDoTable.addItemInCart(new ToDoModel(title_edt.getText().toString(),
                            description_edt.getText().toString(),String.valueOf(df),"0"));
                    Helper.showSnack(create_btn,"Task added successfully.");
                    title_edt.setText("");
                    description_edt.setText("");
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

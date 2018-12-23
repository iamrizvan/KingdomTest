package work.rizvan.com.kingdomtest.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import work.rizvan.com.kingdomtest.R;
import work.rizvan.com.kingdomtest.adapter.ToDoAdapter;
import work.rizvan.com.kingdomtest.model.ToDoModel;
import work.rizvan.com.kingdomtest.table.ToDoTable;
import work.rizvan.com.kingdomtest.utils.UserUtils;

public class ToDoActivity extends AppCompatActivity {

    RecyclerView todo_recycler;
    RecyclerView.LayoutManager layoutManager;
    ToDoAdapter adapter;
    List<ToDoModel> todoList;

    ToDoTable toDoTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);


        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<small>"+"To-Do"+"</small>"));

        if (!UserUtils.getLoginStatus(ToDoActivity.this).equalsIgnoreCase("1"))
        {
            startActivity(new Intent(ToDoActivity.this, LoginActivity.class));
            overridePendingTransition(R.anim.reverse_left_to_right, R.anim.reverse_right_to_left);
            finish();
        }

        todo_recycler = findViewById(R.id.todo_recycler);
        toDoTable = new ToDoTable(ToDoActivity.this);
        todoList = new ArrayList<>();
        loadToDoItems();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ToDoActivity.this,CreateToDoActivity.class));
                overridePendingTransition(R.anim.reverse_left_to_right, R.anim.reverse_right_to_left);
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        loadToDoItems();

    }

    private void loadToDoItems() {
        todoList = toDoTable.getToDoData();
        if (todoList != null && todoList.size()>0)
        {
            adapter = new ToDoAdapter(ToDoActivity.this, todoList, todo_recycler);
            layoutManager = new LinearLayoutManager(ToDoActivity.this);
            todo_recycler.setLayoutManager(layoutManager);
            todo_recycler.setAdapter(adapter);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
            {

                UserUtils.saveLoginStatus(ToDoActivity.this, "0");
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
            break;
        }
        return true;
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

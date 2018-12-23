package work.rizvan.com.kingdomtest.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import work.rizvan.com.kingdomtest.model.ToDoModel;

public class ToDoTable extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="KingdomDatabase";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="todoTable";
    private static final String KEY_ID="key_id";
    private static final String TITLE="color_id";
    private static final String DESCRIPTION="color_order_id";
    private static final String TIME="color_name";
    private static final String STATUS="status";

    private SQLiteDatabase database;
    private List<ToDoModel> todoList;
    private ToDoModel todoModel;
    private String SqlQuery;

    private Context mContext;

    private String msg;


    private int i=0;

    public ToDoTable(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext=context;
        database=this.getWritableDatabase();
        onCreate(database);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        SqlQuery="create table if not exists "+TABLE_NAME+"("+KEY_ID+" integer primary key autoincrement ,"
                +TITLE+ " text,"
                +DESCRIPTION+ " text,"
                +TIME+ " text,"
                +STATUS+" text)";
        sqLiteDatabase.execSQL(SqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int newVersion, int oldVersion)
    {
        onCreate(sqLiteDatabase);
    }



    public void addItemInCart(ToDoModel model)
    {

        database = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(TITLE,model.getTitle());
        cv.put(DESCRIPTION,model.getDescription());
        cv.put(TIME,model.getTime());
        cv.put(STATUS,"0");
        database.insert(TABLE_NAME,null,cv);
        database.close();
    }




    public void dropTable()
    {
        database=this.getWritableDatabase();
        database.execSQL("drop table "+TABLE_NAME);
        database.close();
    }







    public void updateStatus(String keyId)
    {
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STATUS, "1");
        database.update(TABLE_NAME, cv, KEY_ID + "= ?", new String[] {keyId});
        database.close();
    }

    public List<ToDoModel> getToDoData() {

        List<ToDoModel> toDoList = new ArrayList<>();
        String sqlString = "SELECT * FROM "+TABLE_NAME;
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sqlString,null);
        if (cursor != null && cursor.getCount() >0)
        {
            while (cursor.moveToNext())
            {
                String keyId = cursor.getString(0);
                String title = cursor.getString(1);
                String dsc = cursor.getString(2);
                String time = cursor.getString(3);
                String status = cursor.getString(4);
                toDoList.add(new ToDoModel(keyId,title,dsc,time,status));
            }
        }

        if (toDoList.size()>0)
        {
            return toDoList;
        }
        else
        {
            return null;
        }
    }

    public void deleteccToDo(String keyId) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{keyId});
        db.close();

    }

    public void updateData(String keyId, String title, String description) {

        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put(DESCRIPTION,description);
        database.update(TABLE_NAME, cv, KEY_ID + "= ?", new String[] {keyId});
        database.close();
    }
}

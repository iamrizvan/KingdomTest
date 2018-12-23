package work.rizvan.com.kingdomtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import work.rizvan.com.kingdomtest.Helper.Helper;
import work.rizvan.com.kingdomtest.R;
import work.rizvan.com.kingdomtest.activity.ToDoDescriptionActivity;
import work.rizvan.com.kingdomtest.activity.ToDoEditActivity;
import work.rizvan.com.kingdomtest.model.ToDoModel;
import work.rizvan.com.kingdomtest.table.ToDoTable;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder>
{
    RecyclerView recyclerView;
    Context context;
    List<ToDoModel> todoList;
    ToDoModel todoModel;
    ToDoTable toDoTable;

    ToDoAdapter toDoAdapter;

    public ToDoAdapter(Context context, List<ToDoModel> todoList, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.todoList = todoList;
        toDoTable = new ToDoTable(context);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view  = LayoutInflater.from(context).inflate(R.layout.todo_recycler_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        todoModel = todoList.get(position);
        holder.title.setText(todoModel.getTitle());
        holder.description.setText(todoModel.getDescription());

        if (todoModel.getStatus().equalsIgnoreCase("0"))
        {
            holder.parentLayout.setBackgroundColor(context.getResources().getColor(R.color.read_color));
        }
        else
        {
            holder.mark.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount()
    {
        if (todoList.size()>0)
        {
            return todoList.size();
        }
        else
        {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,description;
        ImageView mark,edit, delete;
        LinearLayout parentLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            description = itemView.findViewById(R.id.description_text);
            mark = itemView.findViewById(R.id.mark_icon);
            edit = itemView.findViewById(R.id.edit_icon);
            delete = itemView.findViewById(R.id.delete_icon);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            itemView.setOnClickListener(this);
            mark.setOnClickListener(this);
            edit.setOnClickListener(this);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            switch (view.getId())
            {
                case R.id.mark_icon:
                {
                    todoModel = todoList.get(getAdapterPosition());
                    Helper.showSnack(recyclerView,"Task marked as complete.");
                    toDoTable.updateStatus(todoModel.getKeyId());

                    todoList = toDoTable.getToDoData();
                    notifyDataSetChanged();
                }
                break;
                case R.id.edit_icon:
                {
                    todoModel = todoList.get(getAdapterPosition());
                    Intent intent = new Intent(context,ToDoEditActivity.class);
                    intent.putExtra("TITLE",todoModel.getTitle());
                    intent.putExtra("DESC",todoModel.getDescription());
                    intent.putExtra("KEYID",todoModel.getKeyId());
                    context.startActivity(intent);
                }
                break;
                case R.id.delete_icon:
                {
                   deleteToDo(getAdapterPosition());
                }
                break;
                default:
                {
                    todoModel = todoList.get(getAdapterPosition());
                    Intent intent = new Intent(context,ToDoDescriptionActivity.class);
                    intent.putExtra("TITLE",todoModel.getTitle());
                    intent.putExtra("DESC",todoModel.getDescription());
                    context.startActivity(intent);
                    toDoTable.updateStatus(todoModel.getKeyId());
                }
                break;
            }


        }

        private void deleteToDo(int adapterPosition) {
            toDoTable.deleteccToDo(todoList.get(adapterPosition).getKeyId());
            todoList.remove(adapterPosition);
            notifyDataSetChanged();
        }
    }
}

package hu.bme.android.todorecyclerview.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.bme.android.todorecyclerview.MainActivity;
import hu.bme.android.todorecyclerview.R;
import hu.bme.android.todorecyclerview.data.Todo;

public class TodoAdapter
        extends RecyclerView.Adapter<TodoAdapter.ViewHolder>
        implements TodoTouchHelperAdapter {

    private Context context;
    private List<Todo> todos = new ArrayList<Todo>();

    public TodoAdapter(Context context) {
        this.context = context;

        todos = Todo.listAll(Todo.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_row, parent, false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvName.setText(todos.get(position).getName());
        holder.tvPrice.setText(todos.get(position).getPrice());
        holder.cbBought.isChecked();
        if(!todos.isEmpty()) {

            if (todos.get(position).getType().equals("Grocery")) {
                holder.ivType.setImageResource(R.drawable.food);
            }
            if (todos.get(position).getType().equals("Electronics")) {
                holder.ivType.setImageResource(R.drawable.electronic);
            }
            if (todos.get(position).getType().equals("Books")) {
                holder.ivType.setImageResource(R.drawable.books);
            }
        }

        holder.cbBought.setChecked(todos.get(position).isBought());

        holder.cbBought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo todo = todos.get(position);
                todo.setBought(holder.cbBought.isChecked());

                todo.save();
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).showEditTodoActivity(todos.get(position), position);
            }
        });
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.tvDetails.getVisibility()==View.GONE) {
                    holder.tvDetails.setVisibility(View.VISIBLE);
                    holder.btnDetails.setText(R.string.removeDeets);
                }
                else if(holder.tvDetails.getVisibility()==View.VISIBLE){
                    holder.tvDetails.setVisibility(View.GONE);
                    holder.btnDetails.setText(R.string.viewDeets);
                }
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemDismiss(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void addTodo(Todo todo) {
        todos.add(0, todo);

        todo.save();
        notifyItemInserted(0);
    }

    public void removeTodo(int position) {
        todos.get(position).delete();
        todos.remove(position);
        notifyItemRemoved(position);
    }

    public void updateTodo(int index, Todo todo) {
        todos.set(index, todo);
        todo.save();
        notifyItemChanged(index);
    }
    public void removeAll(){
        todos.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onItemDismiss(int position) {
        removeTodo(position);
    }
    //Harrison and I did this part
    public void deletePurchased(){
        int i=0;
        while(i<todos.size()){
            if(todos.get(i).isBought()){
                Todo.delete(todos.get(i));
                todos.remove(i);
            }else{
                i++;
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(todos, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(todos, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView ivType;
        private TextView tvPrice;
        private CheckBox cbBought;
        private Button btnDelete;
        private Button btnEdit;
        private Button btnDetails;
        private TextView tvDetails;


        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            cbBought = (CheckBox) itemView.findViewById(R.id.cbBought);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            btnDetails = (Button) itemView.findViewById(R.id.btnDetails);
            ivType = (ImageView) itemView.findViewById(R.id.ivType);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            tvDetails=(TextView)itemView.findViewById(R.id.tvDetails);

        }
    }

}

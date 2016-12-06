package hu.bme.android.todorecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import hu.bme.android.todorecyclerview.adapter.TodoAdapter;
import hu.bme.android.todorecyclerview.adapter.TodoItemTouchHelperCallback;
import hu.bme.android.todorecyclerview.data.Todo;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_TODO = 101;
    public static final int REQUEST_CODE_EDIT_TODO = 102;
    public static final String KEY_EDIT = "KEY_EDIT";

    private TodoAdapter todoRecyclerAdapter;

    private Todo todoEditHolder;
    private int todoToEditPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        todoRecyclerAdapter = new TodoAdapter(this);
        final RecyclerView recyclerView =
                (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(todoRecyclerAdapter);

        ItemTouchHelper.Callback callback =
                new TodoItemTouchHelperCallback(todoRecyclerAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void showAddTodoActivity() {
        Intent intentAddTodo = new Intent(MainActivity.this, AddTodoActivity.class);
        startActivityForResult(intentAddTodo,
                REQUEST_CODE_ADD_TODO);
    }
    private void deleteAllActivity(){
        todoRecyclerAdapter.removeAll();
    }


    public void showEditTodoActivity(Todo todoToEdit, int position) {
        Intent intentEditTodo = new Intent(MainActivity.this,
                AddTodoActivity.class);
        todoEditHolder = todoToEdit;
        todoToEditPosition = position;

        intentEditTodo.putExtra(KEY_EDIT, todoToEdit);
        startActivityForResult(intentEditTodo, REQUEST_CODE_EDIT_TODO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == REQUEST_CODE_ADD_TODO) {
                    Todo todo = (Todo) data.getSerializableExtra(
                            AddTodoActivity.KEY_TODO);

                    todoRecyclerAdapter.addTodo(todo);
                } else if (requestCode == REQUEST_CODE_EDIT_TODO) {
                    Todo todoTemp = (Todo) data.getSerializableExtra(
                            AddTodoActivity.KEY_TODO);

                    todoEditHolder.setName(todoTemp.getName());
                    todoEditHolder.setBought(todoTemp.isBought());

                    if (todoToEditPosition != -1) {
                        todoRecyclerAdapter.updateTodo(todoToEditPosition, todoEditHolder);
                        todoToEditPosition = -1;
                    }else {
                        todoRecyclerAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case RESULT_CANCELED:
                Toast.makeText(MainActivity.this, R.string.text_cancelled, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.maddItem:
                showAddTodoActivity();
                break;
            case R.id.mdelaItem:
                deleteAllActivity();
                break;
            case R.id.mdelPurchased:
                todoRecyclerAdapter.deletePurchased();
                break;
            default:
                break;
        }

        return true;
    }
}

package hu.bme.android.todorecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import hu.bme.android.todorecyclerview.data.Todo;

public class AddTodoActivity extends AppCompatActivity {
    public static final String KEY_TODO = "KEY_TODO";
    private EditText etName;
    private EditText etPrice;
    private EditText etDetails;
    private CheckBox isBought;
    private Todo todoToEdit = null;
    public Spinner spinType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        if (getIntent().getSerializableExtra(MainActivity.KEY_EDIT) != null) {
            todoToEdit = (Todo) getIntent().getSerializableExtra(MainActivity.KEY_EDIT);
        }

        spinType=(Spinner)findViewById(R.id.spinType);
        etPrice=(EditText)findViewById(R.id.etPrice);
        etName = (EditText) findViewById(R.id.etName);
        isBought = (CheckBox) findViewById(R.id.isBought);
        etDetails=(EditText)findViewById(R.id.etDetails);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinType.setAdapter(adapter);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTodo();
            }
        });

        if (todoToEdit != null) {
            etPrice.setText(todoToEdit.getPrice());
            etName.setText(todoToEdit.getName());
            etDetails.setText(todoToEdit.getDetails());
            isBought.setChecked(todoToEdit.isBought());
        }
    }

    private void saveTodo() {
        if ("".equals(etName.getText().toString())) {
            etName.setError(getString(R.string.error_field_empty));
        } else {
            Intent intentResult = new Intent();
            Todo todoResult = null;
            if (todoToEdit != null) {
                todoResult = todoToEdit;
            } else {
                todoResult = new Todo();
            }

            todoResult.setName(etName.getText().toString());
            todoResult.setBought(isBought.isChecked());
            todoResult.setPrice(etPrice.getText().toString());
            todoResult.setDetails(etDetails.getText().toString());
            todoResult.setType(spinType.getSelectedItem().toString());

            intentResult.putExtra(KEY_TODO, todoResult);
            setResult(RESULT_OK, intentResult);
            finish();
        }
    }
}

package rp.edu.sg.c346.id20021576.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTaskPos;
    Button btnAdd;
    Button btnClear;
    ListView lvTask;

    Spinner spnAddRmv;
    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTaskPos = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.listViewTask);
        spnAddRmv = findViewById(R.id.spinner);
        btnDel = findViewById(R.id.buttonDel);

        ArrayList<String> tasks;
        tasks = new ArrayList<String>();

        ArrayAdapter aaTask = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks);
        lvTask.setAdapter(aaTask);
        String [] array = getResources().getStringArray(R.array.spinnerItems);
        ArrayAdapter<String> aa = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_item, array);
        spnAddRmv.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((etTaskPos.getText().toString().isEmpty()) == false)){
                    String taskName = etTaskPos.getText().toString();
                    tasks.add(taskName);
                    aaTask.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Enter a task!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tasks.isEmpty() == false){
                    int pos = Integer.parseInt(etTaskPos.getText().toString());
                    tasks.remove(pos);
                    aaTask.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "There are no tasks to delete!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tasks.isEmpty() == false){
                    tasks.clear();
                    aaTask.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "You dont have any task to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spnAddRmv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDel.setEnabled(false);
                        etTaskPos.setHint("Type in a new task here");
                        etTaskPos.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        etTaskPos.setHint("Type in the index of the task to be removed");
                        etTaskPos.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (btnDel.isEnabled()) {
                    String idTask = String.valueOf(position);
                    etTaskPos.setText(idTask);
                }
            }
        });


    }
}
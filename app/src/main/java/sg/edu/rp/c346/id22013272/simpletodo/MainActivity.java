package sg.edu.rp.c346.id22013272.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTask;
    Button btnAdd;
    Button btnClear;
    Button btnDel;
    ListView lvTask;
    Spinner spnAddDel;

    ArrayList<String> taskList;
    ArrayAdapter<String> adaptertask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask=findViewById(R.id.editTextTask);
        btnAdd=findViewById(R.id.btnAdd);
        btnClear=findViewById(R.id.btnClear);
        btnDel=findViewById(R.id.btnDel);
        lvTask=findViewById(R.id.lvtask);
        spnAddDel=findViewById(R.id.spinner);

        taskList=new ArrayList<>();
        adaptertask=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        lvTask.setAdapter(adaptertask);



        spnAddDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    etTask.setHint("Type in a new task");
                    btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String newTask = etTask.getText().toString();
                            taskList.add(newTask);
                            adaptertask.notifyDataSetChanged();
                            etTask.setText("");
                        }
                    });
                    btnDel.setEnabled(false);
                    break;
                case 1:
                    etTask.setHint("Type in index to remove task");
                    btnDel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int newpos=Integer.parseInt(etTask.getText().toString());
                            taskList.remove(newpos);
                            adaptertask.notifyDataSetChanged();
                            etTask.setText("");
                        }
                    });
                    btnDel.setEnabled(true);
                    btnAdd.setEnabled(false);
                    break;
            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.clear();
                adaptertask.notifyDataSetChanged();
            }
        });
        btnDel.setEnabled(true);
        btnAdd.setEnabled(true);
    }
}
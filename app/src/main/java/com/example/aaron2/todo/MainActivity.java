package com.example.aaron2.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> arrayListToDo;
    private ArrayAdapter<String> arrayAdapterToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayListToDo = new ArrayList<String>();
        arrayAdapterToDo = new ArrayAdapter<String>( this, R.layout.todo_row, R.id.textRow, arrayListToDo);

        ListView listView = findViewById(R.id.toDoList);
        listView.setAdapter(arrayAdapterToDo);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() != R.id.toDoList){
            return;
        }

        menu.setHeaderTitle("What do you want to do?");
        String[] options = {"Delete Task", "Return"};
        for(String option : options){
            menu.add(option);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selectedIndex = info.position;

        if(item.getTitle().equals("Delete Task")){
            arrayListToDo.remove(selectedIndex);
            arrayAdapterToDo.notifyDataSetChanged();
        }

        return true;
    }

    public void addButtonClick (View v){
        EditText editTextToDo = findViewById(R.id.inputText);
        String toDo = editTextToDo.getText().toString();
        if(toDo.isEmpty()){
            return;
        }

        arrayAdapterToDo.add(toDo);
        editTextToDo.setText("");
    }
}

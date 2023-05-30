package com.example.to_do_listapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
    private EditText ed;
    private ImageView adding_items;
    private ListView item_list;
    private ArrayList<String> values=new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = findViewById(R.id.edittext);
        adding_items = findViewById(R.id.add);
        item_list = findViewById(R.id.listview);

        adding_items.setOnClickListener(this);
        item_list.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String add_item=ed.getText().toString();
        if(values.contains(add_item))
        {
            Toast.makeText(getBaseContext(),"Item Already Exist", Toast.LENGTH_LONG).show();
        }
        else
        {
            values.add(add_item);
            adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values);
            item_list.setAdapter(adapter);
            ed.setText("");
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        final int removing_item=position;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to delete").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                values.remove(removing_item);
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Item Deleted", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Cancel", null).show();



        return true;
    }
}
package com.myjre.sqlitelearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CountryListActivity extends AppCompatActivity {
    private DBManager dbManager=new DBManager(this);
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from={DataBaseHelper.id,DataBaseHelper.subject,DataBaseHelper.desc};
    final int[] to={R.id.id,R.id.title,R.id.desc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);


        dbManager.open();
        Cursor cursor = dbManager.fetch();
        listView = findViewById(R.id.listview);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView idTextview = view.findViewById(R.id.id);
                TextView titleTextview = view.findViewById(R.id.title);
                TextView descTextview = view.findViewById(R.id.desc);


                String id = idTextview.getText().toString();
                String title = titleTextview.getText().toString();
                String desc = descTextview.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);
                startActivity(modify_intent);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.add_record){
            Intent add_mem=new Intent(this,AddCountryActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}
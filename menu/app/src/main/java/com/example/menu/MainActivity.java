package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, View.OnClickListener {

    private PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerForContextMenu(findViewById(R.id.hello));

        popupMenu = new PopupMenu(this, findViewById(R.id.hello));
        popupMenu.inflate(R.menu.popup);
        popupMenu.setOnMenuItemClickListener(this);

        findViewById(R.id.hello).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if(v.getId() == R.id.hello){
            getMenuInflater().inflate(R.menu.context, menu);
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.main_add:
                Toast.makeText(this, "выбран Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_create:
                Toast.makeText(this,"Выбоали Create", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_del:
                Toast.makeText(this, "Выбрали Delete", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.context_add:
                Toast.makeText(this, "Контекстное меню Add", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {

            if(item.getItemId()==R.id.popup_create) {
                Toast.makeText(this, "Попап меню Create", Toast.LENGTH_SHORT).show();
                return true;
            }
        return false;
    }

    @Override
    public void onClick(View v) {
        popupMenu.show();
    }
}
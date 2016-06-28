package com.example.lahiru.webapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Activity2 extends ActionBarActivity {
    EditText category, name, price, shop_name;
    String category1, name1, price1, shop_name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void RegUser(View view){
        category = (EditText) findViewById(R.id.editText1);
        name = (EditText) findViewById(R.id.editText2);
        shop_name = (EditText) findViewById(R.id.editText3);
        price = (EditText) findViewById(R.id.editText4);

        category1 = category.getText().toString();
        name1 = name.getText().toString();
        shop_name1 = shop_name.getText().toString();
        price1 = price.getText().toString();

        String method = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,category1,name1,shop_name1,price1);
        finish();

    }
}

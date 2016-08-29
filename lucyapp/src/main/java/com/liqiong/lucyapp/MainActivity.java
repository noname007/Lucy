package com.liqiong.lucyapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.liqiong.lucy.module.impl.LucyController;
import com.liqiong.lucy.module.impl.LucyKernel;

public class MainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LucyKernel.getInstance().initialize(this);
        tv = (TextView) findViewById(R.id.tv);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "i'm", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                LucyController.myHelp.fuck();
            }
        });

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DemoRequest() {
                    @Override
                    public void _onSuccess(String result) {
                        LucyController.uiHelp.toast("http://www.lqcode.com--->>>"+result);
                    }

                    @Override
                    public void _onFail(String result) {

                    }
                };
            }
        });
        findViewById(R.id.btn001).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new Demo2Request(){
                    @Override
                    public void _onSuccess(String result) {
                        LucyController.uiHelp.toast("http://www.baidu.com--->>>"+result);
                    }

                    @Override
                    public void _onFail(String result) {

                    }
                };
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}

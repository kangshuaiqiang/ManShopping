package bwie.com.manshopping.MyActivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import bwie.com.manshopping.R;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int timer = 3;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (timer > 0) {
                textView.setText(timer + "s");
                timer--;
                handler.sendEmptyMessageDelayed(0, 1000);
            } else {
                startActivity(new Intent(MainActivity.this, MyHome.class));
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.daojishi);
        handler.sendEmptyMessageDelayed(0, 0);

    }
}

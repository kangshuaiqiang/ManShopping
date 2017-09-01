package bwie.com.manshopping.Myfragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

import bwie.com.manshopping.MyActivity.MainActivity;
import bwie.com.manshopping.R;

public class MyPager2 extends AppCompatActivity {

    private TextView textView;
    private Boolean lager = true;
    private PopupWindow pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pager2);

        ImageView imageView = (ImageView) findViewById(R.id.goback);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MyPager2.this, MyHomePager.class));
//                overridePendingTransition(R.animator.animotion_in, R.animator.animotion_out);
                finish();
            }
        });
        textView = (TextView) findViewById(R.id.popWindow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lager) {
                    showPop();
                    lager = false;
                } else {
                    pop.dismiss();
                    lager = true;
                }

            }
        });


    }

    private void showPop() {
        View view = View.inflate(this, R.layout.pop, null);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        pop.showAsDropDown(textView);
        pop.update();
        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(new ColorDrawable(Color.RED));

        final TextView baby = view.findViewById(R.id.baby);
        baby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
                textView.setText(baby.getText());

            }
        });
        final TextView dainpu = view.findViewById(R.id.dianpu);
        dainpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
                textView.setText(dainpu.getText());

            }
        });

    }


}

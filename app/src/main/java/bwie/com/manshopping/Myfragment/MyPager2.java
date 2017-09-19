package bwie.com.manshopping.Myfragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bwie.com.manshopping.MyActivity.MainActivity;
import bwie.com.manshopping.MyActivity.ShoppingList;
import bwie.com.manshopping.MySqlite.MySqlite;
import bwie.com.manshopping.R;

public class MyPager2 extends AppCompatActivity {

    private TextView textView;
    private Boolean lager = true;
    private PopupWindow pop;
    private SQLiteDatabase writableDatabase;
    private EditText editText;
    private ListView listView;
    private ArrayList<Map<String, String>> arr = new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pager2);


        MySqlite mySqlite = new MySqlite(this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyPager2.this, ShoppingList.class);
                startActivity(intent);
            }
        });
        writableDatabase = mySqlite.getWritableDatabase();
        initSqLite();
        ImageView search = (ImageView) findViewById(R.id.search);
        editText = (EditText) findViewById(R.id.editsearch);

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
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(editText.getText().toString())) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", editText.getText().toString());
                    contentValues.put("label", textView.getText().toString());
                    writableDatabase.insert("sear", null, contentValues);
                    initSqLite();
                } else {
                    Toast.makeText(MyPager2.this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void initSqLite() {
        arr.clear();
        Cursor sear = writableDatabase.query("sear", null, null, null, null, null, null);
        while (sear.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            String name = sear.getString(sear.getColumnIndex("name"));
            String label = sear.getString(sear.getColumnIndex("label"));
            map.put("name", name);
            map.put("label", label);
            arr.add(map);
        }
        if (arr != null) {
            listView.setAdapter(new MyAdapter());

        }

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arr.size();
        }

        @Override
        public Object getItem(int i) {
            return arr.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = View.inflate(MyPager2.this, R.layout.listview, null);
            TextView view1 = v.findViewById(R.id.listviewText1);
            TextView view2 = v.findViewById(R.id.listviewText2);
            view1.setText(arr.get(i).get("name"));
            view2.setText(arr.get(i).get("label"));
            return v;
        }
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

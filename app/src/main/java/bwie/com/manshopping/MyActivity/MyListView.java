package bwie.com.manshopping.MyActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 黑白 on 2017/9/8.
 */

public class MyListView extends ListView {
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

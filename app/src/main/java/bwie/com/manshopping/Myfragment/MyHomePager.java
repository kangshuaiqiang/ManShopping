package bwie.com.manshopping.Myfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.zxing.integration.android.IntentIntegrator;

import bwie.com.manshopping.R;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by 黑白 on 2017/8/31.
 */

public class MyHomePager extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepager, container, false);
        ImageView imageView = view.findViewById(R.id.saoma);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.initiateScan();
            }
        });
        RelativeLayout relativeLayout = view.findViewById(R.id.reLativeLayout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MyPager2.class));

                getActivity().overridePendingTransition(R.animator.animotion_in, R.animator.animotion_out);
//                getActivity().finish();
            }
        });
        return view;
    }
}

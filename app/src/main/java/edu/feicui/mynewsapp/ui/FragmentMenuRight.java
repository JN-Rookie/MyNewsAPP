package edu.feicui.mynewsapp.ui;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import edu.feicui.mynewsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenuRight extends Fragment {
    private ImageView mIvLogin;

    public FragmentMenuRight() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_menu_right, container, false);
        mIvLogin= (ImageView) view.findViewById(R.id.iv_login);
        mIvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LogonScreenActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}

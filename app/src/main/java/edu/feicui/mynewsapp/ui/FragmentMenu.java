package edu.feicui.mynewsapp.ui;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import edu.feicui.mynewsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenu extends Fragment {
    private RelativeLayout[] rls =new RelativeLayout[5];

    public FragmentMenu() {
        // Required empty public constructor
    }


    //重写 onCreateView 方法，设置当前的布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 利用回调中的参数 LayoutInflater 对象导入布局文件，并发挥此 View
        View view=inflater.inflate(R.layout.fragment_menu,container,false);
        rls[0]= (RelativeLayout) view.findViewById(R.id.rl_news);
        rls[1]= (RelativeLayout) view.findViewById(R.id.rl_reading);
        rls[2]= (RelativeLayout) view.findViewById(R.id.rl_local);
        rls[3]= (RelativeLayout) view.findViewById(R.id.rl_commnet);
        rls[4]= (RelativeLayout) view.findViewById(R.id.rl_photo);
        return view;
    }

}

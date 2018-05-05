package com.n1njac.weread.view.fragment;
/*    
 *    Created by N1njaC on 2018/5/5.
 *    email:aiai173cc@gmail.com 
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.n1njac.weread.R;

public class RightMenuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_menu, container, false);
        return view;
    }
}

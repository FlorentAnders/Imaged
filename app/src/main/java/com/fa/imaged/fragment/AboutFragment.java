package com.fa.imaged.fragment;

import com.fa.imaged.R;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutFragment extends Fragment {
	     
    public static Fragment newInstance(Context context) {
        AboutFragment f = new AboutFragment();
 
        return f;
    }
     
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	  
	        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

         
	        return rootView;
	}
}

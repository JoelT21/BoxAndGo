package com.example.boxandgo;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LikedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LikedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LikedFragment newInstance(String param1, String param2) {
        LikedFragment fragment = new LikedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_liked, container, false);

        SharedPreferences sp = getActivity().getSharedPreferences("Shoes", Context.MODE_PRIVATE);
        boolean shoe1 = sp.getBoolean("bool1", false);
        boolean shoe2 = sp.getBoolean("bool2", false);
        boolean shoe3 = sp.getBoolean("bool3", false);

        ImageView liked1 = (ImageView)v.findViewById(R.id.imageView12);
        ImageView liked2 = (ImageView)v.findViewById(R.id.imageView13);
        ImageView liked3 = (ImageView) v.findViewById(R.id.imageView14);

        if(shoe1){
            liked1.setImageResource(R.drawable.yeezy_jpg);
        }
        else{
            liked1.setImageResource(R.drawable.default_shoe);
        }
        if(shoe2){
            liked2.setImageResource(R.drawable.jordan_3_jpy);
        }
        else{
            liked2.setImageResource(R.drawable.default_shoe);
        }
        if(shoe3){
            liked3.setImageResource(R.drawable.jordan_jpg);
        }
        else{
            liked3.setImageResource(R.drawable.default_shoe);
        }


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_liked, container, false);
        return v;
    }
}
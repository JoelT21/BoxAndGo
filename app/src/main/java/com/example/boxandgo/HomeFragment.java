package com.example.boxandgo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private boolean bool1 = false;
    private boolean bool2 = false;
    private boolean bool3 = false;

    private boolean buybool1 = false;
    private boolean buybool2 = false;
    private boolean buybool3 = false;



    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Button btn = (Button) v.findViewById(R.id.button);
        Button btn2 = (Button) v.findViewById(R.id.button4);
        Button btn3 = (Button) v.findViewById(R.id.button5);

        ImageView star_one = (ImageView)v.findViewById(R.id.imageViewStar);
        ImageView star_two = (ImageView)v.findViewById(R.id.imageViewStarTwo);
        ImageView star_three = (ImageView)v.findViewById(R.id.imageViewStarThree);

        SharedPreferences sp = getActivity().getSharedPreferences("Shoes", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        SharedPreferences sp1 = getActivity().getSharedPreferences("BuyShoe", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit1 = sp1.edit();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button clicked", "=============");
                Intent intent = new Intent((Homepage)getActivity(), Checkout.class);
                buybool1 = true;
                edit1.putBoolean("buy1", buybool1);
                edit1.commit();
                edit1.clear();
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button clicked", "=============");
                Intent intent = new Intent((Homepage)getActivity(), Checkout.class);
                buybool2 = true;
                edit1.putBoolean("buy2", buybool2);
                edit1.commit();
                edit1.clear();
                startActivity(intent);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button clicked", "=============");
                Intent intent = new Intent((Homepage)getActivity(), Checkout.class);
                buybool3 = true;
                edit1.putBoolean("buy3", buybool3);
                edit1.commit();
                edit1.clear();
                startActivity(intent);

            }
        });

        star_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(star_one.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_baseline_star_border_24).getConstantState()){
                    star_one.setImageResource(R.drawable.ic_baseline_star_24);
                    bool1 = true;
                    edit.putBoolean("bool1", bool1);
                    edit.commit();
                }
                else{
                    star_one.setImageResource(R.drawable.ic_baseline_star_border_24);
                    bool1 = false;
                    edit.putBoolean("bool1", bool1);
                    edit.commit();
                }


            }
        });

        star_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(star_two.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_baseline_star_border_24).getConstantState()){
                    star_two.setImageResource(R.drawable.ic_baseline_star_24);
                    bool2 = true;
                    edit.putBoolean("bool2", bool2);
                    edit.commit();
                }
                else{
                    star_two.setImageResource(R.drawable.ic_baseline_star_border_24);
                    bool2 = false;
                    edit.putBoolean("bool2", bool2);
                    edit.commit();
                }


            }
        });
        star_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(star_three.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_baseline_star_border_24).getConstantState()){
                    star_three.setImageResource(R.drawable.ic_baseline_star_24);
                    bool3 = true;
                    edit.putBoolean("bool3", bool3);
                    edit.commit();
                }
                else{
                    star_three.setImageResource(R.drawable.ic_baseline_star_border_24);
                    bool3 = false;
                    edit.putBoolean("bool3", bool3);
                    edit.commit();
                }


            }
        });

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
        return v;
    }
}
package com.example.boxandgo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView display_name = (TextView) v.findViewById(R.id.F_textView8);
        TextView actual_name= (TextView) v.findViewById(R.id.F_textViewProfile1);
        TextView actual_username = (TextView) v.findViewById(R.id.F_textViewProfile2);
        TextView actual_dob = (TextView) v.findViewById(R.id.F_textViewProfile3);
        TextView actual_email = (TextView) v.findViewById(R.id.F_textViewProfile4);

        SharedPreferences sp = getActivity().getSharedPreferences("Username", Context.MODE_PRIVATE);
        String username = sp.getString("username", "no username");

        String condition = "username="+'"'+username+'"';

        DBClass db = new DBClass((Homepage)getActivity(),"Info");
        String name = db.selectVal("name","Info",condition);
        String uname = db.selectVal("username","Info",condition);
        String dob = db.selectVal("date_of_birth","Info",condition);
        String email = db.selectVal("user_email","Info",condition);

        display_name.setText(name);
        actual_name.setText("Name: " + name);
        actual_username.setText("User: " + username);
        actual_dob.setText("Date of Birth: " + dob);
        actual_email.setText("Email: " + email);

        Button btn = (Button) v.findViewById(R.id.F_buttonProfile);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button clicked", "=============");
                Intent intent = new Intent((Homepage)getActivity(), Access.class);
                startActivity(intent);
            }
        });


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false);
        return v;
    }
}
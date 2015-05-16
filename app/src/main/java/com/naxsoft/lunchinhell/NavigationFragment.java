package com.naxsoft.lunchinhell;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class NavigationFragment extends Fragment {
    public NavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        final Context context = view.getContext();

        ImageView home = (ImageView) view.findViewById(R.id.navigationHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, VotingActivity.class));
            }
        });


        ImageView results = (ImageView) view.findViewById(R.id.navigationResults);
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), ResultsActivity.class));
            }
        });


        ImageView edit = (ImageView) view.findViewById(R.id.navigationEdit);
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), EditActivity.class));
            }
        });

        return view;
    }
}

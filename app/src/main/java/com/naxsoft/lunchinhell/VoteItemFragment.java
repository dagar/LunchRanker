package com.naxsoft.lunchinhell;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.naxsoft.lunchinhell.data.VoteDS;
import com.naxsoft.lunchinhell.domain.Restaurant;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VoteItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VoteItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoteItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "restaurantName";
    private static final String ARG_PARAM2 = "restaurantId";
    private static final String ARG_PARAM3 = "voteUp";
    private static final String ARG_PARAM4 = "voteDown";

    VoteDS voteDS = new VoteDS();

    // TODO: Rename and change types of parameters
    private String restaurantName;
    private int restaurantId;

    private boolean voteUpValue;
    private boolean voteDownValue;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param restaurant Parameter 1.
     * @return A new instance of fragment VoteItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VoteItemFragment newInstance(Restaurant restaurant) {
        VoteItemFragment fragment = new VoteItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, restaurant.getName());
        args.putInt(ARG_PARAM2, restaurant.getId());
        args.putBoolean(ARG_PARAM3, restaurant.getVoteType().isVoteUp());
        args.putBoolean(ARG_PARAM4, restaurant.getVoteType().isVoteDown());

        fragment.setArguments(args);
        return fragment;
    }

    public VoteItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurantName = getArguments().getString(ARG_PARAM1);
            restaurantId = getArguments().getInt(ARG_PARAM2);
            voteUpValue = getArguments().getBoolean(ARG_PARAM3);
            voteDownValue = getArguments().getBoolean(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_vote_item, container, false);
        TextView restaurantTextView= (TextView) rootView.findViewById(R.id.restaurantName);
        restaurantTextView.setText(restaurantName);

        final ImageView voteUpImageView = (ImageView) rootView.findViewById(R.id.voteUp);
        final ImageView voteDownImageView = (ImageView) rootView.findViewById(R.id.voteDown);

        if (voteUpValue)   {
            voteUpImageView.setBackgroundColor(Color.RED);
            voteUpImageView.setEnabled(false);
        }
        if (voteDownValue) {
            voteDownImageView.setBackgroundColor(Color.RED);
            voteDownImageView.setEnabled(false);
        }

        voteUpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteUpImageView.setEnabled(false);
                voteUpImageView.setBackgroundColor(Color.RED);
                voteUpValue = true;

                voteDownImageView.setEnabled(true);
                voteDownImageView.setBackgroundColor(Color.WHITE);
                voteDownValue = false;


                Toast.makeText(v.getContext(), "Sending voteUp for " + restaurantName + " id " + restaurantId, Toast.LENGTH_SHORT).show();
                voteDS.submitVote(restaurantId, 1);
            }
        });

        voteDownImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteDownImageView.setEnabled(false);
                voteDownImageView.setBackgroundColor(Color.RED);
                voteDownValue = true;

                voteUpImageView.setEnabled(true);
                voteUpImageView.setBackgroundColor(Color.WHITE);
                voteUpValue = false;

                Toast.makeText(v.getContext(), "Sending voteDown for " + restaurantName + " id " + restaurantId, Toast.LENGTH_SHORT).show();
                voteDS.submitVote(restaurantId, -1);

            }
        });


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

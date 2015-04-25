package com.naxsoft.lunchinhell;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naxsoft.lunchinhell.data.VoteDS;
import com.naxsoft.lunchinhell.domain.Restaurant;
import com.naxsoft.lunchinhell.domain.Vote;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VoteResultFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VoteResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoteResultFragment extends Fragment {
    private static final String ARG_PARAM1 = "restaurantName";
    private static final String ARG_PARAM2 = "restaurantId";
    private static final String ARG_PARAM3 = "voteCount";

    // TODO: Rename and change types of parameters
    private String restaurantName;
    private int restaurantId;
    private int voteCount;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param restaurant Parameter 1.
     * @return A new instance of fragment VoteResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VoteResultFragment newInstance(Restaurant restaurant) {
        VoteResultFragment fragment = new VoteResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, restaurant.getName());
        args.putInt(ARG_PARAM2, restaurant.getId());
        args.putInt(ARG_PARAM3, restaurant.getVoteCount());
        fragment.setArguments(args);
        return fragment;
    }

    public VoteResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurantName = getArguments().getString(ARG_PARAM1);
            restaurantId = getArguments().getInt(ARG_PARAM2);
            voteCount = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_vote_result, container, false);


        TextView restaurantTextView= (TextView) rootView.findViewById(R.id.restaurantName);
        restaurantTextView.setText(restaurantName);

        TextView voteCountTextView= (TextView) rootView.findViewById(R.id.voteCount);
        voteCountTextView.setText("Vote count: " + voteCount);
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
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
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

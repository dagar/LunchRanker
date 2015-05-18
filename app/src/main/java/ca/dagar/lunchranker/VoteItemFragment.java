package ca.dagar.lunchranker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ca.dagar.lunchranker.data.VoteDS;
import ca.dagar.lunchranker.domain.Restaurant;
import ca.dagar.lunchranker.domain.Vote;
import ca.dagar.lunchranker.service.RESTService;
import ca.dagar.lunchranker.service.WebHelper;


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
    private static final String ARG_PARAM3 = "voteValue";

    VoteDS voteDS = new VoteDS();

    // TODO: Rename and change types of parameters
    private String restaurantName;
    private int restaurantId;
    private boolean voteValue;

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
        args.putBoolean(ARG_PARAM3, restaurant.isVoteValue());

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
            voteValue = getArguments().getBoolean(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(ca.dagar.lunchranker.R.layout.fragment_vote_item, container, false);
        TextView restaurantTextView= (TextView) rootView.findViewById(ca.dagar.lunchranker.R.id.restaurantName);
        restaurantTextView.setText(restaurantName);

        final ImageView voteUpImageView = (ImageView) rootView.findViewById(ca.dagar.lunchranker.R.id.voteUp);
        final ImageView voteDownImageView = (ImageView) rootView.findViewById(ca.dagar.lunchranker.R.id.voteDown);

        if (voteValue)   {
            voteUpImageView.setBackgroundColor(Color.RED);
            voteUpImageView.setEnabled(false);
        } else {
            voteDownImageView.setBackgroundColor(Color.RED);
            voteDownImageView.setEnabled(false);
        }

        voteUpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WebHelper.isOnline(v.getContext())) {
                    voteValue = true;

                    voteUpImageView.setEnabled(false);
                    voteUpImageView.setBackgroundColor(Color.RED);
                    voteDownImageView.setEnabled(true);
                    voteDownImageView.setBackgroundColor(Color.WHITE);


                    Toast.makeText(v.getContext(), "Sending voteUp for " + restaurantName + " id " + restaurantId, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(v.getContext(), RESTService.class);
                    intent.setAction(RESTService.SAVE_VOTE);
                    intent.putExtra("vote", new Vote(restaurantId, 1));
                    v.getContext().startService(intent);
                } else {
                    Toast.makeText(v.getContext(), "No Network Connectivity.", Toast.LENGTH_LONG).show();
                }
            }
        });

        voteDownImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WebHelper.isOnline(v.getContext())) {
                    voteValue = false;

                    voteDownImageView.setEnabled(false);
                    voteDownImageView.setBackgroundColor(Color.RED);

                    voteUpImageView.setEnabled(true);
                    voteUpImageView.setBackgroundColor(Color.WHITE);

                    Toast.makeText(v.getContext(), "Sending voteDown for " + restaurantName + " id " + restaurantId, Toast.LENGTH_SHORT).show();
                    // voteDS.submitVote(new Vote(restaurantId, -1));

                    Intent intent = new Intent(v.getContext(), RESTService.class);
                    intent.setAction(RESTService.SAVE_VOTE);
                    intent.putExtra("vote", new Vote(restaurantId, 0));
                    v.getContext().startService(intent);
                } else {
                    Toast.makeText(v.getContext(), "No Network Connectivity.", Toast.LENGTH_LONG).show();
                }
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

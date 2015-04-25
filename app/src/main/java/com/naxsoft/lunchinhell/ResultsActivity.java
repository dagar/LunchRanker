package com.naxsoft.lunchinhell;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.naxsoft.lunchinhell.data.VoteDS;
import com.naxsoft.lunchinhell.domain.Restaurant;
import com.naxsoft.lunchinhell.domain.Vote;

import java.util.SortedSet;


public class ResultsActivity extends Activity implements NavigationFragment.OnFragmentInteractionListener, VoteResultFragment.OnFragmentInteractionListener {

    VoteDS voteDS = new VoteDS();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        SortedSet<Vote> votes = voteDS.getVotes();

        for (Vote v : votes) {
            transaction.add(R.id.resultList, VoteResultFragment.newInstance(v));
        }

        transaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test", "value");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void showActivity(Class activityClass) {
        startActivity(new Intent(this, activityClass));
    }

    public void showHome() {
        Toast.makeText(this, "Main clicked…", Toast.LENGTH_LONG).show();
        startActivity(new Intent(Intent.ACTION_MAIN));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "onFragmentInteraction " + uri, Toast.LENGTH_LONG).show();
    }
}

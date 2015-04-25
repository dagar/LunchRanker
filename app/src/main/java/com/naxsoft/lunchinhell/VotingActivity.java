package com.naxsoft.lunchinhell;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class VotingActivity extends Activity implements VoteItemFragment.OnFragmentInteractionListener, NavigationFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0 ; i < 3; i++) {
            transaction.add(R.id.votingList, VoteItemFragment.newInstance("Test " + 1, "" + i), "" + i);
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

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "onFragmentInteraction " + uri, Toast.LENGTH_LONG).show();
    }
}

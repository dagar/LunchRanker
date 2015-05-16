package com.naxsoft.lunchinhell;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.naxsoft.lunchinhell.data.RestaurantDS;
import com.naxsoft.lunchinhell.domain.Restaurant;
import com.naxsoft.lunchinhell.service.RESTService;
import com.naxsoft.lunchinhell.service.WebHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;


public class VotingActivity extends Activity implements VoteItemFragment.OnFragmentInteractionListener  {

    /**
     * The listener that responds to intents sent back from the service
     */
    private BroadcastReceiver onNotice = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Restaurant> restaurants = intent.getParcelableArrayListExtra("restaurants");
            renderRestaurants(restaurants);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        tryRenderRestaurants();
    }

    private void tryRenderRestaurants() {
        if(!WebHelper.isOnline(this)) {
            Toast.makeText(this, "No Network Connectivity.", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(VotingActivity.this, RESTService.class);
            intent.setAction(RESTService.LIST_RESTAURANTS);
            startService(intent);
        }
    }

    private void renderRestaurants(Collection<Restaurant> restaurants) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (Restaurant r : restaurants) {
            transaction.add(R.id.votingList, VoteItemFragment.newInstance(r));
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
        IntentFilter filter = new IntentFilter(RESTService.REFRESH_RESTAURANTS);
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(onNotice);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "onFragmentInteraction " + uri, Toast.LENGTH_LONG).show();
    }
}

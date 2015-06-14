package ca.dagar.lunchranker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ca.dagar.lunchranker.asyncTasks.GetPlacesTask;


public class EditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        GetPlacesTask placesTask = new GetPlacesTask();
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

    private void showConfirm() {
        new AlertDialog.Builder(this).setTitle("Please confirm")
                .setMessage("Are you sure you want to delete this restaurant?")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // TODO
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO
                                dialog.dismiss();
                            }
                        })
                .create()
                .show();
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
}

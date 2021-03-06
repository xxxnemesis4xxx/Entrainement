package lauzon.levis.mag.entrainement;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import lauzon.levis.mag.Goal.Goals;
import lauzon.levis.mag.Models.ModelPanel;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate(R.menu.menu_main, menu);
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

    public void changeDisplayToModele(View view) {
        Intent intent = new Intent(this, ModelPanel.class);
        startActivity(intent);
    }

    public void changeDisplayToPlanifier(View view) {
        Intent intent = new Intent(this, Planifier.class);
        startActivity(intent);
    }

    public void changeDisplayToGoals(View view) {
        Intent intent = new Intent(this,Goals.class);
        startActivity(intent);
    }


}

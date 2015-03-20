package lauzon.levis.mag.entrainement;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Modele extends Activity {

    private int mActiviteCounter = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modele);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modele, menu);
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

    public void newExercice(View view) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        TextView tv = new TextView(this);
        tv.setText("Nom de l'exercice " + String.valueOf(mActiviteCounter) + " :");
        tv.setId(mActiviteCounter);

        if( mActiviteCounter != 1 ){
            params.addRule(RelativeLayout.BELOW, tv.getId() - 1 );
        }

        tv.setLayoutParams(params);
        tv.setTextSize(20);
        tv.setId(mActiviteCounter);

        ++mActiviteCounter;
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.infos);
        layout.addView(tv);
    }
}

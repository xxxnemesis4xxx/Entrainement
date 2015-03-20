package lauzon.levis.mag.Models;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lauzon.levis.mag.database.EntrainementDatasource;
import lauzon.levis.mag.database.model;
import lauzon.levis.mag.entrainement.R;

public class ViewModels extends Activity {
    private EntrainementDatasource datasource;
    private int previousId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_models);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        //Get our Database
        datasource = new EntrainementDatasource(this);
        datasource.open();

        //Get out List of Models
        List<model> values = datasource.getAllModels();

        for (int i = 0; i < values.size(); i++) {
            LoadModelsLayout(values.get(i));
            previousId = (int)values.get(i).getId();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_models, menu);
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

    public void LoadModelsLayout(model value) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.relativeLayout);

        TextView tv = new TextView(this);
        tv.setText(value.getNom());
        tv.setLayoutParams(params);
        tv.setTextSize(20);
        tv.setId((int) value.getId());
        tv.setHeight(90);
        params.setMargins(0,20,0,0);

        if (previousId > 0) {
            params.addRule(RelativeLayout.BELOW, previousId);
        }

        Button button = new Button(this);
        button.setText("Voir les exercices");
        button.setLayoutParams(params2);
        button.setHeight(20);

        params2.addRule(RelativeLayout.RIGHT_OF, tv.getId());
        params2.addRule(RelativeLayout.BELOW, previousId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context,"OUI!",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        layout.addView(tv);
        layout.addView(button);
    }
}

package lauzon.levis.mag.Models;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import lauzon.levis.mag.Goal.Goals;
import lauzon.levis.mag.database.EntrainementDatasource;
import lauzon.levis.mag.database.exercice;
import lauzon.levis.mag.entrainement.R;

public class ViewExercice extends Activity {
    private EntrainementDatasource datasource;
    private int mCounterExercices = 1;
    private int previousId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercice);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        Bundle bundle = getIntent().getExtras();
        long id = bundle.getLong("ID");

        //Get our Database
        datasource = new EntrainementDatasource(this);
        datasource.open();

        //Get out List of Models
        List<exercice> values = datasource.getALLExerciceForOneModel(id);

        for (int i = 0; i < values.size(); i++) {
            LoadExerciceLayout(values.get(i));
            mCounterExercices++;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_exercice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void LoadExerciceLayout(final exercice value) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.LayoutExercice);

        final TextView tv = new TextView(this);
        tv.setText("Exercice " + String.valueOf(mCounterExercices) + " : " + String.valueOf(value.getNom()));
        tv.setLayoutParams(params);
        tv.setTextSize(20);
        tv.setId(mCounterExercices);
        tv.setHeight(90);
        tv.setWidth(400);

        if (mCounterExercices > 1) {
            params.addRule(RelativeLayout.BELOW, tv.getId() - 1);
            params2.addRule(RelativeLayout.RIGHT_OF, tv.getId() - 1);
            params2.addRule(RelativeLayout.BELOW, tv.getId() - 1);
        } else {
            params2.addRule(RelativeLayout.RIGHT_OF, tv.getId());
        }

        Button button = new Button(this);
        button.setText("Objectif");
        button.setLayoutParams(params2);
        button.setHeight(20);
        button.setWidth(150);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Goals.class);
                intent.putExtra("ID",value.getId());
                startActivity(intent);
            }
        });

        layout.addView(tv);
        layout.addView(button);
    }
}

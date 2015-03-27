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

        datasource = new EntrainementDatasource(this);
        datasource.open();

        List<model> values = datasource.getAllModels();

        for (int i = 0; i < values.size(); i++) {
            LoadModelsLayout(values.get(i));
            previousId = (int)values.get(i).getId();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_models, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void LoadModelsLayout(final model value) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.relativeLayout);

        final TextView tv = new TextView(this);
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
        button.setBackgroundColor(0xffd59900);
        params2.setMargins(10,0,0,0);

        params2.addRule(RelativeLayout.RIGHT_OF, tv.getId());
        params2.addRule(RelativeLayout.BELOW, previousId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ViewExercice.class);
                intent.putExtra("ID",value.getId());
                startActivity(intent);
            }
        });

        layout.addView(tv);
        layout.addView(button);
    }

    public void closeCurrentIntent(View view) {
        finish();
    }
}

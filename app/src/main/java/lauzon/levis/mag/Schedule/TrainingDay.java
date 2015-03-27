package lauzon.levis.mag.Schedule;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import java.sql.Date;

import lauzon.levis.mag.database.EntrainementDatasource;
import lauzon.levis.mag.database.model;
import lauzon.levis.mag.entrainement.R;

public class TrainingDay extends Activity {
    private EntrainementDatasource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_training_day);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        datasource = new EntrainementDatasource(this);
        datasource.open();

        List<model> values = datasource.getAllModels();

        ArrayAdapter<model> adapter = new ArrayAdapter<model>(this,
                android.R.layout.simple_list_item_1, values);

        final ListView list = (ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Context context = getApplicationContext();
                Bundle bundle = getIntent().getExtras();

                //Get Date
                Calendar calendar = Calendar.getInstance();
                calendar.set(bundle.getInt("year"), bundle.getInt("month"), bundle.getInt("day"));

                //Get Model Id
                Object o = list.getItemAtPosition(position);
                model str=(model)o;

                datasource.createTrainingDay(calendar.getTime().getTime(), String.valueOf(((model) o).getId()));

                Toast toast = Toast.makeText(context,"Sauvegarde Réussi",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_training_day, menu);
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

    public void changeDisplayToActivity(View view) {
        finish();
    }
}

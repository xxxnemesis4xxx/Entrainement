package lauzon.levis.mag.Schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import lauzon.levis.mag.database.EntrainementDatasource;
import lauzon.levis.mag.database.model;
import lauzon.levis.mag.entrainement.R;

public class TrainingDay extends Activity {
    private EntrainementDatasource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_day);

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

                Object o = list.getItemAtPosition(position);
                model str=(model)o;//As you are using Default String Adapter
                Toast.makeText(getBaseContext(), ((model) o).getNom(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_training_day, menu);
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

    public void changeDisplayToActivity(View view) {
        finish();
    }
}

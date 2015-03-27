package lauzon.levis.mag.Goal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import lauzon.levis.mag.Models.ModelPanel;
import lauzon.levis.mag.database.EntrainementDatasource;
import lauzon.levis.mag.database.exercice;
import lauzon.levis.mag.entrainement.R;


public class Goals extends Activity {
    private EntrainementDatasource datasource;
    private long idExercice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_goals);

        Bundle bundle = getIntent().getExtras();
        long id = bundle.getLong("ID");
        boolean buttonSauv = bundle.getBoolean("mod");

        if (!buttonSauv) {
            Button button = (Button)findViewById(R.id.saveGoal);
            button.setVisibility(View.INVISIBLE);

            EditText etTextGoal = (EditText)findViewById(R.id.etGoal);
            etTextGoal.setEnabled(false);
        }

        //Get our Database
        datasource = new EntrainementDatasource(this);
        datasource.open();

        //Get out List of Models
        exercice value = datasource.getExercice(id);
        idExercice = value.getId();

        if (value.getGoal() == null || value.getGoal().isEmpty()) {
            EditText et = (EditText)findViewById(R.id.etGoal);
            et.setText("Vous avez aucun objectif pour cet exercice");
        } else {
            EditText et = (EditText)findViewById(R.id.etGoal);
            et.setText(value.getGoal());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_goals, menu);
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

    public void closeCurrentIntent(View view) {
        finish();
    }

    public void saveGoalInDB(View view) {
        EditText et = (EditText)findViewById(R.id.etGoal);
        datasource.updateExercice(et.getText().toString(),idExercice);
    }
}

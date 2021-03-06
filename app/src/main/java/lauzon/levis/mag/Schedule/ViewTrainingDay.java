package lauzon.levis.mag.Schedule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import lauzon.levis.mag.Goal.Goals;
import lauzon.levis.mag.database.EntrainementDatasource;
import lauzon.levis.mag.database.exercice;
import lauzon.levis.mag.entrainement.R;


public class ViewTrainingDay extends Activity {
    private EntrainementDatasource datasource;
    private int mCounterExercices = 1;
    private int previousId;
    private long ID;
    private int Completed;
    private String infoSuppl;
    private float Rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_view_training_day);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        Bundle bundle = getIntent().getExtras();
        ID = bundle.getLong("modelID");
        Completed = bundle.getInt("statut");
        infoSuppl = bundle.getString("infoSuppl");
        Rating = bundle.getFloat("rating");

        //Set Underline Text
        String udata="Vos Exercices";
        SpannableString content = new SpannableString(udata);
        content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
        TextView txtTitle = (TextView)findViewById(R.id.textView8);
        txtTitle.setText(content);

        //Get our Database
        datasource = new EntrainementDatasource(this);
        datasource.open();

        //Get out List of Models
        List<exercice> values = datasource.getALLExerciceForOneModel(ID);

        for (int i = 0; i < values.size(); i++) {
            LoadExerciceLayout(values.get(i));
            mCounterExercices++;
        }

        //Look if current day is the same as the trainign Day
        Calendar today = Calendar.getInstance();
        Calendar trainingDay = Calendar.getInstance();
        trainingDay.setTimeInMillis(bundle.getLong("Date"));

        //Set Info for Completing Training
        if (today.get(Calendar.DAY_OF_YEAR) >= trainingDay.get(Calendar.DAY_OF_YEAR)) {
            LoadCompletingTraining();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_training_day, menu);
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

    private void LoadExerciceLayout(final exercice value) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.relativeTrainingDay);

        final TextView tv = new TextView(this);
        tv.setText("Exercice " + String.valueOf(mCounterExercices) + " : " + String.valueOf(value.getNom()));
        tv.setLayoutParams(params);
        tv.setTextSize(25);
        tv.setId(mCounterExercices);
        tv.setHeight(90);
        tv.setWidth(400);
        params.setMargins(0,20,0,0);

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
        button.setBackgroundColor(0xffd59900);
        params2.setMargins(10,0,0,25);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Goals.class);
                intent.putExtra("ID",value.getId());
                intent.putExtra("mod",false);
                startActivity(intent);
            }
        });

        layout.addView(tv);
        layout.addView(button);
    }

    private void LoadCompletingTraining() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params4.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.relativeTrainingDay);

        //Text
        final TextView tv = new TextView(this);
        tv.setText("Notez votre effort :");
        tv.setLayoutParams(params);
        tv.setTextSize(25);
        tv.setId(mCounterExercices);
        tv.setHeight(90);
        params.addRule(RelativeLayout.BELOW, tv.getId() - 1);
        params.setMargins(0,20,0,0);
        mCounterExercices++;

        //Rating Bar
        final RatingBar rbRate = new RatingBar(this);
        rbRate.setId(mCounterExercices);
        rbRate.setLayoutParams(params2);
        rbRate.setNumStars(5);
        params2.addRule(RelativeLayout.BELOW,tv.getId());
        mCounterExercices++;

        //Text
        final TextView tv2 = new TextView(this);
        tv2.setText("Information Supplémentaire");
        tv2.setLayoutParams(params3);
        tv2.setTextSize(25);
        tv2.setId(mCounterExercices);
        tv2.setHeight(90);
        params3.addRule(RelativeLayout.BELOW, rbRate.getId());
        params3.setMargins(0,40,0,0);
        mCounterExercices++;

        //EditText
        final EditText etInfoSupp = new EditText(this);
        etInfoSupp.setId(mCounterExercices);
        etInfoSupp.setLayoutParams(params4);
        etInfoSupp.setHeight(450);
        etInfoSupp.setGravity(Gravity.TOP);
        params4.addRule(RelativeLayout.BELOW, tv2.getId());
        mCounterExercices++;


        //Button for Saving
        final Button button = new Button(this);
        button.setText("Compléter l'entrainement");
        button.setLayoutParams(params5);
        button.setHeight(20);
        button.setId(mCounterExercices);
        button.setBackgroundColor(0xffd59900);
        params5.addRule(RelativeLayout.BELOW, etInfoSupp.getId());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();

                //Get Info Suppl
                String textSuppl = etInfoSupp.getText().toString();
                float intRating = rbRate.getRating();

                datasource.updateTraining(textSuppl,intRating,ID);

                Toast toast = Toast.makeText(context,"Sauvegarde Réussi",Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });
        mCounterExercices++;

        if (Completed == 1) {
            etInfoSupp.setText(infoSuppl);
            etInfoSupp.setEnabled(false);

            rbRate.setRating(Rating);
            rbRate.setEnabled(false);
        }

        layout.addView(tv);
        layout.addView(rbRate);
        layout.addView(tv2);
        layout.addView(etInfoSupp);
        if (Completed != 1) {
            layout.addView(button);
        }
    }

    public void closeCurrentIntent(View view) {
        finish();
    }
}

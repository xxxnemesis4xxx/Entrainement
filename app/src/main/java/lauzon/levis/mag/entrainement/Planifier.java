package lauzon.levis.mag.entrainement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import lauzon.levis.mag.Schedule.TrainingDay;
import lauzon.levis.mag.Schedule.ViewTrainingDay;
import lauzon.levis.mag.database.EntrainementDatasource;
import lauzon.levis.mag.database.entrainement;
import lauzon.levis.mag.database.model;

public class Planifier extends Activity {
    private boolean bButtonClicked = false;
    private EntrainementDatasource datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planifier);

        Calendar calendar = Calendar.getInstance();
        int currentDayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if (currentDayOfWeek != 1)
        {
            calendar.set(Calendar.DAY_OF_WEEK,1);
        }

        long datemin = calendar.getTime().getTime() - 1000;

        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        long datemax = calendar.getTime().getTime() - 1000;


        CalendarView cal = (CalendarView) findViewById(R.id.calendarView);
        cal.setMaxDate(datemax);
        cal.setMinDate(datemin);

        //Set onclick Listener
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                if (bButtonClicked == false) {
                    Intent intent = new Intent(getBaseContext(), TrainingDay.class);
                    intent.putExtra("year",year);
                    intent.putExtra("month",month);
                    intent.putExtra("day",dayOfMonth);
                    startActivity(intent);
                }
            }
        });

        datasource = new EntrainementDatasource(this);
        datasource.open();

        List<entrainement> values = datasource.getAllEntrainements(datemin, datemax);
        ArrayAdapter<entrainement> adapter = new ArrayAdapter<entrainement>(this,
                android.R.layout.simple_list_item_1, values);

        final ListView list = (ListView)findViewById(R.id.listView2);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getBaseContext(), ViewTrainingDay.class);
                entrainement Entrainement = (entrainement)parent.getItemAtPosition(position);
                intent.putExtra("ID", Entrainement.getId());
                intent.putExtra("modelID", Entrainement.getRefidmodel());
                intent.putExtra("Date", Entrainement.getDate());
                intent.putExtra("statut",Entrainement.getCompleted());
                intent.putExtra("infoSuppl",Entrainement.getInfosupp());
                intent.putExtra("rating",Entrainement.getRating());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_planifier, menu);
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

    public void nextWeek(View view) {
            bButtonClicked = true;
            CalendarView cal = (CalendarView) findViewById(R.id.calendarView);

            //Set the time
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(cal.getMinDate());

            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            long datemin = calendar.getTime().getTime() - 1000;
            calendar.getTime().getTime();

            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            long datemax = calendar.getTime().getTime() - 1000;

            cal.setMinDate(datemin);
            cal.setMaxDate(datemax);

            bButtonClicked = false;
    }

    public void previousWeek(View view) {
        bButtonClicked = true;
        CalendarView cal = (CalendarView) findViewById(R.id.calendarView);

        //Set the time
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(cal.getMinDate());

        long datemax = calendar.getTime().getTime() + 1000;
        calendar.getTime().getTime();

        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        long datemin = calendar.getTime().getTime() + 1000;

        cal.setMinDate(datemin);
        cal.setMaxDate(datemax);
        bButtonClicked = false;
    }

    public void changeDisplayToActivity(View view) {
        finish();
    }
}

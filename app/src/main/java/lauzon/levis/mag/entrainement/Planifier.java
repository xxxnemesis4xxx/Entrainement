package lauzon.levis.mag.entrainement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;


public class Planifier extends Activity {
    private boolean bButtonClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planifier);

        Calendar calendar = Calendar.getInstance();
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
                    Context context = getApplicationContext();
                    String dateAffichage = String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);
                    Toast toast = Toast.makeText(context, dateAffichage, Toast.LENGTH_SHORT);
                    toast.show();
                }
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

        //calendar.add(Calendar.WEEK_OF_YEAR, -1);
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

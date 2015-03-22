package lauzon.levis.mag.entrainement;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Date;


public class Planifier extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planifier);

        Calendar calendar = Calendar.getInstance();
        long datemin = calendar.getTime().getTime() - 1000;

        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        long datemax = calendar.getTime().getTime();


        CalendarView cal = (CalendarView) findViewById(R.id.calendarView);
        cal.setMaxDate(datemax);
        cal.setMinDate(datemin);
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
        CalendarView cal = (CalendarView) findViewById(R.id.calendarView);

        //Set the time
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(cal.getMinDate());

        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        long datemin = calendar.getTime().getTime();
        calendar.getTime().getTime();

        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        long datemax = calendar.getTime().getTime();

        cal.setMaxDate(datemax);
        cal.setMinDate(datemin);
    }

    public void previousWeek() {

    }

}

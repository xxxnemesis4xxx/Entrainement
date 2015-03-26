package lauzon.levis.mag.Models;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import lauzon.levis.mag.entrainement.R;

public class ModelPanel extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_panel);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_model_panel, menu);
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

    public void changeDisplayToCreateModel(View view) {
        Intent intent = new Intent(this, CreateModel.class);
        startActivity(intent);
    }

    public void changeDisplayToViewModels(View view) {
        Intent intent = new Intent(this, ViewModels.class);
        startActivity(intent);
    }

    public void changeDisplayToDeleteModel(View view) {
        Intent intent = new Intent(this, Delete.class);
        startActivity(intent);
    }
}

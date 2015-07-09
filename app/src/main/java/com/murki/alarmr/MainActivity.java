package com.murki.alarmr;

import android.databinding.DataBindingUtil;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.murki.alarmr.databinding.ActivityMainBinding;
import com.murki.alarmr.viewmodels.MainActivityVM;

public class MainActivity extends AppCompatActivity implements TimePickerDialogFragment.TimePickerDialogFragmentCallbacks {

    private MainActivityVM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vm = new MainActivityVM("Miguelito", "https://avatars3.githubusercontent.com/u/216735");
        binding.setViewModel(vm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void timeUpdated(int hour, int minute) {
        vm.timeUpdated(findViewById(R.id.root), hour, minute);
    }

    public void pickTime(View view) {
        vm.pickTime(getSupportFragmentManager());
    }

}

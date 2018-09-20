package edu.byui.bra16024.nflpickem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import java.util.Map;

public class Weekly_Schedule extends AppCompatActivity {
    Map<String, Team> league;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly__schedule);
        league = (Map)getIntent().getSerializableExtra("League");
    }

    public void loadWeek(View v) {
        Spinner weekSelect = findViewById(R.id.weekSelect);
        LinearLayout linearLayout = findViewById(R.id.games);
        linearLayout.removeAllViews();
        String week = weekSelect.getSelectedItem().toString();

        week = week.replace(" ", "=");

        AsyncLoadWeek asyncLoadWeek = new AsyncLoadWeek(league, getApplicationContext(), linearLayout, week);
        asyncLoadWeek.execute();
    }


}

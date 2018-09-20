package edu.byui.bra16024.nflpickem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private Map<String, Team> league;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        league = new TreeMap<>();
        setUp();
        load();


        String[] teams = {"Arizona","Atlanta","Baltimore","Buffalo","Carolina","Chicago","Cincinnati",
                "Cleveland","Dallas","Denver","Detroit","Green Bay","Houston","Indianapolis","Jacksonville",
                "Kansas City","LA Chargers","LA Rams","Miami","Minnesota","NY Giants","NY Jets","New England",
                "New Orleans","Oakland","Philadelphia","Pittsburgh","San Francisco","Seattle","Tampa Bay",
                "Tennessee","Washington"};
        Integer[] images = {R.drawable.arizonacardinalslogo, R.drawable.atlantafalconslogo,
                R.drawable.baltimoreravenslogo, R.drawable.buffalobillslogo, R.drawable.carolinapantherslogo,
                R.drawable.chicagobearslogo, R.drawable.cincinnatibengalslogo, R.drawable.clevelandbrownslogo,
                R.drawable.dallascowboyslogo, R.drawable.denverbroncoslogo, R.drawable.detroitlionslogo,
                R.drawable.greenbaypackerslogo, R.drawable.houstontexanslogo, R.drawable.indianapoliscoltslogo,
                R.drawable.jacksonvillejaguarslogo, R.drawable.kansascitychiefslogo, R.drawable.losangeleschargerslogo,
                R.drawable.stlouisramslogo, R.drawable.miamidolphinslogo, R.drawable.minnesotavikingslogo,
                R.drawable.newyorkgiantslogo, R.drawable.newyorkjetslogo, R.drawable.newenglandpatriotslogo,
                R.drawable.neworleanssaintslogo, R.drawable.oaklandraiderslogo, R.drawable.philadelphiaeagleslogo,
                R.drawable.pittsburghsteelerslogo, R.drawable.sanfrancisco49erslogo, R.drawable.seattleseahawkslogo,
                R.drawable.tampabaybuccaneerslogo, R.drawable.tennesseetitanslogo, R.drawable.washingtonredskinslogo};

        ArrayList<ItemData> spinnerItems = new ArrayList<>(0);
        int i = 0;
        for (String s : teams){
            ItemData item = new ItemData(s, images[i]);
            spinnerItems.add(item);
            i++;
        }

        Spinner spHome = findViewById(R.id.spinner_home);
        Spinner spAway = findViewById(R.id.spinner_away);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_layout, R.id.text, spinnerItems);

        spHome.setAdapter(spinnerAdapter);
        spAway.setAdapter(spinnerAdapter);


    }

    public void weeklyActivityLoader (View v){
        Intent intent = new Intent(this, Weekly_Schedule.class);
        intent.putExtra("League", (Serializable) league);
        startActivity(intent);
    }

    public void findWinner(View v){
        Spinner home = findViewById(R.id.spinner_home);
        Spinner away = findViewById(R.id.spinner_away);

        Team homeTeam = league.get(((ItemData)home.getSelectedItem()).getText());
        Team awayTeam = league.get(((ItemData)away.getSelectedItem()).getText());

        TextView textView = findViewById(R.id.winner);
        String winner = "Winner: " + homeTeam.findWinner(awayTeam).getName();
        textView.setText(winner);
    }

    public void load(){
        AsyncLoad load = new AsyncLoad(league, new AsyncLoad.AsyncResponse() {
            @Override
            public void processFinish(Map<String, Team> l) {
                league = l;
            }
        });

        load.execute();

    }


    public void setUp(){
        String [] leagueNames = {"Buffalo", "New England", "Miami", "NY Jets", "Baltimore", "Cincinnati",
                "Cleveland", "Pittsburgh", "Houston", "Indianapolis", "Jacksonville", "Tennessee", "Denver", "Kansas City",
                "Oakland", "LA Chargers", "Dallas", "NY Giants", "Philadelphia", "Washington", "Chicago", "Detroit",
                "Green Bay", "Minnesota", "Atlanta", "Carolina", "New Orleans", "Tampa Bay", "Arizona", "LA Rams", "San Francisco",
                "Seattle"};

        for (String name : leagueNames){
            Team team = new Team();
            team.setName(name);

            league.put(name, team);
        }
    }
}










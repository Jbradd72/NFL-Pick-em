package edu.byui.bra16024.nflpickem;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;

public class AsyncLoadWeek extends AsyncTask<Void, Button, Void> {

    private Map<String, Team> league;
    private WeakReference<Context> context ;
    private WeakReference<LinearLayout> linearLayout;
    private String week;


    AsyncLoadWeek(Map<String, Team> l, Context c, LinearLayout ll, String w){
        this.league = l;
        this.context = new WeakReference<>(c);
        this.linearLayout = new WeakReference<>(ll);
        this.week = w;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String game;
        String[] h = {"", ""};
        try {
            Document doc = Jsoup.connect("http://www.fftoday.com/nfl/schedule.php?o=1&" + week).get();
            Element table = doc.select("tbody").get(7);
            Elements rows = table.select("tr");

            rows.remove(2);

            for (Element ele : rows) {
                Elements cells = ele.select("td");
                if (cells.size() == 4) {
                    String[] a = cells.get(2).text().split(" ");
                    a[0] = a[0].replace(" ", "");
                    a[0] = a[0].replace("\u00A0", "");
                    h = cells.get(3).text().split(" ");
                    h[0] = h[0].replace("\u00A0", "");

                    if (h.length > 2 && !h[2].equals("¹")){
                        h[0] = h[0] + " " + h[1];
                        if (h[2].equals("Jets")){
                            h[0] = "NY Jets";
                        }
                        if (h[2].equals("Giants")){
                            h[0] = "NY Giants";
                        }
                        if (h[2].equals("Rams")){
                            h[0] = "LA Rams";
                        }
                        if (h[2].equals("Chargers")){
                            h[0] = "LA Chargers";
                        }
                    }
                    if (a.length > 2){
                        a[0] = a[0] + " " + a[1];
                        if (a[2].equals("Jets")){
                            a[0] = "NY Jets";
                        }
                        if (a[2].equals("Giants")){
                            a[0] = "NY Giants";
                        }
                        if (a[2].equals("Rams")){
                            a[0] = "LA Rams";
                        }
                        if (a[2].equals("Chargers")){
                            a[0] = "LA Chargers";
                        }

                    }

                    //game = "-"+a[0] + "-" + h[0];
                    Team home = league.get(h[0]);
                    Team away = league.get(a[0]);
                    game = away.getName() + " @ " + home.getName(); /* + " - Winner: ";
                    game += home.findWinner(away).getName();*/
                    final Button button = new Button(context.get());
                    button.setText(game);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String[] teams = button.getText().toString().split(" @ ");
                            if(teams.length > 1) {
                                Team home = league.get(teams[1]);
                                Team away = league.get(teams[0]);
                                String winner = "Projected Winner: " + home.findWinner(away).getName();
                                button.setText(winner);
                            }
                        }
                    });


                    publishProgress(button);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch ( NullPointerException e){
            Log.e("HEY JEFF" , "-" + h[0]);
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void voids){

    }

    @Override
    protected void onProgressUpdate(Button... b){

        linearLayout.get().addView(b[0]);
    }

}

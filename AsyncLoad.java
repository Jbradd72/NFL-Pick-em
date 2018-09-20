package edu.byui.bra16024.nflpickem;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;

public class AsyncLoad extends AsyncTask<Void, Void, Void> {

    public interface AsyncResponse {
        void processFinish(Map<String, Team> l);
    }
    private Map<String, Team> league;
    public AsyncResponse delegate = null;


    AsyncLoad(Map<String, Team> l, AsyncResponse ar){
        this.delegate = ar;
        this.league = l;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        loadDefense();
        loadOffense();

        return null;
    }

    @Override
    protected void onPostExecute(Void voids){
        this.delegate.processFinish(league);

    }

    public void loadDefense(){
        loadThirdDownConversionsD();
        loadTOP();
        loadPtsPerGame();
        loadTdsPerGameD();
        loadRZPCT();
    }

    public void loadRZPCT(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/opponent-red-zone-scoring-pct").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows){
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text());
                String name = cells.get(1).text();

                league.get(name).getDefense().setRedZonePercent(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void loadOffense(){
        loadTurnOverMargin();
        loadYardsPerGame();
        loadFirstDownsPerGame();
        loadThirdDownConversions();
        loadAvgPointDifferential();
        loadTdsPerGame();
        loadRedZoneScores();
    }

    public void loadRedZoneScores(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/red-zone-scores-per-game").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows){
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text());
                String name = cells.get(1).text();

                league.get(name).getOffense().setRedZoneScoresPerGame(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadTdsPerGame(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/offensive-touchdowns-per-game").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows){
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text());
                String name = cells.get(1).text();

                league.get(name).getOffense().setTdsPerGame(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadAvgPointDifferential(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/average-scoring-margin").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows){
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text());
                String name = cells.get(1).text();

                league.get(name).getOffense().setAvgPointDifferential(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadThirdDownConversions(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/third-down-conversions-per-game").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows){
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text().toString());
                String name = cells.get(1).text();

                league.get(name).getOffense().setThirdDownConversion(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadFirstDownsPerGame(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/first-downs-per-game").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows){
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text().toString());
                String name = cells.get(1).text();

                league.get(name).getOffense().setFirstDownsPerGame(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public void loadTdsPerGameD(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/opponent-offensive-touchdowns-per-game").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows){
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text());
                String name = cells.get(1).text();

                league.get(name).getDefense().setTdsPerGame(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void loadPtsPerGame(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/opponent-points-per-game").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows){
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text());
                String name = cells.get(1).text();

                league.get(name).getDefense().setPtsPerGame(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadTOP(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/opponent-average-time-of-possession-net-of-ot").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows){
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text());
                String name = cells.get(1).text();

                league.get(name).getDefense().settOP(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadThirdDownConversionsD() {
        Document doc = null;
        String name = "-";
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/opponent-third-down-conversions-per-game").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows) {
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text());
                name = cells.get(1).text();

                league.get(name).getDefense().setThirdDownConversion(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            Log.e("Hey Jeff", name);
        }


    }


    public void loadYardsPerGame(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/yards-per-game").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows){
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text().toString());
                String name = cells.get(1).text();

                league.get(name).getOffense().setYardsPerGame(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadTurnOverMargin(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.teamrankings.com/nfl/stat/turnover-margin-per-game").get();
            Element table = doc.select("tbody").get(0);
            Elements rows = table.select("tr");

            for (Element ele : rows){
                Elements cells = ele.select("td");
                Integer rank = Integer.valueOf(cells.get(0).text().toString());
                String name = cells.get(1).text();

                league.get(name).getDefense().settOMargin(rank);
                league.get(name).getOffense().settOMargin(rank);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

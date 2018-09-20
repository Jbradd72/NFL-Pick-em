package  edu.byui.bra16024.nflpickem;

import java.io.Serializable;

public class Offense implements Serializable {

    private Integer tOMargin;
    private Integer yardsPerGame;
    private Integer firstDownsPerGame;
    private Integer thirdDownConversion;
    private Integer avgPointDifferential;
    private Integer tdsPerGame;
    private Integer redZoneScoresPerGame;

    private final Double weights[] = {0.12, 0.09, 0.09, 0.08, 0.09, 0.07, 0.09};

    public Double getAdjustedRank(){
        return tOMargin * weights[0] + yardsPerGame * weights[1] + firstDownsPerGame * weights[2] + thirdDownConversion * weights[3]
                + avgPointDifferential * weights[4] + tdsPerGame * weights[5] + redZoneScoresPerGame * weights[6];
    }

    public Offense(){
        tOMargin = thirdDownConversion = tdsPerGame = yardsPerGame = firstDownsPerGame = avgPointDifferential = redZoneScoresPerGame = 0;
    }

    public Integer gettOMargin() {
        return tOMargin;
    }

    public void settOMargin(Integer tOMargin) {
        this.tOMargin = tOMargin;
    }

    public Integer getYardsPerGame() {
        return yardsPerGame;
    }

    public void setYardsPerGame(Integer yardsPerGame) {
        this.yardsPerGame = yardsPerGame;
    }

    public Integer getFirstDownsPerGame() {
        return firstDownsPerGame;
    }

    public void setFirstDownsPerGame(Integer firstDownsPerGame) {
        this.firstDownsPerGame = firstDownsPerGame;
    }

    public Integer getThirdDownConversion() {
        return thirdDownConversion;
    }

    public void setThirdDownConversion(Integer thirdDownConversion) {
        this.thirdDownConversion = thirdDownConversion;
    }

    public Integer getAvgPointDifferential() {
        return avgPointDifferential;
    }

    public void setAvgPointDifferential(Integer avgPointDifferential) {
        this.avgPointDifferential = avgPointDifferential;
    }

    public Integer getTdsPerGame() {
        return tdsPerGame;
    }

    public void setTdsPerGame(Integer tdsPerGame) {
        this.tdsPerGame = tdsPerGame;
    }

    public Integer getRedZoneScoresPerGame() {
        return redZoneScoresPerGame;
    }

    public void setRedZoneScoresPerGame(Integer redZoneScoresPerGame) {
        this.redZoneScoresPerGame = redZoneScoresPerGame;
    }
}

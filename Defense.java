package  edu.byui.bra16024.nflpickem;

import java.io.Serializable;

public class Defense implements Serializable{
    private Integer tOMargin;
    private Integer thirdDownConversion;
    private Integer tOP;
    private Integer ptsPerGame;
    private Integer tdsPerGame;
    private Integer redZonePercent;

    public Defense(){
        tOMargin = thirdDownConversion = tdsPerGame = tOP = ptsPerGame = redZonePercent = 0;
    }

    public Integer gettOMargin() {
        return tOMargin;
    }

    public void settOMargin(Integer tOMargin) {
        this.tOMargin = tOMargin;
    }

    public Integer getThirdDownConversion() {
        return thirdDownConversion;
    }

    public void setThirdDownConversion(Integer thirdDownConversion) {
        this.thirdDownConversion = thirdDownConversion;
    }

    public Integer gettOP() {
        return tOP;
    }

    public void settOP(Integer tOP) {
        this.tOP = tOP;
    }

    public Integer getPtsPerGame() {
        return ptsPerGame;
    }

    public void setPtsPerGame(Integer ptsPerGame) {
        this.ptsPerGame = ptsPerGame;
    }

    public Integer getTdsPerGame() {
        return tdsPerGame;
    }

    public void setTdsPerGame(Integer tdsPerGame) {
        this.tdsPerGame = tdsPerGame;
    }

    public Integer getRedZonePercent() {
        return redZonePercent;
    }

    public void setRedZonePercent(Integer redZonePercent) {
        this.redZonePercent = redZonePercent;
    }

    private final Double weights[] = {0.12, 0.07, 0.07, 0.075, 0.075, 0.08};

    public Double getAdjustedRank(){
        return tOMargin * weights[0] + thirdDownConversion * weights[1] + tOP * weights[2] + ptsPerGame * weights[3]
                + tdsPerGame * weights[4] + redZonePercent * weights[5];
    }
}

package com.google.rohingyahealthcare;

/**
 * Created by Kimia Tuz Zaman on 8/5/2018.
 */

public class symptoms {

    private boolean gastric;
    private boolean pain_lower_abdomen;
    private boolean pain_upper_abdomen;
    private boolean headache;
    private boolean chest_pain;
    private boolean anemia;
    private String others;

    public symptoms() {
        this.gastric = false;
        this.pain_lower_abdomen = false;
        this.pain_upper_abdomen = false;
        this.headache = false;
        this.chest_pain = false;
        this.anemia = false;
        this.others = "";
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public boolean isGastric() {
        return gastric;
    }

    public void setGastric(boolean gastric) {
        this.gastric = gastric;
    }

    public boolean isPain_lower_abdomen() {
        return pain_lower_abdomen;
    }

    public void setPain_lower_abdomen(boolean pain_lower_abdomen) {
        this.pain_lower_abdomen = pain_lower_abdomen;
    }

    public boolean isPain_upper_abdomen() {
        return pain_upper_abdomen;
    }

    public void setPain_upper_abdomen(boolean pain_upper_abdomen) {
        this.pain_upper_abdomen = pain_upper_abdomen;
    }

    public boolean isHeadache() {
        return headache;
    }

    public void setHeadache(boolean headache) {
        this.headache = headache;
    }

    public boolean isChest_pain() {
        return chest_pain;
    }

    public void setChest_pain(boolean chest_pain) {
        this.chest_pain = chest_pain;
    }

    public boolean isAnemia() {
        return anemia;
    }

    public void setAnemia(boolean anemia) {
        this.anemia = anemia;
    }
}

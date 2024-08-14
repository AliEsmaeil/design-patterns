package design_patterns.creational_patterns;

import java.util.ArrayList;

class Episod {

    String name;
    int minuteLength;

    Episod(String name, int minuteLength) {
        this.name = name;
        this.minuteLength = minuteLength;
    }
}

class Series implements Cloneable<Series> {

    String name; // reference data type
    int season; // primitive data type
    ArrayList<Episod> episods; // reference data type

    // creatinf list pf episods takes too long, because they are stored on a server
    // (API)
    Series(String name, int season, ArrayList<Episod> episods) {
        this.name = name;
        this.season = season;
        this.episods = episods;
    }

    @Override
    public Series shallowCopy() {

        Series newSeries = new Series(name, season, episods);
        return newSeries;
    }

    @Override
    public Series deepCopy() {
        ArrayList<Episod> newEpisods = new ArrayList<Episod>();
        for (var eps : episods) {
            newEpisods.add(new Episod(eps.name, eps.minuteLength));
        }
        Series newSeries = new Series(name, season, newEpisods);
        return newSeries;
    }

    void printData() {
        System.out.println("Series name : " + name + " season no : " + season);
        for (var episod : episods) {
            System.out.println("episod : " + episod.name + " length  : " + episod.minuteLength);
        }
    }
}

interface Cloneable<T> {

    T shallowCopy();

    T deepCopy();

}


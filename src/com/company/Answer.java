package com.company;
import java.util.*;

public class Answer {

    public List<List<Integer>> tracks;

    public Answer() {
        tracks = new ArrayList<List<Integer>>();
    }

    public void add(List<Integer> track) {
        tracks.add(track);
    }

    public int KXT;

    public int trackSize;

    private int trackNumb;

    public int trackNumb() {
        return tracks.size();
    }

    public String trackPath() {
        String s = "";
        for (List<Integer> track : tracks) {
            for (int peak : track) {
                s = s + peak+" ";
            }
            s += "\n";
        }
        return s;
    }
}



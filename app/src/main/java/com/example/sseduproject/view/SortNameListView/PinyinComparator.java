package com.example.sseduproject.view.SortNameListView;

import java.util.Comparator;

public class PinyinComparator implements Comparator<SortModel> {

    public int compare(SortModel o1, SortModel o2) {
        if (o1.getSortLetters().equals("★") || o2.getSortLetters().equals("★")) {
            return 1;
        } else if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
            return 2;
        } else if (o1.getSortLetters().equals("#") || o2.getSortLetters().equals("@")) {
            return -1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
//        if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
//            return 1;
//        } else if (o1.getSortLetters().equals("#") || o2.getSortLetters().equals("@")) {
//            return -1;
//        } else {
//            return o1.getSortLetters().compareTo(o2.getSortLetters());
//        }
    }

}

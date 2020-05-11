package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class DataOverview {

    public ArrayList<Integer> convert(int number_of_vertices, int[][] adjacency_matrix)
    {
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < number_of_vertices; i++) {
            for (int j = 0; j < number_of_vertices; j++) {
                if (i != j)
                    array.add(adjacency_matrix[i][j]);
            }
            // System.out.println();
        }
        return array;
    }

    public void calculateReputation(ArrayList<Integer> array){
        Collections.sort(array, Collections.reverseOrder());
        int numberOfRep = 0;
        int uniq = 0;
        int current = array.get(0);
        for(int elem: array)
        {
            if (current == elem){
                numberOfRep++;
            } else
            {
                current = elem;
                uniq++;
            }
        }
        System.out.println(numberOfRep);
        System.out.println(uniq);
    }
}

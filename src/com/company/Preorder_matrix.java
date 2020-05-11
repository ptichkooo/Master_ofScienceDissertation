package com.company;

import java.util.*;

public class Preorder_matrix {
    private int[][] adjacency_matrix;
    private int dimension;
    private int max_int = Integer.MAX_VALUE;

    public Preorder_matrix(int[][] adjacency_matrix, int dimension) {
        this.adjacency_matrix = adjacency_matrix;
        this.dimension = dimension;
    }

    public int[][] Get_preorder(){
        ArrayList<Integer> elements = new ArrayList<Integer>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (elements.indexOf(adjacency_matrix[i][j]) == -1 && i!=j) {
                    elements.add(adjacency_matrix[i][j]);
                }
            }
        }

        Collections.sort(elements);

        int[][] preorder_matrix = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i!=j) {
                    preorder_matrix[i][j] = elements.indexOf(adjacency_matrix[i][j]);
                } else
                    preorder_matrix[i][j] = max_int;
            }
        }

        return preorder_matrix;
    }
}

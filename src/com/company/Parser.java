package com.company;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

public class Parser {
    Scanner sc;

    private int[][] FULL_MATRIX(int number_of_vertices) {
        int[][] adjacency_matrix;
        adjacency_matrix = new int[number_of_vertices][number_of_vertices];

        String line;
        while ((line = sc.nextLine()) != null) {
            if (line.contains("EDGE_WEIGHT_SECTION")) {
                while (sc.hasNextInt() && !line.equalsIgnoreCase("EOF")) {
                    for (int i = 0; i < dimension; i++)
                        for (int j = 0; j < dimension; j++) {
                            int value = sc.nextInt();
                            if (i != j)
                                adjacency_matrix[i][j] = value;
                            else {
                                adjacency_matrix[i][j] = Integer.MAX_VALUE;
                            }
                        }
                }
                return adjacency_matrix;
            }
        }
        return null;
    }

    private int[][] LOWER_DIAG_ROW(int number_of_vertices) {

        int[][] adjacency_matrix;
        adjacency_matrix = new int[number_of_vertices][number_of_vertices];

        String line;
        while ((line = sc.nextLine()) != null) {
            if (line.contains("EDGE_WEIGHT_SECTION")) {
                while (sc.hasNextInt() && !line.equalsIgnoreCase("EOF")) {
                    for (int i = 0; i < number_of_vertices; i++) {
                        for (int j = 0; j <= i; j++) {
                            int value = sc.nextInt();
                            if (i != j) {
                                adjacency_matrix[i][j] = adjacency_matrix[j][i] = value;
                            } else
                                adjacency_matrix[i][j] = Integer.MAX_VALUE;

                        }
                    }
                    return adjacency_matrix;
                }
            }
        }
        return null;

    }

    private int[][] UPPER_ROW(int number_of_vertices) {
        int[][] adjacency_matrix;
        adjacency_matrix = new int[number_of_vertices][number_of_vertices];

        String line;
        while ((line = sc.nextLine()) != null) {
            if (line.contains("EDGE_WEIGHT_SECTION")) {
                while (sc.hasNextInt() && !line.equalsIgnoreCase("EOF")) {
                    for (int i = 0; i < number_of_vertices; i++) {
                        for (int j = i + 1; j < number_of_vertices; j++) {
                            int value = sc.nextInt();
                            if (i == j) {
                                adjacency_matrix[i][j] = Integer.MAX_VALUE;
                            } else adjacency_matrix[i][j] = adjacency_matrix[j][i] = value;


                        }
                    }
                    return adjacency_matrix;
                }
            }
        }
        return null;
    }

    private int[][] UPPER_DIAG_ROW(int number_of_vertices) {
        int[][] adjacency_matrix;
        adjacency_matrix = new int[number_of_vertices][number_of_vertices];

        String line;
        while ((line = sc.nextLine()) != null) {
            if (line.contains("EDGE_WEIGHT_SECTION")) {
                while (sc.hasNextInt() && !line.equalsIgnoreCase("EOF")) {
                    for (int i = 0; i < number_of_vertices; i++) {
                        for (int j = i; j < number_of_vertices; j++) {
                            int value = sc.nextInt();
                            if (i != j)
                                adjacency_matrix[i][j] = adjacency_matrix[j][i] = value;
                            else
                                adjacency_matrix[i][j] = Integer.MAX_VALUE;
                        }
                    }
                    return adjacency_matrix;
                }
            }
        }
        return null;
    }

    private int[][] EUC_2D(int number_of_vertices) {
        int[][] adjacency_matrix;
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        String line;
        while ((line = sc.nextLine()) != null) {
            if (line.contains("NODE_COORD_SECTION")) {

                for (int i = 0; i < number_of_vertices; i++) {
                    sc.next();
                    double x_value = Double.parseDouble(sc.next());
                    double y_value = Double.parseDouble(sc.next());

                    x.add(x_value);
                    y.add(y_value);
                }

                double xd, yd;

                adjacency_matrix = new int[number_of_vertices][number_of_vertices];
                for (int i = 0; i < number_of_vertices; i++) {
                    for (int j = 0; j < number_of_vertices; j++) {
                        xd = x.get(i) - x.get(j);
                        yd = y.get(i) - y.get(j);
                        if (i == j) {
                            adjacency_matrix[i][j] = Integer.MAX_VALUE;
                        } else adjacency_matrix[i][j] = (int) Math.round((Math.sqrt(xd * xd + yd * yd)));
                    }
                }
                return adjacency_matrix;
            }
        }
        return null;
    }

    private int[][] ATT(int number_of_vertices) {
        int[][] adjacency_matrix;
        ArrayList<Float> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();

        String line;
        while ((line = sc.nextLine()) != null) {
            if (line.contains("NODE_COORD_SECTION")) {

                for (int i = 0; i < number_of_vertices; i++) {
                    sc.next();
                    float x_value = sc.nextFloat();
                    float y_value = sc.nextFloat();

                    x.add(x_value);
                    y.add(y_value);
                }

                float xd, yd;
                double rij;
                int tij;
                adjacency_matrix = new int[number_of_vertices][number_of_vertices];
                for (int i = 0; i < number_of_vertices; i++) {
                    for (int j = 0; j < number_of_vertices; j++) {
                        xd = x.get(i) - x.get(j);
                        yd = y.get(i) - y.get(j);
                        rij = Math.sqrt((xd * xd + yd * yd) / 10);
                        tij = (int) Math.round(rij + 0.5);

                        if (i == j) {
                            adjacency_matrix[i][j] = Integer.MAX_VALUE;
                        } else if (tij < rij) adjacency_matrix[i][j] = tij + 1;
                        else adjacency_matrix[i][j] = tij;
                    }
                }
                return adjacency_matrix;
            }
        }
        return null;


    }

    private int[][] CEIL_2D(int number_of_vertices) {
        int[][] adjacency_matrix;
        ArrayList<Float> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();


        String line;
        while ((line = sc.nextLine()) != null) {
            if (line.contains("NODE_COORD_SECTION")) {
                for (int i = 0; i < number_of_vertices; i++) {
                    sc.next();
                    float x_value = sc.nextFloat();
                    float y_value = sc.nextFloat();

                    x.add(x_value);
                    y.add(y_value);
                }

                float xd, yd;
                adjacency_matrix = new int[number_of_vertices][number_of_vertices];
                for (int i = 0; i < number_of_vertices; i++) {
                    for (int j = 0; j < number_of_vertices; j++) {
                        xd = x.get(i) - x.get(j);
                        yd = y.get(i) - y.get(j);
                        if (i == j) {
                            adjacency_matrix[i][j] = Integer.MAX_VALUE;
                        } else adjacency_matrix[i][j] = (int) Math.ceil(Math.sqrt(xd * xd + yd * yd));
                    }
                }
                return adjacency_matrix;
            }
        }
        return null;
    }

    private double PI = 3.141592;
    private double RRR = 6378.388;

    private int[][] GEO(int number_of_vertices) {
        int[][] adjacency_matrix;
        ArrayList<Float> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();

        String line;
        while ((line = sc.nextLine()) != null) {
            if (line.contains("NODE_COORD_SECTION")) {

                for (int i = 0; i < number_of_vertices; i++) {
                    sc.next();
                    // Float x_value = sc.next().replace('.', ',');
                    Float x_value = sc.nextFloat();
                    Float y_value = sc.nextFloat();
                    x.add(x_value);
                    y.add(y_value);
                }


                double[] latitude = new double[number_of_vertices];
                double[] longitude = new double[number_of_vertices];
                adjacency_matrix = new int[number_of_vertices][number_of_vertices];

                int deg;
                float min;
                for (int i = 0; i < number_of_vertices; i++) {
                    deg = (int) (x.get(i) + 0.5);
                    min = x.get(i) - deg;
                    latitude[i] = PI * (deg + 5 * min / 3) / 180;

                    deg = (int) (y.get(i) + 0.5);
                    min = y.get(i) - deg;
                    longitude[i] = PI * (deg + 5 * min / 3) / 180;

                }

                double q1, q2, q3;
                for (int i = 0; i < number_of_vertices; i++) {
                    for (int j = 0; j < number_of_vertices; j++) {
                        if (i == j) {
                            adjacency_matrix[i][j] = Integer.MAX_VALUE;
                        } else {
                            q1 = Math.cos(longitude[i] - longitude[j]);
                            q2 = Math.cos(latitude[i] - latitude[j]);
                            q3 = Math.cos(latitude[i] + latitude[j]);

                            adjacency_matrix[i][j] = (int) (RRR * Math.acos(0.5 * ((1 + q1) * q2 - (1 - q1) * q3)) + 1);

                        }
                    }
                }
                return adjacency_matrix;
            }
        }
        return null;
    }

    public String name;
    public int dimension;
    public int[][] result;

    public void readFile(String fileName) throws Exception {
        try {
            sc = new Scanner(new FileReader(fileName));
            String line;

            while ((line = sc.nextLine()) != null) {
                if (line.contains("NAME")) {
                    name = line.split(":")[1].trim();
                }
                if (line.contains("DIMENSION")) {
                    dimension = Integer.parseInt(line.split(":")[1].trim());
                }

                if (line.contains("EDGE_WEIGHT_TYPE")) {
                    String type = line.split(":")[1].trim();
                    if (type.equalsIgnoreCase("EUC_2D")) {
                        result = EUC_2D(dimension);
                    }
                    if (type.equalsIgnoreCase("ATT")) {
                        result = ATT(dimension);
                    }

                    if (type.equalsIgnoreCase("CEIL_2D")) {
                        result = CEIL_2D(dimension);
                    }

                    if (type.equalsIgnoreCase("GEO")) {
                        result = GEO(dimension);
                    }
                }

                if (line.contains("EDGE_WEIGHT_FORMAT")) {
                    String type = line.split(":")[1].trim();
                    if (type.equalsIgnoreCase("FULL_MATRIX")) {
                        result = FULL_MATRIX(dimension);
                    }
                    if (type.equalsIgnoreCase("LOWER_DIAG_ROW")) {
                        result = LOWER_DIAG_ROW(dimension);
                    }

                    if (type.equalsIgnoreCase("UPPER_ROW")) {
                        result = UPPER_ROW(dimension);
                    }

                    if (type.equalsIgnoreCase("UPPER_DIAG_ROW")) {
                        result = UPPER_DIAG_ROW(dimension);
                    }

                }
            }
        } catch (Exception e) {
        }
    }
}

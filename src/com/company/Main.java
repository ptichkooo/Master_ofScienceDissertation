package com.company;
import com.sun.xml.internal.bind.v2.TODO;

import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class Main {

    static void print(int number_of_vertices, int[][] adjacency_matrix) {
        for (int i = 0; i < number_of_vertices; i++) {
            for (int j = 0; j < number_of_vertices; j++) {
                if (i == j)
                    System.out.print("0\t");
                else System.out.print(adjacency_matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static void read_from_console() {
        int dimension = 29;
        int[][] matrix = new int[dimension][dimension];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                matrix[i][j] = scanner.nextInt();

    }

    //LKH
    static void get_result_STPS() {
        String path_preorder_result = "/Users/natalia/Desktop/Файлы/ВКР/Project/src/com/company/STSP/Preorder_STSP_LKH/";
        String path = "/Users/natalia/Desktop/Файлы/ВКР/Project/src/com/company/STSP/";

        File dir = new File(path);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            for (File child : directoryListing) {
                String current_path = path + child.getName();
                Parser parser = null;

                try {
                    parser = new Parser();
                    parser.readFile(current_path);
                    int[][] matrix = parser.result;
                    if ( matrix == null) {
                        //System.out.println("PROBLEM = " + current_path);
                    }
                    else {
                        String result_path = path_preorder_result + parser.name+"Preorder_result.txt";
                        System.out.println(result_path);
                        Scanner sc = new Scanner(new FileReader(result_path));
                        String line;
                        int result = 0;
                        while( (line = sc.nextLine()) != null ) {

                            if(line.contains("TOUR_SECTION")){
                                {
                                    int x = sc.nextInt(), y = sc.nextInt();
                                    result += matrix[x-1][y-1];
                                    while(sc.hasNextInt()){
                                        x = y;
                                        y = sc.nextInt();
                                        if(y !=-1)
                                        {
                                            result += matrix[x-1][y-1];
                                        }
                                        else{
                                            result += matrix[x-1][0];
                                        }

                                    }
                                    System.out.println(parser.name + ";"+ result);
                                }
                            }
                        }
                    }

                } catch (Exception e) {
                   // System.out.println("PROBLEM = "+e);
                }

            }
        }
    }


    public static void main(String[] args) throws Exception {
        MBandB mBandB = new MBandB();
        String path = "/Users/natalia/Desktop/Файлы/ВКР/Project/src/com/company/STSP/";
        for (int i = 1; i <= 11; i++)
            mBandB.Method_branch_and_bound_for_TSP(path + i +"/");
    }


/*

    public static void main(String[] args) throws Exception {
        //read and generate file .par for preorder matrix
        ATSP atsp = new ATSP();
        //[TODO] using LKH get result, all par file running out of system
        //get result from par and get total weight of Hamiltonian cycle
        atsp.get_result_ATSP_LKH();
        // get result from MBaB
        atsp.get_result_MBaB();

        //read and generate file .par for preorder matrix
        STSP stsp = new STSP();
        //[TODO] using LKH get result, all par file running out of system
        //get result from par and get total weight of Hamiltonian cycle
        stsp.get_result_STSP_LKH();
        // get result from MBaB
        stsp.get_result_MBaB();
    }
    */

}


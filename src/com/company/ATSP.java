package com.company;

import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class ATSP {

    String path = "/Users/natalia/Desktop/Файлы/ВКР/Project/src/com/company/ATSP/";
    String path_preorder_result = "/Users/natalia/Desktop/Файлы/ВКР/Project/src/com/company/ATSP/Preorder_ATSP_LKH/Result_for_preorder/";

    public ATSP(){
        File dir = new File(path);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {

            for (File child : directoryListing) {
                String current_path = path + child.getName();
                Parser parser = null;
                try {
                    parser = new Parser();
                    parser.readFile(current_path);
                    if (parser.result == null) {
                        System.out.println("PROBLEM = " + current_path);
                    }
                    else {
                        generateFileASTPandPar(parser);
                    }

                } catch (Exception e) {
                    System.out.println("PROBLEM = " + current_path);
                }

            }
        }
    }

    public void get_result_ATSP_LKH() {
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
                    System.out.println("PROBLEM = "+e);
                }

            }
        }
    }

    public void get_result_MBaB(){
        MBandB mBandB = new MBandB();
        mBandB.Method_branch_and_bound_for_TSP(path);
    }

    private void generateFileASTPandPar(Parser parser) {
        String preorder_file = parser.name + "Preorder.atsp";
        String preorder_par_file = parser.name + "Preorder.par";

        System.out.println(preorder_file);
        try {
            FileWriter myWriter = new FileWriter(preorder_file);
            myWriter.write("NAME: " + parser.name + "Preorder" + "\n");
            myWriter.write("TYPE: ATSP" + "\n");
            myWriter.write("DIMENSION: " + parser.dimension + "\n");
            myWriter.write("EDGE_WEIGHT_TYPE: EXPLICIT\n" +
                    "EDGE_WEIGHT_FORMAT: FULL_MATRIX \n");
            myWriter.write("EDGE_WEIGHT_SECTION\n");
            Preorder_matrix preorder_matrix = new Preorder_matrix(parser.result, parser.dimension);
            int[][] matrix = preorder_matrix.Get_preorder();

            for (int i = 0; i < parser.dimension; i++) {
                for (int j = 0; j < parser.dimension; j++) {
                    if (i != j)
                        myWriter.write(matrix[i][j] + "\t");
                    else
                        myWriter.write(9999 + "\t");
                }
                myWriter.write("\n");
            }
            myWriter.write("EOF");
            myWriter.close();

            myWriter = new FileWriter(preorder_par_file);
            myWriter.write("PROBLEM_FILE = " + preorder_file+"\n");
            myWriter.write("OUTPUT_TOUR_FILE = " + parser.name+"Preorder_result.txt"+"\n");
            myWriter.write("MOVE_TYPE = 5\n" +
                    "PATCHING_C = 3\n" +
                    "PATCHING_A = 2\n" +
                    "RUNS = 10\n");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}

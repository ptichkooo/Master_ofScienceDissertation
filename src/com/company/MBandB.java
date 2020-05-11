package com.company;

import java.io.File;
import java.util.*;

public class MBandB
{
    private int U = Integer.MAX_VALUE;
    private int MXT, KXT;
    int[][] C, CQ;//с -исходный сй ниже изменяем
    int[] V, W;//беззнаковый.  волновые функции- создаем массивы
    int[] L, PRED, RANG, XI, XJ;// л -ссылка на левого ребенка, пред -ссылка на родитель.хи и хо - дуга ветвления(включаем или не вкл вершину)
    int[] B;//нижняя оценка
    int Q;
    int N;

    Answer answer;

    public MBandB(){}
    public MBandB(int[][] arrays, int N)
    {
        this.N = N;
        int N1 = N + 1;
        C = new int[N1][N1];
        CQ = new int[N1][N1];
        V = new int[N1];//вершины по
        W = new int[N1];
        MXT = 9999999;
        L = new int[MXT];
        PRED = new int[MXT];
        B = new int[MXT];
        RANG = new int[MXT];
        XI = new int[MXT];
        XJ = new int[MXT];
        answer = new Answer();

        //заполнение исходного массива
        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= N; j++)
            {
                if (i == j)
                    C[i][j] = U;
                    else
                {
                    int indexi = i - 1;
                    int indexj = j - 1;
                    C[i][j] = arrays[indexi][indexj];
                }
            }
        }
    }

    public void Method()
    {
        KXT = 1;
        Q = 1;
        L[Q] = 0;
        PRED[Q] = 0;
        B[Q] = LB(Q);
        RANG[Q] = N;
        int D = U;
        for (;;)
        {
            // находим такую точку, чтобы она
            int BQ = U;
            int RQ = N;
            for (int R = 1; R <= KXT; R++)
            {
                if (L[R] == 0)

                    if (!((BQ < B[R]) || (BQ == B[R]) && (RQ <= RANG[R])))
                    {
                        Q = R;
                        BQ = B[R];
                        RQ = RANG[R];
                    }
            }
            L[Q] = -1;
            if (BQ > D)
                return;
            B[Q] = LB(Q);
            if (RQ == 2)
            {
                D = BQ;
                for (int i = 1; i <= N; i++)
                    if (V[i] == 0)
                        for (int j = 1; j <= N; j++)
                            if (W[j] == 0)
                                if (CQ[i][j] == 0)
                {
                    V[i] = j;
                    W[j] = i;
                }
                List<Integer> track = new ArrayList<>();
                for (int i = 1, j = 0; j <= N; i = V[i], j++)
                {
                    track.add(i);
                }
                answer.add(track);
                answer.KXT = KXT;
                answer.trackSize = D;
            }
            else
            {
                if (KXT + 2 > MXT)
                {
                    throw new OutOfMemoryError("Out of memory");
                }
                int DB = -1;
                for (int i = 1; i <= N; i++)
                    if (V[i] == 0)
                        for (int j = 1; j <= N; j++)
                            if (W[j] == 0)
                                if (CQ[i][j] == 0)
                {
                    int AI = U;
                    for (int i1 = 1; i1 <= N; i1++)
                        if (V[i1] == 0)
                            if (i1 != i)
                                AI = Math.min(AI, CQ[i1][j]);
                    int BJ = U;
                    for (int j1 = 1; j1 <= N; j1++)
                        if (W[j1] == 0)
                            if (j1 != j)
                                BJ = Math.min(BJ, CQ[i][j1]);
                    if ((AI == U) || (BJ == U))
                    {
                        DB = U;
                        XI[Q] = i;
                        XJ[Q] = j;
                    }
                    else
                    if (DB < AI + BJ)
                    {
                        DB = AI + BJ;
                        XI[Q] = i;
                        XJ[Q] = j;
                    }
                }
                //сохранение потомов
                // Q0
               // System.out.println("XI {" + XI[Q] + "}, XJ {"+ XJ[Q]);
                KXT++;
                L[Q] = KXT;
                L[KXT] = 0;
                PRED[KXT] = Q;
                if (DB == U) { B[KXT] = U; } else B[KXT] = BQ + DB;
                B[KXT] = LB(KXT);
                RANG[KXT] = RQ;
                //System.out.println("Q0, KXT {" + KXT + "}, Pred {"+PRED[KXT] + "} NB = "+ B[KXT]);
                //Q1
                KXT++;
                L[KXT] = 0;
                PRED[KXT] = Q;
                Q = KXT;
                B[Q] = LB(Q);
                RANG[KXT] = RQ - 1;
                //System.out.println("Q1, KXT {" + KXT + "}, Pred {"+PRED[KXT] + "} NB = "+ B[KXT]);
                //System.out.println("------");
            }
        }
    }

    //вычисление нижней оценки
    private int LB(int Q)
    {
        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= N; j++)
            {
                CQ[i][j] = C[i][j];
            }
            V[i] = 0;
            W[i] = 0;
        }
        int LB = 0;
        int PR;
        for (int R = Q; R != 1; R = PR)
        {
            PR = PRED[R];
            int I = XI[PR];
            int J = XJ[PR];
            if (R - L[PR] == 1)
            {
                V[I] = J;
                W[J] = I;
                LB += C[I][J];
                for (; V[I] != 0;) I = V[I];
                for (; W[J] != 0;) J = W[J];
            }
            CQ[I][J] = U;
        }
        //вычисление по строке
        for (int I = 1; I <= N; I++)
            if (V[I] == 0)
            {
                int AI = U;
                for (int J = 1; J <= N; J++)
                    if (W[J] == 0)
                    {
                        AI = Math.min(AI, CQ[I][J]);
                    }
                for (int J = 1; J <= N; J++)
                    if (W[J] == 0)
                    {
                        if (CQ[I][J] != U)
                        CQ[I][J] -= AI;
                    }
                if(LB != U)
                    if (AI == U)
                        LB = U;
                    else
                        LB += AI;
            }
        //вычисление по столбцам
        for (int J = 1; J <= N; J++)
            if (W[J] == 0)
            {
                int BJ = U;
                for (int I = 1; I <= N; I++)
                    if (V[I] == 0)
                    {
                        BJ = Math.min(BJ, CQ[I][J]);
                    }
                for (int I = 1; I <= N; I++)
                    if (V[I] == 0)
                    {
                        if (CQ[I][J] != U)
                        CQ[I][J] -= BJ;
                    }
                if(LB != U)
                    if (BJ == U)
                        LB = U;
                    else
                        LB += BJ;
            }
        return LB;
    }

    //MethodBranch and Bound
     public void Method_branch_and_bound_for_TSP(String path) {

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
                    } else {
                        // Get
                        String name = parser.name;
                        int dim = parser.dimension;
                        int[][] matrix = parser.result;
                        int[][] preorder = new Preorder_matrix(matrix, dim).Get_preorder();

                        MBandB mBandB;
                        if (dim <= 150) {
                            try {
                                System.out.print(name + ";");
                                mBandB = new MBandB(matrix, dim);
                                mBandB.Method();
                                int result_real = mBandB.answer.trackSize;
                                System.out.print(result_real + ";" + "KXT " + mBandB.answer.KXT + ";");

                                mBandB = new MBandB(preorder, dim);
                                mBandB.Method();
                                List<Integer> res = mBandB.answer.tracks.get(0);
                                int result_preorder = 0;
                                for (int i = 0; i < dim; i++) {
                                    result_preorder += matrix[res.get(i) - 1][res.get(i + 1) - 1];
                                }
                                System.out.print(result_preorder + ";" + "KXT " + mBandB.answer.KXT);
                                System.out.println();
                            } catch (OutOfMemoryError e) {
                                System.out.print("Out of memory in real task;");
                            }
                            System.out.println();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("PROBLEM = " + current_path);
                }
            }
        }
    }
}


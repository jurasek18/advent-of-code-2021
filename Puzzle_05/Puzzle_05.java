import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class Puzzle_05 {
    public final static int INPUTSIZE = 500;
    // public final static int INPUTSIZE = 10;

    public static Integer[][] loadInput() {
        Integer[][] input = new Integer[INPUTSIZE][4];
        try {
            Scanner file = new Scanner(new File("./Puzzle_05/day05_input.txt"));
            int line = 0;

            while (file.hasNext()) {
                String fc1 = file.next();
                String[] fcoo1 = fc1.split(",");
                input[line][0] = Integer.parseInt(fcoo1[0]);
                input[line][1] = Integer.parseInt(fcoo1[1]);

                // System.out.print("Coordinates: [" + input[line][0] + "," + input[line][1] +
                // "] -> [");

                file.next(); // read arrow (->) and ignore it

                String fc2 = file.next();
                String[] fcoo2 = fc2.split(",");
                input[line][2] = Integer.parseInt(fcoo2[0]);
                input[line][3] = Integer.parseInt(fcoo2[1]);

                // System.out.println(input[line][2] + "," + input[line][3] + "]");
                line++;
            }

            file.close();
        } catch (Exception e) {
            System.out.println("Error in input");
            System.out.println(e);
        }

        return input;
    }

    public static int findMaxX(Integer[][] mc) {
        int max = 0;
        for (int i = 0; i < INPUTSIZE; i++) {
            if (max < mc[i][0])
                max = mc[i][0];
            if (max < mc[i][2])
                max = mc[i][2];
        }
        max++;
        System.out.println("MaxX: " + max);
        return max;
    }

    public static int findMaxY(Integer[][] mc) {
        int max = 0;
        for (int i = 0; i < INPUTSIZE; i++) {
            if (max < mc[i][1])
                max = mc[i][1];

            if (max < mc[i][3])
                max = mc[i][3];
        }
        max++;
        System.out.println("MaxY: " + max);
        return max;
    }

    public static void printDanger(Integer[][] d, int maxX, int maxY) {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++)
                System.out.print(d[i][j]);
            System.out.println();
        }
    }

    public static Integer[][] computeCover(Integer[][] d, Integer[][] coo, int maxX, int maxY) {
        for (int i = 0; i < coo.length; i++) {
            // for (int i = 0; i < 100; i++) {
            // System.out.println(coo[i][0] + " " + coo[i][2]);
            // System.out.println(coo[i][0].equals(coo[i][2]));
            if (coo[i][0].equals(coo[i][2])) {
                System.out
                        .println("\nMatch Analyzing coordinates: [" + coo[i][0] + "," + coo[i][1] + "]->[" + coo[i][2]
                                + "," + coo[i][3] + "]");
                // vertical change
                int sc = coo[i][1];
                int fc = coo[i][3];

                if (sc > fc) {
                    int h = fc;
                    fc = sc;
                    sc = h;
                }
                System.out.println("Vertical change: Start " + sc + " Finish: " + fc);
                // System.out.println("\n" + coo[i][0]);
                for (int j = sc; j <= fc; j++) {
                    // System.out.print(d[j][coo[i][0]]++);
                    d[j][coo[i][0]]++;
                }
                // printDanger(d, 9, 9);
                // int tempResult = 0;
                // for (int ti = 0; ti < maxX; ti++)
                // for (int tj = 0; tj < maxY; tj++)
                // if (d[ti][tj] >= 2)
                // tempResult++;
                // System.out.println("Temp number of danger points: " + tempResult);
            } else if (coo[i][1].equals(coo[i][3])) {
                System.out.println("\nMatch Analyzing coordinates: [" + coo[i][0] + "," +
                        coo[i][1] + "]->[" + coo[i][2]
                        + "," + coo[i][3] + "]");
                // horizontal change
                int sc = coo[i][0];
                int fc = coo[i][2];

                if (sc > fc) {
                    int h = fc;
                    fc = sc;
                    sc = h;
                }
                System.out.println("Horizontal change: Start " + sc + " Finish: " + fc);
                for (int j = sc; j <= fc; j++) {
                    // System.out.print(j + " " + coo[i][1] + " " + j + "\n");
                    d[coo[i][1]][j]++;
                }
                // int tempResult = 0;
                // for (int ti = 0; ti < maxY; ti++)
                // for (int tj = 0; tj < maxX; tj++)
                // if (d[ti][tj] >= 2)
                // tempResult++;
                // System.out.println("Temp number of danger points: " + tempResult);
            } else {
                // System.out.println("Ignoring coordinates: [" + coo[i][0] + "," +
                // coo[i][1] + "]->[" + coo[i][2]
                // + "," + coo[i][3] + "]");

                // diagonal change
                System.out.println("\nMatch Analyzing coordinates: [" + coo[i][0] + "," +
                        coo[i][1] + "]->[" + coo[i][2]
                        + "," + coo[i][3] + "]");

                int x1 = coo[i][0];
                int y1 = coo[i][1];
                int x2 = coo[i][2];
                int y2 = coo[i][3];
                if (x1 < x2) {
                    if (y1 < y2) {
                        System.out.println("X1<X2 Y1<Y2: " + x1 + "," + y1 + " " + x2 + "," + y2);

                        for (int xx = 0; xx <= (x2 - x1); xx++)
                            d[y1 + xx][x1 + xx]++;
                    } else if (y1 > y2) {
                        System.out.println("X1<X2 Y1>Y2: " + x1 + "," + y1 + " " + x2 + "," + y2);
                        // todo chybi mi 1 krok na posledni misto 8,2
                        for (int xx = 0; xx <= (x2 - x1); xx++)
                            d[y1 - xx][x1 + xx]++;
                    }
                } else if (x1 > x2) {
                    if (y1 < y2) {
                        System.out.println("X1>X2 Y1<Y2: " + x1 + "," + y1 + " " + x2 + "," + y2);

                        for (int xx = 0; xx <= (x1 - x2); xx++) {
                            // System.out.println((x1 - xx) + "," + (y1 + xx));
                            d[y1 + xx][x1 - xx]++;
                        }
                    } else if (y1 > y2) {
                        System.out.println("X1>X2 Y1>Y2: " + x1 + "," + y1 + " " + x2 + "," + y2);

                        for (int xx = 0; xx <= (x1 - x2); xx++) {
                            System.out.println((x1 - xx) + "," + (y1 - xx) + ": " + d[x1 - xx][y1 - xx]);
                            // printDanger(d, maxX, maxY);
                            d[y1 - xx][x1 - xx]++;
                            // System.out.println(d[x1 - xx][y1 - xx]);
                            // printDanger(d, maxX, maxY);
                        }
                    }
                }

                // for (int j = diag_scX; j <= diag_fcX; j++) {
                // // System.out.print(j + " " + coo[i][1] + " " + j + "\n");
                // d[coo[i][1]][j]++;
                // }
            }
        }
        return d;
    }

    public static void main(String[] args) {
        System.out.println("Puzzle 05");

        Integer[][] myCoo = loadInput();

        int maxX = findMaxX(myCoo);
        int maxY = findMaxY(myCoo);
        Integer[][] danger = new Integer[maxY][maxX];
        for (int i = 0; i < maxY; i++)
            for (int j = 0; j < maxX; j++)
                danger[i][j] = 0;

        Integer[][] finalDanger = computeCover(danger, myCoo, maxX, maxY);
        printDanger(finalDanger, maxX, maxY);
        int result = 0;
        for (int i = 0; i < maxY; i++)
            for (int j = 0; j < maxX; j++)
                if (finalDanger[i][j] >= 2)
                    result++;
        System.out.println("Number of danger points: " + result);

        try {
            Writer wr = new FileWriter("result.txt");
            for (int i = 0; i < maxY; i++) {
                for (int j = 0; j < maxX; j++) {
                    int a = finalDanger[i][j];
                    String str = String.valueOf(a);
                    wr.write(str);
                }
                wr.write("\n");
            }

            wr.close();
        } catch (Exception e) {
            System.out.println("Error in input");
            System.out.println(e);
        }

    }

}

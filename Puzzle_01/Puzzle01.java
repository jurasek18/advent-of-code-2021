
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

class Puzzle01 {
    public static void main(String[] args) {
        System.out.println("Puzzle 01");

        // int[] input = new int[2000];
        int[] input = new int[2000];
        int[] input3 = new int[2000];


        Integer i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("./Puzzle_01/day01_input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                input[i] = Integer.parseInt(line);
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error in input");
            System.out.println(new File(".").getAbsolutePath());
            System.out.println(e); 
        }   
        System.out.println(Arrays.toString(input)); 
        // int pValue = input[0];
        int incCount = 0;
        
        // Day 01 puzzle part 01 
        // for (int s : input) {
        //     if (s > pValue) {
        //         incCount++;
        //     };
        //     pValue = s;
        // }

        // Day 02 puzzle part 02
        for (int index=2; index<2000; index++) {
            input3[index] = input[index] + input[index-1] + input[index-2];
            // System.out.println("Suma: " + input3[index]);
        };

        int pValue = input3[2];

        for (int v=2; v<2000; v++) {
            // System.out.println(String.format("%d %d", input3[v], pValue));
            if (input3[v] > pValue) {
                incCount++;
            };
            pValue = input3[v];
        }        
        System.out.println(incCount);
    }
}
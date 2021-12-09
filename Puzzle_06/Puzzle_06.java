import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle_06 {
    public final static int DAYS = 256;

    public static ArrayList<Long> loadInput() {
        ArrayList<Long> input = new ArrayList<Long>();

        try {
            Scanner file = new Scanner(new File("./Puzzle_06/day06_input.txt"));

            while (file.hasNext()) {
                String s = file.next();
                System.out.println(s);

                String[] a = s.split(",");
                for (int i = 0; i < a.length; i++) {
                    Long n = Long.parseLong(a[i]);
                    input.add(n);
                }
            }

            file.close();

        } catch (Exception e) {
            System.out.println("Error in input");
            System.out.println(e);
        }

        return input;
    }

    public static void printFishes(Long[] fishes) {
        for (int i = 0; i < fishes.length; i++)
            System.out.print(fishes[i] + ",");
        System.out.println();
    }

    public static long computeGenerationsA(ArrayList<Long> lanterns) {
        for (int i = 1; i <= DAYS; i++) {
            System.out.print("\nAfter day " + i + ": ");
            long newBornCount = 0;

            for (int index = 0; index < lanterns.size(); index++) {

                if (lanterns.get(index) == 0) {
                    // reset timer to 6
                    Long newValue = 6L;
                    lanterns.set(index, newValue);
                    // System.out.print(lanterns.get(index) + ",");
                    newBornCount++;
                } else {
                    Long newValue = lanterns.get(index) - 1;
                    lanterns.set(index, newValue);
                    // System.out.print(lanterns.get(index) + ",");
                }
            }

            for (int j = 0; j < newBornCount; j++) {
                // spawn new fish with timer 8
                lanterns.add(8L);
                // System.out.print("8,");
            }

        }

        return lanterns.size();
    }

    public static long computeGenerationsB(Long[] population) {
        long size = 0;

        for (int i = 1; i <= DAYS; i++) {

            long newBorn = 0;
            Long[] newPopulation = new Long[9];
            for (int o = 0; o < newPopulation.length; o++) {
                newPopulation[o] = 0L;
            }

            // System.out.println("\nPopulation before day: " + i);
            // for (int xxx = 0; xxx < population.length; xxx++) {
            // System.out.print(population[xxx] + ",");
            // }

            for (int index = 0; index < 8; index++) {
                if (index == 0) {
                    if (population[0] > 0) {
                        // System.out.print("\nIndex0 before day: ");
                        // printFishes(population);
                        // reset timer to 6
                        newPopulation[6] = population[0];
                        // decrese counter
                        newPopulation[0] = population[1];
                        // newborn counter
                        newBorn = newBorn + population[0];
                        // System.out.print("\nIndex0 after day: ");
                        // printFishes(newPopulation);
                    } else {
                        newPopulation[0] = population[1];
                    }
                } else if (index == 6) {
                    // System.out.print("\nIndex6 before day: ");
                    // printFishes(population);
                    newPopulation[index] = newPopulation[index] + population[7];
                    // System.out.print("\nIndex6 after day: ");

                    // printFishes(newPopulation);
                } else {
                    newPopulation[index] = population[index + 1];
                }
            }

            // spawn new lanterns
            newPopulation[8] = newBorn;

            System.out.print("\nAfter day " + i + ": ");
            for (int x = 0; x < newPopulation.length; x++) {
                System.out.print(newPopulation[x] + ",");
                population[x] = newPopulation[x];
            }

        }

        // count fishes
        for (int i = 0; i < population.length; i++) {
            size = size + population[i];
        }

        return size;

    }

    public static void main(String[] args) {
        System.out.println("Puzzle 06");

        ArrayList<Long> lanterns = loadInput();
        System.out.print("Initial state: ");

        // Solution A
        // for (Long fish : lanterns) {
        // System.out.print(fish + ",");
        // }
        // int fishCount = computeGenerationsA(lanterns);

        Long[] population = new Long[9];
        for (int i = 0; i < population.length; i++) {
            population[i] = 0L;
        }
        for (int i = 0; i < lanterns.size(); i++) {
            population[Integer.valueOf(lanterns.get(i).intValue())]++;
        }
        for (Long value : population) {
            System.out.print(value + ",");
        }
        long fishCount = computeGenerationsB(population);

        System.out.println("\n\nNumber of lanterns after " + DAYS + " days is: " + fishCount);

    }

}

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Puzzle_04 {
    public final static int CARDSIZE = 5;

    public static class BingoCard {
        Integer[][] card = new Integer[CARDSIZE][CARDSIZE];
        Integer[][] result = new Integer[CARDSIZE][CARDSIZE];
        int bingo = 0;

        BingoCard(Integer[][] c, Integer[][] r) {
            card = c;
            result = r;
        }

        void printCard() {
            System.out.println("\nBingo card: ");
            for (int i = 0; i < CARDSIZE; i++) {
                for (int j = 0; j < CARDSIZE; j++) {
                    System.out.print(card[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\nResult card: ");
            for (int i = 0; i < CARDSIZE; i++) {
                for (int j = 0; j < CARDSIZE; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Bingo COUNT: " + bingo);
        }
    }

    public static void nullifyResults(BingoCard b) {
        for (int i = 0; i < CARDSIZE; i++)
            for (int j = 0; j < CARDSIZE; j++) {
                b.result[i][j] = null;
            }

    }

    public static boolean tryToFind(Integer n, BingoCard b) {
        if (b.bingo > 0)
            return false;

        for (int i = 0; i < CARDSIZE; i++)
            for (int j = 0; j < CARDSIZE; j++) {
                if (n == b.card[i][j]) {
                    b.result[i][j] = 1;
                    System.out.println("Number " + n + " found at [" + i + "," + j + "]");
                    // b.printCard();
                    return true;
                }
            }
        System.out.println("Number " + n + " NOT FOUND");
        return false;
    }

    public static boolean isBingo(BingoCard b) {
        if (b.bingo > 0)
            return false;

        // check rows
        for (int i = 0; i < CARDSIZE; i++)
            for (int j = 0; j < CARDSIZE; j++) {
                if (b.result[i][j] == null)
                    break;
                if ((b.result[i][j] == 1) && (j == (CARDSIZE - 1))) {
                    System.out.println("BINGO in row: " + j);
                    // b.printCard();
                    return true;
                }
            }

        // check columns
        for (int i = 0; i < CARDSIZE; i++)
            for (int j = 0; j < CARDSIZE; j++) {
                if (b.result[j][i] == null)
                    break;
                if ((b.result[j][i] == 1) && (j == (CARDSIZE - 1))) {
                    System.out.println("BINGO in column: " + j);
                    b.printCard();
                    return true;
                }
            }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Puzzle 04");

        // ArrayList<Integer> cardsNumbers = new ArrayList<>();
        // ArrayList<Integer[][]> cardsList = new ArrayList<>();
        ArrayList<BingoCard> cardsList = new ArrayList<>();

        ArrayList<Integer[][]> bingoList = new ArrayList<>();

        Integer actCard[][] = new Integer[5][5];
        Integer nullCard[][] = new Integer[5][5];

        String numInput = "";

        try {
            Scanner file = new Scanner(new File("./Puzzle_04/day04_input.txt"));

            numInput = file.next();
            String emptyLine = file.nextLine();
            System.out.println(emptyLine + "Bingo cards: ");
            int countX = 0;
            int countY = 0;

            while (file.hasNextInt()) {
                int newNumber = file.nextInt();

                // System.out.println("\n ########");

                // for (int x = 0; x < CARDSIZE; x++) {
                // for (int y = 0; y < CARDSIZE; y++) {
                // System.out.print(actCard[x][y] + " ");
                // }
                // }

                // System.out.println("\nNEW number#: " + newNumber + " countX: " + countX + "
                // countY " + countY);

                if (countY == 5) {
                    // System.out.println("SAVING CARD");
                    BingoCard bCard = new BingoCard(actCard, nullCard);
                    cardsList.add(bCard);
                    actCard = new Integer[5][5];
                    nullCard = new Integer[5][5];
                    // System.out.println("CARD SAVED, printing existing cards:");
                    // vypis vseho
                    // for (Integer[][] e : cardsList) {
                    // System.out.println("\nNEXT CARD: ");
                    // for (int ii = 0; ii < CARDSIZE; ii++) {
                    // for (int jj = 0; jj < CARDSIZE; jj++) {
                    // System.out.print(e[ii][jj] + " ");
                    // }
                    // System.out.println();
                    // }
                    // }
                    countY = 0;
                    countX = 0;
                }
                if (countX < 4) {
                    actCard[countY][countX] = newNumber;
                    countX++;
                } else if (countX == 4) {
                    actCard[countY][countX] = newNumber;
                    countX = 0;
                    countY++;
                }
            }
            BingoCard bCard = new BingoCard(actCard, nullCard);
            cardsList.add(bCard);

            file.close();
        } catch (Exception e) {
            System.out.println("Error in input");
            System.out.println(e);
        }

        String[] numbers = numInput.split(",");
        System.out.println("\nBingo numbers: ");
        for (String i : numbers) {
            System.out.print(i + " ");
        }

        // for (Integer i : cardsNumbers) {
        // System.out.print(i.toString() + " ");
        // }

        // vypis vseho cardsList
        // for (BingoCard b : cardsList) {
        // b.printCard();
        // }

        // prepare resultssheet
        // for (int i = 0; i < cardsList.size(); i++) {
        // Integer tempCard[][] = new Integer[5][5];
        // bingoList.add(tempCard);
        // }

        // for (Integer[][] b : bingoList) {
        // System.out.println("\nBingo Result Card: ");
        // for (int ii = 0; ii < CARDSIZE; ii++) {
        // for (int jj = 0; jj < CARDSIZE; jj++) {
        // System.out.print(b[ii][jj] + " ");
        // }
        // System.out.println();
        // }
        // }

        // // find the winner cardboard
        // System.out.println();
        // boolean bingo = false;
        // // for (int i = 0; i < 15; i++) {
        // for (int i = 0; i < numbers.length; i++) {
        // System.out.println("Searching for number: " + numbers[i]);
        // int index = 0;
        // for (BingoCard b : cardsList) {
        // System.out.println("\nSearching in card: " + index);
        // index++;

        // boolean found = false;
        // found = tryToFind(Integer.parseInt(numbers[i]), b);
        // if (found) {
        // bingo = isBingo(b);
        // if (bingo) {
        // System.out.println("BINGO");

        // int sumUnmarked = 0;
        // for (int x = 0; x < CARDSIZE; x++)
        // for (int y = 0; y < CARDSIZE; y++) {
        // if (b.result[x][y] == null)
        // sumUnmarked = sumUnmarked + b.card[x][y];
        // }
        // System.out.println("Sum unmarked: " + sumUnmarked);
        // System.out.println("Winning number: " + numbers[i]);

        // int result = sumUnmarked * Integer.parseInt(numbers[i]);
        // System.out.println("RESULT: " + result);

        // return;
        // }
        // }

        // }
        // }

        // find the loosing cardboard
        System.out.println();
        boolean bingo = false;
        int bingoCount = 0;

        Integer nullCard1[][] = new Integer[5][5];
        Integer nullCard2[][] = new Integer[5][5];
        BingoCard lastBoard = new BingoCard(nullCard1, nullCard2);
        lastBoard.printCard();

        // for (int i = 0; i < 15; i++) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Searching for number: " + numbers[i]);
            int index = 0;
            for (BingoCard b : cardsList) {
                System.out.println("\nSearching in card: " + index);
                index++;

                boolean found = false;
                found = tryToFind(Integer.parseInt(numbers[i]), b);
                if (found) {
                    bingo = isBingo(b);

                    if (bingo) {
                        lastBoard = b;
                        lastBoard.printCard();
                        bingoCount++;
                        b.bingo = bingoCount;
                        // if (bingoCount < cardsList.size()) {
                        // nullifyResults(b);
                        // }

                        System.out.println("BINGO #" + bingoCount);
                        if (bingoCount == cardsList.size()) {
                            lastBoard.printCard();

                            int sumUnmarked = 0;
                            for (int x = 0; x < CARDSIZE; x++)
                                for (int y = 0; y < CARDSIZE; y++) {
                                    if (lastBoard.result[x][y] == null)
                                        sumUnmarked = sumUnmarked + lastBoard.card[x][y];
                                }
                            System.out.println("Sum unmarked: " + sumUnmarked);
                            System.out.println("Winning number: " + numbers[i]);

                            int result = sumUnmarked * Integer.parseInt(numbers[i]);
                            System.out.println("RESULT: " + result);
                            return;
                        }
                        // int sumUnmarked = 0;
                        // for (int x = 0; x < CARDSIZE; x++)
                        // for (int y = 0; y < CARDSIZE; y++) {
                        // if (b.result[x][y] == null)
                        // sumUnmarked = sumUnmarked + b.card[x][y];
                        // }
                        // System.out.println("Sum unmarked: " + sumUnmarked);
                        // System.out.println("Winning number: " + numbers[i]);

                        // int result = sumUnmarked * Integer.parseInt(numbers[i]);
                        // System.out.println("RESULT: " + result);

                        // return;
                    }
                }

            }
        }

        // vypis vseho cardsList
        for (

        BingoCard b : cardsList) {
            b.printCard();
        }

    }

}

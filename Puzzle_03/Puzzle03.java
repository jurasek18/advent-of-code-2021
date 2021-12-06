import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Puzzle03 {
    public final static int COUNT = 1000;

    public static class Diagnostics {
        String diag;

        Diagnostics(String d) {
            diag = d;
        }

        void printDiag() {
            System.out.println(
                    "Diagnostics: " + diag);
        }
    }

    public static class Bits {
        String MCB;
        String LCB;

        Bits() {
            MCB = "";
            LCB = "";
        }

        void printBits() {
            System.out.println("MCB: " + MCB + "   LCB: " + LCB);
        }
    }

    // public static class Results {
    // public int direction;
    // public int speed;
    // public int aim;

    // Results() {
    // direction = 0;
    // speed = 0;
    // aim = 0;
    // }

    // void printRes() {
    // System.out.println("Direction = " + direction + " " + " speed = " + speed + "
    // aim = " + aim);
    // }
    // }

    public static void main(String[] args) {
        System.out.println("Puzzle 03");

        Diagnostics[] input = new Diagnostics[COUNT];
        int index = 0;
        try {
            Scanner file = new Scanner(new File("./Puzzle_03/day03_input.txt"));

            while (file.hasNext()) {
                input[index] = new Diagnostics(file.next());
                input[index].printDiag();
                index++;
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error in input");
            System.out.println(e);
        }

        int length = input[0].diag.length();
        System.out.println("LENGTH: " + length);
        Bits bits = new Bits();
        bits.printBits();

        int ones;
        int zeros;
        for (int j = 0; j < length; j++) {

            ones = 0;
            zeros = 0;
            for (int i = 0; i < COUNT; i++) {
                if (input[i].diag.charAt(j) == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }
            if (ones > zeros) {
                bits.MCB = bits.MCB + "1";
                bits.LCB = bits.LCB + "0";
            } else {
                bits.MCB = bits.MCB + "0";
                bits.LCB = bits.LCB + "1";
            }
            bits.printBits();
        }

        int decimalMCB = Integer.parseInt(bits.MCB, 2);
        int decimalLCB = Integer.parseInt(bits.LCB, 2);

        System.out.println("MCB: " + decimalMCB + " LCB: " + decimalLCB);
        int result = decimalMCB * decimalLCB;
        System.out.println("RESULT: " + result);

        // Compute OGR
        ArrayList<Diagnostics> OGR = new ArrayList<Diagnostics>();
        for (int i = 0; i < COUNT; i++) {
            OGR.add(new Diagnostics(input[i].diag));
        }

        for (int i = 0; i < length; i++) {
            System.out.println("OGR length: " + OGR.size());

            // compute new MCB and LCB from remaining
            int bones = 0;
            int bzeros = 0;
            char aMCB = 'x';

            for (Diagnostics d : OGR) {
                if (d.diag.charAt(i) == '1') {
                    bones++;
                } else {
                    bzeros++;
                }
            }
            if (bones >= bzeros) {
                aMCB = '1';
            } else {
                aMCB = '0';
            }

            System.out.println("AMCB: " + aMCB);
            if (aMCB == '1') {
                System.out.println("More 1s, Deleting 0s");
                List<Diagnostics> toDelete = new ArrayList<Diagnostics>();
                for (Diagnostics d : OGR) {
                    if (d.diag.charAt(i) == '0') {
                        toDelete.add(d);
                    }
                }
                OGR.removeAll(toDelete);
                System.out.println("AFTER DELETE");
                for (Diagnostics d : OGR) {
                    d.printDiag();
                }
            }
            if (aMCB == '0') {
                System.out.println("More 0s, Deleting 1s");
                List<Diagnostics> toDelete = new ArrayList<Diagnostics>();
                for (Diagnostics d : OGR) {
                    if (d.diag.charAt(i) == '1') {
                        toDelete.add(d);
                    }
                }
                OGR.removeAll(toDelete);
                System.out.println("AFTER DELETE");
                for (Diagnostics d : OGR) {
                    d.printDiag();
                }
            }
            int ogrSize = OGR.size();
            if (ogrSize == 1) {
                break;
            }
        }
        int decimalOGR = Integer.parseInt(OGR.get(0).diag, 2);
        System.out.println("OGR dec: " + decimalOGR);

        // Compute CO2SR
        ArrayList<Diagnostics> CO2SR = new ArrayList<Diagnostics>();
        for (int i = 0; i < COUNT; i++) {
            CO2SR.add(new Diagnostics(input[i].diag));
        }

        for (int i = 0; i < length; i++) {
            System.out.println("CO2SR length: " + CO2SR.size());

            // compute new MCB and LCB from remaining
            int bones = 0;
            int bzeros = 0;
            char aMCB = 'x';

            for (Diagnostics d : CO2SR) {
                if (d.diag.charAt(i) == '1') {
                    bones++;
                } else {
                    bzeros++;
                }
            }
            if (bones >= bzeros) {
                aMCB = '1';
            } else {
                aMCB = '0';
            }

            System.out.println("AMCB: " + aMCB);
            if (aMCB == '0') {
                System.out.println("More 1s, Deleting 0s");
                List<Diagnostics> toDelete = new ArrayList<Diagnostics>();
                for (Diagnostics d : CO2SR) {
                    if (d.diag.charAt(i) == '0') {
                        toDelete.add(d);
                    }
                }
                CO2SR.removeAll(toDelete);
                System.out.println("AFTER DELETE");
                for (Diagnostics d : CO2SR) {
                    d.printDiag();
                }
            }
            if (aMCB == '1') {
                System.out.println("More 0s, Deleting 1s");
                List<Diagnostics> toDelete = new ArrayList<Diagnostics>();
                for (Diagnostics d : CO2SR) {
                    if (d.diag.charAt(i) == '1') {
                        toDelete.add(d);
                    }
                }
                CO2SR.removeAll(toDelete);
                System.out.println("AFTER DELETE");
                for (Diagnostics d : CO2SR) {
                    d.printDiag();
                }
            }
            int co2Size = CO2SR.size();
            if (co2Size == 1) {
                System.out.println("CO2 size: " + co2Size);
                break;
            }
        }
        int decimalCO2SR = Integer.parseInt(CO2SR.get(0).diag, 2);
        System.out.println("CO2SR dec: " + decimalCO2SR);

        int result2 = decimalOGR * decimalCO2SR;
        System.out.println("RESULT:" + result2);

    }
}

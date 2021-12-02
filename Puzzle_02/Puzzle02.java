import java.io.File;
import java.util.Scanner;

class Puzzle02 {
    public final static int COUNT = 1000;
    public static class Instructions {
        public String direction;
        public int speed;

        Instructions(String d, int s) {
            direction = d;
            speed = s;
        }

        void printInst() {
            System.out.println("Direction = "+ direction + " " + " speed = " + speed);
        } 
    }
    
    public static class Results {
        public int direction;
        public int speed;
        public int aim;

        Results() {
            direction = 0;
            speed = 0;
            aim = 0;
        }

        void printRes() {
            System.out.println("Direction = "+ direction + " " + " speed = " + speed + " aim = " + aim);
        } 
    }

    public static void main(String[] args) {
        System.out.println("Puzzle 02");

        Instructions[] input = new Instructions[COUNT];    
        int nw = 0;
        try {
            Scanner file = new Scanner(new File("./Puzzle_02/day02_input.txt"));

            while (file.hasNext()) {
                System.out.print("Word #: ");
                System.out.println(nw);
                String dir = file.next();
                String sp = file.next();
                int s = Integer.parseInt(sp);
                input[nw] = new Instructions(dir,s);
                input[nw].printInst();
                nw++;
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error in input");
            System.out.println(e); 
        }   

        Results result = new Results();
        System.out.println("Computing...");
        result.printRes();

        for (int i=0;i<COUNT;i++) {
            input[i].printInst();

            switch(input[i].direction) {
                case "forward":
                    result.direction = result.direction + input[i].speed;
                    result.speed = result.speed + (input[i].speed * result.aim);
                    break;
                case "up":
                    result.aim = result.aim - input[i].speed;
                break;
                case "down":
                    result.aim = result.aim + input[i].speed;
                break;
                default:
                  System.out.println("Unsupported direction");
            }
            result.printRes();
        }

        System.out.println(result.speed * result.direction);
        
    }
}

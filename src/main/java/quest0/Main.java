package quest0;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try(Scanner scanner = new Scanner(System.in)) {
            double tests = Double.parseDouble(scanner.nextLine());

            int i = 0;
            while (i < tests) {
                String[] testParams = scanner.nextLine().split(" ");

                double c = Double.parseDouble(testParams[0]);
                double k = Double.parseDouble(testParams[1]);
                double w = Double.parseDouble(testParams[2]);

                String result =  c * w <= k ? "yes" : "no";
                System.out.println(result);
                i++;
            }
        }
    }
}

package quest1;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int columns = scanner.nextInt();
        int elements = scanner.nextInt();
        int result = (int)Math.pow(columns, columns + 1) * elements;
        System.out.println(result);

    }
}

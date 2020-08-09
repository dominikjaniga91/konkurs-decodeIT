package quest3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Service service = new Service();
        Scanner scanner = new Scanner(System.in);
        int iterations = scanner.nextInt();
        int i = 0;
        while (i < iterations) {

            int numberOfGroups = scanner.nextInt();
            scanner.nextLine();
            String code = scanner.nextLine();
            String[] foursDigitsNumbers =  service.getFourDigitsGroups(code, numberOfGroups);
            int[] asciiCodes = service.getAsciiCodesArray(foursDigitsNumbers);
            String password = service.getPassword(asciiCodes);
            System.out.println(password);
            i++;
        }

    }
}

class Service{

    public String[] getFourDigitsGroups(String code, int groups) {

        String[] numbers = new String[groups];
        int i = 0;
        int j = 0;

        while (i < code.length()) {
            numbers[j] = code.substring(i, i+4);
            i += 4;
            j++;
        }
        getAsciiCodesArray(numbers);
        return numbers;
    }

    public int[] getAsciiCodesArray(String[] numbers) {

        int[] asciiCodes = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            char[] temporary = numbers[i].toCharArray();

            String first = getAsString(temporary, 0) + getAsString(temporary, 2);
            String second = getAsString(temporary, 1) + getAsString(temporary, 3);
            asciiCodes[i] = getAsInteger(first) + getAsInteger(second);
        }
        return asciiCodes;
    }
    private int getAsInteger(String number){
        return Integer.parseInt(number);
    }

    private String getAsString(char[] temporary, int index){
        return String.valueOf(temporary[index]);
    }

    public String getPassword(int[] asciiCodes){

        StringBuilder builder = new StringBuilder();
        for (int number : asciiCodes) {
            builder.append((char)number);
        }
        return builder.toString();
    }
}

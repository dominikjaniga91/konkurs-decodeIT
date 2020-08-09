package quest2;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        var scanner     = new Scanner(System.in);
        var service     = new Service();
        int cases           = Integer.parseInt(scanner.nextLine());
        int i               = 0;

        while (i < cases) {
            int number = scanner.nextInt();
            System.out.println(service.getSteps(number));
            i++;
        }
    }
}

class Service {

    public int getSteps(int input){

        int steps = 0;
        int result = 0;
        int[] numbers = toIntegerArray(input);

        while (result != 6174 && input != 6174) {
            if (input % 1111 == 0) {
                steps = -1;
                break;
            }
            int ascending = setNumbersAscending(numbers);
            int descending = setNumbersDescending(numbers);
            result = descending - ascending;
            result = hasLessThanFourDigits(result) ? addZeroAtTheEnd(result)
                    : result;
            numbers = toIntegerArray(result);
            steps++;
        }

        return steps;
    }

    private boolean hasLessThanFourDigits(int result){
        return String.valueOf(result).length() < 4;
    }

    private int addZeroAtTheEnd(int result){
        char[] chars = String.valueOf(result).toCharArray();
        int[] newResult = new int[4];

        for (int i = 0; i < chars.length; i++) {
            newResult[i] = Integer.parseInt(String.valueOf(chars[i]));
        }

        return getAsNumber(newResult);
    }


    private int setNumbersAscending(int[] numbers){

        boolean isTrue = false;

        while (!isTrue) {
            isTrue = true;
            for (int i = 1; i < numbers.length; i++) {
                int temp = numbers[i];
                if (temp < numbers[i - 1]) {
                    numbers[i] = numbers[i - 1];
                    numbers[i - 1] = temp;
                    isTrue = false;
                }
            }
        }

        return getAsNumber(numbers);
    }

    private int setNumbersDescending(int[] numbers){

        boolean isTrue = false;

        while (!isTrue) {
            isTrue = true;
            for (int i = 1; i < numbers.length; i++) {
                int temp = numbers[i];
                if (temp > numbers[i - 1]) {
                    numbers[i] = numbers[i - 1];
                    numbers[i - 1] = temp;
                    isTrue = false;
                }
            }
        }
        return getAsNumber(numbers);
    }

    private int[] toIntegerArray(int input){

        char[] characters = String.valueOf(input).toCharArray();
        int[] numbers = new int[characters.length];

        for (int i = 0; i < numbers.length; i++){
            numbers[i] = Character.getNumericValue(characters[i]);
        }
        return numbers;
    }

    private int getAsNumber(int[] array) {

        String number = Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());

        return Integer.parseInt(number);
    }
}
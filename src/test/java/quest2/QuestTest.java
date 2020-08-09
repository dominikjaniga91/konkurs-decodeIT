package quest2;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class QuestTest {

    private Service service = new Service();


    @ParameterizedTest
    @CsvSource({
            "4527, 7",
            "6174, 0",
            "4223, 3",
            "2088, 2",
            "8532, 1",
            "1112, 5",
            "3524, 3",
            "1111, -1",
            "2222, -1",
            "3333, -1",
            "4444, -1",
            "5555, -1",
            "6666, -1",
            "7777, -1",
            "8888, -1",
            "9999, -1",
            "1024, 7",
            "1949, 7",
            "5200, 7",
            "1789, 3",
    })
    void shouldReturnAppropriateNumbersOfSteps(int input, int steps){

        Assertions.assertEquals(steps, service.getSteps(input));
    }

}

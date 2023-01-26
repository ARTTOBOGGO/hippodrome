import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @MethodSource("horseListForTest")
    void getHorses(List<Horse> horses) {
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());

    }
    public static Stream<Arguments> horseListForTest(){
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 1; i <31; i++) {
            horses.add(new Horse("a"+i,i,i));
        }
        return Stream.of(
                Arguments.of(horses)
        );
    }

    @ParameterizedTest
    @MethodSource("mockListForTest")
    void move(List<Horse> horses) {
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse hors : horses) {
            Mockito.verify(hors).move();
        }
    }
    public static Stream<Arguments> mockListForTest(){
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            Horse mock = Mockito.mock(Horse.class);
            horses.add(mock);
        }
        return Stream.of(
                Arguments.of(horses)
        );
    }

    @ParameterizedTest
    @MethodSource("horseListForTest")
    void getWinner(List<Horse> horses) {
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(horses.size()-1).getDistance(),hippodrome.getWinner().getDistance());
    }
    @ParameterizedTest
    @MethodSource("horseListForTest")
    void constructorTest(List<Horse> horses){
        String oneParametrs = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null)).getMessage();
        assertEquals("Horses cannot be null.",oneParametrs);

        String twoParametrs = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>())).getMessage();
        assertEquals("Horses cannot be empty.",twoParametrs);
    }


}
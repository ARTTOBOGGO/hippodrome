import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorseTest {

    public static Stream<Arguments> argsProviderFactory() {
        return Stream.of(
                Arguments.of("\n"),
                Arguments.of("\r"),
                Arguments.of("\t")
        );
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals("t",new Horse("t",0,0).getName());
    }

    @Test
    void getSpeed() {
        assertEquals(0,new Horse("t",0,0).getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(1,new Horse("t",0,1).getDistance());
        assertEquals(0,new Horse("t",0).getDistance());
    }

    @Test
    void move() {
        Horse horse = new Horse("j", 0, 0);

        try(MockedStatic<Horse> mockRund = mockStatic(Horse.class)) {
            mockRund.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(2d);
            //System.out.println(Horse.getRandomDouble(0.2,0.9));
            horse.move();
            mockRund.verify(()->Horse.getRandomDouble(0.2, 0.9), times(1));
            assertEquals(2d,Horse.getRandomDouble(0.2,0.9));
        }






    }


    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    void HorseConstructor(String args){
        String oneParametrs = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0, 0)).getMessage();
        assertEquals("Name cannot be null.",oneParametrs);

        String spaceParametrs = assertThrows(IllegalArgumentException.class, () -> new Horse(args, 0, 0)).getMessage();
        assertEquals("Name cannot be blank.",spaceParametrs);

        String secondParametr = assertThrows(IllegalArgumentException.class, () -> new Horse("t", -2, 0)).getMessage();
        assertEquals("Speed cannot be negative.",secondParametr);

        String thirdParametrs = assertThrows(IllegalArgumentException.class, () -> new Horse("t", 2, -3)).getMessage();
        assertEquals("Distance cannot be negative.",thirdParametrs);



    }
}
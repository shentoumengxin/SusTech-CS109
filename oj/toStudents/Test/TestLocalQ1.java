import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
public class TestLocalQ1 {

    @Test
    public void TestDifference() {
        try {
            Class<?> clz = Class.forName("MortgageDate");
            Method getDifferenceMethod = clz.getDeclaredMethod("getDifference",int.class,int.class);
            int count = 1;
            for (int i = 2023; i < 2025; i++) {
                for (int j = 1; j <= 12; j++) {
                    int p1= 202212;
                    int p2 = i*100+j;
                    assertEquals(count, getDifferenceMethod.invoke(null,p1,p2),String.format("p1 is %d, p2 is %d, result should be %d", p1, p2, count));
                    count++;
                }
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestGetMonthsPassedDate() {
        try {
            Class<?> clz = Class.forName("MortgageDate");
            Method getMonthsPassedDateMethod = clz.getDeclaredMethod("getMonthsPassedDate", int.class, int.class);
            int startDate = 202310;
            int[] result = {202310, 202401, 202404, 202407, 202410, 202501, 202504, 202507, 202510};
            for (int i = 0; i < 25; i+=3) {
                int targetData = result[i/3];
                assertEquals(getMonthsPassedDateMethod.invoke(null, startDate, i), targetData, String.format("Start date is %d, pass %d months, the date should be %d", startDate, i, targetData));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    public static void main(String[] args) {
        for (int i = 2023; i <2025 ; i++) {
            for (int j = 1; j <=3 ; j++) {
                System.out.println(MortgageDate.getDifference(202301,i*100+j));
            }

        }
        int startDate = 202310;
        int[] result = {202310, 202401, 202404, 202407, 202410, 202501, 202504, 202507, 202510};
        for (int i = 0; i < 25; i+=3) {
            System.out.println(MortgageDate.getMonthsPassedDate( startDate,i ));
        }
    }
}

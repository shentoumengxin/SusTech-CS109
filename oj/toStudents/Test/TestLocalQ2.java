import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
public class TestLocalQ2 {
    @Test
    public void TestEqualPrincipalRepayerConstructor_1() {
        try {
            Class<?> clz = Class.forName("EqualPrincipalRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);

            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            assertEquals(clz.getMethod("getId").invoke(object_1), 188, "ID should be 188");
            assertEquals(clz.getMethod("getGender").invoke(object_1), 'F', String.format("Gender should be %c", 'F'));
            assertEquals(clz.getMethod("getProfession").invoke(object_1), Profession.AGRICULTURE, "Profession should be AGRICULTURE");
            assertEquals(clz.getMethod("getTotalLoan").invoke(object_1), 20000, "Total loan should be 20000");
            assertEquals(clz.getMethod("getMortgageStartDate").invoke(object_1), 202311, "Mortgage start date should be 202311");
            assertEquals(clz.getMethod("getMortgageTotalMonths").invoke(object_1), 12, "Mortgage total months should be 12");
            assertEquals(clz.getMethod("getRate").invoke(object_1), 0.005, "Rate should be 0.005");

            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            assertEquals(clz.getMethod("getId").invoke(object_2), 12, "ID should be 12");
            assertEquals(clz.getMethod("getGender").invoke(object_2), 'M', String.format("Gender should be %c", 'M'));
            assertEquals(clz.getMethod("getProfession").invoke(object_2), Profession.ENTERPRISE, "Profession should be ENTERPRISE");
            assertEquals(clz.getMethod("getTotalLoan").invoke(object_2), 50000, "Total loan should be 50000");
            assertEquals(clz.getMethod("getMortgageStartDate").invoke(object_2), 202301, "Mortgage start date should be 202301");
            assertEquals(clz.getMethod("getMortgageTotalMonths").invoke(object_2), 24, "Mortgage total months should be 24");
            assertEquals(clz.getMethod("getRate").invoke(object_2), 0.003, "Rate should be 0.003");

            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            assertEquals(clz.getMethod("getId").invoke(object_3), 3402, "ID should be 3402");
            assertEquals(clz.getMethod("getGender").invoke(object_3), 'M', String.format("Gender should be %c", 'M'));
            assertEquals(clz.getMethod("getProfession").invoke(object_3), Profession.PUBLIC_INSTITUTIONS, "Profession should be PUBLIC_INSTITUTIONS");
            assertEquals(clz.getMethod("getTotalLoan").invoke(object_3), 100000, "Total loan should be 100000");
            assertEquals(clz.getMethod("getMortgageStartDate").invoke(object_3), 202401, "Mortgage start date should be 202401");
            assertEquals(clz.getMethod("getMortgageTotalMonths").invoke(object_3), 36, "Mortgage total months should be 36");
            assertEquals(clz.getMethod("getRate").invoke(object_3), 0.004, "Rate should be 0.004");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestEqualPaymentRepayerConstructor() {
        try {
            Class<?> clz = Class.forName("EqualPaymentRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);

            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            assertEquals(clz.getMethod("getId").invoke(object_1), 188, "ID should be 188");
            assertEquals(clz.getMethod("getGender").invoke(object_1), 'F', String.format("Gender should be %c", 'F'));
            assertEquals(clz.getMethod("getProfession").invoke(object_1), Profession.AGRICULTURE, "Profession should be AGRICULTURE");
            assertEquals(clz.getMethod("getTotalLoan").invoke(object_1), 20000, "Total loan should be 20000");
            assertEquals(clz.getMethod("getMortgageStartDate").invoke(object_1), 202311, "Mortgage start date should be 202311");
            assertEquals(clz.getMethod("getMortgageTotalMonths").invoke(object_1), 12, "Mortgage total months should be 12");
            assertEquals(clz.getMethod("getRate").invoke(object_1), 0.005, "Rate should be 0.005");

            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            assertEquals(clz.getMethod("getId").invoke(object_2), 12, "ID should be 12");
            assertEquals(clz.getMethod("getGender").invoke(object_2), 'M', String.format("Gender should be %c", 'M'));
            assertEquals(clz.getMethod("getProfession").invoke(object_2), Profession.ENTERPRISE, "Profession should be ENTERPRISE");
            assertEquals(clz.getMethod("getTotalLoan").invoke(object_2), 50000, "Total loan should be 50000");
            assertEquals(clz.getMethod("getMortgageStartDate").invoke(object_2), 202301, "Mortgage start date should be 202301");
            assertEquals(clz.getMethod("getMortgageTotalMonths").invoke(object_2), 24, "Mortgage total months should be 24");
            assertEquals(clz.getMethod("getRate").invoke(object_2), 0.003, "Rate should be 0.003");

            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            assertEquals(clz.getMethod("getId").invoke(object_3), 3402, "ID should be 3402");
            assertEquals(clz.getMethod("getGender").invoke(object_3), 'M', String.format("Gender should be %c", 'M'));
            assertEquals(clz.getMethod("getProfession").invoke(object_3), Profession.PUBLIC_INSTITUTIONS, "Profession should be PUBLIC_INSTITUTIONS");
            assertEquals(clz.getMethod("getTotalLoan").invoke(object_3), 100000, "Total loan should be 100000");
            assertEquals(clz.getMethod("getMortgageStartDate").invoke(object_3), 202401, "Mortgage start date should be 202401");
            assertEquals(clz.getMethod("getMortgageTotalMonths").invoke(object_3), 36, "Mortgage total months should be 36");
            assertEquals(clz.getMethod("getRate").invoke(object_3), 0.004, "Rate should be 0.004");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestRepayerToString() {
        try {
            Class<?> clz = Class.forName("EqualPaymentRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);

            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            assertEquals(clz.getMethod("toString").invoke(object_1), "[188] F AGRICULTURE", "The output should be '[188] F AGRICULTURE'");
            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            assertEquals(clz.getMethod("toString").invoke(object_2), "[12] M ENTERPRISE", "The output should be '[12] M ENTERPRISE'");
            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            assertEquals(clz.getMethod("toString").invoke(object_3), "[3402] M PUBLIC_INSTITUTIONS", "The output should be '[3402] M PUBLIC_INSTITUTIONS'");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestRepayerGetMonthNumber() {
        try {
            Class<?> clz = Class.forName("EqualPaymentRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);

            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            int[] dates_1 = {202310, 202311, 202402, 202406, 202410, 202411, 202501};
            int[] results_1 = {0, 1, 4, 8, 12, 0, 0};
            for (int i = 0; i < dates_1.length; i++) {
                int monthNum = (int) clz.getMethod("getMonthNumber", int.class).invoke(object_1, dates_1[i]);
                assertEquals(monthNum, results_1[i], String.format("Month number of %d should be %d", dates_1[i], results_1[i]));
            }

            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            int[] dates_2 = {202212, 202301, 202309, 202404, 202412, 202501, 202512};
            int[] results_2 = {0, 1, 9, 16, 24, 0, 0};
            for (int i = 0; i < dates_2.length; i++) {
                int monthNum = (int) clz.getMethod("getMonthNumber", int.class).invoke(object_2, dates_2[i]);
                assertEquals(monthNum, results_2[i], String.format("Month number of %d should be %d", dates_2[i], results_2[i]));
            }

            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            int[] dates_3 = {202312, 202401, 202412, 202512, 202612, 202701, 202712};
            int[] results_3 = {0, 1, 12, 24, 36, 0, 0};
            for (int i = 0; i < dates_3.length; i++) {
                int monthNum = (int) clz.getMethod("getMonthNumber", int.class).invoke(object_3, dates_3[i]);
                assertEquals(monthNum, results_3[i], String.format("Month number of %d should be %d", dates_3[i], results_3[i]));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestEqualPaymentRepayerGetPayment() {
        try {
            Class<?> clz = Class.forName("EqualPaymentRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);

            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            int[] dates_1 = {202310, 202311, 202402, 202406, 202410, 202411, 202501};
            double[] results_1 = {0, 1671.18, 1671.18, 1671.18, 1671.18, 0, 0};
            for (int i = 0; i < dates_1.length; i++) {
                double payment = (double) clz.getMethod("getPayment", int.class).invoke(object_1, dates_1[i]);
                assertTrue(Math.abs(payment - results_1[i]) <= 0.01, String.format("The payment of %d should be %f", dates_1[i], results_1[i]));
            }

            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            int[] dates_2 = {202212, 202301, 202309, 202404, 202412, 202501, 202512};
            double[] results_2 = {0, 2089.85, 2089.85, 2089.85, 2089.85, 0, 0};
            for (int i = 0; i < dates_2.length; i++) {
                double payment = (double) clz.getMethod("getPayment", int.class).invoke(object_2, dates_2[i]);
                assertTrue(Math.abs(payment - results_2[i]) <= 0.01, String.format("The payment of %d should be %f", dates_2[i], results_2[i]));
            }

            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            int[] dates_3 = {202312, 202401, 202412, 202512, 202612, 202701, 202712};
            double[] results_3 = {0, 2794.94, 2794.94, 2794.94, 2794.94, 0, 0};
            for (int i = 0; i < dates_3.length; i++) {
                double payment = (double) clz.getMethod("getPayment", int.class).invoke(object_3, dates_3[i]);
                assertTrue(Math.abs(payment - results_3[i]) <= 0.01, String.format("The payment of %d should be %f", dates_3[i], results_3[i]));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestEqualPrincipleRepayerGetPayment() {
        try {
            Class<?> clz = Class.forName("EqualPrincipalRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);

            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            int[] dates_1 = {202310, 202311, 202402, 202406, 202410, 202411, 202501};
            double[] results_1 = {0, 1675, 1672.92, 1670.14, 1667.36, 0, 0};
            for (int i = 0; i < dates_1.length; i++) {
                double payment = (double) clz.getMethod("getPayment", int.class).invoke(object_1, dates_1[i]);
                assertTrue(Math.abs(payment - results_1[i]) <= 0.01, String.format("The payment of %d should be %f", dates_1[i], results_1[i]));
            }

            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            int[] dates_2 = {202212, 202301, 202309, 202404, 202412, 202501, 202512};
            double[] results_2 = {0, 2095.83, 2091.67, 2088.02, 2083.85, 0, 0};
            for (int i = 0; i < dates_2.length; i++) {
                double payment = (double) clz.getMethod("getPayment", int.class).invoke(object_2, dates_2[i]);
                assertTrue(Math.abs(payment - results_2[i]) <= 0.01, String.format("The payment of %d should be %f", dates_2[i], results_2[i]));
            }

            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            int[] dates_3 = {202312, 202401, 202412, 202512, 202612, 202701, 202712};
            double[] results_3 = {0, 2811.11, 2800.93, 2789.81, 2778.70, 0, 0};
            for (int i = 0; i < dates_3.length; i++) {
                double payment = (double) clz.getMethod("getPayment", int.class).invoke(object_3, dates_3[i]);
                assertTrue(Math.abs(payment - results_3[i]) <= 0.01, String.format("The payment of %d should be %f", dates_3[i], results_3[i]));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestEqualPaymentRepayerGetInterest() {
        try {
            Class<?> clz = Class.forName("EqualPaymentRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);

            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            int[] dates_1 = {202310, 202311, 202402, 202406, 202410, 202411, 202501};
            double[] results_1 = {0, 8.33, 6.25, 3.48, 0.70, 0, 0};
            for (int i = 0; i < dates_1.length; i++) {
                double interest = (double) clz.getMethod("getInterest", int.class).invoke(object_1, dates_1[i]);
                assertTrue(Math.abs(interest - results_1[i]) <= 0.01, String.format("The interest of %d should be %f", dates_1[i], results_1[i]));
            }

            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            int[] dates_2 = {202212, 202301, 202309, 202404, 202412, 202501, 202512};
            double[] results_2 = {0, 12.5, 8.34, 4.70, 0.52, 0, 0};
            for (int i = 0; i < dates_2.length; i++) {
                double interest = (double) clz.getMethod("getInterest", int.class).invoke(object_2, dates_2[i]);
                assertTrue(Math.abs(interest - results_2[i]) <= 0.01, String.format("The interest of %d should be %f", dates_2[i], results_2[i]));
            }

            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            int[] dates_3 = {202312, 202401, 202412, 202512, 202612, 202701, 202712};
            double[] results_3 = {0, 33.33, 23.19, 12.08, 0.93, 0, 0};
            for (int i = 0; i < dates_3.length; i++) {
                double interest = (double) clz.getMethod("getInterest", int.class).invoke(object_3, dates_3[i]);
                assertTrue(Math.abs(interest - results_3[i]) <= 0.01, String.format("The interest of %d should be %f", dates_3[i], results_3[i]));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestEqualPrincipleRepayerGetInterest() {
        try {
            Class<?> clz = Class.forName("EqualPrincipalRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);

            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            int[] dates_1 = {202310, 202311, 202402, 202406, 202410, 202411, 202501};
            double[] results_1 = {0, 8.33, 6.25, 3.47, 0.69, 0, 0};
            for (int i = 0; i < dates_1.length; i++) {
                double interest = (double) clz.getMethod("getInterest", int.class).invoke(object_1, dates_1[i]);
                assertTrue(Math.abs(interest - results_1[i]) <= 0.01, String.format("The interest of %d should be %f", dates_1[i], results_1[i]));
            }

            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            int[] dates_2 = {202212, 202301, 202309, 202404, 202412, 202501, 202512};
            double[] results_2 = {0, 12.5, 8.33, 4.69, 0.52, 0, 0};
            for (int i = 0; i < dates_2.length; i++) {
                double interest = (double) clz.getMethod("getInterest", int.class).invoke(object_2, dates_2[i]);
                assertTrue(Math.abs(interest - results_2[i]) <= 0.01, String.format("The interest of %d should be %f", dates_2[i], results_2[i]));
            }

            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            int[] dates_3 = {202312, 202401, 202412, 202512, 202612, 202701, 202712};
            double[] results_3 = {0, 33.33, 23.14, 12.04, 0.93, 0, 0};
            for (int i = 0; i < dates_3.length; i++) {
                double interest = (double) clz.getMethod("getInterest", int.class).invoke(object_3, dates_3[i]);
                assertTrue(Math.abs(interest - results_3[i]) <= 0.01, String.format("The interest of %d should be %f", dates_3[i], results_3[i]));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestEqualPaymentRepayerGetPrincipal() {
        try {
            Class<?> clz = Class.forName("EqualPaymentRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);
            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            int[] dates_2 = {202212, 202301, 202309, 202404, 202412, 202501, 202512};
            double[] results_2 = {0, 2077.35, 2081.51, 2085.15, 2089.33, 0, 0};
            for (int i = 0; i < dates_2.length; i++) {
                double principal = (double) clz.getMethod("getPrincipal", int.class).invoke(object_2, dates_2[i]);
                assertTrue(Math.abs(principal - results_2[i]) <= 0.01, String.format("The principal of %d should be %f", dates_2[i], results_2[i]));
            }

            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            int[] dates_1 = {202310, 202311, 202402, 202406, 202410, 202411, 202501};
            double[] results_1 = {0, 1662.85, 1664.93, 1667.71, 1670.49, 0, 0};
            for (int i = 0; i < dates_1.length; i++) {
                double principal = (double) clz.getMethod("getPrincipal", int.class).invoke(object_1, dates_1[i]);
                assertTrue(Math.abs(principal - results_1[i]) <= 0.01, String.format("The principal of %d should be %f", dates_1[i], results_1[i]));
            }



            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            int[] dates_3 = {202312, 202401, 202412, 202512, 202612, 202701, 202712};
            double[] results_3 = {0, 2761.61, 2771.75, 2782.86, 2794.01, 0, 0};
            for (int i = 0; i < dates_3.length; i++) {
                double principal = (double) clz.getMethod("getPrincipal", int.class).invoke(object_3, dates_3[i]);
                assertTrue(Math.abs(principal - results_3[i]) <= 0.01, String.format("The principal of %d should be %f", dates_3[i], results_3[i]));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestEqualPrincipleRepayerGetPrincipal() {
        try {
            Class<?> clz = Class.forName("EqualPrincipalRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);


            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            int[] dates_1 = {202310, 202311, 202402, 202406, 202410, 202411, 202501};
            double[] results_1 = {0, 1666.67, 1666.67, 1666.67, 1666.67, 0, 0};
            for (int i = 0; i < dates_1.length; i++) {
                double principal = (double) clz.getMethod("getPrincipal", int.class).invoke(object_1, dates_1[i]);
                assertTrue(Math.abs(principal - results_1[i]) <= 0.01, String.format("The principal of %d should be %f", dates_1[i], results_1[i]));
            }

            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            int[] dates_2 = {202212, 202301, 202309, 202404, 202412, 202501, 202512};
            double[] results_2 = {0, 2083.33, 2083.33, 2083.33, 2083.33, 0, 0};
            for (int i = 0; i < dates_2.length; i++) {
                double principal = (double) clz.getMethod("getPrincipal", int.class).invoke(object_2, dates_2[i]);
                assertTrue(Math.abs(principal - results_2[i]) <= 0.01, String.format("The principal of %d should be %f", dates_2[i], results_2[i]));
            }

            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            int[] dates_3 = {202312, 202401, 202412, 202512, 202612, 202701, 202712};
            double[] results_3 = {0, 2777.78, 2777.78, 2777.78, 2777.78, 0, 0};
            for (int i = 0; i < dates_3.length; i++) {
                double principal = (double) clz.getMethod("getPrincipal", int.class).invoke(object_3, dates_3[i]);
                assertTrue(Math.abs(principal - results_3[i]) <= 0.01, String.format("The principal of %d should be %f", dates_3[i], results_3[i]));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestEqualPaymentRepayerGetCurrentLiability() {
        try {
            Class<?> clz = Class.forName("EqualPaymentRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);
            
            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            int[] dates_1 = {202310, 202311, 202402, 202406, 202410, 202411, 202501};
            double[] results_1 = {0, 20000, 15009.37, 8345.49, 1670.49, 0, 0};
            for (int i = 0; i < dates_1.length; i++) {
                double currentLiability = (double) clz.getMethod("getCurrentLiability", int.class).invoke(object_1, dates_1[i]);
                assertTrue(Math.abs(currentLiability - results_1[i]) <= 0.01, String.format("The currentLiability of %d should be %f", dates_1[i], results_1[i]));
            }

            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            int[] dates_2 = {202212, 202301, 202309, 202404, 202412, 202501, 202512};
            double[] results_2 = {0, 50000, 33366.65, 18785.16, 2089.33, 0, 0};
            for (int i = 0; i < dates_2.length; i++) {
                double currentLiability = (double) clz.getMethod("getCurrentLiability", int.class).invoke(object_2, dates_2[i]);
                assertTrue(Math.abs(currentLiability - results_2[i]) <= 0.01, String.format("The currentLiability of %d should be %f", dates_2[i], results_2[i]));
            }

            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            int[] dates_3 = {202312, 202401, 202412, 202512, 202612, 202701, 202712};
            double[] results_3 = {0, 100000, 69571.64, 36249.59, 2794.01, 0, 0};
            for (int i = 0; i < dates_3.length; i++) {
                double currentLiability = (double) clz.getMethod("getCurrentLiability", int.class).invoke(object_3, dates_3[i]);
                assertTrue(Math.abs(currentLiability - results_3[i]) <= 0.01, String.format("The currentLiability of %d should be %f", dates_3[i], results_3[i]));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestEqualPrincipalRepayerGetCurrentLiability() {
        try {
            Class<?> clz = Class.forName("EqualPrincipalRepayer");
            Constructor<?> constructor = clz.getConstructor(String.class);

            String repayerInfo_1 = "188 F 3 20000 202311 12 0.005";
            Object object_1 = constructor.newInstance(repayerInfo_1);
            int[] dates_1 = {202310, 202311, 202402, 202406, 202410, 202411, 202501};
            double[] results_1 = {0, 20000, 15000, 8333.33, 1666.66, 0, 0};
            for (int i = 0; i < dates_1.length; i++) {
                double currentLiability = (double) clz.getMethod("getCurrentLiability", int.class).invoke(object_1, dates_1[i]);
                assertTrue(Math.abs(currentLiability - results_1[i]) <= 0.01, String.format("The currentLiability of %d should be %f", dates_1[i], results_1[i]));
            }

            String repayerInfo_2 = "12 M 2 50000 202301 24 0.003";
            Object object_2 = constructor.newInstance(repayerInfo_2);
            int[] dates_2 = {202212, 202301, 202309, 202404, 202412, 202501, 202512};
            double[] results_2 = {0, 50000, 33333.33, 18750, 2083.33, 0, 0};
            for (int i = 0; i < dates_2.length; i++) {
                double currentLiability = (double) clz.getMethod("getCurrentLiability", int.class).invoke(object_2, dates_2[i]);
                assertTrue(Math.abs(currentLiability - results_2[i]) <= 0.01, String.format("The currentLiability of %d should be %f", dates_2[i], results_2[i]));
            }

            String repayerInfo_3 = "3402 M 1 100000 202401 36 0.004";
            Object object_3 = constructor.newInstance(repayerInfo_3);
            int[] dates_3 = {202312, 202401, 202412, 202512, 202612, 202701, 202712};
            double[] results_3 = {0, 100000, 69444.44, 36111.11, 2777.78, 0, 0};
            for (int i = 0; i < dates_3.length; i++) {
                double currentLiability = (double) clz.getMethod("getCurrentLiability", int.class).invoke(object_3, dates_3[i]);
                assertTrue(Math.abs(currentLiability - results_3[i]) <= 0.01, String.format("The currentLiability of %d should be %f", dates_3[i], results_3[i]));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}

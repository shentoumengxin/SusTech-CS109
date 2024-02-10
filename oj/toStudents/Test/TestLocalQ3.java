import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class TestLocalQ3 {
    private static Field repayers;
    private static Field totalLoan;
    private static Field mortgageStartDate;
    private static Field mortgageTotalMonths;
    private static Field rate;

    @BeforeAll
    public static void initialTest() throws NoSuchFieldException {
        repayers = ConcreteCommercialLoan.class.getDeclaredField("repayers");
        totalLoan = Repayer.class.getDeclaredField("totalLoan");
        mortgageStartDate = Repayer.class.getDeclaredField("mortgageStartDate");
        mortgageTotalMonths = Repayer.class.getDeclaredField("mortgageTotalMonths");
        rate = Repayer.class.getDeclaredField("rate");
    }

    @Test
    public void testDefinedFieldAndMethod() throws NoSuchMethodException, IllegalAccessException {
        ParameterizedType repayersParameterizedType = (ParameterizedType) repayers.getGenericType();
        assertEquals(List.class, repayersParameterizedType.getRawType());
        assertEquals(Repayer.class, repayersParameterizedType.getActualTypeArguments()[0]);

        Constructor<ConcreteCommercialLoan> constructor = ConcreteCommercialLoan.class.getDeclaredConstructor();
        CommercialLoan commercialLoan = new ConcreteCommercialLoan();
        repayers.setAccessible(true);
        List<Repayer> repayersData = (List<Repayer>) repayers.get(commercialLoan);
        repayers.setAccessible(false);
        assertNotNull(repayersData);

        Method loan = CommercialLoan.class.getDeclaredMethod("loan", String.class);
        Method loanList = CommercialLoan.class.getDeclaredMethod("loan", List.class);
        Method getRepayerCountsByProfession = CommercialLoan.class.getDeclaredMethod("getRepayerCountsByProfession", Profession.class);
        Method getRepayerById = CommercialLoan.class.getDeclaredMethod("getRepayerById", int.class);
        Method getAllRepayersById = CommercialLoan.class.getDeclaredMethod("getAllRepayersById");
        Method getAllIncomeByDate = CommercialLoan.class.getDeclaredMethod("getAllIncomeByDate", int.class);
        Method getAllInterestByDate = CommercialLoan.class.getDeclaredMethod("getAllInterestByDate", int.class);
        Method getAllRepayersByCurrentLiabilityAndId = CommercialLoan.class.getDeclaredMethod("getAllRepayersByCurrentLiabilityAndId", int.class);

        assertEquals(void.class, loan.getReturnType());
        assertEquals(void.class, loanList.getReturnType());
        assertEquals(int.class, getRepayerCountsByProfession.getReturnType());
        assertEquals(Repayer.class, getRepayerById.getReturnType());
        ParameterizedType getAllRepayersByIdReturnType = (ParameterizedType) getAllRepayersById.getGenericReturnType();
        assertEquals(List.class, getAllRepayersByIdReturnType.getRawType());
        assertEquals(Repayer.class, getAllRepayersByIdReturnType.getActualTypeArguments()[0]);
        assertEquals(double.class, getAllIncomeByDate.getReturnType());
        assertEquals(double.class, getAllInterestByDate.getReturnType());
        ParameterizedType getAllRepayersByCurrentLiabilityAndIdReturnType = (ParameterizedType) getAllRepayersByCurrentLiabilityAndId.getGenericReturnType();
        assertEquals(List.class, getAllRepayersByCurrentLiabilityAndIdReturnType.getRawType());
        assertEquals(Repayer.class, getAllRepayersByCurrentLiabilityAndIdReturnType.getActualTypeArguments()[0]);
    }

    @Test
    public void testLoan() throws IllegalAccessException {
        CommercialLoan commercialLoan = new ConcreteCommercialLoan();
        commercialLoan.loan("1|101 F 2 3000000 202301 240 0.4");
        commercialLoan.loan("2|102 M 3 2500000 202210 180 0.45");
        commercialLoan.loan("1|103 F 2 800000 202101 60 0.57");
        commercialLoan.loan("2|98 F 1 1600066 202012 213 0.21");
        commercialLoan.loan("2|1 M 5 36000 201802 123 0.3");
        commercialLoan.loan("1|1006 M 4 123321 202207 16 0.77");
        List<String> repayerInfos = new ArrayList<>();
        repayerInfos.add("1|1008 M 5 654321 202206 120 0.36");
        repayerInfos.add("1|105 F 3 777000 201508 161 0.59");
        repayerInfos.add("2|104 F 4 302050 202302 81 0.17");
        commercialLoan.loan(repayerInfos);
        repayers.setAccessible(true);
        List<Repayer> repayersData = (List<Repayer>) repayers.get(commercialLoan);
        repayers.setAccessible(false);
        ArrayList<String> answerList = new ArrayList<>();
        answerList.add("EqualPaymentRepayer [101] F ENTERPRISE 3000000 202301 240 0.40");
        answerList.add("EqualPrincipalRepayer [102] M AGRICULTURE 2500000 202210 180 0.45");
        answerList.add("EqualPaymentRepayer [103] F ENTERPRISE 800000 202101 60 0.57");
        answerList.add("EqualPrincipalRepayer [98] F PUBLIC_INSTITUTIONS 1600066 202012 213 0.21");
        answerList.add("EqualPrincipalRepayer [1] M OTHER 36000 201802 123 0.30");
        answerList.add("EqualPaymentRepayer [1006] M MILITARY_POLICE 123321 202207 16 0.77");
        answerList.add("EqualPaymentRepayer [1008] M OTHER 654321 202206 120 0.36");
        answerList.add("EqualPaymentRepayer [105] F AGRICULTURE 777000 201508 161 0.59");
        answerList.add("EqualPrincipalRepayer [104] F MILITARY_POLICE 302050 202302 81 0.17");
        setRepayerFieldsAccessible(true);
        checkRepayerInfos(repayersData, answerList);
        setRepayerFieldsAccessible(false);
    }

    @Test
    public void testGetRepayerInfos0() throws IllegalAccessException {
        CommercialLoan commercialLoan = new ConcreteCommercialLoan();
        commercialLoan.loan("1|101 F 2 3000000 202301 240 0.4");
        commercialLoan.loan("2|102 M 3 2500000 202210 180 0.45");
        commercialLoan.loan("1|103 F 5 800000 202101 60 0.57");
        commercialLoan.loan("2|98 F 1 1600066 202012 213 0.21");
        commercialLoan.loan("2|1 M 5 36000 201802 123 0.3");
        commercialLoan.loan("1|1006 M 4 123321 202207 16 0.77");
        commercialLoan.loan("1|105 M 2 2600000 202110 20 0.2");
        commercialLoan.loan("2|104 M 3 300000 201601 165 0.61");
        commercialLoan.loan("1|111 F 2 3523657 201906 111 0.49");
        commercialLoan.loan("2|333 F 2 14573 202111 50 0.56");
        commercialLoan.loan("2|5 M 5 66761 201809 30 0.28");
        commercialLoan.loan("1|1001 F 2 324756 202005 168 0.37");
        assertEquals(1, commercialLoan.getRepayerCountsByProfession(Profession.PUBLIC_INSTITUTIONS));
        assertEquals(5, commercialLoan.getRepayerCountsByProfession(Profession.ENTERPRISE));
        assertEquals(2, commercialLoan.getRepayerCountsByProfession(Profession.AGRICULTURE));
        assertEquals(1, commercialLoan.getRepayerCountsByProfession(Profession.MILITARY_POLICE));
        assertEquals(3, commercialLoan.getRepayerCountsByProfession(Profession.OTHER));
        ArrayList<Repayer> repayerList = new ArrayList<>();
        repayerList.add(commercialLoan.getRepayerById(103));
        repayerList.add(commercialLoan.getRepayerById(98));
        repayerList.add(commercialLoan.getRepayerById(102));
        repayerList.add(commercialLoan.getRepayerById(1));
        repayerList.add(commercialLoan.getRepayerById(333));
        repayerList.add(commercialLoan.getRepayerById(1006));
        repayerList.add(commercialLoan.getRepayerById(101));
        repayerList.add(commercialLoan.getRepayerById(105));
        repayerList.add(commercialLoan.getRepayerById(1001));
        repayerList.add(commercialLoan.getRepayerById(104));
        repayerList.add(commercialLoan.getRepayerById(111));
        repayerList.add(commercialLoan.getRepayerById(5));
        List<Repayer> repayerListById = commercialLoan.getAllRepayersById();
        ArrayList<String> answerList = new ArrayList<>();
        answerList.add("EqualPaymentRepayer [103] F OTHER 800000 202101 60 0.57");
        answerList.add("EqualPrincipalRepayer [98] F PUBLIC_INSTITUTIONS 1600066 202012 213 0.21");
        answerList.add("EqualPrincipalRepayer [102] M AGRICULTURE 2500000 202210 180 0.45");
        answerList.add("EqualPrincipalRepayer [1] M OTHER 36000 201802 123 0.30");
        answerList.add("EqualPrincipalRepayer [333] F ENTERPRISE 14573 202111 50 0.56");
        answerList.add("EqualPaymentRepayer [1006] M MILITARY_POLICE 123321 202207 16 0.77");
        answerList.add("EqualPaymentRepayer [101] F ENTERPRISE 3000000 202301 240 0.40");
        answerList.add("EqualPaymentRepayer [105] M ENTERPRISE 2600000 202110 20 0.20");
        answerList.add("EqualPaymentRepayer [1001] F ENTERPRISE 324756 202005 168 0.37");
        answerList.add("EqualPrincipalRepayer [104] M AGRICULTURE 300000 201601 165 0.61");
        answerList.add("EqualPaymentRepayer [111] F ENTERPRISE 3523657 201906 111 0.49");
        answerList.add("EqualPrincipalRepayer [5] M OTHER 66761 201809 30 0.28");
        setRepayerFieldsAccessible(true);
        checkRepayerInfos(repayerList, answerList);
        answerList.sort(Comparator.comparingInt(s -> Integer.parseInt(s.substring(s.indexOf('[') + 1, s.indexOf(']')))));
        checkRepayerInfos(repayerListById, answerList);
        setRepayerFieldsAccessible(false);
    }

    @Test
    public void testGetRepayerInfos1() throws IllegalAccessException {
        CommercialLoan commercialLoan = new ConcreteCommercialLoan();
        commercialLoan.loan("2|10012 F 1 4325212 201205 76 0.39");
        commercialLoan.loan("2|10011 F 3 675643 201810 53 0.42");
        commercialLoan.loan("1|1836 M 5 3603242 200311 174 0.16");
        commercialLoan.loan("2|1029 M 5 15543 202104 287 0.68");
        commercialLoan.loan("2|872 F 5 972474 201503 27 0.12");
        commercialLoan.loan("1|563 M 5 123483 200612 211 0.83");
        commercialLoan.loan("2|201 M 5 86532 200107 199 0.32");
        commercialLoan.loan("2|109 F 3 13634 201604 6 0.17");
        commercialLoan.loan("1|103 F 1 645162 200701 164 0.37");
        commercialLoan.loan("2|92 M 5 85674 199605 244 0.24");
        commercialLoan.loan("1|13 M 5 13964 199711 124 0.59");
        commercialLoan.loan("2|7 M 2 392314 202304 107 0.61");
        assertEquals(2, commercialLoan.getRepayerCountsByProfession(Profession.PUBLIC_INSTITUTIONS));
        assertEquals(1, commercialLoan.getRepayerCountsByProfession(Profession.ENTERPRISE));
        assertEquals(2, commercialLoan.getRepayerCountsByProfession(Profession.AGRICULTURE));
        assertEquals(0, commercialLoan.getRepayerCountsByProfession(Profession.MILITARY_POLICE));
        assertEquals(7, commercialLoan.getRepayerCountsByProfession(Profession.OTHER));
        ArrayList<Repayer> repayerList = new ArrayList<>();
        repayerList.add(commercialLoan.getRepayerById(872));
        repayerList.add(commercialLoan.getRepayerById(872));
        repayerList.add(commercialLoan.getRepayerById(872));
        repayerList.add(commercialLoan.getRepayerById(10011));
        repayerList.add(commercialLoan.getRepayerById(13));
        repayerList.add(commercialLoan.getRepayerById(13));
        repayerList.add(commercialLoan.getRepayerById(10011));
        repayerList.add(commercialLoan.getRepayerById(7));
        repayerList.add(commercialLoan.getRepayerById(10011));
        repayerList.add(commercialLoan.getRepayerById(7));
        List<Repayer> repayerListById = commercialLoan.getAllRepayersById();
        ArrayList<String> answerList = new ArrayList<>();
        answerList.add("EqualPrincipalRepayer [872] F OTHER 972474 201503 27 0.12");
        answerList.add("EqualPrincipalRepayer [872] F OTHER 972474 201503 27 0.12");
        answerList.add("EqualPrincipalRepayer [872] F OTHER 972474 201503 27 0.12");
        answerList.add("EqualPrincipalRepayer [10011] F AGRICULTURE 675643 201810 53 0.42");
        answerList.add("EqualPaymentRepayer [13] M OTHER 13964 199711 124 0.59");
        answerList.add("EqualPaymentRepayer [13] M OTHER 13964 199711 124 0.59");
        answerList.add("EqualPrincipalRepayer [10011] F AGRICULTURE 675643 201810 53 0.42");
        answerList.add("EqualPrincipalRepayer [7] M ENTERPRISE 392314 202304 107 0.61");
        answerList.add("EqualPrincipalRepayer [10011] F AGRICULTURE 675643 201810 53 0.42");
        answerList.add("EqualPrincipalRepayer [7] M ENTERPRISE 392314 202304 107 0.61");
        ArrayList<String> answerListById = new ArrayList<>();
        answerListById.add("EqualPrincipalRepayer [7] M ENTERPRISE 392314 202304 107 0.61");
        answerListById.add("EqualPaymentRepayer [13] M OTHER 13964 199711 124 0.59");
        answerListById.add("EqualPrincipalRepayer [92] M OTHER 85674 199605 244 0.24");
        answerListById.add("EqualPaymentRepayer [103] F PUBLIC_INSTITUTIONS 645162 200701 164 0.37");
        answerListById.add("EqualPrincipalRepayer [109] F AGRICULTURE 13634 201604 6 0.17");
        answerListById.add("EqualPrincipalRepayer [201] M OTHER 86532 200107 199 0.32");
        answerListById.add("EqualPaymentRepayer [563] M OTHER 123483 200612 211 0.83");
        answerListById.add("EqualPrincipalRepayer [872] F OTHER 972474 201503 27 0.12");
        answerListById.add("EqualPrincipalRepayer [1029] M OTHER 15543 202104 287 0.68");
        answerListById.add("EqualPaymentRepayer [1836] M OTHER 3603242 200311 174 0.16");
        answerListById.add("EqualPrincipalRepayer [10011] F AGRICULTURE 675643 201810 53 0.42");
        answerListById.add("EqualPrincipalRepayer [10012] F PUBLIC_INSTITUTIONS 4325212 201205 76 0.39");
        setRepayerFieldsAccessible(true);
        checkRepayerInfos(repayerList, answerList);
        checkRepayerInfos(repayerListById, answerListById);
        setRepayerFieldsAccessible(false);
        List<String> repayerInfos = new ArrayList<>();
        repayerInfos.add("1|113 F 3 82146 201110 201 0.33");
        repayerInfos.add("2|105 F 4 764341 202306 175 0.27");
        repayerInfos.add("2|1034 M 5 126443 201703 93 0.15");
        repayerInfos.add("1|3 F 4 310481 201905 148 0.54");
        repayerInfos.add("2|59 M 3 623271 202112 29 0.27");
        repayerInfos.add("1|10206 M 2 91235 201308 139 0.34");
        commercialLoan.loan(repayerInfos);
        assertEquals(2, commercialLoan.getRepayerCountsByProfession(Profession.PUBLIC_INSTITUTIONS));
        assertEquals(2, commercialLoan.getRepayerCountsByProfession(Profession.ENTERPRISE));
        assertEquals(4, commercialLoan.getRepayerCountsByProfession(Profession.AGRICULTURE));
        assertEquals(2, commercialLoan.getRepayerCountsByProfession(Profession.MILITARY_POLICE));
        assertEquals(8, commercialLoan.getRepayerCountsByProfession(Profession.OTHER));
        repayerList.clear();
        repayerList.add(commercialLoan.getRepayerById(1034));
        repayerList.add(commercialLoan.getRepayerById(201));
        repayerList.add(commercialLoan.getRepayerById(3));
        repayerList.add(commercialLoan.getRepayerById(1034));
        repayerList.add(commercialLoan.getRepayerById(3));
        repayerList.add(commercialLoan.getRepayerById(10206));
        repayerList.add(commercialLoan.getRepayerById(563));
        repayerList.add(commercialLoan.getRepayerById(10206));
        repayerList.add(commercialLoan.getRepayerById(59));
        repayerList.add(commercialLoan.getRepayerById(59));
        repayerListById = commercialLoan.getAllRepayersById();
        answerList.clear();
        answerList.add("EqualPrincipalRepayer [1034] M OTHER 126443 201703 93 0.15");
        answerList.add("EqualPrincipalRepayer [201] M OTHER 86532 200107 199 0.32");
        answerList.add("EqualPaymentRepayer [3] F MILITARY_POLICE 310481 201905 148 0.54");
        answerList.add("EqualPrincipalRepayer [1034] M OTHER 126443 201703 93 0.15");
        answerList.add("EqualPaymentRepayer [3] F MILITARY_POLICE 310481 201905 148 0.54");
        answerList.add("EqualPaymentRepayer [10206] M ENTERPRISE 91235 201308 139 0.34");
        answerList.add("EqualPaymentRepayer [563] M OTHER 123483 200612 211 0.83");
        answerList.add("EqualPaymentRepayer [10206] M ENTERPRISE 91235 201308 139 0.34");
        answerList.add("EqualPrincipalRepayer [59] M AGRICULTURE 623271 202112 29 0.27");
        answerList.add("EqualPrincipalRepayer [59] M AGRICULTURE 623271 202112 29 0.27");
        answerListById.clear();
        answerListById.add("EqualPaymentRepayer [3] F MILITARY_POLICE 310481 201905 148 0.54");
        answerListById.add("EqualPrincipalRepayer [7] M ENTERPRISE 392314 202304 107 0.61");
        answerListById.add("EqualPaymentRepayer [13] M OTHER 13964 199711 124 0.59");
        answerListById.add("EqualPrincipalRepayer [59] M AGRICULTURE 623271 202112 29 0.27");
        answerListById.add("EqualPrincipalRepayer [92] M OTHER 85674 199605 244 0.24");
        answerListById.add("EqualPaymentRepayer [103] F PUBLIC_INSTITUTIONS 645162 200701 164 0.37");
        answerListById.add("EqualPrincipalRepayer [105] F MILITARY_POLICE 764341 202306 175 0.27");
        answerListById.add("EqualPrincipalRepayer [109] F AGRICULTURE 13634 201604 6 0.17");
        answerListById.add("EqualPaymentRepayer [113] F AGRICULTURE 82146 201110 201 0.33");
        answerListById.add("EqualPrincipalRepayer [201] M OTHER 86532 200107 199 0.32");
        answerListById.add("EqualPaymentRepayer [563] M OTHER 123483 200612 211 0.83");
        answerListById.add("EqualPrincipalRepayer [872] F OTHER 972474 201503 27 0.12");
        answerListById.add("EqualPrincipalRepayer [1029] M OTHER 15543 202104 287 0.68");
        answerListById.add("EqualPrincipalRepayer [1034] M OTHER 126443 201703 93 0.15");
        answerListById.add("EqualPaymentRepayer [1836] M OTHER 3603242 200311 174 0.16");
        answerListById.add("EqualPrincipalRepayer [10011] F AGRICULTURE 675643 201810 53 0.42");
        answerListById.add("EqualPrincipalRepayer [10012] F PUBLIC_INSTITUTIONS 4325212 201205 76 0.39");
        answerListById.add("EqualPaymentRepayer [10206] M ENTERPRISE 91235 201308 139 0.34");
        setRepayerFieldsAccessible(true);
        checkRepayerInfos(repayerList, answerList);
        checkRepayerInfos(repayerListById, answerListById);
        setRepayerFieldsAccessible(false);
    }

    @Test
    public void testGetRepayerLoans() {
        CommercialLoan commercialLoan = new ConcreteCommercialLoan();
        commercialLoan.loan("2|321 M 3 61263 202206 13 0.39");
        commercialLoan.loan("1|103 F 5 130242 202203 59 0.42");
        commercialLoan.loan("2|105 M 1 98645 202208 258 0.16");
        commercialLoan.loan("1|116 M 3 54351 202108 123 0.68");
        commercialLoan.loan("2|100 F 2 504327 202301 191 0.12");
        commercialLoan.loan("2|106 F 5 112415 202305 206 0.83");
        commercialLoan.loan("2|261 M 4 153221 202209 63 0.32");
        commercialLoan.loan("1|75 F 2 76438 202212 91 0.17");
        commercialLoan.loan("2|1 M 4 85432 202304 6 0.37");
        commercialLoan.loan("1|104 M 1 54351 202108 123 0.68");
        commercialLoan.loan("1|389 F 1 71432 202303 176 0.59");
        commercialLoan.loan("2|55 F 1 94621 202301 56 0.61");
        assertEquals("0.00", String.format("%.2f", commercialLoan.getAllIncomeByDate(202107)));
        assertEquals("6166.79", String.format("%.2f", commercialLoan.getAllIncomeByDate(202108)));
        assertEquals("6166.79", String.format("%.2f", commercialLoan.getAllIncomeByDate(202109)));
        assertEquals("60039.42", String.format("%.2f", commercialLoan.getAllIncomeByDate(202304)));
        assertEquals("67586.06", String.format("%.2f", commercialLoan.getAllIncomeByDate(202305)));
        assertEquals("66773.88", String.format("%.2f", commercialLoan.getAllIncomeByDate(202306)));
        assertEquals("61249.17", String.format("%.2f", commercialLoan.getAllIncomeByDate(202307)));
        assertEquals("392.54", String.format("%.2f", commercialLoan.getAllIncomeByDate(204312)));
        assertEquals("387.44", String.format("%.2f", commercialLoan.getAllIncomeByDate(204401)));
        assertEquals("0.00", String.format("%.2f", commercialLoan.getAllIncomeByDate(204402)));
        assertEquals("0.00", String.format("%.2f", commercialLoan.getAllInterestByDate(202107)));
        assertEquals("6159.78", String.format("%.2f", commercialLoan.getAllInterestByDate(202108)));
        assertEquals("6159.38", String.format("%.2f", commercialLoan.getAllInterestByDate(202109)));
        assertEquals("32402.40", String.format("%.2f", commercialLoan.getAllInterestByDate(202304)));
        assertEquals("39358.11", String.format("%.2f", commercialLoan.getAllInterestByDate(202305)));
        assertEquals("38499.24", String.format("%.2f", commercialLoan.getAllInterestByDate(202306)));
        assertEquals("37638.83", String.format("%.2f", commercialLoan.getAllInterestByDate(202307)));
        assertEquals("10.20", String.format("%.2f", commercialLoan.getAllInterestByDate(204312)));
        assertEquals("5.10", String.format("%.2f", commercialLoan.getAllInterestByDate(204401)));
        assertEquals("0.00", String.format("%.2f", commercialLoan.getAllInterestByDate(204402)));
        assertEquals("[[1] M MILITARY_POLICE, [55] F PUBLIC_INSTITUTIONS, " +
                "[75] F ENTERPRISE, [100] F ENTERPRISE, " +
                "[103] F OTHER, [104] M PUBLIC_INSTITUTIONS, " +
                "[105] M PUBLIC_INSTITUTIONS, [106] F OTHER, " +
                "[116] M AGRICULTURE, [261] M MILITARY_POLICE, " +
                "[321] M AGRICULTURE, [389] F PUBLIC_INSTITUTIONS]", commercialLoan.getAllRepayersByCurrentLiabilityAndId(202107).toString());
        assertEquals("[[1] M MILITARY_POLICE, [55] F PUBLIC_INSTITUTIONS, " +
                "[75] F ENTERPRISE, [100] F ENTERPRISE, " +
                "[103] F OTHER, [105] M PUBLIC_INSTITUTIONS, " +
                "[106] F OTHER, [261] M MILITARY_POLICE, " +
                "[321] M AGRICULTURE, [389] F PUBLIC_INSTITUTIONS, " +
                "[104] M PUBLIC_INSTITUTIONS, [116] M AGRICULTURE]", commercialLoan.getAllRepayersByCurrentLiabilityAndId(202108).toString());
        assertEquals("[[1] M MILITARY_POLICE, [55] F PUBLIC_INSTITUTIONS, " +
                "[75] F ENTERPRISE, [100] F ENTERPRISE, " +
                "[103] F OTHER, [105] M PUBLIC_INSTITUTIONS, " +
                "[106] F OTHER, [261] M MILITARY_POLICE, " +
                "[321] M AGRICULTURE, [389] F PUBLIC_INSTITUTIONS, " +
                "[104] M PUBLIC_INSTITUTIONS, [116] M AGRICULTURE]", commercialLoan.getAllRepayersByCurrentLiabilityAndId(202109).toString());
        assertEquals("[[106] F OTHER, [321] M AGRICULTURE, " +
                "[104] M PUBLIC_INSTITUTIONS, [116] M AGRICULTURE, " +
                "[389] F PUBLIC_INSTITUTIONS, [75] F ENTERPRISE, " +
                "[1] M MILITARY_POLICE, [55] F PUBLIC_INSTITUTIONS, " +
                "[105] M PUBLIC_INSTITUTIONS, [103] F OTHER, " +
                "[261] M MILITARY_POLICE, [100] F ENTERPRISE]", commercialLoan.getAllRepayersByCurrentLiabilityAndId(202304).toString());
        assertEquals("[[321] M AGRICULTURE, [104] M PUBLIC_INSTITUTIONS, " +
                "[116] M AGRICULTURE, [1] M MILITARY_POLICE, " +
                "[389] F PUBLIC_INSTITUTIONS, [75] F ENTERPRISE, " +
                "[55] F PUBLIC_INSTITUTIONS, [105] M PUBLIC_INSTITUTIONS, " +
                "[106] F OTHER, [103] F OTHER, [261] M MILITARY_POLICE, " +
                "[100] F ENTERPRISE]", commercialLoan.getAllRepayersByCurrentLiabilityAndId(202305).toString());
        assertEquals("[[321] M AGRICULTURE, [104] M PUBLIC_INSTITUTIONS, " +
                "[116] M AGRICULTURE, [1] M MILITARY_POLICE, " +
                "[389] F PUBLIC_INSTITUTIONS, [75] F ENTERPRISE, " +
                "[55] F PUBLIC_INSTITUTIONS, [105] M PUBLIC_INSTITUTIONS, " +
                "[106] F OTHER, [103] F OTHER, " +
                "[261] M MILITARY_POLICE, [100] F ENTERPRISE]", commercialLoan.getAllRepayersByCurrentLiabilityAndId(202306).toString());
        assertEquals("[[321] M AGRICULTURE, [1] M MILITARY_POLICE, " +
                "[104] M PUBLIC_INSTITUTIONS, [116] M AGRICULTURE, " +
                "[389] F PUBLIC_INSTITUTIONS, [75] F ENTERPRISE, " +
                "[55] F PUBLIC_INSTITUTIONS, [105] M PUBLIC_INSTITUTIONS, " +
                "[106] F OTHER, [103] F OTHER, " +
                "[261] M MILITARY_POLICE, [100] F ENTERPRISE]", commercialLoan.getAllRepayersByCurrentLiabilityAndId(202307).toString());
        assertEquals("[[1] M MILITARY_POLICE, [55] F PUBLIC_INSTITUTIONS, " +
                "[75] F ENTERPRISE, [100] F ENTERPRISE, " +
                "[103] F OTHER, [104] M PUBLIC_INSTITUTIONS, " +
                "[106] F OTHER, [116] M AGRICULTURE, " +
                "[261] M MILITARY_POLICE, [321] M AGRICULTURE, " +
                "[389] F PUBLIC_INSTITUTIONS, [105] M PUBLIC_INSTITUTIONS]", commercialLoan.getAllRepayersByCurrentLiabilityAndId(204312).toString());
        assertEquals("[[1] M MILITARY_POLICE, [55] F PUBLIC_INSTITUTIONS, " +
                "[75] F ENTERPRISE, [100] F ENTERPRISE, " +
                "[103] F OTHER, [104] M PUBLIC_INSTITUTIONS, " +
                "[106] F OTHER, [116] M AGRICULTURE, " +
                "[261] M MILITARY_POLICE, [321] M AGRICULTURE, " +
                "[389] F PUBLIC_INSTITUTIONS, [105] M PUBLIC_INSTITUTIONS]", commercialLoan.getAllRepayersByCurrentLiabilityAndId(204401).toString());
        assertEquals("[[1] M MILITARY_POLICE, [55] F PUBLIC_INSTITUTIONS, " +
                "[75] F ENTERPRISE, [100] F ENTERPRISE, " +
                "[103] F OTHER, [104] M PUBLIC_INSTITUTIONS, " +
                "[105] M PUBLIC_INSTITUTIONS, [106] F OTHER, " +
                "[116] M AGRICULTURE, [261] M MILITARY_POLICE, " +
                "[321] M AGRICULTURE, [389] F PUBLIC_INSTITUTIONS]", commercialLoan.getAllRepayersByCurrentLiabilityAndId(204402).toString());
    }

    @Test
    public void testPolymorphism() throws IllegalAccessException {
        CommercialLoan commercialLoan = new ConcreteCommercialLoan();
        repayers.setAccessible(true);
        List<Repayer> repayerList = (List<Repayer>) repayers.get(commercialLoan);
        repayers.setAccessible(false);
        repayerList.add(new testRepayer("102 M 3 6346255 202301 125 0.42"));
        repayerList.add(new testRepayer("101 F 2 324168 202212 263 0.81"));
        assertEquals(0, commercialLoan.getRepayerCountsByProfession(Profession.PUBLIC_INSTITUTIONS));
        assertEquals(1, commercialLoan.getRepayerCountsByProfession(Profession.ENTERPRISE));
        assertEquals(1, commercialLoan.getRepayerCountsByProfession(Profession.AGRICULTURE));
        assertEquals(0, commercialLoan.getRepayerCountsByProfession(Profession.MILITARY_POLICE));
        assertEquals(0, commercialLoan.getRepayerCountsByProfession(Profession.OTHER));
        List<Repayer> gotRepayerList = new ArrayList<>();
        gotRepayerList.add(commercialLoan.getRepayerById(102));
        gotRepayerList.addAll(commercialLoan.getAllRepayersById());
        List<String> answerList = new ArrayList<>();
        answerList.add(String.format("%s$testRepayer [102] M AGRICULTURE 6346255 202301 125 0.42", this.getClass().getName()));
        answerList.add(String.format("%s$testRepayer [101] F ENTERPRISE 324168 202212 263 0.81", this.getClass().getName()));
        answerList.add(String.format("%s$testRepayer [102] M AGRICULTURE 6346255 202301 125 0.42", this.getClass().getName()));
        checkRepayerInfos(gotRepayerList, answerList);
        assertEquals("1152403.88", String.format("%.2f", commercialLoan.getAllIncomeByDate(202306)));
        assertEquals("40666.71", String.format("%.2f", commercialLoan.getAllInterestByDate(202306)));
        assertEquals("[[101] F ENTERPRISE, [102] M AGRICULTURE]", commercialLoan.getAllRepayersByCurrentLiabilityAndId(202306).toString());
    }

    static class testRepayer extends Repayer {

        public testRepayer(String infos) {
            super(infos);
        }

        @Override
        public double getPayment(int date) {
            return ((double) super.totalLoan) / (date % 100) * (1 + super.rate / 12);
        }

        @Override
        public double getInterest(int date) {
            return ((double) super.totalLoan) / (date % 100) * (super.rate / 12);
        }

        @Override
        public double getPrincipal(int date) {
            return ((double) super.totalLoan) / (date % 100);
        }

        @Override
        public double getCurrentLiability(int date) {
            return super.totalLoan - ((double) super.totalLoan) / (date % 100);
        }
    }

    public void setRepayerFieldsAccessible(boolean accessible) {
        totalLoan.setAccessible(accessible);
        mortgageStartDate.setAccessible(accessible);
        mortgageTotalMonths.setAccessible(accessible);
        rate.setAccessible(accessible);
    }

    public void checkRepayerInfos(List<Repayer> repayerList, List<String> answerList) throws IllegalAccessException {
        for (int i = 0; i < repayerList.size(); i++) {
            assertEquals(answerList.get(i), String.format("%s %s %s %s %s %.2f", repayerList.get(i).getClass().getName(), repayerList.get(i), totalLoan.get(repayerList.get(i)).toString(),
                    mortgageStartDate.get(repayerList.get(i)).toString(), mortgageTotalMonths.get(repayerList.get(i)).toString(), (double) rate.get(repayerList.get(i))));
        }
    }
}

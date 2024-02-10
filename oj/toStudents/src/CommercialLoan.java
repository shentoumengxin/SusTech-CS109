import java.util.List;

public interface CommercialLoan {

    public static void main(String[] args) {

    }
    public void loan(String loanInfo);

    public void loan(List<String> loanInfos);

    public int getRepayerCountsByProfession(Profession profession);

    public Repayer getRepayerById(int id);


    public List<Repayer> getAllRepayersById();

    public double getAllIncomeByDate(int date);

    public double getAllInterestByDate(int date);

    public List<Repayer> getAllRepayersByCurrentLiabilityAndId(int date);

}

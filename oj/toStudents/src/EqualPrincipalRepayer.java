public class EqualPrincipalRepayer extends Repayer {
    private final double principal= (double) totalLoan /mortgageTotalMonths;;

    public EqualPrincipalRepayer(String infos) {
        super(infos);
    }

    @Override
    public double getPayment(int date) {
        if(getMonthNumber(date)==0){
            return 0;
        }else{
            return getInterest(date)+getPrincipal(date);
        }

    }

    @Override
    public double getPrincipal(int date) {
        if(getMonthNumber(date)==0){
            return 0;
        }else{
            return principal;
        }
    }

    @Override
    public double getInterest(int date) {
        if(getMonthNumber(date)==0){
            return 0;
        }else{
            return ((totalLoan-((getMonthNumber(date)-1)*principal))*rate/12);
        }
    }

    @Override
    public double getCurrentLiability(int date) {
        if(getMonthNumber(date)==0){
            return 0;
        }else{
            return totalLoan-principal*(getMonthNumber(date)-1);
        }

    }
}

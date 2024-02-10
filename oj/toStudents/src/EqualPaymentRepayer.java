

public class EqualPaymentRepayer extends Repayer{

    private final double[] interests=new double[mortgageTotalMonths+1] ;
    private final double[] principle=new double[mortgageTotalMonths+1] ;
    public EqualPaymentRepayer(String infos){
        super(infos);
        for (int i = 1; i <=mortgageTotalMonths ; i++) {
            //interests[i]=(double) (totalLoan/i-payment)*(double)(12/rate/i-1);
            double sum_principle = 0;
            for(int j=1;j<=i;j++){
                sum_principle+=principle[j];
            }
            //principle[i]=Double.parseDouble(df.format(payment-((totalLoan-sum_principle)*rate/12)));
            principle[i]=payment-((totalLoan-sum_principle)*rate/12);
            interests[i]=payment-principle[i];
        }
    }

    private final double payment=(totalLoan*rate/12*Math.pow(1+rate/12,mortgageTotalMonths))/(Math.pow(1+rate/12,mortgageTotalMonths)-1);
    @Override
    public double getPayment(int date) {
        if(getMonthNumber(date)==0){
            return 0;
        }else{
            return payment;
        }

    }

    @Override
    public double getInterest(int date) {
        if(getMonthNumber(date)==0){
            return 0;
        }else{
            if(getMonthNumber(date)==1){
                return (totalLoan)*rate/12;
            }
            return interests[getMonthNumber(date)];
        }

    }

    @Override
    public double getPrincipal(int date) {
        if(getMonthNumber(date)==0){
            return 0;
        }else{
            return payment-getInterest(date);
        }

    }

    @Override
    public double getCurrentLiability(int date) {
        if(getMonthNumber(date)==0){
            return 0;
        }else{
            return (double) getInterest(date)/rate*12;
        }

    }
}

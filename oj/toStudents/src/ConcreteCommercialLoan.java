import java.util.ArrayList;
import java.util.List;

public class ConcreteCommercialLoan implements CommercialLoan {
    int equal;
    private List<Repayer>repayers;
    public ConcreteCommercialLoan(){
        this.repayers=new ArrayList<>();
    }
    @Override
    public void loan(String loanInfo) {
        String []spil=new String[2];
        spil= loanInfo.split("\\|");
        if(Integer.parseInt(spil[0])==1){
            EqualPaymentRepayer repayer=new EqualPaymentRepayer(spil[1]) ;
            repayers.add(repayer);
        }else{
            EqualPrincipalRepayer repayer=new EqualPrincipalRepayer(spil[1]);
            repayers.add(repayer);
        }
    }
    public void loan(List<String>loanInfos){
        /*while(!loanInfos.isEmpty()){
            String []spil=new String[2];
            spil= loanInfos.get(loanInfos.size()-1).split("\\|");
            loanInfos.remove(loanInfos.size()-1);
            if(Integer.parseInt(spil[0])==1){
                EqualPaymentRepayer repayer=new EqualPaymentRepayer(spil[1]) ;
                repayers.add(repayer);
            }else if(Integer.parseInt(spil[0])==2){
                EqualPrincipalRepayer repayer=new EqualPrincipalRepayer(spil[1]);
                repayers.add(repayer);
            }
        }*/
        for (String loanInfo:loanInfos) {
            String []spil=new String[2];
            spil= loanInfo.split("\\|");
            if(Integer.parseInt(spil[0])==1){
                EqualPaymentRepayer repayer=new EqualPaymentRepayer(spil[1]) ;
                repayers.add(repayer);
            }else{
                EqualPrincipalRepayer repayer=new EqualPrincipalRepayer(spil[1]);
                repayers.add(repayer);
            }
        }
    }


    @Override
    public int getRepayerCountsByProfession(Profession profession) {
        int count=0;
        for (Repayer person:repayers) {
            if(person.getProfession()==profession)count++;
        }
        return count;
    }

    @Override
    public Repayer getRepayerById(int id) {
        for(Repayer person:repayers){
            if(person.getId()==id)return person;
        }
        return null;
    }


    @Override
    public List<Repayer> getAllRepayersById() {
        //return a list of repayers,sorted by id
        for(int i=0;i<repayers.size()-1;i++){
            for (int j = 0; j <repayers.size()-i-1 ; j++) {
                if(repayers.get(j).getId()>repayers.get(j+1).getId()){
                    Repayer temp=repayers.get(j);
                    repayers.set(j,repayers.get(j+1));
                    repayers.set(j+1,temp);
                }
            }
        }
        return repayers;
    }


    @Override
    public double getAllIncomeByDate(int date) {
        double sum=0;
        for (Repayer person:repayers) {
            sum+=person.getPayment(date);
        }
        return sum;
    }

    @Override
    public double getAllInterestByDate(int date) {
        double sum=0;
        for (Repayer person:repayers) {
            sum+=person.getInterest(date);
        }
        return sum;
    }

    @Override
    public List<Repayer> getAllRepayersByCurrentLiabilityAndId(int date) {
        //return a list of repayers,sorted by current liability and id
        for(int i=0;i<=repayers.size()-1;i++){
            for (int j = 0; j <repayers.size()-i-1 ; j++) {
                if(repayers.get(j).getCurrentLiability(date)>repayers.get(j+1).getCurrentLiability(date)){
                    Repayer temp=repayers.get(j);
                    repayers.set(j,repayers.get(j+1));
                    repayers.set(j+1,temp);
                }else if (repayers.get(j).getCurrentLiability(date)==repayers.get(j+1).getCurrentLiability(date)){
                    if(repayers.get(j).getId()>repayers.get(j+1).getId()){
                        Repayer temp=repayers.get(j);
                        repayers.set(j,repayers.get(j+1));
                        repayers.set(j+1,temp);
                    }
            }
        }
        }
        return repayers;
    }
}


public abstract class Repayer {
    private int id;
    private char gender;//M or F
    private Profession profession;

    protected int totalLoan;
    protected int mortgageStartDate;
    protected int mortgageTotalMonths;

    protected double rate;//year rate
    protected int way;

    public Repayer(String infos) {
        String[] info = infos.split(" ");  //information of repayer
        this.id=Integer.parseInt(info[0]);
        this.gender=info[1].charAt(0);
        if(info[2].equals("1")){              //profession
            this.profession=Profession.PUBLIC_INSTITUTIONS;
        }
        if(info[2].equals("2")){
            this.profession=Profession.ENTERPRISE;
        }
        if(info[2].equals("3")){
            this.profession=Profession.AGRICULTURE;
        }
        if(info[2].equals("4")){
            this.profession=Profession.MILITARY_POLICE;
        }
        if(info[2].equals("5")){
            this.profession=Profession.OTHER;
        }
        this.totalLoan=Integer.parseInt(info[3]);
        this.mortgageStartDate=Integer.parseInt(info[4]);
        this.mortgageTotalMonths=Integer.parseInt(info[5]);
        this.rate=Double.parseDouble(info[6]);
    }


    public int getMonthNumber(int date) {
        if (mortgageStartDate > date) {
            return 0;
        }
        int endDate =MortgageDate.getMonthsPassedDate(mortgageStartDate,mortgageTotalMonths);
        if(endDate<=date){
            return 0;
        }
        return MortgageDate.getDifference(mortgageStartDate,date)+1;
    }
    /**
     * payment = interest + principal
     *
     * @param date
     * @return The total payment in current month in date
     */
    public abstract double getPayment(int date);

    /**
     * @param date
     * @return The interest in current month in date
     */
    public abstract double getInterest(int date);

    /**
     * @param date
     * @return The principal in current month in date
     */
    public abstract double getPrincipal(int date);

    /**
     * @param date
     * @return Before the payment in current month of date, the total liability
     */
    public abstract double getCurrentLiability(int date);

    public int getId() {
        return id;
    }

    public char getGender() {
        return gender;
    }

    public Profession getProfession() {
        return profession;
    }

    public int getTotalLoan() {
        return totalLoan;
    }

    public int getMortgageStartDate() {
        return mortgageStartDate;
    }

    public int getMortgageTotalMonths() {
        return mortgageTotalMonths;
    }

    public double getRate() {
        return rate;
    }

    public String toString() {
        return String.format("[%d] %c %s",this.id,this.gender,this.profession);
    }

}

public class MainForEmployee 
{
    public static void main(String[] args)
    {
        Payroll p1 = new Payroll();
        p1.baseSalary = 50000;
        p1.perfBonus = 10000;
        p1.insurance = 5000;
        p1.healthInsurance = 3000;
        p1.pf = 6000;

        FullTimeEmployee e = new FullTimeEmployee();
        System.out.println("Full Time Employee CTC:" + e.calcCTC(p1));

        Payroll p2 = new Payroll();
        p2.baseSalary = 80000;
        p2.perfBonus = 20000;
        p2.insurance = 7000;
        p2.healthInsurance = 4000;
        p2.pf = 8000;

        Manager m = new Manager();
        System.out.println("Manager CTC:" + m.calcCTC(p2));
        
        Payroll p3 = new Payroll();
        p3.noOfHours = 160;
        p3.hourlyRates = 500;

        ContractEmployee ce = new ContractEmployee();
        System.out.println("Contract Employee CTC:" + ce.calcCTC(p3));
    }
}

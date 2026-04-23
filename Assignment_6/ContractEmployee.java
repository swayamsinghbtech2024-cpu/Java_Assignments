public class ContractEmployee extends Employee
{
    public float calcBenefits(Payroll p)
    {
        // minimal benefits
        return 2000;
    }
    
    public float calcCTC(Payroll p)
    {
        return (p.noOfHours * p.hourlyRates) + calcBenefits(p);
    }
}

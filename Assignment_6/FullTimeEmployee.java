public class FullTimeEmployee extends Employee
{
    public float calcBenefits(Payroll p)
    {
        // full-time benefits
        float benefits = p.insurance + p.healthInsurance + p.pf;
        return benefits;
    }

    public float calcCTC(Payroll p)
    {
        return p.baseSalary + p.perfBonus + calcBenefits(p);
    }
}

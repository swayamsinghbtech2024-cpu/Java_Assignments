public class Manager extends FullTimeEmployee
{
    public float travelAllowance = 10000;
    public float educationAllowance = 5000;

    public float calcBenefits(Payroll p)
    {
        float extra = 10000; // manager-specific benefit
        return super.calcBenefits(p) + extra;
    }

    public float calcCTC(Payroll p)
    {
        return p.baseSalary + p.perfBonus + travelAllowance + educationAllowance + calcBenefits(p);
    }
}

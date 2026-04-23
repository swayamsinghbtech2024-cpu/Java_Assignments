public abstract class Employee 
{
    private String empID;
    public String name;
    private String pan;
    public String joiningDate;
    public String designation;

    public String getEmpID()
    {
        return empID;
    }
    public void setEmpID(String empID)
    {
        this.empID = empID;
    }

    public String getPAN()
    {
        return pan;
    }
    public void setPAN(String pan)
    {
        this.pan = pan;
    }

    public abstract float calcBenefits(Payroll p);
    public abstract float calcCTC(Payroll p);
}


public class Utilitybill implements Billable{

    private String customername;
    private String billtype;
    private int prev_reading;
    private int curr_reading;
    private int units;
    private double tax;
    private double total;

    public Utilitybill(String name,String type, int prev, int curr)
    {
        this.customername=name;
        this.billtype=type;
        this.prev_reading=prev;
        this.curr_reading=curr;
    }

    public boolean validateip()
    {
        if(curr_reading<prev_reading)
        {
            System.out.println("Invalid meter readingd");
            return false;
        }
        return true;
    }
    public void cal_units()
    {
        units=curr_reading-prev_reading;
    }
    @Override
    public double cal_total(int units)
    {
        double amount=0;
        if(billtype.equalsIgnoreCase("electricity"))
        {
            if(units<=100)
            {
                amount=units*1;
            }
            else if(units<=300)
            {
                amount=(100*1)+(units-100)*2;
            }
            else
            {
                amount=(100*1)+(200*2)+(units-300)*5;
            }
        }
        else if(billtype.equalsIgnoreCase("water"))
        {
            if(units<=100)
            {
                amount=units*0.5;
            }
            else if(units<=300)
            {
                amount=(100*0.5)+(units-100)*1.5;
            }
            else{
                amount=(100*0.5)+(200*1.5)+(units-300)*3;
            }
        }
        tax=amount*0.10;
        total=amount+tax;
        return total;
        
    }
    public void display()
    {
        System.out.println("Smart receipt");
        System.out.println("Customer name: "+customername);
        System.out.println("Bill type: "+billtype);
        System.out.println("Units consumed: "+units);
        System.out.printf("Tax amount: %.2f\n",tax);
        System.out.printf("Total bill: %.2f\n",total);
        System.out.println("==============");


    }


}
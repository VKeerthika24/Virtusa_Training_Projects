import java.util.*;

public class Smartpay{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ArrayList<Utilitybill> bills=new ArrayList<>();
        while(true)
        {
            System.out.println("Smart menu");
            System.out.println("1.Generate new bill");
            System.out.println("2. View all bills");
            System.out.println("3. Exit");

            System.out.println("Enter Choice: ");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                System.out.println("Enter customer name ");
                String name=sc.nextLine();
                System.out.println("Enter bill type(electricity/water): ");
                String type=sc.nextLine();
                if(!type.equalsIgnoreCase("electricity")&&!type.equalsIgnoreCase("water"))
                {
                    System.out.println("invalid bill type");
                    break;
                }
                System.out.println("enter previous reading: ");
                int prev=sc.nextInt();
                System.out.println("enter current reading: ");
                int curr=sc.nextInt();
                sc.nextLine();

                Utilitybill bill=new Utilitybill(name, type, prev, curr);
                if(!bill.validateip())
                {
                    break;
                }
                bill.cal_units();
                bill.cal_total(curr-prev);
                bills.add(bill);
                System.out.println("bill generated successfully");
                bill.display();
                break;

                case 2:
                if(bills.isEmpty())
                {
                    System.out.println("no bills available");
                }
                else{
                    for(Utilitybill b: bills)
                    {
                        b.display();
                    }
                }
                break;

                case 3:
                System.out.println("exciting smartpay");
                sc.close();
                return;

                default:
                System.out.println("invalid choice");
            }
        }
    }
}
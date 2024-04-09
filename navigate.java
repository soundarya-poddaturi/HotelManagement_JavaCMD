import java.util.*;
public class navigate {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean t=true;
        while(t)
        {
            System.out.println("\n1.Management side\n2.User side");
            System.out.print("enter your choice : ");
            int ch=sc.nextInt();
            switch(ch)
            {
                case 1:
                    System.out.println("\n1.Add rooms\n2.Display rooms\n");
                    System.out.print("enter your choice : ");
                    int y=sc.nextInt();
                    switch(y)
                    {
                        case 1:
                            String a[]={"helo","gsdgc"};
                            management.main(a);
                            break;
                        case 2:
                            management.display(management.get_room_list());
                            break;
                        
                        
                        default:
                        System.out.println("Enter correct option");
                        break;
                    }
                    break;
                case 2:
                    System.out.println("\n1.Book a room\n2.Check passcode\n3.Verify passcode to get room no\n");
                    System.out.print("enter your choice : ");
                    int x=sc.nextInt();
                    switch(x)
                    {
                        case 1:
                        try {
                            ArrayList<Room> rlist=management.get_room_list();
                            hotel.main(rlist);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;

                        case 3:
                        management.issue_room();
                        break;
                        case 2:
                            hotel.display_customer_details();
                            break;

                        default:
                        System.out.println("Enter correct option");
                        break;

                    }
                    break;
                default:
                System.out.println("Enter correct option");
                break;
            }
            System.out.println("want to continue ??(true/false) ");
            boolean tn=sc.nextBoolean();
            t=tn;
        }
    }
    
}

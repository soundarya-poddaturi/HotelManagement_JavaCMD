// import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class customer 
{
    String name;
    int total;
    long mobileno;
    ArrayList<Integer> pass=new ArrayList<>();
    double totalAmount;
    customer (String n,int t,long m){
        name =n;
        total=t;
        mobileno=m;
    }
    public void setPrice(double p)
    {
        totalAmount=p;
    }
    public void setPasscode(int p)
    {
        pass.add(p);
        System.out.println(pass);

    }
    public String toString()
    {
        return "Name : "+name+"\nTotal_people "+total+"\nTotal amt  "+totalAmount+"\npasscodes :"+pass;
    }

    ArrayList<Room> search(ArrayList<Room> r,boolean a,LocalDate ind,LocalTime inti,LocalDate out,LocalTime outti)
    {   
        ArrayList<Room> z=new ArrayList<Room>();
        for(Room x:r)
        {
            int flag=0;
            if(x.arr.isEmpty())
            {
                if(x.ac==a)
                {
                    z.add(x);
                    x.status=false;
                }
            }
            else
            {
                for(int i=0;i<x.arr.size();i++)
                {
                    inout y=x.arr.get(i);  
                    if(out.isAfter(y.getcid())&&out.isBefore(y.getcod())||
                    ind.isEqual(y.getcid())&& (inti.isAfter(y.getcit()))||
                    ind.isEqual(y.getcid())&& (inti.compareTo(y.getcit())==0)||
                    ind.isEqual(y.getcid())||
                    ind.isAfter(y.getcid())&&ind.isBefore(y.getcod())||
                    ind.isBefore(y.getcid())&&out.isAfter(y.getcod())||
                    ind.isEqual(y.getcod())&&inti.isBefore(y.getcot()))
                    {
                        flag=0;
                        break;
                    }
                    else
                    {
                        flag=1;
                    }
                }
                if(flag==1)
                {
                    if(x.ac==a)
                    {
                        z.add(x);
                        x.status=false;
                    }
                }  
            }
        }
            return z;
        }
        
        ArrayList<Room>[] book(ArrayList<Room> r,int ch,LocalDate ind,LocalTime inti,LocalDate out,LocalTime outti,String cname)
        {
            ArrayList<Room> price=new  ArrayList<>();
            ArrayList<Room> z[]=new  ArrayList[2];
            for(Room x:r)
            {
                if(x.capacity==ch)
                {
                    
                    System.out.println("Successfully booked a room of capacity "+x.get_capacity());
                    x.status=false;
                    int pass = ThreadLocalRandom.current().nextInt(1000);
                    System.out.println("Passcode  : " + pass+"\n");
                    this.setPasscode(pass);
                    x.arr.add(new inout(ind,out,inti,outti,pass,cname));
                    r.remove(x);
                    price.add(x);
                    z[0]=r;z[1]=price;
                    break;
            }
        }
        return z;
    }
}

public class hotel
{
    public static ArrayList<customer> cust=new ArrayList<>();
     static void display_customer_details()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter customer mobile no : ");
        long m=sc.nextLong();
        int flag=0;
        {
            for(int i=0;i<cust.size();i++)
            {
                customer cc=cust.get(i);
                if(cc.mobileno==m)
                {
                    System.out.println("ur passcodes are : "+cc.pass);
                    flag=1;
                } 
            }
            if(flag==0)
             System.out.println("No rooms were reserved");
        }
    }
    public static void main(ArrayList<Room>rlist)throws Exception 
    {
        Scanner sc=new Scanner(System.in);
        String custname;
        int totpeople;long mobile;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tformatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String date1;String date2;String ac;
        LocalDate in = null;LocalDate out=null;
        String time1;String time2;
        LocalTime in_time=null;LocalTime out_time=null;
        boolean ac_1=true;boolean flag=true;boolean sure=false;
        int si=0;int p=0;
        

            System.out.println("Enter customer name :");
            while(true){ 
                try{
                    custname=sc.next();
                    if(!(custname.matches("[a-zA-Z]+"))){
                        throw new InputMismatchException() ;
                    }
                    break;
                }
                catch(InputMismatchException e){
                    System.out.println("\n Wrong Format !!!! \nRe-enter ur name : ");
                }
            }
            System.out.println("Enter total no of people:");
            while(true){
                try {
                        totpeople = sc.nextInt();
                        break;
                    } 
                    catch (Exception e) {
                        System.out.println("\n Wrong Format !!!\nRe-enter the total number of people: ");
                        sc.next();
                    }
                }
                System.out.println("Enter mobile no :");
                while (true) {
                    try {
                        System.out.print("Enter a 10-digit mobile number: ");
                        String mobiles = sc.next();
                        System.out.println(mobiles.matches("^[0-9]{10}$"));
                        if (!mobiles.matches("^[0-9]{10}$")) {
                            throw new InputMismatchException("Wrong Format !!!");
                        } else {
                            mobile = Long.parseLong(mobiles);
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage() + "\nRe-enter the mobile number : ");
                        sc.next(); // Consume the invalid input before asking again
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid 10-digit number.");
                        sc.next(); // Consume the invalid input before asking again
                    } catch (Exception e) {
                        System.out.println("Error occurred: " + e.getMessage());
                        sc.next(); // Consume the invalid input before asking again
                    }
                }
                System.out.println("Room size\tPrice\tac");
                HashMap<HashMap<Integer, Float>, Boolean> l = new HashMap<>();
                for (int i = 0; i < rlist.size(); i++) {
                    HashMap<Integer, Float> j = new HashMap<>();
                    j.put(rlist.get(i).get_capacity(), rlist.get(i).get_price());
                    l.put(new HashMap<>(j), rlist.get(i).ac);
                }
                System.out.println(l);
                System.out.println("Enter check_in date(yyyy-MM-dd) : ");
                while(true){
                    try{
                        date1=sc.next();
                        in = LocalDate.parse(date1, formatter);
                        break;
                    }
                    catch(Exception e){
                        System.out.println("\n Wrong Format !!!\nRe-Enter check_in date(yyyy-MM-dd) : ");
                    }
                }

                System.out.println("Enter check_in time (HH:mm:ss): ");
                while(true){
                    try{
                        time1=sc.next();
                        in_time = LocalTime.parse(time1, tformatter);
                        break;
                    }
                    catch(Exception e){
                        System.out.println("\nWrong Format !!!\nre-Enter check_in time (HH:mm:ss): ");
                        
                    }
                }        

                System.out.println("Enter check_out date(yyyy-MM-dd) : ");
                while(true){
                    try{
                        date2=sc.next();
                        out = LocalDate.parse(date2, formatter);
                        if(!out.isAfter(in)&&!out.isEqual(in))
                        throw new InputMismatchException();
                        break;
                    }
                    catch(InputMismatchException e1){
                        System.out.println("\nAlert!!!\ncheckout date should be after check-in date !!");
                        System.out.println("re-Enter check_out date(yyyy-MM-dd) : ");
                    }
                    catch(Exception e){
                        System.out.println("\nWrong Format !!!\nre-Enter check_out date(yyyy-MM-dd) : ");
                    }
                }

                System.out.println("Enter checkout time (HH:mm:ss) : ");
                while(true){
                try{
                    time2=sc.next();
                    out_time = LocalTime.parse(time2, tformatter);
                    break;
                }
                catch(Exception e){
                    System.out.println("\nWrong Format !!!\nre-Enter checkout time (HH:mm:ss) : ");
                }
            }

            System.out.println("Check-in : "+in+" "+in_time+"\n"+"Check-out : "+out+" "+out_time);
            System.out.println("Enter if ac required (yes/no): ");
            while(true){
                try{
                    ac=sc.next();
                    if (!(ac.equalsIgnoreCase("yes") || ac.equalsIgnoreCase("no"))){
                        throw new InputMismatchException() ;
                    }
                    break;
                }
                catch(InputMismatchException e){
                    System.out.println("\nWrong Format !!!\nre-Enter if ac required (yes/no): ");
                }
            }
            if(ac.equals("yes"))
            {
                ac_1=true;
            }
            else if(ac.equals("no"))
            {
                ac_1=false;
            }
        
            customer c=new customer(custname,totpeople,mobile); 
            ArrayList<Room> w=c.search(rlist,ac_1,in,in_time,out,out_time);
            //w-->list of rooms with given conditions
            HashMap<Integer,Integer> av=new HashMap<>();
            //av is used to display room size and count of it which are available

            
            for(int i=0;i<w.size();i++)
            {
                Room x=w.get(i);
                int k=x.get_capacity();
                if(av.containsKey(k))
                {
                    int count = av.get(k);
                    av.put(k, count + 1);
                }
                else
                    av.put(k, 1);
            }
            System.out.print("room size - available : ");
            System.out.println(av);
            System.out.println("Are you sure u want to book ???(true/false)");
            while(true){
                try{
                    sure=sc.nextBoolean();
                    break;
                }
                catch( InputMismatchException e){
                    System.out.println("\nWrong Format !!!\nre-Enter  confirmation (true/false): ");
                    sc.nextLine();
                }
            }
            if(sure)
            {
                while(true)
                {
                    try
                    {
                        System.out.println("Total no of rooms required : ");
                        int noOfRooms=sc.nextInt();
                        ArrayList<Integer> f=new ArrayList<>();
                        for(int i=0;i<noOfRooms;i++)
                        {
                            System.out.println("Enter room size : ");
                            int s=sc.nextInt();
                            int count=av.get(s);
                            if(count!=0)
                            {
                                av.put(s, count-1);
                                f.add(s); //add desired room sizes into f for later use
                                si=si+s;
                            }
                            else
                            {
                                System.out.println(s+" sized room not available");
                                av.put(s, count+1);
                                f.remove(s);
                                p=1;
                                throw new InputMismatchException();
                            }  
                        }
                        if(c.total-si>=1||p!=0)
                        {
                            System.out.println("For given capacity cannot allocate a smaller room");
                            throw new InputMismatchException();
                        }
                        else
                        {
                            float sum=0;
                            for(int i=0;i<noOfRooms;i++)
                            {
                                ArrayList<Room>e[]=c.book(w,f.get(i),in,in_time,out,out_time,custname);
                                w=e[0];
                                float total=e[1].get(0).get_price()*(ChronoUnit.DAYS.between(in, out));
                                sum=sum+total;

                            }
                            c.setPrice(sum);
                            System.out.println("\nTOTAL PRICE  : "+sum);

                        }
                        System.out.println(c);
                        management.send(rlist);
                        cust.add(c);
                        si=0;p=0;
                        break;
                    }
                    catch(InputMismatchException e1)
                    {
                        System.out.println("Re-nter credentials");
                    }
                    catch(Exception e)
                    {
                        System.out.println("\nWrong format !!!!\nPlease Re-enter the credentials");
                    }
                    si=0;p=0;
                }
            }
            else
            {
                    si=0;p=0;
                    return;
            }
    }
}
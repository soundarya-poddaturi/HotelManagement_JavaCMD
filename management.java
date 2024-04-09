import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
class inout
{
    LocalDate check_in_date;
    LocalDate check_out_date;
    LocalTime check_in_time;
    LocalTime check_out_time;
    int passcode;
    String customerName;
    inout(LocalDate ind,LocalDate oud,LocalTime inti,LocalTime outti,int p,String cusname)
    {
        check_in_date=ind;
        check_in_time=inti;
        check_out_date=oud;
        check_out_time=outti;
        passcode =p ;
        customerName=cusname;
    }
   public LocalDate getcid(){
        return check_in_date;
    }
    public LocalDate getcod(){
        return check_out_date;
    }
    LocalTime getcit(){
        return check_in_time;
    }
    LocalTime getcot(){
        return check_out_time;
    }
    public void set_passcode(int n)
    {
        passcode=n;
    }
    public int get_passcode()
    {
        return passcode;
    }
    public String getName()
    {
        return customerName;
    }
    public String toString()
    {
        return ""+getcid()+" "+getcit()+" "+getcod()+" "+getcot()+" "+get_passcode()+" "+getName();//2023-07-20 10:00:00
    }
    public boolean equals(Object o) {
        if (this == o) return true; // If comparing with itself, they are equal
        if (o == null || getClass() != o.getClass()) return false; // If the classes are different, they can't be equal

        inout z = (inout) o;
        // Compare specific attributes for equality
        return Objects.equals(check_in_date, z.check_in_date) &&
               Objects.equals(check_out_date, z.check_out_date) &&
               Objects.equals(check_in_time, z.check_in_time) &&
               Objects.equals(check_out_time, z.check_out_time) &&
               passcode == z.passcode;
    }
}
class Room
{
    private static HashSet<Integer> uniqueRoomNumbers = new HashSet<>();
    int roomNumber;
    float price;
    boolean status;
    public  ArrayList<inout> arr=new ArrayList<inout>();
    int capacity;
    boolean ac;
    static int in=-1;
    static int out=0;
    Room(int i,int c,boolean s,boolean ac,float p)
    {
        if (!uniqueRoomNumbers.contains(i)) {
        roomNumber=i;
        status=s;
        capacity=c;
        this.ac=ac;
        price=p;
        uniqueRoomNumbers.add(i);
        }else throw new IllegalArgumentException("Room number " + i + " already exists.");
    }
    public String toString()
    {
        return  capacity+"";
    }
    int get_room()
    {
        return this.roomNumber;
    }
    int get_capacity()
    {
        return this.capacity;
    }
    public float get_price()
    {
        return price;
    }
}
public class management {
    public static ArrayList<Room> rlist = new ArrayList<>();
    
    public static void display(ArrayList<Room>x)
    {
        // rlist=x;
        for(int i=0;i<rlist.size();i++)
        System.out.println("Room no: "+rlist.get(i).roomNumber+"\t"+"ac_availability : "+rlist.get(i).ac+"\t"+"price : "+rlist.get(i).price+"\tcheckInOut : "+rlist.get(i).arr);

    }
    public static void send(ArrayList<Room>x)
    {
        rlist=x;
    }
    public static  ArrayList<Room>get_room_list()
    {
        return rlist;
    }
    public static void issue_room()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter passcode : ");
        int p=sc.nextInt();
        
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter tformatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String date1,time1,date2,time2;
            LocalTime in_time,out_time;
            LocalDate in,out;
            System.out.println("Enter check_in date(yyyy-MM-dd) : ");
            while(true){ 
                try{
                    date1=sc.next();
                    in = LocalDate.parse(date1, formatter);
                    break;
                }
                catch(InputMismatchException e){
                    System.out.println("\n Wrong Format !!!! \nRe-enter ur name : ");
                }
            }
            System.out.println("Enter check_in time (HH:mm:ss): ");
            while(true){ 
                try{
                    time1=sc.next();
                    in_time = LocalTime.parse(time1, tformatter);
                    break;
                }
                catch(InputMismatchException e){
                    System.out.println("\n Wrong Format !!!! \nRe-enter ur name : ");
                }
            }
            System.out.println("Enter check_out date(yyyy-MM-dd) : ");
           while(true){ 
                try{
                    date2=sc.next();
                     out = LocalDate.parse(date2, formatter);
                    break;
                }
                catch(InputMismatchException e){
                    System.out.println("\n Wrong Format !!!! \nRe-enter ur name : ");
                }
            }
            System.out.println("Enter check_in time (HH:mm:ss): ");
            while(true){ 
                try{
                    time2=sc.next();
                    out_time = LocalTime.parse(time2, tformatter);
                    break;
                }
                catch(InputMismatchException e){
                    System.out.println("\n Wrong Format !!!! \nRe-enter ur name : ");
                }
            }

            inout x=new inout(in, out, in_time, out_time, p,null);
            int flag=0;
            for (Room y:rlist)
            {
                for(int i=0;i<y.arr.size();i++)
                {
                    inout z=y.arr.get(i);
                    if(z.equals(x))
                    {
                        System.out.println("room alloted  for passcode "+p+"is : "+y.get_room());
                        flag=1;
                        break;
                    }
                }
            }
            if(flag==0)
            {
                System.out.println("invalid passcode");
            }
    }
    public static void main(String[] args) {
        try {
            
        
        Scanner sc=new Scanner(System.in);

        //creation of room
        System.out.println("Enter the number of rooms:");
        // int n=sc.nextInt();
        
        // for (int i = 0 ; i <n ; ++i)
        // {
        //     System.out.println("Enter room id, ac availability, capacity,price");
        //     int roomid=sc.nextInt();
        //     Boolean stat=true; //default value is available if not mentioned in input file
        //     Boolean ac=sc.nextBoolean();
        //     int capacity=sc.nextInt();
        //     float price =sc.nextFloat();
        //     Room r=new Room(roomid,capacity,stat,ac,price);
        //     rlist.add(r);
        // }
        Room r1=new Room(1,2,true,true,1000);
        Room r2=new Room(2,2,true,true,1000);
        Room r3=new Room(3,2,true,false,800);
        Room r4=new Room(4,2,false,false,800);
        Room r5=new Room(5,4,true,true,2000);
        Room r6=new Room(6,4,true,true,2000);
        Room r7=new Room(7,4,true,false,1500);
        Room r8=new Room(8,4,true,false,1500);
        Room r9=new Room(9,6,true,true,3000);
        Room r10=new Room(10,6,true,true,3000);
        Room r11=new Room(11,6,true,false,2800);
        Room r12=new Room(12,6,true,false,2800);
        rlist.add(r1);rlist.add(r2);rlist.add(r3);rlist.add(r4);
        rlist.add(r5);rlist.add(r6);rlist.add(r7);rlist.add(r8);
        rlist.add(r9);rlist.add(r10);rlist.add(r11);rlist.add(r12);
        send(rlist);
        display(rlist);
        // try{
        //         hotel.main(rlist);
        // }
        // catch(Exception e){
        //         System.out.println(e);
        // }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}

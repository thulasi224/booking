import java.util.*;
class theatre
{
    String location;
    String tname;
    int capacity;
    String moviename;
    int available;
    int booked;
    int priceperticket;
    theatre()
    {

    }
    theatre(String location,int capacity,String moviename,int priceperticket,String tname)
    {
        this.location=location;
        this.capacity=capacity;
        this.moviename=moviename;
        this.priceperticket=priceperticket;
        this.available=capacity;
        this.booked=0;
        this.tname=tname;
    }

}
public class audience {
    static int cnt = 0;
    int ticketid;
    String name;
    int age;
    int noofticketsrequired;
    int amount;
    static List<String> auddetails = new ArrayList<String>();
    static List<Integer> booked = new ArrayList<>();

    audience(String name, int age, int noofticketsrequired) {
        this.ticketid = cnt++;
        this.name = name;
        this.age = age;
        this.amount = 0;
        this.noofticketsrequired = noofticketsrequired;
    }

    static boolean checkavailability(String name, List<theatre> list) {
        for (theatre t : list) {
            if (t.tname == name) {
                if (t.booked > 0) {
                    System.out.println("Available tickets=" + t.available);
                    return true;
                }
            }
        }
        return false;

    }

    static void bookticket(audience cust, List<theatre> list, String name) {
        for (theatre t : list) {
            if (t.tname == name) {
                int price = cust.noofticketsrequired * t.priceperticket;
                cust.amount = price;
                System.out.println("-----------------BOOKED SUCCESSFULLY-----------------");
                String det = "Ticket Id:" + cust.ticketid + "Customer Name:" + cust.name + "\nCustomer Age:" + cust.age + "\nMovie Name:" + t.moviename + "\n Theatre Name" + t.tname + "Total Amount:" + cust.amount;
                auddetails.add(det);
                booked.add(cust.ticketid);

            }

        }
    }

    public static void main() {
        List<theatre> theatrelist = new ArrayList<>();
        theatre a = new theatre("anatapur", 300, "kalki", 300, "gangotri");
        theatre b = new theatre("anatapur", 350, "julai", 100, "inox");
        theatre c = new theatre("anatapur", 250, "interstellar", 300, "anandhi");
        theatre d = new theatre("anatapur", 400, "vakeel sab", 300, "imax");
        theatrelist.add(a);
        theatrelist.add(b);
        theatrelist.add(c);
        theatrelist.add(d);
        Scanner s = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("enter \n 1->booking");
            int choice = s.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("enter theatre name");
                    String name = s.nextLine();
                    boolean status = checkavailability(name, theatrelist);
                    if (status) {
                        System.out.println("enter name age movie name,nooftickets");
                        String custname = s.nextLine();
                        int custage = s.nextInt();
                        int custtickets = s.nextInt();
                        audience cust = new audience(custname, custage, custtickets);
                        bookticket(cust, theatrelist, name);

                    }
                    break;
                }
            }
        }


    }

}
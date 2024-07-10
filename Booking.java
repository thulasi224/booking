import java.util.*;
class taxi
{
    static int cnt=0;
    int id;
    boolean booked;
    char currentspot;
    int freetime;
    int totalearnings;
    List<String> trips;
    taxi()
    {
        cnt=cnt+1;
        id=cnt;
        booked=false;
        currentspot='A';
        freetime=6;
        totalearnings=0;
        trips=new ArrayList<>();
    }
    void setdetails(boolean booked,char currentspot,int freetime,int totalearnings,String tripdet)
    {
        this.booked=booked;
        this.currentspot=currentspot;
        this.freetime=freetime;
        this.totalearnings=totalearnings;
        this.trips.add(tripdet);
    }
    void printdetails(){

        System.out.println("id:"+this.id+"total earnings"+this.totalearnings);
        System.out.println("taxiid      bookingid       customerid      from        to      pickuptime      droptime        amount");
        for(String s:trips)
        {
            System.out.println(id+" "+s);

        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
    }
    void printtaxis()
    {
        System.out.println("    id"+this.id+"   currentspot"+this.currentspot+"     freetime"+this.freetime+"       Earninds:"+this.totalearnings);
    }

}
public class booking
{
    static void book(int id,int customerid,char pickuppoint,char droppoint,int pickuptime,List<taxi> freetaxi)
    {
        int min=999;
        int disdroppick=0;
        int earning=0;
        int nextfreetime=0;
        int droptime=0;
        char nextspot='z';
        taxi bookedtaxi=null;
        String tripdet="";

        for(taxi t:freetaxi)
        {
            int disdroppickcust=Math.abs((t.currentspot-'0')-(pickuppoint-'0'))*15;
            if(disdroppick<min)
            {
                bookedtaxi=t;
                disdroppick=Math.abs((droppoint-'0')-(pickuppoint-'0'))*15;
                if(disdroppick<=5)
                {
                    earning=100;

                }else{
                    earning=(disdroppick-5)*10+100;
                }
                droptime=pickuptime+disdroppick/15;
                nextfreetime=droppoint;
                nextspot=droppoint;
                tripdet= id+"       "+customerid+"      "+pickuppoint+"     "+droppoint+"       "+pickuptime+"      "+droptime+"        "+earning;

            }
        }
        bookedtaxi.setdetails(true,nextspot,nextfreetime,bookedtaxi.totalearnings+earning,tripdet);
        System.out.println("successfully booked");
    }
    static List<taxi> getfreetaxis(List<taxi> taxis,int pickuptime,char pickuppoint)
    {
        List<taxi> freetaxis=new ArrayList<>();
        for(taxi t:taxis)
        {
            if(t.freetime<=pickuptime &&( Math.abs((t.currentspot-'0')-(pickuppoint-'0'))<=pickuptime-t.freetime))
            {
                freetaxis.add(t);
            }
        }
        return freetaxis;

    }
    public static void main(String[] args)
    {
        List<taxi> taxis=new ArrayList<>();
        for(int i=0;i<4;i++)
        {
            taxi t=new taxi();
            taxis.add(t);
        }
        int id=1;
        while(true) {
            Scanner s = new Scanner(System.in);
            System.out.println("1-> booking\n2-> printing");
            int choice=s.nextInt();
            switch(choice)
            {
                case 1:
                {
                    System.out.println("enter pickuppoint");
                    char pick=s.next().charAt(0);
                    System.out.println("enter drop point");
                    char drop=s.next().charAt(0);
                    System.out.println("enter pickpup time");
                    int pickuptime=s.nextInt();
                    if(pick<'A' && pick>'G'&& drop<'A' && drop>'G')
                    {
                        System.out.println("invalid input");
                    }
                    List<taxi> freetaxis=booking.getfreetaxis(taxis,pickuptime,pick);
                    if(freetaxis.size()<=0) {
                        System.out.println("no taxis available");
                        return;
                    }
                    Collections.sort(freetaxis,(a,b)->a.totalearnings-b.totalearnings);
                    book(id,id,pick,drop,pickuptime,freetaxis);

                    id++;
                    break;
                }
                case 2:
                {
                    for(taxi t:taxis)
                    {
                        t.printtaxis();
                    }
                    for(taxi t:taxis)
                    {
                        t.printdetails();
                    }
                }
            }
        }

    }
}

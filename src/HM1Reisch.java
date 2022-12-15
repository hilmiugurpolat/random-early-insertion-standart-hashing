import java.util.Random;

import javax.swing.table.TableStringConverter;

public class HM1Reisch {
	
	
	Random rnd = new Random();	
	int[] keys= new int[900];

	int[] ReischArray= new int[900];
	String[] link = new String[900];
	public void generateRandomNumbers()
	{
		for(int i=0;i<900;i++)
		{
			keys[i]=rnd.nextInt(1000);
			}
	}
public static void main(String[] args) {
		
		
	    HM1Reisch HM = new HM1Reisch();
	    HM.generateRandomNumbers();
	    HM.place_random();
	    HM.print();
        System.out.println( HM.ReischSearch(65));
        
        System.out.println("packing factor: "+HM.packingFactor());
       
        
	}

	public void place_random()
	{
		int homeAddress;
        int randomAddress;

        for (int i = 0; i < keys.length; i++) {
            homeAddress = keys[i] % 900;

            if (ReischArray[homeAddress] == 0) {
            	ReischArray[homeAddress] = keys[i];
            }
            else if (ReischArray[homeAddress] != 0 && link[homeAddress] == null) {

                do {
                    randomAddress = rnd.nextInt(900);
                } while (ReischArray[randomAddress] != 0);

                ReischArray[randomAddress] = keys[i];
                link[homeAddress] = randomAddress + "";
            }
            else if (ReischArray[homeAddress] != 0 && link[homeAddress] != null) {

                do {
                    randomAddress = rnd.nextInt(900);
                } while (ReischArray[randomAddress] != 0);

                ReischArray[randomAddress] = keys[i];
                int tempAddress = Integer.parseInt(link[homeAddress]);
                link[homeAddress] = randomAddress + "";
                link[randomAddress] = tempAddress + "";
            }
        }
	} 
	public int ReischSearch(int enterednumber)
    {
        
        int probecount = 1;
        int homeadress = enterednumber % 900;
        boolean isContinue = true;
        int currentkey;
        int currentlink;
        String str1=Integer.toString(ReischArray[homeadress]);
        while (enterednumber != ReischArray[homeadress] && isContinue)
        {
            currentkey = ReischArray[homeadress];
            currentlink = Integer.parseInt(link[homeadress]);
            String str2=link[homeadress];
            
            if (enterednumber == currentkey)
            {   System.out.println("key: "+currentlink);
                break;
            }
           
            
             
            else if (enterednumber != currentkey && !str2.isBlank() && !str2.isEmpty())
            {
                probecount++;
                homeadress = currentlink;
                currentkey = ReischArray[homeadress];
               
            }
            if (!str1.isEmpty() & !str1.isBlank() &currentkey != enterednumber)
            {  
                isContinue = false;
                System.out.println("key: "+currentlink);
            }
        }
        
        return probecount;
    }
	  
	 public void print() {
	        System.out.println("Index\tKeys\tLinks");
	        for (int i = 0; i < ReischArray.length; i++)
	            System.out.println(i + "\t\t" + ReischArray[i] + "\t\t" + link[i]);
	    }
	
	 
	 public double AverageProbe()
	 {
		 int counter=0;
		 for(int i=0;i<ReischArray.length;i++)
		 {
			 counter+=ReischSearch(ReischArray[i]);
		 }
		 return (double)counter;
	 }
	 
	 public double packingFactor()
	 {
		 int size=908;
		 return ((double)ReischArray.length/(double)size)*100;
	 }
	  
}
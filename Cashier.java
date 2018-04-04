/**
 * @(#)Cashier Counter Simulatiob.java
 *
 *1B-170495900
 *                                  ********************************
 * @author Li Jun Xi
 * @version
 */

import java.util.*;

public class Cashier{

    public  static void main (String [] args){
        Scanner kb = new Scanner (System.in);
        final int maxTime,maxTellers,maxSer,seedNo;
        int tellercheck=0,serTime=0,currentTime=0,customersered=0,
        		currentQueue=0,maxQueue=0,totalQueue=0,ranuse=0;
        ListQueue waiting=new ListQueue();
        
        
        //try{
            System.out.println("------ SETUP SIMULATION ENVIRONMENT--------");
            System.out.print("Input simulation length(min):");
            maxTime = kb.nextInt();
            //if(maxTime<=0)
            	//throw new numberexception();
           
	        System.out.print("Input number of tellers:");
    	    maxTellers = kb.nextInt();
    	    int [] teller = new int[maxTellers];
    	    
            System.out.print("Input maximum serving time for a customer:");
            maxSer=kb.nextInt();
            
            System.out.print("Input seed number:");
            seedNo=kb.nextInt();
          
            Random rnd= new Random();
            rnd.setSeed(seedNo);
            int[] randomlist=new int[maxTime];
            
            for(int i=0;i<maxTime;i++) {
            	randomlist[i]=rnd.nextInt(maxSer+1);
            	}
 
           
            System.out.println("---- START SIMULATION -----");
                        
           while(currentTime<maxTime){
        	   currentTime++;
        	   
      	   // Check whether has customer finished the servicing and exited from the teller. 
        	   for(int i=0;i<maxTellers;i++) {
        		   if(teller[i]==currentTime-1&&!waiting.isEmpty()) {       			  
        			   teller[i]=(int)waiting.dequeue()+currentTime;
        			   customersered++;
        			   break; // maybe??
        		   }
        	   }
        	
        	   
        	// Check whether the customer is arrival. 
        	   if(seedNo==0) {
        		System.out.print("A customer came with serving time:");
           	    serTime = kb.nextInt();
           	    //if (serTime>maxSer) throw new   check input sertime is larger than the maxSer
        	   }else {
        		  serTime=randomlist[ranuse];
        		  ranuse++;
        	   }
        	   
        	// If one customer is arrival,  Put in the teller if a teller is available
        	   // Put in the queue if all the tellers are full 
        	   
        	   
        	   //something wrong in this
        	   	if(serTime>0) {
        	   		for(int i=0;i<maxTellers;i++) 
        	   		{
             		   if(teller[i]==currentTime-1) {
             			   teller[i]=serTime+currentTime;
             			  customersered++;
             			  break;		//maybe??
             		   }
             		   //else if(i==maxTellers)
             		  // waiting.enqueue(serTime);
             		 // break; //maybe
        	   		}	   	
        	   	}
   	   
        	/**for(tellercheck=0;tellercheck>maxTellers;tellercheck+=0) {
        		if(teller[tellercheck]<currentTime)
        			teller[tellercheck]=serTime+currentTime;
        		else tellercheck++;
        		if(tellercheck==maxTellers)
        			waiting.enqueue(serTime);	
        	}**/
        	   
  
        	   System.out.print("R"+currentTime);
        	for(int i=1;i<maxTellers+1;i++){
        	   System.out.print("   Teller_"+i+"["+teller[i-1]+"]");
           }
        	
        	System.out.println("   Waiting Queue:"+waiting);
			
        	currentQueue=waiting.count();
        	if(currentQueue>maxQueue)
        		maxQueue=currentQueue;
        	totalQueue+=currentQueue;
           }
    
        System.out.println("---- END OF SIMULATION ----");
        System.out.println("Total minute simulated: "+maxTime+" minutes");
       	System.out.println("Number of Tellers: "+maxTellers);
       	System.out.println("Number of customer served:"+customersered+" customers");
       	System.out.println("Average Waiting Line Length:"+totalQueue/maxTime+" customers");
       	System.out.println("Maximum Waiting Line Length:"+maxQueue+" customers");
       	}
	




//public static int[] genrand(int seed,int max,int time){
	
	
	//return randomlist;	
//}
}



   /**     catch(numberexception e) {
        	System.out.println(e.getMessage());        	
        	}
    }
    
    class numberexception extends Exception{
    	public numberexception(){
    		super("Please input a integer");
    	}
      }
}
    

	
	
	

	
	
	
	
	
	**/
 
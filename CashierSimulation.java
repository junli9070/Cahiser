/**
 * @(#)CashierSimulation.java
 *Li Jun Xi
 *1B-170495900
 *simulation of Cashier Counter and Queue
 * @author 
 * @version
 */

import java.util.*;

public class CashierSimulation{

    public  static void main (String [] args){
        Scanner kb = new Scanner (System.in);		
        final int maxTime,maxTellers,maxSer;		/**Initialize the variable
         											* maxTime is the length of simulation
         											* maxTellers is the no of counters
         											* maxSer is the maximum serving time 
         											* As those variable will not be change in future, final is used**/          
        int serTime=0,currentTime=0,customerSered=0,
        	currentQueue=0,maxQueue=0,ranUse=0,seedNo = 0;
       	double totalQueue=0;						/**Initialize the variable
       												*serTime is to save the serving time of coming customer 
       												*currentTime is to save the current time
       												*customerSered is to save The total number of the customers has served
       												*currentQueue is to save how many people are queuing at each round
       												*maxQueue is to save Maximum queue length during the simulation
       												*ranUse is to count  the random number used 
       												*seedNo is the seed number used to generate random number
       												*totalQueue is to count how many people have ever queued
       												**/
       					
        ListQueue waitLine=new ListQueue();			//create a Queue 
        
        
        try{
            System.out.println("------------- SETUP SIMULATION ENVIRONMENT -------------");
            System.out.print("Input simulation length(min):");
            maxTime = kb.nextInt();					//ask for the length of simulation 
            if(maxTime<=0)
            	throw new numberexceptionI();			
           
	        System.out.print("Input number of tellers:");
    	    maxTellers = kb.nextInt();				//ask for the number of counters 
    	    if(maxTellers<=0)
    	    	throw new numberexceptionI();
    	    int [] teller = new int[maxTellers];	//create an array according to the number inputed to save the leave time of different tellers
    	    
            System.out.print("Input maximum serving time for a customer:");
            maxSer=kb.nextInt();					//ask for maximum serving time
            if(maxSer<=0)
            	throw new numberexceptionI();
            
            System.out.print("Input seed number:");
            seedNo=kb.nextInt();					//ask for seed number
            if(seedNo<0)
            	throw new numberexceptionI();
            
            Random rnd= new Random();
            rnd.setSeed(seedNo);
            int[] randomlist=new int[maxTime];				//create an array for save the random number			           
            for(int i=0;i<maxTime;i++) {
            	randomlist[i]=rnd.nextInt(maxSer+1);		//save the data into the array
            	}

          
            System.out.println("--------------- START SIMULATION ---------------");
                        
           while(currentTime<maxTime){					//check the simulation is finished or not
        	   currentTime++;							//a new round 
        	   
      	   // Check whether any tellers is not using,if it is and someone is queuing,let the customer use the tellers
        	   for(int i=0;i<maxTellers;i++) {      	   	//loop according to number of tellers
        	   	
        		   if(teller[i]==currentTime-1){
        		   		teller[i] = currentTime;
        		   }
        		   if(teller[i]==currentTime&&!waitLine.isEmpty()) {        
        			   teller[i]=(int)waitLine.dequeue()+currentTime;
        			   customerSered++;
        		   }
        	   }
        	
        	   
        	// Check whether the customer is arrival. 
        	   if(seedNo==0) {
        		System.out.print("A customer came with serving time:");
           	    serTime = kb.nextInt();					//ask for new customer serving time
           	    if (serTime>maxSer||serTime<0) 			//check input serTime is between 0 and maximum serving time or not
           	    	throw new logicerrorexception();     
        	   }else {
        		  serTime=randomlist[ranUse];
        		  ranUse++;
        	   }
        	   
        	// If one customer is arrival, Put in the teller if a teller is available
        	   //Put in the queue if all the tellers are full 
        	   	if(serTime>0) {
        	   		for(int i=0;i<maxTellers;i++) 		//loop to see any teller available
        	   		{
             		   if(teller[i]==currentTime) {		
             			   teller[i]=serTime+currentTime;
             			   customerSered++;				
             			   break;						//break the loop if the customer find a available teller
             		   }
             		   else if(i==(maxTellers-1)){		
             			   waitLine.enqueue(serTime);						
        	   			} 	 	
        	   		}
        	   	}        	   	       	   
  
        	   System.out.print("R"+currentTime);		// Display the current status for end of this minute
        	   for(int i=1;i<maxTellers+1;i++){
        		   System.out.print("   Teller_"+i+"[");
        	   if(teller[i-1]==currentTime){
        	   		System.out.print("0]");
        	   }
        	   else{
        	   		System.out.print(teller[i-1]+"]");
        	   }
        	   
           }
        	
        	System.out.println("   Waiting Queue:"+waitLine);	//Display the current status of the queue for end of this minute
			
        	currentQueue=waitLine.count();				//record the current status of the queue for end of this minute
        	if(currentQueue>maxQueue)				
        		maxQueue=currentQueue;
        	totalQueue+=currentQueue;
           }
    
        System.out.println("--------------- END OF SIMULATION ---------------");				//display the result of the simulation
        System.out.println("Total minute simulated: "+maxTime+" minutes");
       	System.out.println("Number of Tellers: "+maxTellers);
       	System.out.println("Number of customer served: "+customerSered+" customers");
       	System.out.println("Average Waiting Line Length: "+totalQueue/maxTime+" customers");
       	System.out.println("Maximum Waiting Line Length: "+maxQueue+" customers");
       	System.out.println("-------------------------------------------------");
    }
        catch(numberexceptionI e) {
        	if(seedNo<0) {
        		System.out.println("Please input integer which is equals or lager than zero!");
        	}
        	else
        		System.out.println(e.getMessage());
        } 
        catch(InputMismatchException e1) {
        	System.out.println("Please input a postive integer!");
        }
        catch(logicerrorexception e2) {
        	System.out.println(e2.getMessage());
        }
        catch(Exception e3) {
        	System.out.println("An error occoured");
        }
     }
}


class numberexceptionI extends Exception{
	public numberexceptionI() {
		super("Please input a postive integer!");
	}
}

class logicerrorexception extends Exception{
	public logicerrorexception() {
		super("The serving time must be between 0 and maximum serving time!");
	}
}

 class ListQueue extends LinkedList {

    public ListQueue() {
		super();
    }
	
	public boolean empty() {
		return isEmpty();
	}
	
	public void enqueue(Object item) {
		addToTail(item);
	}
	
	public Object dequeue() {
		return removeFromHead();
	}
	
} // class ListQueue

class ListNode {

	private Object data;
	private ListNode next;

	public ListNode(Object o) { data = o; next = null; }
	public ListNode(Object o, ListNode nextNode)
		{ data = o; next = nextNode; }

	public Object getData() { return data; }
	public void setData(Object o) { data = o; }
	public ListNode getNext() { return next; }
	public void setNext(ListNode next) { this.next = next; }
} // class ListNode

class EmptyListException extends RuntimeException {
	public EmptyListException ()
		{ super("List is empty"); }
} // class EmptyListException


class LinkedList {

	private ListNode head;
	private ListNode tail;

	public LinkedList() { head = tail = null; }

	public boolean isEmpty() { return head == null; }

	public void addToHead(Object item) {
		if (isEmpty())
			head = tail = new ListNode(item);
		else
			head = new ListNode(item, head);
	}

	public void addToTail(Object item) {
		if (isEmpty())
			head = tail = new ListNode(item);
		else {
			tail.setNext(new ListNode(item));
			tail = tail.getNext();
		}
	}

	public Object removeFromHead() throws EmptyListException {
		Object item = null;
		if (isEmpty())
			throw new EmptyListException();
		item = head.getData();
		if (head == tail)
			head = tail = null;
		else
			head = head.getNext();
		return item;
	}

	public Object removeFromTail() throws EmptyListException {
		Object item = null;
		if (isEmpty())
			throw new EmptyListException();
		item = tail.getData();
		if (head == tail)
			head = tail = null;
		else {
			ListNode current = head;
			while (current.getNext() != tail)
				current = current.getNext();
			tail = current;
			current.setNext(null);
		}
		return item;
	}

	public String toString() {
		String s = "[ ";
		ListNode current = head;
		while (current != null) {
			s += current.getData() + " ";
			current = current.getNext();
		}
		return s + "]";
	}

	public int count() {
	
		ListNode current = head;
		int i = 0;
		while (current != null) {
			i++;
			current = current.getNext();
		}
		return i;
	}
	
} // class LinkedList

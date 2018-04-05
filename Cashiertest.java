/**
 * @(#)Cashier Counter Simulatiob.java
 *
 *1B-170495900
 *                                  ********************************
 * @author Li Jun Xi
 * @version
 */

import java.util.*;

public class Cashiertest{

    public  static void main (String [] args){
        Scanner kb = new Scanner (System.in);
        final int maxTime,maxTellers,maxSer,seedNo;
        int tellercheck=0,serTime=0,currentTime=0,customersered=0,
        		currentQueue=0,maxQueue=0,ranuse=0;
       	double totalQueue=0;
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

          
            System.out.println("-------- START SIMULATION ---------");
                        
           while(currentTime<maxTime){
        	   currentTime++;
        	   
      	   // Check whether has customer finished the servicing and exited from the teller. 
        	   for(int i=0;i<maxTellers;i++) {
        	   	
        	   		//
        		   if(teller[i]==currentTime-1){
        		   		teller[i] = currentTime;
        		   }
        		   //
        		   if(teller[i]==currentTime&&!waiting.isEmpty()) {       	//currentTimeÀ³¸Ó­ø¬~-1	  
        			   teller[i]=(int)waiting.dequeue()+currentTime;
        			   customersered++;
        			    // maybe??  ­ø¬~break;
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
        	   
        	// If one customer is arrival, „Ã Put in the teller if a teller is available
        	   //„Ã Put in the queue if all the tellers are full 
        	   
        	   
        	   //something wrong in this
        	   	if(serTime>0) {
        	   		for(int i=0;i<maxTellers;i++) 
        	   		{
             		   if(teller[i]==currentTime) {		//À³¸Ó­ø¬~-1
             			   teller[i]=serTime+currentTime;
             			  customersered++;
             			  break;		//maybe??	À³¸Ó­nbreak;
             		   }
             		   else if(i==(maxTellers-1)){		//maxTellers­n-1, ¦]¬°i<maxTellers
             		  	waiting.enqueue(serTime);
             		 	break; //maybe  ­n­ø­n³£¤@¼Ë, ¦]¬°·|run¨Ì¬q?®É­Ô, ³£¤w¸g­nÂ÷¶}loop
        	   			} 	 	
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
        	   System.out.print("   Teller_"+i+"[");
        	   
        	   //
        	   if(teller[i-1]==currentTime){
        	   		System.out.print("0]");
        	   }
        	   else{
        	   		System.out.print(teller[i-1]+"]");
        	   }
        	   //
        	   
           }
        	
        	System.out.println("   Waiting Queue:"+waiting);
			
        	currentQueue=waiting.count();
        	if(currentQueue>maxQueue)
        		maxQueue=currentQueue;
        	totalQueue+=currentQueue;
           }
    
        System.out.println("------- END OF SIMULATION -------");
        System.out.println("Total minute simulated: "+maxTime+" minutes");
       	System.out.println("Number of Tellers: "+maxTellers);
       	System.out.println("Number of customer served:"+customersered+" customers");
       	System.out.println("Average Waiting Line Length:"+totalQueue/maxTime+" customers");
       	System.out.println("Maximum Waiting Line Length:"+maxQueue+" customers");
       	System.out.println("-------------------------------------------------");
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

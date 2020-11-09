import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

//cu ajutorul acestei clase gestionam adaugarea si stergerea mesajelor Topic din coada

public class Topic{

    Queue<Message> q = new LinkedList<Message>();
    private long maxTime = 100;

    public  void receive(int tip) throws InterruptedException{
        while (true) {	
            synchronized(this){
            	
            	while (q.isEmpty()) {														//astept cat timp coada este goala
                    wait();
                }
            	
            	
                for (Iterator<Message> ms = q.iterator(); ms.hasNext();)				//parcurg coada de mesaje
	            {
                	Message mes = ms.next();
                	
//                	if(mes.getTip() == tip) //in acest caz dest = tip
//               	 {
//           
//               		// System.out.println(m);
//               		 System.out.println("Mesajul topic " + mes.getText() + " a fost citit.\n");
//               	 }
//                	
	                 if(System.currentTimeMillis() - mes.postTime > mes.getAvailability())	//daca mesajul postat a depasit limita maxima de timp
						 { 
	                	
	                    	 Message m = q.element();
		                    	 if(mes.getTip() == tip)									//pentru a fi receptionat, mesajul trebuie sa aiba tipul corespunzator
				                    	 {
				                
				                    		 //System.out.println(m);
		                    		 
		                    		 		//daca tipul a corespuns, inseamna ca a fost citit de cel putin un cititor
											//iar dupa ce timpul expira, mesajul se sterge
												
				                    		 System.out.println("Mesajul TOPIC " + mes.getText() + " de tipul " + m.getTip() + " a fost citit de cel putin un destinatar.\n");
				                    		 System.out.println("Mesajul TOPIC "+  m.getText() + " de tipul " + m.getTip() +" a fost sters.\n"); 
				                    	 }
	                        	
								 else 
											 //daca tipul nu a corespuns, dar totusi timpul a expirat, mesajul este sters fara a fi citit
											 
		                    		 	System.out.println("Mesajul TOPIC "+  m.getText() + " de tipul " + m.getTip() + " a fost sters fara a fi citit de niciun destinatar.\n"); 
	                    
	                    	q.remove();
						 
						 }
	                 else
						 if(System.currentTimeMillis() - mes.postTime > maxTime) //daca mesajul a depasit limita de timp data in antet
									 {
							 
									 Message m = q.element();
									 if(mes.getTip() == tip) 
			                    	 {
			                
			                    		 System.out.println("Mesajul TOPIC " + mes.getText() + " de tipul " + m.getTip() + " a fost citit de cel putin un destinatar.\n");
			                    		 System.out.println("Mesajul TOPIC "+  m.getText() + " de tipul " + m.getTip() + " a fost sters.\n"); 
			                    	 }
			                        	 
	                        	 else
	                        		 System.out.println("Mesajul TOPIC "+  m.getText() + " de tipul " + m.getTip() + " a fost sters fara a fi citit de niciun destinatar.\n"); 
							
								q.remove();
							 }                
                }
                
                notifyAll();
            }
        }
    }
   

 	synchronized void send(Message message) {
       q.add(message);					//se adauga mesajul
       message.displayTopic();
       notify();
    }
}

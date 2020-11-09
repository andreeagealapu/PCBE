public class Client_Main {

	
	public static void main(String[] args)  {
		
		Manager Q = new Manager();
		Topic Q2 = new Topic();
		
		//Manager
		Thread sender1 = new Thread(new Sender(Q,2));
		Thread sender2 = new Thread(new Sender(Q,2));
		
		Thread receiver1 = new Thread(new Receiver(Q,0));
	    Thread receiver2 = new Thread(new Receiver(Q,1));
	    
	    sender1.start();
		sender2.start();

		receiver1.start();
		receiver2.start();
		
		//Topic
		Thread s1 = new Thread(new Sender(Q2));
		Thread s2 = new Thread(new Sender(Q2));

		Thread r1 = new Thread(new Receiver(Q2,1));
	    Thread r2 = new Thread(new Receiver(Q2,2));

	    s1.start();
		s2.start();
			
		r1.start();
		r2.start();

	}

}

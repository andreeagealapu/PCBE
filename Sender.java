import java.lang.Math;

public class Sender implements Runnable{
	
	Manager manager;
	Topic managerTopic;
	private int no_of_receivers;

	public Sender(Manager manager,int no_of_receivers) {
		this.manager = manager;
		this.no_of_receivers=no_of_receivers;
		
	}
	
	public Sender(Topic managerTopic) {
		this.managerTopic = managerTopic;
		
	}

	@Override
	public void run() {
		
		//verific daca resursa este QUEUE  sau  TOPIC
		
		if (manager != null) {
			while(true){
				try{
					Message m = new Message("mesaj " + Math.random(), Utility.getRandomNumber(0, no_of_receivers)); //construiesc mesajul
					manager.send(m);

					Thread.sleep(1000);

				}catch(Exception e){
					System.out.println(e);
				}
			}
		}else{
			if (managerTopic != null)
			while(true){
				try{
					//construiesc mesajul
					Message m = new Message("mesaj " + Math.random(), Utility.getRandomNumber(0, 3), System.currentTimeMillis(), 100);
					managerTopic.send(m);	//tpublic mesajul
					Thread.sleep(1000);

				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
	
	}
}
	

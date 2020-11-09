public class Receiver implements Runnable{
	
	
    Manager manager;
	Topic managerTopic;
	private int dest;
	private int tip;
	
	public Receiver(Manager manager,int dest) {
		this.manager = manager;
		this.dest = dest;
		
}
	public Receiver(Topic managerTopic, int tip) {
		this.managerTopic = managerTopic;
		this.tip = tip;
		
}

	@Override
	public void run() {

		// verific dacă resursa este TOPIC sau QUEUE

		if (manager != null){
			while(true)
			{
				try{
						manager.receive(dest);
				}
				catch(Exception e){
					System.out.println(e);
				}
			}
		
		}else{
			if (managerTopic != null){
				while(true)
				{
					try{
						managerTopic.receive(tip);
						}
					catch(Exception e){
						System.out.println(e);
					}
				}
			}
		}
	}
}



import java.util.Queue;
import java.util.LinkedList;

//cu ajutorul acestei clase gestionam adaugarea si stergerea mesajelor MessageQueue din coada


public class Manager {

    Queue<Message> q = new LinkedList<Message>();
    private int queueMaxSize = 10;	                    //dimensiunea maxima a cozii

   public  void receive(int dest) throws InterruptedException {
        while (true) {
            synchronized(this){
                while (q.isEmpty()) {	                //pentru stergere/primirea mesajului, asteptam cat timp coada e goala 
                    wait();
                }
                
                if(q.peek().getDest() == dest){         //programul trebuie sa fie destinatar al mesajului pt a-l receptiona
                    
                    Message m = q.poll();	            //extrag mesajul din coada si apoi il sterg, deoarece a fost citit/primit
                    m.displayQueue();	            //mesajul a fost trimis

                    System.out.println("Mesajul QUEUE " + m.getText() + " a fost citit de catre " + m.getDest()+ " si apoi a fost sters.\n");
                    	
                }

                notifyAll();
            }
        }
    }

	synchronized void send(Message message) throws InterruptedException {

        while (q.size() == queueMaxSize) {	            //astept cat timp coada e plina
            wait();
        }

        q.add(message);                             	//adaug mesajul in coada
        notify();
    }
}

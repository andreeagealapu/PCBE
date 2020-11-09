public class Message {

	//pentru mesajele QUEUE
	private String text;        	// reprezinta continutul mesajului
	private int destinatar;			// reprezinta destinatarul mesajului Queue aflat in antetul mesajului

	//pentru mesajele TOPIC
	public long postTime;			//momentul la care a fost postat mesajul
	private long availability;		//valabilitatea mesajului
	private int tip;				//tipul mesajului
	
	//la crearea unui mesaj Queue se initializeaza textul si destinatarul
	public Message(String text, int destinatar) {  
		
		this.text = text;							
		this.destinatar = destinatar;
	}

	//la crearea unui mesaj Queue se initializeaza textul,tipul,postTime,valabilitatea
	public Message(String text, int tip, long postTime, long availability) {

		this.text = text;
		this.tip = tip;
		this.postTime = postTime;
		this.availability = availability;
	}


	//metode de preluare a atributelor specifice mesajului
	
	public String getText() {
		return text;
	}

	public int getDest() {
		return destinatar;
	}

	public long getAvailability() {
		return availability;
	}

	public int getTip() {
		return tip;
	}


	//metoda de afisare a mesajului Queue
	public void displayQueue() {

		System.out.println( "Mesajul QUEUE " + text + "  s-a trimis lui  " + destinatar + ".\n");
	}

	//metoda de afisare a mesajului Topic
	public void displayTopic() {
		System.out.println( "Mesajul TOPIC " + text + " de tipul " + tip + " cu valabilitate " + availability + "ms a fost publicat la " + postTime + "ms.\n");
	}
}

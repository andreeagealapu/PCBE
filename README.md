# PCBE
Proiect 1 - Server de mesagerie

Echipa este formată din:
	Nicolae Cristina,
	Gealapu Andreea,
	Culda Paula,
	Covaci Crina.
	
	
	Proiectul nostru este serverul de mesagerie. 
	Un client poate trimite,respectiv recepționa un mesaj prin 2 resurse: MessageQueue si Topics. Pentru acestea, avem și o parte de management.
	Am implementat atât partea de MessageQueue, cât și pe cea de Topic, cu ajutorul cozilor de mesaje.


Clasele și rolul lor:

	1. Message
				- 2 constructori (overloaded), pentru mesaje Topic, dar și pentru mesaje Queue.
			
				 Pentru mesajele Queue, avem nevoie de text și destinatar - le-am inițializat în constructor și am creat metode (getters) 
																			pentru preluare informațiilor și folosirea lor în celelalte clase.
																			
				 Pentru mesajele Topic, avem nevoie de text, tip, momentul_postării și valabilitate - 
																			le-am inițializat în constructor și am creat metode (getters) 
																			pentru preluare informațiilor și folosirea lor în celelalte clase.
																			
																
				Am creat și două metode de afișare a mesajelor.
	
	
	2. Manager
				- Aici sunt gestionate trimiterea și receptionarea mesajelor de tip MessageQueue.
				- În această clasă au fost rezolvate cele 2 probleme de concurență întâlnite,folosind monitoare, clase sincronizate, metodele predefintite wait() si notifyall().
				- Q reprezinta coada în care o să se adauge/șteargă mesajele, iar queueMaxSize reprezintă limita mesajelor din coadă.
				- Prima problemă de concurență este rezolvată in met. receive() ce primește ca param un destinatar. 
					Problema este ștergerea din coada goală, rezolvându-se punând în așteptare procesele Consumer, la noi Receiver și notificând procesele Sender să adauge in coada goală. 
					Se verifică destinatarul ca să se trimită cui trebuie mesajul și apoi șters când este recepționat.
				- A doua problemă de concurență este prezentată în met send() ce are ca param un mesaj: dacă coada este plină, nu se mai adaugă în coadă, ci procesele Consumer(Sender) sunt puse în așteptare și 
						procesele Receiver sunt notificate să șteargă din coadă.
						
	
	3. Sender
				= clientul ce trimite mesajul prin cele 2 modalități: Queue sau Topic.
				- În aceasta există 2 constructori ce ințializează variabilele in funcție de tipul resursei (Topic sau Queue).
				- Apoi se verifică ce tip de resursă este și se apelează metoda send pe tipul respectiv.
	
	4. Receiver
				- reprezintă și ea tot un client, dar de data asta cel care recepționează mesajul
				- La fel ca la Sender, 2 constructori cu param diferiți, și în run verificarea tipului de mesaj și apelarea pe tipul respectiv a met receive specifică tipului.
				
	5. Topic 
				= clasa în care se administrează mesajele de tip Topic
				- În metoda receive se parcurge coada de mesaje, și se verifică cele 2 condiții de ștergere: fie când se depășește perioada maximă dată ca antet, sau dacă nu, când se depășește termenul de valabilitate.
				- Se verifică și dacă tipul mesajului este tipul așteptat de destinatar, dacă da, destinatarul citește postarea (mesajul).
				- Nu este un destinatar explicit, mai mulți cititori pot citi postarea respectivă, se verifică doar tipul mesajului.
				- Fie că postarea a fost sau nu citită, ea se va șterge după expirarea timpului
						- Am ales să afișăm dacă mesajul a fost sau nu citit odată cu ștergerea lui,
							pentru ca în cazul în care e citit de f multe ori, să nu printăm întruna că mesajul a fost citit.
								
				
				- În metoda receive se testează coada să nu fie goală și se așteaptă cât timp e goală, pentru a avea ce șterge.
				
				- În metoda de send nu se mai testează capacitatea cozii, deoarece nu avem o limită de mesaje pe care să le punem în coadă.
				
				
	6. Client_Main
				- Aici am create thread-urile și le-am pornit
				- Avem o coadă pentru toate mesajele de tip Queue și una pentru mesajele de tip Topic
				
	
	
	7. Utility 
			= o clasă de care ne-am ajutat pentru a calcula un random

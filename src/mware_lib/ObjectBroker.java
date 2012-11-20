package mware_lib;

public class ObjectBroker {

	// Das hier zurückgelieferte Objekt soll der zentrale Einstiegspunkt
	// der Middleware aus Anwendersicht sein.
	// Parameter: Host und Port, bei dem die Dienste (Namensdienst)
	// kontaktiert werden sollen.
	public static ObjectBroker getBroker(String serviceHost, int listenPort) { 
		
<<<<<<< HEAD
			return null;
=======
<<<<<<< HEAD
			return null;
=======
			return new Object();
>>>>>>> origin/master
>>>>>>> e781f7fb2013ee933d124d66fc4acacb6313ca30
			}
	// Liefert den Namensdienst (Stellvetreterobjekt).
	public NameService getNameService() {
			
		
		return null;
	}
		
	
}

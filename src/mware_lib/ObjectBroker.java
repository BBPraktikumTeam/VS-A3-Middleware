package mware_lib;

public abstract class ObjectBroker {

	// Das hier zurückgelieferte Objekt soll der zentrale Einstiegspunkt
	// der Middleware aus Anwendersicht sein.
	// Parameter: Host und Port, bei dem die Dienste (Namensdienst)
	// kontaktiert werden sollen.
	public static ObjectBroker getBroker(String serviceHost, int listenPort) { 
		
			return null;
			}
	// Liefert den Namensdienst (Stellvetreterobjekt).
	public NameService getNameService() {
			
		
		return null;
	}
		
	
}

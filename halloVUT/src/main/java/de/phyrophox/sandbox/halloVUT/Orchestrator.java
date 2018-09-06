package de.phyrophox.sandbox.halloVUT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.*;


public class Orchestrator {

	
	final static Logger Log = LogManager.getFormatterLogger(Orchestrator.class);
	
	public Orchestrator() {
		
		Log.info("Jetzt geht's erst mal los und wir loggen ein wenig.");
		Log.info("Ich beschraenke mich dabei auf Dateiausgabe, koennte aber auch zum Beispiel in eine Cassandra loggen um im Nachgang z.B.via Apache Spark Auswertungenauf den Logs zu betreiben.");
		
		Log.error("Falls wir in einen Fehler laufen, so wuerde dieser im Fehlerlog auftauchen");
		Log.error("Das Fehlerlog wuerde ich von Nagios ueberwachen lassen.");
		Log.error("Alternativ kann über den Apache Kafka Appender auch eine aktive Benachrichtigung erfolgen");
				
		Log.debug("Falls wir Zusatzinformationen zum debuggen brauchen werden diese hier gelogt.");
		Log.debug("Debugging kann zur Laufzeit durch Anpassung der log4j Konfiguration zugeschaltet werden.");
		
	}
	
	public static void main(String[] args) {
		
	
		Orchestrator orchestrator = new Orchestrator();
		
		orchestrator.demonstriertDenUnGeprueftenUmgangMitCollections();
		orchestrator.demonstriertDenGeprueftenUmgangMitCollections();
				
	}

	private void demonstriertDenUnGeprueftenUmgangMitCollections() {
	
	
		List<String> namenDiverserAutoMarken = new ArrayList<>();
	    Collections.addAll(namenDiverserAutoMarken, "Jeep", "Mercedes", "Audi", "BMW");
	    
	    List irgendEineListe = namenDiverserAutoMarken;
	    
	    List<Integer> angeblichEineListeVonIntegern = irgendEineListe;
	    
	    try {
	    	angeblichEineListeVonIntegern.add(42);
	    } catch (Exception e) {
	    	Log.error("Das hat nicht funktioniert.", e);
	    }
	    
	    Log.info("Ooops, der Insert hat funktioniert da der typ nicht geprueft wird");
	    	    
	    String alleNamen = new String();
	    
	    try {
	    	
	      alleNamen = (String) irgendEineListe.stream().collect(Collectors.joining(", "));
	    } catch (Exception e) {
	    		Log.error("Erst bei der Verarbeitung bricht es uns mit einer cast Exception weg", e);	    	
	    }
	    
	    Log.info("Und alle Informationen sind weg: ", alleNamen);
	    
	    
	    
	}
	
	
	
	private void demonstriertDenGeprueftenUmgangMitCollections() {
		
		    List<String> namenDiverserAutoMarken = Collections.checkedList(
		        new ArrayList<>(), String.class);		  
		    Collections.addAll(namenDiverserAutoMarken, "Jeep", "Mercedes", "Audi", "BMW");
		    
		    List irgendEineListe  = namenDiverserAutoMarken;
		    
		    
		    List<Integer> angeblichEineListeVonIntegern = irgendEineListe;
		    
		    try {
		    	angeblichEineListeVonIntegern.add(42);
		    } catch (Exception e) {
		    	Log.error("Das hat dank der checkedList nicht funktioniert. Die Exception ist aussagekraeftig und kurz.", e);
		    }
		    
		    String alleNamen = new String();
		    
		    try {		    	
		    	alleNamen = (String) irgendEineListe.stream().collect(Collectors.joining(", "));
		    } catch (Exception e) {
		    		Log.error("Hier duerfen wir never ever landen...");	
		    }
		    
		    
		    Log.info("So haben wir zumindest einen Teil der Information gerettet: " + alleNamen);
		    
		    
		    
		    
   }
		
	
	
	
	
}

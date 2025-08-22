package ma.srm.srm.backend;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")  // Préfixe commun à tous les endpoints
public class ApplicationConfig extends Application {
    // Rien à mettre, Jakarta scanne automatiquement les classes @Path
    
    
}

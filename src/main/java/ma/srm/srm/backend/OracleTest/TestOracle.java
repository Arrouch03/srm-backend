package ma.srm.srm.backend.OracleTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestOracle {
    public static void main(String[] args) {
        // Chaîne de connexion JDBC
        String url = "jdbc:oracle:thin:@//192.168.11.204:1521/orclpdb";
        String user = "srmBD";       // utilisateur Oracle
        String password = "12345";   // mot de passe Oracle

        try {
            // Charger le driver Oracle (souvent inutile avec JDBC 4+ si ojdbc est dans le classpath)
            Class.forName("oracle.jdbc.OracleDriver");

            // Connexion
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                System.out.println("✅ Connexion réussie à Oracle !");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver Oracle introuvable. Vérifie ton ojdbc.jar ou ton pom.xml.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion Oracle !");
            e.printStackTrace();
        }
    }
}

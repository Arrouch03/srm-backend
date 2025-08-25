package ma.srm.srm.backend.model;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;
import java.util.List;

@Entity
@Table(name = "SECTEUR")
public class Secteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(length = 100)
    private String ville = "Marrakech";

    // -------------------------
    // Listes des compteurs associées
    // -------------------------
    @OneToMany(mappedBy = "secteur")
    @JsonbTransient // Ignorer la liste lors de la sérialisation JSON
    private List<CompteurEau> compteursEau;

    @OneToMany(mappedBy = "secteur")
    @JsonbTransient
    private List<CompteurElectricite> compteursElectricite;

    // -------------------------
    // Constructeurs
    // -------------------------
    public Secteur() {}

    public Secteur(String nom, String ville) {
        this.nom = nom;
        this.ville = ville;
    }

    // -------------------------
    // Getters & Setters
    // -------------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public List<CompteurEau> getCompteursEau() { return compteursEau; }
    public void setCompteursEau(List<CompteurEau> compteursEau) { this.compteursEau = compteursEau; }

    public List<CompteurElectricite> getCompteursElectricite() { return compteursElectricite; }
    public void setCompteursElectricite(List<CompteurElectricite> compteursElectricite) { this.compteursElectricite = compteursElectricite; }
}

package ma.srm.srm.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "COMPTEUR_TYPE")
public class CompteurType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrément si activé
    private Long id;

    @Column(nullable = false, length = 100)
    private String libelle;

    // Constructeurs
    public CompteurType() {}

    public CompteurType(String libelle) {
        this.libelle = libelle;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
}

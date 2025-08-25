package ma.srm.srm.backend.dto;

public class SecteurDTO {
    private Long id;
    private String nom;
    private String ville;

    public SecteurDTO() {}

    public SecteurDTO(Long id, String nom, String ville) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
}

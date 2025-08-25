package ma.srm.srm.backend.model;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;
import java.util.Date;

@Entity
@Table(name = "COMPTEUR_ELECTRICITE")
public class CompteurElectricite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String numero;

    @Column(name = "NB_FILS")
    private Integer nbFils;

    @Column(name = "NB_ROUES")
    private Integer nbRoues;

    @Column(length = 50)
    private String calibre;

    @Column(name = "DATE_POSE")
    private Date datePose;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CompteurType type;

    private Double longitude;
    private Double latitude;

    @Lob
    @Column(name = "PHOTO")
    private byte[] photo;

    @Column(name = "STATUT", length = 50)
    private String statut;

    @ManyToOne
    @JoinColumn(name = "SECTEUR_ID")
    @JsonbTransient // évite la sérialisation cyclique
    private Secteur secteur;

    @Transient
    private Long secteurId; // pour réception JSON depuis le frontend

    // Constructeurs
    public CompteurElectricite() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Integer getNbFils() { return nbFils; }
    public void setNbFils(Integer nbFils) { this.nbFils = nbFils; }

    public Integer getNbRoues() { return nbRoues; }
    public void setNbRoues(Integer nbRoues) { this.nbRoues = nbRoues; }

    public String getCalibre() { return calibre; }
    public void setCalibre(String calibre) { this.calibre = calibre; }

    public Date getDatePose() { return datePose; }
    public void setDatePose(Date datePose) { this.datePose = datePose; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public CompteurType getType() { return type; }
    public void setType(CompteurType type) { this.type = type; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public byte[] getPhoto() { return photo; }
    public void setPhoto(byte[] photo) { this.photo = photo; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Secteur getSecteur() { return secteur; }
    public void setSecteur(Secteur secteur) { this.secteur = secteur; }

    public Long getSecteurId() { return secteurId; }
    public void setSecteurId(Long secteurId) { this.secteurId = secteurId; }
}

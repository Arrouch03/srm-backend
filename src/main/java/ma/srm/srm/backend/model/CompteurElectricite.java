package ma.srm.srm.backend.model;

import jakarta.persistence.*;
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

    // Relation avec User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Relation avec CompteurType
    @ManyToOne
    @JoinColumn(name = "type_id")
    private CompteurType type;

    private Double longitude;
    private Double latitude;

    // Photo du compteur
    @Lob
    @Column(name = "PHOTO")
    private byte[] photo;

    // Statut du compteur (ex: Normal, À contrôler, Frauduleux)
    @Column(name = "STATUT", length = 50)
    private String statut;

    // Relation avec Secteur
    @ManyToOne
    @JoinColumn(name = "SECTEUR_ID")
    private Secteur secteur;

    // -------------------------
    // Constructeurs
    // -------------------------
    public CompteurElectricite() {}

    public CompteurElectricite(String numero, Integer nbFils, Integer nbRoues, String calibre,
                               Date datePose, User user, CompteurType type,
                               Double longitude, Double latitude, byte[] photo, String statut,
                               Secteur secteur) {
        this.numero = numero;
        this.nbFils = nbFils;
        this.nbRoues = nbRoues;
        this.calibre = calibre;
        this.datePose = datePose;
        this.user = user;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.photo = photo;
        this.statut = statut;
        this.secteur = secteur;
    }

    // -------------------------
    // Getters & Setters
    // -------------------------
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
}

package ma.srm.srm.backend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMPTEUR_EAU")
public class CompteurEau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String numero;

    private Double diametre;

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

    // Nouvelle colonne pour la photo
    @Lob
    @Column(name = "PHOTO")
    private byte[] photo;

    // Constructeurs
    public CompteurEau() {}

    public CompteurEau(String numero, Double diametre, Date datePose, User user, CompteurType type,
                       Double longitude, Double latitude, byte[] photo) {
        this.numero = numero;
        this.diametre = diametre;
        this.datePose = datePose;
        this.user = user;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.photo = photo;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Double getDiametre() { return diametre; }
    public void setDiametre(Double diametre) { this.diametre = diametre; }

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
}

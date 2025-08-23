package ma.srm.srm.backend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PLANIFICATION")
public class Planification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "AGENT_ID")
    private User agent;

    @ManyToOne
    @JoinColumn(name = "SECTEUR_ID")
    private Secteur secteur;

    @Column(name = "DATE_PLAN")
    private Date datePlan;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getAgent() { return agent; }
    public void setAgent(User agent) { this.agent = agent; }

    public Secteur getSecteur() { return secteur; }
    public void setSecteur(Secteur secteur) { this.secteur = secteur; }

    public Date getDatePlan() { return datePlan; }
    public void setDatePlan(Date datePlan) { this.datePlan = datePlan; }
}

package designPatterns.composite;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import designPatterns.composite.Employe;
import entreprise.gestionProjet.Investissement;

public class Projet extends Element {
    private int id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private BigDecimal cout;
    private Employe chefProjet;
    private List<Investissement> investissements=new ArrayList<>();

    public Projet(int id, String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal cout, Employe chefProjet) {
        super(id);
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.chefProjet = chefProjet;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public Employe getChefProjet() {
        return chefProjet;
    }

    public List<Investissement> getInvestissements() {
        return investissements;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    public void setChefProjet(Employe chefProjet) {
        this.chefProjet = chefProjet;
    }

    public void setInvestissements(List<Investissement> investissements) {
        this.investissements = investissements;
    }

    @Override
    public BigDecimal totalCoup() {
        return cout;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", cout=" + cout +
                ", chefProjet=" + chefProjet +
                ", investissements=" + investissements +
                '}';
    }
}

package designPatterns.observer;

import designPatterns.observer.Employe;
import entreprise.gestionProjet.Investissement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Projet extends Subject {
    private int id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private BigDecimal cout;
    private Employe chefProjet;
    private List<Investissement> investissements = new ArrayList<>();

    public Projet(int id, String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal cout, Employe chefProjet) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projet projet = (Projet) o;
        return id == projet.id && Objects.equals(nom, projet.nom) && Objects.equals(dateDebut, projet.dateDebut) && Objects.equals(dateFin, projet.dateFin) && Objects.equals(cout, projet.cout) && Objects.equals(chefProjet, projet.chefProjet) && Objects.equals(investissements, projet.investissements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, dateDebut, dateFin, cout, chefProjet, investissements);
    }

    @Override
    public String getNotification() {
        return "Le projet " + nom + " date de fin " + dateFin + " a été modifié.";
    }
}
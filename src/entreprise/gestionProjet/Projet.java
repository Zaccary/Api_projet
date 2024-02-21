package entreprise.gestionProjet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projet {
    protected int id_projet;
    protected String nom;
    protected LocalDate dateDebut;
    protected LocalDate dateFin;
    protected BigDecimal cout;
    protected Employe chefProjet;
    protected List<Investissement> investissements=new ArrayList<>();
    public Projet() {
    }
    public Projet(String nom, Employe chefProjet) {
        this.nom = nom;
        this.chefProjet = chefProjet;
    }
    public Projet(String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal cout, Employe chefProjet) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.chefProjet = chefProjet;
    }
    public List<Investissement> listeDisciplinesEtInvestissement() {
        return investissements;
    }
    public void investissementsTotal() {
        int total = 0;
        for (Investissement investissement : investissements) {
            total += investissement.quantiteJH;
        }
        System.out.println("Total : " + total);
    }
    public void addDiscipline(Disciplines discipline, int quantite){
        Investissement temp = new Investissement(discipline, quantite);
        investissements.add(temp);
    }

    public void modifDiscipline(Disciplines discipline, int quantite) {
        for(Investissement investissement : investissements) {
            if(investissements.get(investissements.indexOf(investissement)).discipline.equals(discipline)) {
                investissements.get(investissements.indexOf(investissement)).quantiteJH = quantite;
            }
        }
    }
    public void suppDiscipline(Disciplines discipline) {
        investissements.remove(discipline);
    }
    //TODO finir la méthode
    public List<Competence> niveauResponsableDiscipline() {
        List<Disciplines> temp=new ArrayList<>();
        List<Competence> temp2=new ArrayList<>();
        for (Investissement investissement : investissements) {
            temp.add(investissements.get(investissements.indexOf(investissement)).discipline);
        }
        for(Competence competence : chefProjet.competences) {
            if(temp.contains(competence.discipline)) {
                temp2.add(competence);
                temp.remove(competence.discipline);
            }
        }
        for(Disciplines disciplines :temp){
            temp2.add(new Competence(disciplines,0));
        }
        return temp2;
    }

    public int getId_projet() {
        return id_projet;
    }

    public void setId_projet(int id_projet) {
        this.id_projet = id_projet;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    public Employe getChefProjet() {
        return chefProjet;
    }

    public void setChefProjet(Employe chefProjet) {
        this.chefProjet = chefProjet;
    }

    public List<Investissement> getInvestissements() {
        return investissements;
    }

    public void setInvestissements(List<Investissement> investissements) {
        this.investissements = investissements;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id_projet=" + id_projet +
                ", nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", cout=" + cout +
                ", chefProjet=" + chefProjet +
                ", investissements=" + investissements +
                '}';
    }
}

package entreprise.gestionProjet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * classe Projet de gestion de projet
 * @author Walem Zaccary
 * @version 1.0
 */
public class Projet {
    /**
     * id auto-incrémenté
     */
    static int id=1;
    /**
     * id du projet
     */
    protected int id_projet;
    /**
     * nom du projet
     */
    protected String nom;
    /**
     * date de début du projet
     */
    protected LocalDate dateDebut;
    /**
     * date de fin du projet
     */
    protected LocalDate dateFin;
    /**
     * coût du projet
     */
    protected BigDecimal cout;
    /**
     * chef de projet
     */
    protected Employe chefProjet;
    /**
     * liste des disciplines et le niveau du chef de projet
     */
    protected List<Investissement> investissements=new ArrayList<>();
    /**
     * Constructeur de la classe Projet alternatif pour les tests
     * @param nom nom du projet
     * @param chefProjet chef de projet
     */
    public Projet(String nom, Employe chefProjet) {
        this.nom = nom;
        this.chefProjet = chefProjet;
    }
    /**
     * Constructeur de la classe Projet
     * @param nom nom du projet
     * @param dateDebut date de début du projet
     * @param dateFin date de fin du projet
     * @param cout coût du projet
     * @param chefProjet chef de projet
     */
    public Projet(String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal cout, Employe chefProjet) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.chefProjet = chefProjet;
        this.id_projet=id++;
    }
    /**
     * retourne la liste des disciplines et le niveau du chef de projet
     * @return liste des disciplines et le niveau du chef de projet
     */
    public List<Investissement> listeDisciplinesEtInvestissement() {
        return investissements;
    }
    /**
     * retourne le total des investissements
     * @return total des investissements
     */
    public int investissementsTotal() {
        int total = 0;
        for (Investissement investissement : investissements) {
            total += investissement.quantiteJH;
        }
        return total;
    }
    /**
     * Ajoute une discipline
     * @param discipline discipline à ajouter
     * @param quantite quantité de discipline
     */
    public void addDiscipline(Disciplines discipline, int quantite){
        Investissement temp = new Investissement(discipline, quantite);
        investissements.add(temp);
    }
    /**
     * Modifie la quantité d'une discipline
     * @param discipline discipline à modifier
     * @param quantite nouvelle quantité
     */
    public void modifDiscipline(Disciplines discipline, int quantite) {
        for(Investissement investissement : investissements) {
            int pos=investissements.indexOf(investissement);
            if(investissements.get(pos).discipline.equals(discipline)) {
                investissements.get(pos).quantiteJH = quantite;
            }
        }
    }
    /**
     * Supprime une discipline
     * @param discipline discipline à supprimer
     */
    public void suppDiscipline(Disciplines discipline) {
        investissements.remove(discipline);
    }
    /**
     *retourne la liste des disciplines et le niveau du chef de projet
     * @return liste des disciplines et le niveau du chef de projet
     */
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

    /**
     * retourne l'id du projet
     * @return id du projet
     */
    public int getId_projet() {
        return id_projet;
    }
    /**
     * modifie l'id du projet
     * @param id_projet id du projet
     */
    public void setId_projet(int id_projet) {
        this.id_projet = id_projet;
    }
    /**
     * retourne le nom du projet
     * @return nom du projet
     */
    public String getNom() {
        return nom;
    }
    /**
     * modifie le nom du projet
     * @param nom nom du projet
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * retourne la date de début du projet
     * @return date de début du projet
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }
    /**
     * modifie la date de début du projet
     * @param dateDebut date de début du projet
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
    /**
     * retourne la date de fin du projet
     * @return date de fin du projet
     */
    public LocalDate getDateFin() {
        return dateFin;
    }
    /**
     * modifie la date de fin du projet
     * @param dateFin date de fin du projet
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    /**
     * retourne le coût du projet
     * @return coût du projet
     */
    public BigDecimal getCout() {
        return cout;
    }
    /**
     * modifie le coût du projet
     * @param cout coût du projet
     */
    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }
    /**
     * retourne le chef de projet
     * @return chef de projet
     */
    public Employe getChefProjet() {
        return chefProjet;
    }
    /**
     * modifie le chef de projet
     * @param chefProjet chef de projet
     */

    public void setChefProjet(Employe chefProjet) {
        this.chefProjet = chefProjet;
    }
    /**
     * retourne la liste des investissements
     * @return liste des investissements
     */
    public List<Investissement> getInvestissements() {
        return investissements;
    }
    /**
     * modifie la liste des investissements
     * @param investissements liste des investissements
     */
    public void setInvestissements(List<Investissement> investissements) {
        this.investissements = investissements;
    }

    /**
     * override de la méthode toString
     * @return les informations du projet
     */
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

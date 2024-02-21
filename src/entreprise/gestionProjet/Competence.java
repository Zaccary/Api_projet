package entreprise.gestionProjet;
/**
 * classe Competence de gestion de projet
 * @author Walem Zaccary
 * @version 1.0
 */
public class Competence {
    /**
     * id auto-incrémenté
     */
    private static int id=1;
    /**
     * id de la compétence
     */
    protected int id_competence;
    /**
     * niveau de la compétence
     */
    protected int niveau;
    /**
     * discipline
     */
    protected Disciplines discipline;
    /**
     * Constructeur de la classe Competence
     * @param d discipline
     * @param niveau niveau de la compétence
     */
    public Competence(Disciplines d,int niveau) {
        this.discipline= d;
        this.niveau = niveau;
        this.id_competence = id++;
    }
    /**
     * retourne l'id de la compétence
     * @return id de la compétence
     */
    public int getId_competence() {
        return id_competence;
    }
    /**
     * modifie l'id de la compétence
     * @param id_competence
     */
    public void setId_competence(int id_competence) {
        this.id_competence = id_competence;
    }
    /**
     * retourne le niveau de la compétence
     * @return niveau de la compétence
     */
    public int getNiveau() {
        return niveau;
    }
    /**
     * modifie le niveau de la compétence
     * @param niveau niveau de la compétence
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    /**
     * retourne la discipline
     * @return discipline
     */
    public Disciplines getDiscipline() {
        return discipline;
    }
    /**
     * modifie la discipline
     * @param discipline
     */
    public void setDiscipline(Disciplines discipline) {
        this.discipline = discipline;
    }
}
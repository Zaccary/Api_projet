package entreprise.gestionProjet;

import java.util.ArrayList;
import java.util.List;

/**
 * classe Employe de gestion de projet
 * @author Walem Zaccary
 * @version 1.0
 */
public class Employe {
    /**
     * id auto-incrémenté
     */
    private static int id=1;
    /**
     * id de l'employé
     */
    protected int id_emplpoye;
    /**
     * matricule de l'employé
     */
    protected String matricule;
    /**
     * nom de l'employé
     */
    protected String nom;
    /**
     * prénom de l'employé
     */
    protected String prenom;
    /**
     * adresse de l'employé
     */
    protected String tel;
    /**
     * mail de l'employé
     */
    protected String mail;
    /**
     * liste des projets
     */
    protected List<Projet> projets=new ArrayList<>();
    /**
     * liste des disciplines et le niveau de l'employé
     */
    protected List<Competence> competences=new ArrayList<>();
    /**
     * Constructeur de la classe Employe alternatif pour les tests
     */
    public Employe() {
    }
    /**
     * Constructeur de la classe Employe
     * @param matricule matricule de l'employé
     * @param nom nom de l'employé
     * @param prenom prénom de l'employé
     * @param tel adresse de l'employé
     * @param mail mail de l'employé
     */
    public Employe(int id_emplpoye,String matricule, String nom, String prenom, String tel, String mail) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mail = mail;
        this.id_emplpoye = id_emplpoye;
    }

    /**
     * retourne une liste de DisciplinesEtNiveau
     * @return liste de DisciplinesEtNiveau (competences)
     */
    public List<Competence> listeDisciplinesEtNiveau() {
        return competences;
    }

    /**
     * L'ajout et la suppression des competence pourait être modifié pour être plus simple mais j'ai respecté le code de base car c'est écrit comme ça dans le pdf
     * ajoute une discipline avec un niveau
     * @param discipline
     * @param niveau
     */
    public void addDiscipline(int id_competence,Disciplines discipline, int niveau) {
        Competence temp = new Competence(id_competence,discipline, niveau);
        competences.add(temp);
    }
    /*
    comme ceci
    public void addDiscipline(Competence competence) {
        competences.add(competence);
     */
    /**
     * modifie le niveau d'une discipline
     * @param discipline
     * @param niveau
     */
    public void modifDiscipline(Disciplines discipline, int niveau) {
        for (Competence competence : competences) {
            int pos = competences.indexOf(competence);
            if(competences.get(pos).discipline.equals(discipline)) {
                competences.get(pos).niveau = niveau;
            }
        }
    }

    /**
     * supprime une discipline
     * même remarque que pour addDiscipline
     * @param discipline
     */
    public void suppDiscipline(Disciplines discipline) {
        competences.removeIf(competence -> competence.getDiscipline().equals(discipline));
    }
    /*
    et comme ça
    public void suppDiscipline(Competence competence) {
        competences.remove(competence);
     */
    /**
     * retourne l'id de l'employé
     * @return id de l'employé
     */
    public int getId_emplpoye() {
        return id_emplpoye;
    }
    /**
     * modifie l'id de l'employé
     * @param id_emplpoye id de l'employé
     */
    public void setId_emplpoye(int id_emplpoye) {
        this.id_emplpoye = id_emplpoye;
    }
    /**
     * retourne le matricule de l'employé
     * @return matricule de l'employé
     */
    public String getMatricule() {
        return matricule;
    }
    /**
     * modifie le matricule de l'employé
     * @param matricule matricule de l'employé
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    /**
     * retourne le nom de l'employé
     * @return nom de l'employé
     */
    public String getNom() {
        return nom;
    }
    /**
     * modifie le nom de l'employé
     * @param nom nom de l'employé
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * retourne le prénom de l'employé
     * @return prénom de l'employé
     */
    public String getPrenom() {
        return prenom;
    }
    /**
     * modifie le prénom de l'employé
     * @param prenom prénom de l'employé
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    /**
     * retourne l'adresse de l'employé
     * @return adresse de l'employé
     */
    public String getTel() {
        return tel;
    }
    /**
     * modifie l'adresse de l'employé
     * @param tel adresse de l'employé
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    /**
     * retourne le mail de l'employé
     * @return mail de l'employé
     */
    public String getMail() {
        return mail;
    }
    /**
     * modifie le mail de l'employé
     * @param mail mail de l'employé
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    /**
     * retourne la liste des projets
     * @return liste des projets
     */
    public List<Projet> getProjets() {
        return projets;
    }
    /**
     * modifie la liste des projets
     * @param projets liste des projets
     */
    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }
    /**
     * retourne la liste des disciplines et le niveau de l'employé
     * @return liste des disciplines et le niveau de l'employé
     */
    public List<Competence> getCompetences() {
        return competences;
    }
    /**
     * modifie la liste des disciplines et le niveau de l'employé
     * @param competences liste des disciplines et le niveau de l'employé
     */
    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id_emplpoye=" + id_emplpoye +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                ", projets=" + projets +
                ", competences=" + competences +
                '}';
    }
}

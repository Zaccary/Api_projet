package designPatterns.composite;

import entreprise.gestionProjet.Competence;
import entreprise.gestionProjet.Projet;

import java.util.ArrayList;
import java.util.List;

public class Employe {
    protected int id;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected String tel;
    protected String mail;
    protected List<Projet> projets = new ArrayList<>();
    protected List<Competence> competences = new ArrayList<>();

    public Employe(int id, String matricule, String nom, String prenom, String tel, String mail) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
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

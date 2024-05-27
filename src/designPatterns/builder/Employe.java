package designPatterns.eb;

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

    private Employe(EmployeBuilder eb) {
        this.id = eb.id;
        this.matricule = eb.matricule;
        this.nom = eb.nom;
        this.prenom = eb.prenom;
        this.tel = eb.tel;
        this.mail = eb.mail;
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

    public static class EmployeBuilder {
        private int id;
        private String matricule;
        private String nom;
        private String prenom;
        private String tel;
        private String mail;
        
            public EmployeBuilder setId(int id) {
                this.id = id;
                return this;
            }

            public EmployeBuilder setMatricule(String matricule) {
                this.matricule = matricule;
                return this;
            }

            public EmployeBuilder setNom(String nom) {
                this.nom = nom;
                return this;
            }

            public EmployeBuilder setPrenom(String prenom) {
                this.prenom = prenom;
                return this;
            }

            public EmployeBuilder setTel(String tel) {
                this.tel = tel;
                return this;
            }

            public EmployeBuilder setMail(String mail) {
                this.mail = mail;
                return this;
            }

        public Employe build() throws Exception {
            if(this.matricule == null || this.nom == null || this.prenom == null)
                throw new Exception("données obligatoires manquantes");
            return new Employe(this);
        }

    }
}



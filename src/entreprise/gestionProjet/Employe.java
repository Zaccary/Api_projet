package entreprise.gestionProjet;

import java.util.ArrayList;
import java.util.List;

public class Employe {

    protected int id_emplpoye;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected String tel;
    protected String mail;
    protected List<Projet> projets=new ArrayList<>();
    protected List<Competence> competences=new ArrayList<>();
    public Employe() {
    }
    public void listeDisciplinesEtNiveau() {
        for (Competence competence : competences) {
            System.out.println(competence.discipline.nom + " : " + competence.niveau);
        }
    }
    public void addDiscipline(Disciplines discipline, int niveau) {
        Competence temp = new Competence();
        temp.discipline = discipline;
        temp.niveau = niveau;
        competences.add(temp);
    }
    public void modifDiscipline(Disciplines discipline, int niveau) {
        competences.get(competences.indexOf(discipline)).niveau = niveau;
    }
    public void suppDiscipline(Disciplines discipline) {
        competences.remove(discipline);
    }
}

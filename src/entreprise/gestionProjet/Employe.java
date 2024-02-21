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
    public List<Competence> listeDisciplinesEtNiveau() {
        return competences;
    }
    public void addDiscipline(Disciplines discipline, int niveau) {
        Competence temp = new Competence(discipline, niveau);
        competences.add(temp);
    }
    public void modifDiscipline(Disciplines discipline, int niveau) {
        for (Competence competence : competences) {
            if(competences.get(competences.indexOf(competence)).discipline.equals(discipline)) {
                competences.get(competences.indexOf(competence)).niveau = niveau;
            }
        }
    }
    public void suppDiscipline(Disciplines discipline) {
        competences.remove(discipline);
    }
}

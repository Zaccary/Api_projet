package mvc.model;
import entreprise.gestionProjet.Competence;
import entreprise.gestionProjet.Disciplines;
import entreprise.gestionProjet.Employe;
import entreprise.gestionProjet.Projet;
import mvc.observer.Subject;

import java.util.List;

public abstract class  DAOEmploye extends Subject {
    public abstract Employe addEmploye(Employe employe);

    public abstract boolean removeEmploye(Employe Employe);

    public abstract Employe updateEmploye(Employe Employe);

    public abstract Employe readEmploye(int idEmploye);

    public abstract List<Employe> getEmployes();

    public abstract List<Competence> listeDisciplinesEtNiveau(Employe employe);

    public abstract boolean addDisciplines(Employe employe,Disciplines discipline, int niveau);

    public abstract boolean modifDiscipline(Employe employe,Disciplines discipline, int niveau);

    public abstract boolean suppDiscipline(Employe employe,Disciplines discipline);

    public abstract List<Projet> listeProjets(Employe employe);
    CompetenceModelDB pm3 = new CompetenceModelDB();
}

package mvc.model;
import entreprise.gestionProjet.Competence;
import mvc.observer.Subject;

import java.util.List;

public abstract class  DAOCompetence extends Subject {
    public abstract Competence addCompetence(Competence Competence, int id_Employe);

    public abstract boolean removeCompetence(Competence Competence);

    public abstract Competence updateCompetence(Competence Competence, int id_Employe);

    public abstract Competence readCompetence(int idCompetence);

    public abstract List<Competence> getCompetences();
}

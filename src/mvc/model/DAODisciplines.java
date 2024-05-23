package mvc.model;
import entreprise.gestionProjet.Disciplines;
import mvc.observer.Subject;

import java.util.List;

public abstract class  DAODisciplines extends Subject {
    public abstract Disciplines addDisciplines(Disciplines Disciplines);

    public abstract boolean removeDisciplines(Disciplines Disciplines);

    public abstract Disciplines updateDisciplines(Disciplines Disciplines);

    public abstract Disciplines readDisciplines(int idDisciplines);

    public abstract List<Disciplines> getDiscipliness();
}

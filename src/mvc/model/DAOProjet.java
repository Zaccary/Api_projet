package mvc.model;
import entreprise.gestionProjet.*;
import entreprise.gestionProjet.Projet;
import mvc.observer.Subject;

import java.util.List;

public abstract class  DAOProjet extends Subject {
    public abstract Projet addProjet(Projet Projet);

    public abstract boolean removeProjet(Projet Projet);

    public abstract Projet updateProjet(Projet Projet);

    public abstract Projet readProjet(int idProjet);

    public abstract List<Projet> getProjets();

    public abstract List<Investissement> listeDisciplinesEtInvestissement(Projet projet);

    public abstract boolean addDisciplines(Projet projet, Disciplines discipline, int niveau);

    public abstract boolean modifDiscipline(Projet projet,Disciplines discipline, int niveau);

    public abstract boolean suppDiscipline(Projet projet,Disciplines discipline);

    public abstract List<Competence> niveauxResponsableDisciplines(Projet projet);
    InvestissementModelDB pm4 = new InvestissementModelDB();
}

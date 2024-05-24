package mvc.model;
import entreprise.gestionProjet.Investissement;
import mvc.observer.Subject;

import java.util.List;

public abstract class  DAOInvestissement extends Subject {
    public abstract Investissement addInvestissement(Investissement Investissement, int id_projet);

    public abstract boolean removeInvestissement(Investissement Investissement);

    public abstract Investissement updateInvestissement(Investissement Investissement, int id_projet);

    public abstract Investissement readInvestissement(int idInvestissement);

    public abstract List<Investissement> getInvestissements();
}

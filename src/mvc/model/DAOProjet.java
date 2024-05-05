package mvc.model;
import entreprise.gestionProjet.Projet;
import mvc.observer.Subject;

import java.util.List;

public abstract class  DAOProjet extends Subject {
    public abstract Projet addProjet(Projet Projet);

    public abstract boolean removeProjet(Projet Projet);

    public abstract Projet updateProjet(Projet Projet);

    public abstract Projet readProjet(int idProjet);

    public abstract List<Projet> getProjets();
}

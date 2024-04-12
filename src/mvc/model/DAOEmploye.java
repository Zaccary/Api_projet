package mvc.model;
import entreprise.gestionProjet.Employe;
import mvc.observer.Subject;

import java.util.List;

public abstract class  DAOEmploye extends Subject {
    public abstract Employe addEmploye(Employe employe);

    public abstract boolean removeEmploye(Employe Employe);

    public abstract Employe updateEmploye(Employe Employe);

    public abstract Employe readEmploye(int idEmploye);

    public abstract List<Employe> getEmployes();
}

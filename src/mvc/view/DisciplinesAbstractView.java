package mvc.view;
import entreprise.gestionProjet.Disciplines;
import mvc.controller.DisciplinesController;
import mvc.observer.Observer;

import java.util.List;

public abstract class DisciplinesAbstractView implements Observer {

    protected DisciplinesController DisciplinesController;
    protected List<Disciplines> lp;

    public void  setController(DisciplinesController DisciplinesController){
        this.DisciplinesController=DisciplinesController;
    }
    public abstract void affMsg(String msg);

    public abstract Disciplines selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lp) {
        this.lp = lp;
    }

}

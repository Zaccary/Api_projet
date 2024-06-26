package mvc.view;
import entreprise.gestionProjet.Projet;
import mvc.controller.ProjetController;
import mvc.observer.Observer;

import java.util.List;

public abstract class ProjetAbstractView implements Observer {

    protected ProjetController ProjetController;
    protected List<Projet> lp;
    protected EmployeAbstractView pv;
    protected DisciplinesAbstractView pv2;

    public void  setController(ProjetController ProjetController){
        this.ProjetController=ProjetController;
    }
    public void setEmployeView(EmployeAbstractView pv){
        this.pv=pv;
    }
    public  void setDisciplineView(DisciplinesAbstractView pv2){
        this.pv2=pv2;
    }
    public abstract void affMsg(String msg);

    public abstract Projet getWithDiscipline(int id_investissement);

    public abstract Projet selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lp) {
        this.lp = lp;
    }

}

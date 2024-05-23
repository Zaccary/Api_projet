package mvc.view;
import entreprise.gestionProjet.Competence;
import mvc.controller.CompetenceController;
import mvc.observer.Observer;


import java.util.List;

public abstract class CompetenceAbstractView implements Observer {

    protected CompetenceController CompetenceController;
    protected List<Competence> lp;
    protected EmployeAbstractView pv;
    protected DisciplinesAbstractView pv2;

    public void  setController(CompetenceController CompetenceController){
        this.CompetenceController=CompetenceController;
    }
    public void setEmployeView(EmployeAbstractView pv){
        this.pv=pv;
    }
    public void setDisciplinesView(DisciplinesAbstractView pv2){
        this.pv2=pv2;
    }
    public abstract void affMsg(String msg);

    public abstract Competence selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lp) {
        this.lp = lp;
    }

}

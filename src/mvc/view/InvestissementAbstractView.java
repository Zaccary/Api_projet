package mvc.view;
import entreprise.gestionProjet.Competence;
import entreprise.gestionProjet.Investissement;
import mvc.controller.InvestissementController;
import mvc.observer.Observer;

import java.util.List;

public abstract class InvestissementAbstractView implements Observer {

    protected InvestissementController InvestissementController;
    protected List<Investissement> lp;
    protected ProjetAbstractView pv1;
    protected DisciplinesAbstractView pv2;

    public void  setController(InvestissementController InvestissementController){
        this.InvestissementController=InvestissementController;
    }
    public void setProjetView(ProjetAbstractView pv1){
        this.pv1=pv1;
    }
    public void setDisciplinesView(DisciplinesAbstractView pv2){
        this.pv2=pv2;
    }
    public abstract void affMsg(String msg);

    public abstract Investissement selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lp) {
        this.lp = lp;
    }

}

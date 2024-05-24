package mvc.controller;

import entreprise.gestionProjet.Investissement;
import mvc.model.DAOInvestissement;
import mvc.view.InvestissementAbstractView;


import java.util.List;

public class InvestissementController {
    private DAOInvestissement model;
    private InvestissementAbstractView view;

    public InvestissementController(DAOInvestissement model, InvestissementAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }
    public List<Investissement> getAll(){
        return model.getInvestissements();
    }
    public Investissement addInvestissement(Investissement Investissement, int id_projet) {
        return  model.addInvestissement(Investissement, id_projet);
    }

    public boolean removeInvestissement(Investissement Investissement) {
        return model.removeInvestissement(Investissement);
    }

    public Investissement updateInvestissement(Investissement Investissement, int id_projet) {
        return model.updateInvestissement(Investissement, id_projet);
    }

    public Investissement search(int idInvestissement) {
        return model.readInvestissement(idInvestissement);
    }
}


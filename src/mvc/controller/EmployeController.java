package mvc.controller;

import entreprise.gestionProjet.Employe;
import mvc.model.DAOEmploye;
import mvc.view.EmployeAbstractView;


import java.util.List;

public class EmployeController {
    private DAOEmploye model;
    private EmployeAbstractView view;

    public EmployeController(DAOEmploye model, EmployeAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }
    public List<Employe> getAll(){
        return model.getEmployes();
    }
    public Employe addEmploye(Employe Employe) {
        return  model.addEmploye(Employe);
    }

    public boolean removeEmploye(Employe Employe) {
        return model.removeEmploye(Employe);
    }

    public Employe updateEmploye(Employe Employe) {
        return model.updateEmploye(Employe);
    }

    public Employe search(int idEmploye) {
        return model.readEmploye(idEmploye);
    }
}


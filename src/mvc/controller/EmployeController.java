package mvc.controller;

import entreprise.gestionProjet.Competence;
import entreprise.gestionProjet.Disciplines;
import entreprise.gestionProjet.Employe;
import entreprise.gestionProjet.Projet;
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

    public List<Competence> listeDisciplinesEtNiveau(Employe employe) {
        return model.listeDisciplinesEtNiveau(employe);
    }

    public boolean addDisciplines(Employe employe,Disciplines discipline, int niveau) {
        return model.addDisciplines(employe,discipline, niveau);
    }

    public boolean modifDiscipline(Employe employe,Disciplines discipline, int niveau) {
        return model.modifDiscipline(employe,discipline, niveau);
    }

    public boolean suppDiscipline(Employe employe,Disciplines discipline) {
        return model.suppDiscipline(employe,discipline);
    }

    public List<Projet> listeProjets(Employe employe) {
        return model.listeProjets(employe);
    }

}


package mvc.controller;

import entreprise.gestionProjet.Disciplines;
import mvc.model.DAODisciplines;
import mvc.view.DisciplinesAbstractView;


import java.util.List;

public class DisciplinesController {
    private DAODisciplines model;
    private DisciplinesAbstractView view;

    public DisciplinesController(DAODisciplines model, DisciplinesAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }
    public List<Disciplines> getAll(){
        return model.getDiscipliness();
    }
    public Disciplines addDisciplines(Disciplines Disciplines) {
        return  model.addDisciplines(Disciplines);
    }

    public boolean removeDisciplines(Disciplines Disciplines) {
        return model.removeDisciplines(Disciplines);
    }

    public Disciplines updateDisciplines(Disciplines Disciplines) {
        return model.updateDisciplines(Disciplines);
    }

    public Disciplines search(int idDisciplines) {
        return model.readDisciplines(idDisciplines);
    }
}


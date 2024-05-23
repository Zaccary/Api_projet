
package mvc.controller;

import entreprise.gestionProjet.Competence;
import entreprise.gestionProjet.Competence;
import mvc.model.DAOCompetence;
import mvc.view.CompetenceAbstractView;

import java.util.List;

public class CompetenceController {
    private DAOCompetence model;
    private CompetenceAbstractView view;

    public CompetenceController(DAOCompetence model, CompetenceAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }
    public List<Competence> getAll(){
        return model.getCompetences();
    }
    public Competence addCompetence(Competence Competence, int id_Employe) {
        return  model.addCompetence(Competence, id_Employe);
    }

    public boolean removeCompetence(Competence Competence) {
        return model.removeCompetence(Competence);
    }

    public Competence updateCompetence(Competence Competence, int id_Employe) {
        return model.updateCompetence(Competence, id_Employe);
    }

    public Competence search(int idCompetence) {
        return model.readCompetence(idCompetence);
    }
}


package mvc.controller;

import entreprise.gestionProjet.Projet;
import entreprise.gestionProjet.Projet;
import mvc.model.DAOProjet;
import mvc.view.ProjetAbstractView;

import java.util.List;

public class ProjetController {
    private DAOProjet model;
    private ProjetAbstractView view;

    public ProjetController(DAOProjet model, ProjetAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }
    public List<Projet> getAll(){
        return model.getProjets();
    }
    public Projet addProjet(Projet Projet) {
        return  model.addProjet(Projet);
    }

    public boolean removeProjet(Projet Projet) {
        return model.removeProjet(Projet);
    }

    public Projet updateProjet(Projet Projet) {
        return model.updateProjet(Projet);
    }

    public Projet search(int idProjet) {
        return model.readProjet(idProjet);
    }
}

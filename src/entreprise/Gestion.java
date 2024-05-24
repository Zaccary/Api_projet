package entreprise;

import entreprise.gestionProjet.Disciplines;
import entreprise.gestionProjet.Employe;
import entreprise.gestionProjet.Investissement;
import entreprise.gestionProjet.Projet;

public class Gestion {
    private Projet projet1;
    private Employe employe1 = new Employe();
    public Gestion() {
        projet1 = new Projet("Projet1", employe1);
        creerDeBase();
        Disciplines discipline1 = new Disciplines("Discipline1");
        Investissement investissement1 = new Investissement(0,discipline1, 10);
        System.out.println(projet1.getInvestissements());
        projet1.modifDiscipline(discipline1, 9);
        System.out.println(projet1.getInvestissements());
        employe1.modifDiscipline(discipline1, 15);
        projet1.niveauResponsableDiscipline();
        System.out.println(projet1.niveauResponsableDiscipline());
        System.out.println(projet1.investissementsTotal());
    }
    public void creerDeBase(){
        Disciplines discipline1 = new Disciplines("Discipline1");
        Disciplines discipline2 = new Disciplines("Discipline2");
        Disciplines discipline3 = new Disciplines("Discipline3");
        Disciplines discipline4 = new Disciplines("Discipline4");
        Disciplines discipline5 = new Disciplines("Discipline5");
        Investissement investissement2 = new Investissement(0,discipline2, 20);
        Investissement investissement3 = new Investissement(0,discipline3, 30);
        Investissement investissement4 = new Investissement(0,discipline4, 40);
        Investissement investissement5 = new Investissement(0,discipline5, 50);
        projet1.addDiscipline(0,discipline1, 10);
        projet1.addDiscipline(0,discipline2, 20);
        projet1.addDiscipline(0,discipline3, 30);
        projet1.addDiscipline(0,discipline4, 40);
        projet1.addDiscipline(0,discipline5, 50);
        employe1.addDiscipline(1,discipline1, 16);
        employe1.addDiscipline(2,discipline2, 21);
        employe1.addDiscipline(3,discipline3, 32);
    }
}

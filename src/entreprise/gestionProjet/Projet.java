package entreprise.gestionProjet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Projet {
    protected int id_projet;
    protected String nom;
    protected LocalDate dateDebut;
    protected LocalDate dateFin;
    protected BigDecimal cout;
    protected Employe chefProjet;
    protected List<Investissement> investissements=new ArrayList<>();
    public Projet() {
    }
    public List<Investissement> listeDisciplinesEtInvestissement() {
        for (Investissement investissement : investissements) {
            System.out.println(investissement.discipline.nom + " : " + investissement.quantiteJH);
        }
        return investissements;
    }
    public void investissementsTotal() {
        int total = 0;
        for (Investissement investissement : investissements) {
            total += investissement.quantiteJH;
        }
        System.out.println("Total : " + total);
    }
    public void addDiscipline(Disciplines discipline, int quantite){
        Investissement temp = new Investissement();
        temp.discipline = discipline;
        temp.quantiteJH = quantite;
        investissements.add(temp);
    }

    public void modifDiscipline(Disciplines discipline, int quantite) {
        investissements.get(investissements.indexOf(discipline)).quantiteJH = quantite;
    }
    public void suppDiscipline(Disciplines discipline) {
        investissements.remove(discipline);
    }
    public void niveauResponsableDiscipline() {
    }

}

package mvc.view;

import entreprise.gestionProjet.*;
import entreprise.gestionProjet.Investissement;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class InvestissementViewConsole extends InvestissementAbstractView {
    private Scanner sc = new Scanner(System.in);


    @Override
    public void affMsg(String msg) {
        System.out.println("information:"+msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }


    public void menu(){
        update(InvestissementController.getAll());
        affList(lp);
        do{
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));

            switch(ch){
                case 1: ajouter();
                    break;
                case 2 : retirer();
                    break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5 : return;
            }
        }while(true);
    }
    private void modifier() {
        int nl = choixElt(lp);
        Investissement pr = lp.get(nl-1);
        int QuantiteJH = Integer.parseInt(modifyIfNotBlank("QuantiteJH de Investissement",""+pr.getQuantiteJH()));
        System.out.println("Discipline : "+pr.getDiscipline());
        Disciplines discipline = pv2.selectionner();
        Projet Projet2 = pv1.getWithDiscipline(pr.getId_investissement());
        System.out.println("Projet : "+Projet2);
        Projet Projet = pv1.selectionner();
        Investissement prmaj =  InvestissementController.updateInvestissement(new Investissement(pr.getId_investissement(),discipline,QuantiteJH),Projet.getId_projet());
        //voir commentaire des fonctions addDiscipline et suppDiscipline dans Projet.java
        Projet2.suppDiscipline(pr.getDiscipline());
        Projet.addDiscipline(prmaj.getId_investissement(),prmaj.getDiscipline(),prmaj.getQuantiteJH());
        if(prmaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+prmaj);
    }

    private void rechercher() {
        System.out.println("idInvestissement : ");
        int idInvestissement = sc.nextInt();
        InvestissementController.search(idInvestissement);
        System.out.println("Investissement trouvé : "+InvestissementController.search(idInvestissement));
    }

    private void retirer() {

        int nl = choixElt(lp);
        Investissement pr = lp.get(nl-1);
        boolean ok = InvestissementController.removeInvestissement(pr);
        if(ok) affMsg("Investissement effacé");
        else affMsg("Investissement non effacé");
    }
    private void ajouter() {
        System.out.print("QuantiteJH :");
        int QuantiteJH = sc.nextInt();
        System.out.print("Discipline :");
        Disciplines discipline = pv2.selectionner();
        System.out.println("Projet :");
        Projet projet = pv1.selectionner();
        Investissement pr = InvestissementController.addInvestissement(new Investissement(0,discipline,QuantiteJH),projet.getId_projet() ) ;
        projet.addDiscipline(pr.getId_investissement(),pr.getDiscipline(),pr.getQuantiteJH());
        if(pr!=null) affMsg("création de :"+pr);
        else affMsg("erreur de création");
    }

    @Override
    public Investissement selectionner(){
        update(InvestissementController.getAll());
        System.out.println("liste des Investissements");
        int nl =  choixListe(lp);
        Investissement pr = lp.get(nl-1);
        return pr;
    }
}


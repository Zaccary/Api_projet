package mvc.view;

import entreprise.gestionProjet.Competence;
import entreprise.gestionProjet.Employe;
import entreprise.gestionProjet.Projet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import entreprise.gestionProjet.Disciplines;
import mvc.controller.EmployeController;

import static utilitaires.Utilitaire.*;

public class CompetenceViewConsole extends CompetenceAbstractView {
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
        update(CompetenceController.getAll());
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
        Competence pr = lp.get(nl-1);
        int niveau = Integer.parseInt(modifyIfNotBlank("niveau de Competence",""+pr.getNiveau()));
        System.out.println("Discipline : "+pr.getDiscipline());
        Disciplines discipline = pv2.selectionner();
        Employe employe2 = pv.getWithDiscipline(pr.getId_competence());
        System.out.println("Employe : "+employe2);
        Employe employe = pv.selectionner();
        Competence prmaj =  CompetenceController.updateCompetence(new Competence(pr.getId_competence(),discipline,niveau),employe.getId_emplpoye());
        //voir commentaire des fonctions addDiscipline et suppDiscipline dans Employe.java
        employe2.suppDiscipline(pr.getDiscipline());
        employe.addDiscipline(prmaj.getId_competence(),prmaj.getDiscipline(),prmaj.getNiveau());
        if(prmaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+prmaj);
    }

    private void rechercher() {
        System.out.println("idCompetence : ");
        int idCompetence = sc.nextInt();
        CompetenceController.search(idCompetence);
        System.out.println("Competence trouvé : "+CompetenceController.search(idCompetence));
    }

    private void retirer() {

        int nl = choixElt(lp);
        Competence pr = lp.get(nl-1);
        boolean ok = CompetenceController.removeCompetence(pr);
        if(ok) affMsg("Competence effacé");
        else affMsg("Competence non effacé");
    }

    private void ajouter() {
        System.out.print("niveau :");
        int niveau = sc.nextInt();
        System.out.print("Discipline :");
        Disciplines discipline = pv2.selectionner();
        System.out.println("Employe :");
        Employe employe = pv.selectionner();
        Competence pr = CompetenceController.addCompetence(new Competence(0,discipline,niveau),employe.getId_emplpoye() ) ;
        employe.addDiscipline(pr.getId_competence(),pr.getDiscipline(),pr.getNiveau());
        if(pr!=null) affMsg("création de :"+pr);
        else affMsg("erreur de création");
    }

    @Override
    public Competence selectionner(){
        update(CompetenceController.getAll());
        System.out.println("liste des Competences");
        int nl =  choixListe(lp);
        Competence pr = lp.get(nl-1);
        return pr;
    }
}


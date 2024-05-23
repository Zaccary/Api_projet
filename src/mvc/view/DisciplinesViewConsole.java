package mvc.view;

import entreprise.gestionProjet.Disciplines;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class DisciplinesViewConsole extends DisciplinesAbstractView {
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
        update(DisciplinesController.getAll());
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

        Disciplines pr = lp.get(nl-1);
        String nom = modifyIfNotBlank("nom de Disciplines",pr.getNom());
        String Description = modifyIfNotBlank("Description de Disciplines",pr.getDescription());
        Disciplines prmaj =  DisciplinesController.updateDisciplines(new Disciplines(pr.getId_discipline(),nom,Description));
        if(prmaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+prmaj);
    }

    private void rechercher() {
        System.out.println("idDisciplines : ");
        int idDisciplines = sc.nextInt();
        DisciplinesController.search(idDisciplines);
        System.out.println("Disciplines trouvé : "+DisciplinesController.search(idDisciplines));
    }

    private void retirer() {

        int nl = choixElt(lp);
        Disciplines pr = lp.get(nl-1);
        boolean ok = DisciplinesController.removeDisciplines(pr);
        if(ok) affMsg("Disciplines effacé");
        else affMsg("Disciplines non effacé");
    }

    private void ajouter() {
        System.out.print("nom :");
        String nom = sc.nextLine();
        System.out.println("Description :");
        String Description = sc.nextLine();
        Disciplines pr = DisciplinesController.addDisciplines(new Disciplines(0,nom,Description) ) ;
        if(pr!=null) affMsg("création de :"+pr);
        else affMsg("erreur de création");
    }

    @Override
    public Disciplines selectionner(){
        update(DisciplinesController.getAll());
        System.out.println("liste des Discipliness");
        int nl =  choixListe(lp);
        Disciplines pr = lp.get(nl-1);
        return pr;
    }
}


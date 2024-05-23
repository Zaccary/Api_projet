package mvc.view;

import entreprise.gestionProjet.Competence;
import entreprise.gestionProjet.Employe;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class EmployeViewConsole extends EmployeAbstractView {
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
        update(EmployeController.getAll());
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

        Employe pr = lp.get(nl-1);
        String matricule = modifyIfNotBlank("Matricule de Employe",pr.getMatricule());
        String nom = modifyIfNotBlank("nom de Employe",pr.getNom());
        String prenom = modifyIfNotBlank("nom de Employe",pr.getPrenom());
        String tel = modifyIfNotBlank("tel de Employe",pr.getTel());
        String mail = modifyIfNotBlank("mail de Employe",pr.getMail());
        Employe prmaj =  EmployeController.updateEmploye(new Employe(pr.getId_emplpoye(),matricule,nom,prenom,tel,mail));
        if(prmaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+prmaj);
    }

    private void rechercher() {
        System.out.println("idEmploye : ");
        int idEmploye = sc.nextInt();
        EmployeController.search(idEmploye);
        System.out.println("Employe trouvé : "+EmployeController.search(idEmploye));
    }

    private void retirer() {

        int nl = choixElt(lp);
        Employe pr = lp.get(nl-1);
        boolean ok = EmployeController.removeEmploye(pr);
        if(ok) affMsg("Employe effacé");
        else affMsg("Employe non effacé");
    }

    private void ajouter() {
        System.out.print("matricule :");
        String matricule = sc.nextLine();
        System.out.print("nom :");
        String nom = sc.nextLine();
        System.out.print("prénom :");
        String prenom = sc.nextLine();
        System.out.print("tel :");
        String tel = sc.nextLine();
        System.out.print("email :");
        String mail = sc.nextLine();
        Employe pr = EmployeController.addEmploye(new Employe(0,matricule,nom,prenom,tel,mail)) ;
        if(pr!=null) affMsg("création de :"+pr);
        else affMsg("erreur de création");
    }

    @Override
    public Employe getWithDiscipline(int id_Competence) {
        update(EmployeController.getAll());
        for (Employe pr : lp) {
            List<Competence> comp = pr.getCompetences();
              for (Competence c : comp) {
                    if (c.getId_competence() == id_Competence) {
                        return pr;
                    }
              }
        }
        return null;
    }

    @Override
    public Employe selectionner(){
        update(EmployeController.getAll());
        System.out.println("liste des Employes");
        int nl =  choixListe(lp);
        Employe pr = lp.get(nl-1);
        return pr;
    }
}


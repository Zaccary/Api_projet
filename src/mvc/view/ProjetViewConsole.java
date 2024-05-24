package mvc.view;

import entreprise.gestionProjet.Investissement;
import entreprise.gestionProjet.Employe;
import entreprise.gestionProjet.Projet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class ProjetViewConsole extends ProjetAbstractView {
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
        update(ProjetController.getAll());
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
        Projet pr = lp.get(nl-1);
        String nom = modifyIfNotBlank("nom du projet",pr.getNom());
        LocalDate datedebut = getDate(modifyIfNotBlank("date de debut du projet",getDateFrench(pr.getDateDebut())));
        LocalDate datefin = getDate(modifyIfNotBlank("date de fin du projet",getDateFrench(pr.getDateFin())));
        BigDecimal cout = BigDecimal.valueOf(Long.parseLong(modifyIfNotBlank("cout du projet",""+pr.getCout())));
        System.out.println("chef de projet : "+pr.getChefProjet());
        Employe chefprojet = pv.selectionner();
        Projet prmaj =  ProjetController.updateProjet(new Projet(pr.getId_projet(),nom,datedebut,datefin,cout,chefprojet));
        if(prmaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+prmaj);
    }

    private void rechercher() {
        System.out.println("idProjet : ");
        int idProjet = sc.nextInt();
        ProjetController.search(idProjet);
        System.out.println("Projet trouvé : "+ProjetController.search(idProjet));
    }

    private void retirer() {

        int nl = choixElt(lp);
        Projet pr = lp.get(nl-1);
        boolean ok = ProjetController.removeProjet(pr);
        if(ok) affMsg("Projet effacé");
        else affMsg("Projet non effacé");
    }

    private void ajouter() {
        System.out.print("nom :");
        String nom = sc.nextLine();
        System.out.print("Date Debut :");
        LocalDate datedebut = lecDate();
        System.out.println("Date Fin :");
        LocalDate datefin = lecDate();
        System.out.println("cout :");
        BigDecimal cout = new BigDecimal(sc.nextLine());
        Employe chefprojet = pv.selectionner();
        Projet pr = ProjetController.addProjet(new Projet(0,nom,datedebut,datefin,cout,chefprojet)) ;
        if(pr!=null) affMsg("création de :"+pr);
        else affMsg("erreur de création");
    }
    @Override
    public Projet getWithDiscipline(int id_investissement) {
        update(ProjetController.getAll());
        for (Projet pr : lp) {
            List<Investissement> comp = pr.getInvestissements();
            for (Investissement c : comp) {
                if (c.getId_investissement() == id_investissement) {
                    return pr;
                }
            }
        }
        return null;
    }

    @Override
    public Projet selectionner(){
        update(ProjetController.getAll());
        int nl =  choixListe(lp);
        Projet pr = lp.get(nl-1);
        return pr;
    }
}


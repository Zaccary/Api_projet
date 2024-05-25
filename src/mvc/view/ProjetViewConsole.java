package mvc.view;

import entreprise.gestionProjet.*;

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
    private void special(Projet pr) {

        do {
            int ch = choixListe(Arrays.asList("liste des disciplines et investissements", "ajouter discipline", "modifier discipline", "supprimer discipline", "liste des niveaux responsables des disciplines", "menu principal"));
            switch (ch) {
                case 1:
                    listeDisciplineEtInvestissement(pr);
                    break;

                case 2:
                    ajouterDiscipline(pr);
                    break;

                case 3:
                    modifierDiscipline(pr);
                    break;

                case 4:
                    supprimerDiscipline(pr);
                    break;

                case 5:
                    niveauxResponsablesDisciplines(pr);
                    break;

                case 6:
                    return;
            }
            ;
        } while (true);
    }
    public void listeDisciplineEtInvestissement(Projet pr) {
        List<Investissement> comp= ProjetController.listeDisciplinesEtInvestissement(pr);
        if(comp.isEmpty()) affMsg("aucune discipline trouvée");
        else affList(comp);
    }
    public void ajouterDiscipline(Projet pr) {
        Disciplines di = pv2.selectionner();
        System.out.println("niveau ?");
        int niv = sc.nextInt();
        boolean ok = ProjetController.addDisciplines(pr, di, niv);
        if (ok) affMsg("Discipline ajoutée");
        else affMsg("Erreur lors de l'ajout de la discipline");

    }

    public void supprimerDiscipline(Projet pr) {
        Disciplines di = pv2.selectionner();
        boolean ok = ProjetController.suppDiscipline(pr, di);
        if (ok) affMsg("Discipline supprimée");
        else affMsg("Erreur lors de la suppression de la discipline");

    }

    public void modifierDiscipline(Projet pr) {
        Disciplines di = pv2.selectionner();
        System.out.println("niveau ?");
        int niv = sc.nextInt();
        boolean ok = ProjetController.modifDiscipline(pr, di, niv);
        if (ok) affMsg("Discipline modifiée");
        else affMsg("Erreur lors de la modification de la discipline");

    }

    public void niveauxResponsablesDisciplines(Projet pr) {
        List<Competence> lp = ProjetController.niveauxResponsableDisciplines(pr);
        if (lp.isEmpty()) affMsg("aucune competence trouvé");
        else affList(lp);
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
        Projet pr=ProjetController.search(idProjet);
        if (pr!=null){
            System.out.println("Projet trouvé : "+pr);
            special(pr);
        }
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


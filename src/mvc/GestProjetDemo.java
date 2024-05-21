package mvc;

import mvc.controller.EmployeController;
import mvc.model.*;
import mvc.view.EmployeAbstractView;
import mvc.view.EmployeViewConsole;
import mvc.view.ProjetViewConsole;
import utilitaires.Utilitaire;
import mvc.controller.ProjetController;
import mvc.model.DAOProjet;
import mvc.view.ProjetAbstractView;

import java.util.Arrays;
import java.util.List;

public class GestProjetDemo {
    //utilisation du nom de variable pm,pv,pc n'est pas le plus judicieux, si le temps changer mettre première lettre de la classe d'origine puis le nom de la classe spécifique ex: employeModel->em et employeView->ev
    private DAOEmploye pm;
    private EmployeController pc;
    private EmployeAbstractView pv;
    private DAOProjet pm1;
    private ProjetController pc1;
    private ProjetAbstractView pv1;
    public void gestion(){
        pm=new EmployeModelDB();
        pv =  new EmployeViewConsole();
        pc= new EmployeController(pm,pv);
        pm1=new ProjetModelDB();
        pv1 =  new ProjetViewConsole();
        pc1= new ProjetController(pm1,pv1);
        pv1.setEmployeView(pv);
        pm.addObserver(pv);
        List<String> loptions = Arrays.asList("Employes","Projet","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: pv.menu();
                    break;
                 case 2: pv1.menu();
                    break;
                case 3: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
        GestProjetDemo gm = new GestProjetDemo();
        gm.gestion();
    }
}

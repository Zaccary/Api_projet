package mvc;

import mvc.controller.EmployeController;
import mvc.model.*;
import mvc.view.EmployeAbstractView;
import mvc.view.EmployeViewConsole;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestProjetDemo {
    private DAOEmploye pm;
    private EmployeController pc;
    private EmployeAbstractView pv;
    public void gestion(){
        pm=new EmployeModelDB();
        pv =  new EmployeViewConsole();
        pc= new EmployeController(pm,pv);
        pm.addObserver(pv);
        List<String> loptions = Arrays.asList("Employes","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: pv.menu();
                    break;
                case 2: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
        GestProjetDemo gm = new GestProjetDemo();
        gm.gestion();
    }
}

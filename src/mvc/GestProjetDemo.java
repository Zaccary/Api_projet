package mvc;

import mvc.controller.EmployeController;
import mvc.model.*;
import mvc.view.*;
import utilitaires.Utilitaire;
import mvc.controller.ProjetController;
import mvc.model.DAOProjet;
import mvc.controller.DisciplinesController;
import mvc.model.DAODisciplines;
import mvc.controller.CompetenceController;
import mvc.model.DAOCompetence;

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
    private DAODisciplines pm2;
    private DisciplinesController pc2;
    private DisciplinesAbstractView pv2;
    private DAOCompetence pm3;
    private CompetenceController pc3;
    private CompetenceAbstractView pv3;
    public void gestion(){
        pm=new EmployeModelDB();
        pv =  new EmployeViewConsole();
        pc= new EmployeController(pm,pv);
        pm1=new ProjetModelDB();
        pv1 =  new ProjetViewConsole();
        pc1= new ProjetController(pm1,pv1);
        pm2=new DisciplinesModelDB();
        pv2 =  new DisciplinesViewConsole();
        pc2= new DisciplinesController(pm2,pv2);
        pm3=new CompetenceModelDB();
        pv3 =  new CompetenceViewConsole();
        pc3= new CompetenceController(pm3,pv3);
        pv1.setEmployeView(pv);
        pv3.setEmployeView(pv);
        pv3.setDisciplinesView(pv2);
        pm.addObserver(pv);
        pm1.addObserver(pv1);
        pm2.addObserver(pv2);
        pm3.addObserver(pv3);
        List<String> loptions = Arrays.asList("Employes","Projet","Disciplines","Competence","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: pv.menu();
                    break;
                 case 2: pv1.menu();
                    break;
                case 3: pv2.menu();
                    break;
                case 4: pv3.menu();
                    break;
                case 5: return;
            }
        }while(true);
    }
    public static void main(String[] args) {
        GestProjetDemo gm = new GestProjetDemo();
        gm.gestion();
    }
}

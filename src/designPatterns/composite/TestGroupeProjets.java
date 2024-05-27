package designPatterns.composite;

import java.math.BigDecimal;

import static utilitaires.Utilitaire.getDate;

public class TestGroupeProjets {
    public static void main(String[] args) {
GroupeProjet gp1 = new GroupeProjet(1, "Groupe 1");
        GroupeProjet gp2 = new GroupeProjet(2, "Groupe 2");
        Employe e1 = new Employe(1, "Emp 1", "Emp 1", "Emp 1","003","Emp 1 mail") ;
        Employe e2 = new Employe(2, "Emp 2", "Emp 2", "Emp 2","002","Emp 2 mail") ;
        Projet p1 = new Projet(1, "Projet 1", getDate("10 05 2023"), getDate("25 10 2023"), BigDecimal.valueOf(10), e1);
        Projet p2 = new Projet(2, "Projet 2", getDate("15 05 2023"), getDate("02 02 2025"), BigDecimal.valueOf(50), e2);
        Projet p3 = new Projet(3, "Projet 3", getDate("01 03 2023"), getDate("10 07 2024"), BigDecimal.valueOf(100), e1);
        Projet p4 = new Projet(4, "Projet 4", getDate("05 01 2024"), getDate("23 10 2024"), BigDecimal.valueOf(75), e2);
        gp1.getElts().add(p1);
        gp1.getElts().add(p2);
        gp2.getElts().add(p3);
        gp2.getElts().add(p4);
        gp1.getElts().add(gp2);
        System.out.println(gp1);
    }
}

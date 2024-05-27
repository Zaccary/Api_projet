package designPatterns.observer;

import java.math.BigDecimal;

import static utilitaires.Utilitaire.getDate;

public class TestObserver {
    public static void main(String[] args) {
        Employe e1 = new Employe(1, "Emp 1", "Emp 1", "Emp 1","003","Emp 1 mail") ;
        Projet p1 = new Projet(1, "Projet 1", getDate("10 05 2023"), getDate("25 10 2023"), BigDecimal.valueOf(10), e1);
        p1.addObserver(e1);
        p1.setDateFin(getDate("25 10 2024"));
        p1.notifyObservers();
    }

}

package designPatterns.builder;

import designPatterns.eb.Employe;

public class TestEmploye {
    public static void main(String[] args) {
        try {
            Employe employe1 = new Employe.EmployeBuilder().
                    setId(1).
                    setMatricule("M1").
                    setNom("nom1").
                    setPrenom("prenom1").
                    setTel("tel1").
                    setMail("mail1").
                    build();
            System.out.println(employe1);
        } catch (Exception e) {
            System.out.println("erreur :" + e.getMessage());

        }
    }
}

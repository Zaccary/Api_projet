package demodb;

import entreprise.gestionProjet.Employe;
import myconnections.DBConnection;

import java.sql.*;
import java.util.Scanner;

/**
 *
 *
 * @author Walem Zaccary
 */
public class GestEmploye {

    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        do {
            System.out.println("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.tous\n6.fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }




    public void ajout() {

        //sc.skip("\n"); pour vider le buffer
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
        String query1 = "insert into APIEMPLOYE(matricule,nom,prenom,tel,mail) values(?,?,?,?,?)";
        String query2 = "select id_employe from APIEMPLOYE where matricule =?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
            ){
            pstm1.setString(1,matricule);
            pstm1.setString(2,nom);
            pstm1.setString(3,prenom);
            pstm1.setString(4,tel);
            pstm1.setString(5,mail);
            int n = pstm1.executeUpdate();
            System.out.println(n+" ligne insérée");
            if(n==1){
                pstm2.setString(1,matricule);
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int id_employe= rs.getInt(1);
                    System.out.println("id_employe = "+id_employe);
                }
                else System.out.println("record introuvable");
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
      }


    public void recherche() {

        System.out.println("id de l'employe recherché :");
        int idrech = sc.nextInt();
        String query = "select * from APIEMPLOYE where id_employe = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe Em = new Employe(idrech,matricule,nom,prenom,tel,mail);
                System.out.println(Em);

            }
            else System.out.println("record introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }

    public void modification() {
        System.out.println("id du Employe recherché ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("nouveau téléphone ");
        String ntel = sc.nextLine();
        System.out.println("nouveau Mail ");
        String Email = sc.nextLine();
        //test modification de champs multiples
        String query = "update APIEMPLOYE set tel=?, mail=? where id_employe = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,ntel);
            pstm.setString(2,Email);
            pstm.setInt(3,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne mise à jour");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }
    public void suppression() {
        System.out.println("id de l'employe recherché ");
        int idrech = sc.nextInt();
        String query = "delete from APIEMPLOYE where id_employe = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne supprimée");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }

    private void tous() {
        String query="select * from APIEMPLOYE";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int id_employe = rs.getInt(1);
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);

                Employe Em = new Employe(id_employe,matricule,nom,prenom,tel,mail);
                System.out.println(Em);
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }
    public static void main(String[] args) {

        GestEmploye g = new GestEmploye();
        g.gestion();
    }
//TODO désactivé les id static car on utilise la base de données
}

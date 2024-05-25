package mvc.model;


import entreprise.gestionProjet.Competence;
import entreprise.gestionProjet.Disciplines;
import entreprise.gestionProjet.Employe;
import entreprise.gestionProjet.Projet;
import myconnections.DBConnection;


import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeModelDB extends DAOEmploye{

    protected Connection dbConnect;

    public EmployeModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }

    @Override
    public Employe addEmploye(Employe Employe) {
        String query1 = "insert into APIEMPLOYE(matricule,nom,prenom,tel,mail) values(?,?,?,?,?)";
        String query2 = "select id_employe from APIEMPLOYE where matricule =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, Employe.getMatricule());
            pstm1.setString(2, Employe.getNom());
            pstm1.setString(3, Employe.getPrenom());
            pstm1.setString(4, Employe.getTel());
            pstm1.setString(5, Employe.getMail());
            int n = pstm1.executeUpdate();
            System.out.println(n + " ligne insérée");
            if (n == 1) {
                pstm2.setString(1, Employe.getMatricule());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int id_employe = rs.getInt(1);
                    System.out.println("id_employe = " + id_employe);
                    notifyObservers();
                    return Employe;
                } else{
                    System.out.println("record introuvable");
                    return null;
                }
            }
            else return null;

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public boolean removeEmploye(Employe Employe) {
        String query = "delete from APIEmploye where id_Employe = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,Employe.getId_emplpoye());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return false;
        }
    }

    @Override
    public Employe updateEmploye(Employe Employe) {
        String query = "update APIEmploye set matricule =?,nom=?,prenom=?,tel=?,mail=? where id_employe = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, Employe.getMatricule());
            pstm.setString(2, Employe.getNom());
            pstm.setString(3, Employe.getPrenom());
            pstm.setString(4, Employe.getTel());
            pstm.setString(5, Employe.getMail());
            pstm.setInt(6, Employe.getId_emplpoye());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readEmploye(Employe.getId_emplpoye());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql update :" + e);

            return null;
        }
    }

    @Override
    public Employe readEmploye(int idEmploye) {
        String query = "select * from APIEmploye where id_employe = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idEmploye);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe pr = new Employe(idEmploye,matricule,nom,prenom,tel,mail);
                return  pr;

            }
            else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public List<Employe> getEmployes() {
        List<Employe> lp = new ArrayList<>();
        String query="select * from APIEmploye";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idEmploye = rs.getInt(1);
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe pr = new Employe(idEmploye,matricule,nom,prenom,tel,mail);
                lp.add(pr);
            }
            return lp;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }
    //faite dans modelHyb
    @Override
    public List<Competence> listeDisciplinesEtNiveau(Employe employe) {
        return List.of();
    }

    @Override
    public boolean addDisciplines(Employe employe, Disciplines discipline, int niveau) {
        return false;
    }

    @Override
    public boolean modifDiscipline(Employe employe, Disciplines discipline, int niveau) {
        return false;
    }

    @Override
    public boolean suppDiscipline(Employe employe, Disciplines discipline) {
        return false;
    }

    @Override
    public List<Projet> listeProjets(Employe employe) {
        return List.of();
    }

    @Override
    public List getNotification() {
        return getEmployes();
    }
}

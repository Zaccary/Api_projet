package mvc.model;


import entreprise.gestionProjet.Disciplines;
import myconnections.DBConnection;


import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DisciplinesModelDB extends DAODisciplines{

    protected Connection dbConnect;

    public DisciplinesModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }

    @Override
    public Disciplines addDisciplines(Disciplines Disciplines) {
        String query1 = "insert into APIDisciplines(nom,description) values(?,?)";
        String query2 = "select id_discipline from APIDisciplines where nom =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, Disciplines.getNom());
            pstm1.setString(2,Disciplines.getDescription());
            int n = pstm1.executeUpdate();
            System.out.println(n + " ligne insérée");
            if (n == 1) {
                pstm2.setString(1, Disciplines.getNom());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int id_discipline = rs.getInt(1);
                    System.out.println("id_discipline = " + id_discipline);
                    notifyObservers();
                    return Disciplines;
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
    public boolean removeDisciplines(Disciplines Disciplines) {
        String query = "delete from APIDisciplines where id_discipline = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,Disciplines.getId_discipline());
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
    public Disciplines updateDisciplines(Disciplines Disciplines) {
        String query = "update APIDisciplines set NOM= ?, DESCRIPTION=? where ID_DISCIPLINE = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, Disciplines.getNom());
            pstm.setString(2,Disciplines.getDescription());
            pstm.setInt(3,Disciplines.getId_discipline());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readDisciplines(Disciplines.getId_discipline());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql update :" + e);

            return null;
        }
    }

    @Override
    public Disciplines readDisciplines(int idDisciplines) {
        String query = "select * from APIDisciplines where id_Discipline = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idDisciplines);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String nom = rs.getString(2);
                String Description = rs.getString(3);
                Disciplines pr = new Disciplines(idDisciplines,nom,Description);
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
    public List<Disciplines> getDiscipliness() {
        List<Disciplines> lp = new ArrayList<>();
        String query="select * from APIDisciplines";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idDisciplines = rs.getInt(1);
                String nom = rs.getString(2);
                String Description = rs.getString(3);
                Disciplines pr = new Disciplines(idDisciplines,nom,Description);
                lp.add(pr);
            }
            return lp;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public List getNotification() {
        return getDiscipliness();
    }
}

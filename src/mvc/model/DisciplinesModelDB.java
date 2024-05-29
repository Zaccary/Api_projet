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
        String query = "{call APICREATEDISCIPLINES(?, ?, ?)}";
        try (CallableStatement cstmt = dbConnect.prepareCall(query)) {
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, Disciplines.getNom());
            cstmt.setString(3, Disciplines.getDescription());
            cstmt.execute();
            int id_discipline = cstmt.getInt(1);
            Disciplines.setId_discipline(id_discipline);
            notifyObservers();
            return Disciplines;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public boolean removeDisciplines(Disciplines Disciplines) {
        String query = "{call SUPPDISCIPLINES(?)}";
        try (CallableStatement cstmt = dbConnect.prepareCall(query)) {
            cstmt.setInt(1, Disciplines.getId_discipline());
            cstmt.execute();
            notifyObservers();
            return true;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public Disciplines updateDisciplines(Disciplines Disciplines) {
        String query = "{call APIMODIFDISCIPLINES(?, ?, ?)}";
        try (CallableStatement cstmt = dbConnect.prepareCall(query)) {
            cstmt.setInt(1, Disciplines.getId_discipline());
            cstmt.setString(2, Disciplines.getNom());
            cstmt.setString(3, Disciplines.getDescription());
            cstmt.execute();
            notifyObservers();
            return Disciplines;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
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

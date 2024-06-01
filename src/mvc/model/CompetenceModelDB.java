package mvc.model;


import entreprise.gestionProjet.Competence;
import entreprise.gestionProjet.Disciplines;
import myconnections.DBConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CompetenceModelDB extends DAOCompetence{

    protected Connection dbConnect;

    public CompetenceModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }

    @Override
    public Competence addCompetence(Competence Competence, int id_Employe) {
        try (CallableStatement call = dbConnect.prepareCall("{ call APICREATECOMPETENCE(?, ?, ?, ?) }")) {
            call.registerOutParameter(1, Types.INTEGER);
            call.setInt(2, Competence.getNiveau());
            call.setInt(3, Competence.getDiscipline().getId_discipline());
            call.setInt(4, id_Employe);
            call.execute();

            int id_competence = call.getInt(1);
            Competence.setId_competence(id_competence);
            notifyObservers();
            return Competence;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public boolean removeCompetence(Competence competence) {
        String query = "{call SUPPCOMPETENCE(?)}";
        try (CallableStatement cstmt = dbConnect.prepareCall(query)) {
            cstmt.setInt(1, competence.getId_competence());
            cstmt.execute();
            notifyObservers();
            return true;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return false;
        }
    }
    @Override
    public Competence updateCompetence(Competence competence, int id_Employe) {
        try (CallableStatement call = dbConnect.prepareCall("{ call APIMODIFCOMPETENCE(?, ?, ?, ?) }")) {
            call.setInt(1, competence.getId_competence());
            call.setInt(2, competence.getNiveau());
            call.setInt(3, competence.getDiscipline().getId_discipline());
            call.setInt(4, id_Employe);
            call.execute();
            notifyObservers();
            return competence;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public Competence readCompetence(int idCompetence) {
        String query = "select * from APICompetenceDisciplines where id_competence = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query);) {
            pstm.setInt(1,idCompetence);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                int niveau = rs.getInt(2);
                int idDiscipline = rs.getInt(3);
                String nom = rs.getString(4);
                String description = rs.getString(5);
                Competence pr = new Competence(idCompetence,new Disciplines(idDiscipline,nom,description),niveau);
                return  pr;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public List<Competence> getCompetences() {
        List<Competence> lp = new ArrayList<>();
        String query="select * from APICompetenceDisciplines";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idCompetence = rs.getInt(1);
                int niveau = rs.getInt(2);
                int idDiscipline = rs.getInt(3);
                String nom = rs.getString(4);
                String description = rs.getString(5);
                Competence pr = new Competence(idCompetence,new Disciplines(idDiscipline,nom,description),niveau);
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
        return getCompetences();
    }
}

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
        String query1 = "insert into APICompetence(niveau,discipline,employe) values(?,?,?)";
        String query2 = "select id_competence from APICompetence where discipline =? and employe =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setInt(1, Competence.getNiveau());
            pstm1.setInt(2,Competence.getDiscipline().getId_discipline());
            pstm1.setInt(3,id_Employe);
            int n = pstm1.executeUpdate();
            System.out.println(n + " ligne insérée");
            if (n == 1) {
                pstm2.setInt(1, Competence.getDiscipline().getId_discipline());
                pstm2.setInt(2, id_Employe);
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int id_competence = rs.getInt(1);
                    System.out.println("id_competence = " + id_competence);
                    notifyObservers();
                    return Competence;
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
    public boolean removeCompetence(Competence Competence) {
        String query = "delete from APICompetence where id_competence = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,Competence.getId_competence());
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
    public Competence updateCompetence(Competence Competence, int id_Employe) {
        String query = "update APICompetence set  niveau =?, discipline = ?, employe = ? where id_competence = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, Competence.getNiveau());
            pstm.setInt(2,Competence.getDiscipline().getId_discipline());
            pstm.setInt(3,id_Employe);
            pstm.setInt(4,Competence.getId_competence());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readCompetence(Competence.getId_competence());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql update :" + e);

            return null;
        }
    }

    @Override
    public Competence readCompetence(int idCompetence) {
        String query = "select * from APICompetence where id_competence = ?";
        String query2 = "select * from APIDisciplines where id_discipline = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query);
            PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm.setInt(1,idCompetence);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                pstm2.setInt(1,rs.getInt("discipline"));
                ResultSet rs2 = pstm2.executeQuery();
                if (rs2.next()) { // Ajoutez cette ligne
                    int niveau = rs.getInt("niveau");
                    Competence pr = new Competence(idCompetence,new Disciplines(rs2.getInt("id_discipline"),rs2.getString("nom"),rs2.getString("description")),niveau);
                    return  pr;
                } else {
                    return null;
                }
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
        String query="select * from APICompetence";
        String query2 = "select * from APIDisciplines where id_discipline = ?";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idCompetence = rs.getInt(1);
                int niveau = rs.getInt(2);
                try {
                    PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
                    pstm2.setInt(1,rs.getInt("discipline"));
                    ResultSet rs2 = pstm2.executeQuery();
                    if (rs2.next()) { // Ajoutez cette ligne
                        Competence pr = new Competence(idCompetence,new Disciplines(rs2.getInt("id_discipline"),rs2.getString("nom"),rs2.getString("description")),niveau);
                        lp.add(pr);
                    }
                } catch (SQLException e) {
                    System.err.println("erreur sql :"+e);
                    return null;
                }
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

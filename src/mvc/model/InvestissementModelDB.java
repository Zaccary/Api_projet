package mvc.model;


import entreprise.gestionProjet.Investissement;
import entreprise.gestionProjet.Disciplines;
import myconnections.DBConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InvestissementModelDB extends DAOInvestissement{

    protected Connection dbConnect;

    public InvestissementModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }

    @Override
    public Investissement addInvestissement(Investissement Investissement, int id_Projet) {
        String query1 = "insert into APIInvestissement(quantiteJH,discipline,projet) values(?,?,?)";
        String query2 = "select id_investissement from APIInvestissement where discipline =? and projet =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setInt(1, Investissement.getQuantiteJH());
            pstm1.setInt(2,Investissement.getDiscipline().getId_discipline());
            pstm1.setInt(3,id_Projet);
            int n = pstm1.executeUpdate();
            System.out.println(n + " ligne insérée");
            if (n == 1) {
                pstm2.setInt(1, Investissement.getDiscipline().getId_discipline());
                pstm2.setInt(2, id_Projet);
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int id_investissement = rs.getInt(1);
                    System.out.println("id_investissement = " + id_investissement);
                    notifyObservers();
                    return Investissement;
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
    public boolean removeInvestissement(Investissement Investissement) {
        String query = "delete from APIInvestissement where id_investissement = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,Investissement.getId_investissement());
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
    public Investissement updateInvestissement(Investissement Investissement, int id_projet) {
        String query = "update APIInvestissement set  quantiteJH =?, discipline = ?, projet = ? where id_investissement = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, Investissement.getQuantiteJH());
            pstm.setInt(2,Investissement.getDiscipline().getId_discipline());
            pstm.setInt(3,id_projet);
            pstm.setInt(4,Investissement.getId_investissement());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readInvestissement(Investissement.getId_investissement());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql update :" + e);

            return null;
        }
    }

    @Override
    public Investissement readInvestissement(int idInvestissement) {
        String query = "select * from APIInvestissementDisciplines where id_investissement = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query);) {
            pstm.setInt(1,idInvestissement);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                int quantieJH = rs.getInt(2);
                int idDiscipline = rs.getInt(3);
                String nom = rs.getString(4);
                String description = rs.getString(5);
                Investissement pr = new Investissement(idInvestissement,new Disciplines(idDiscipline,nom,description),quantieJH);
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
    public List<Investissement> getInvestissements() {
        List<Investissement> lp = new ArrayList<>();
        String query="select * from APIInvestissementDisciplines";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idInvestissement = rs.getInt(1);
                int quantiteJH = rs.getInt(2);
                int idDiscipline = rs.getInt(3);
                String nom = rs.getString(4);
                String description = rs.getString(5);
                Investissement pr = new Investissement(idInvestissement,new Disciplines(idDiscipline,nom,description),quantiteJH);
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
        return getInvestissements();
    }
}

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
    public Investissement addInvestissement(Investissement investissement, int id_Projet) {
        try (CallableStatement call = dbConnect.prepareCall("{ call APICREATEINVESTISSEMENT(?, ?, ?, ?) }")) {
            call.registerOutParameter(1, Types.INTEGER);
            call.setInt(2, investissement.getQuantiteJH());
            call.setInt(3, investissement.getDiscipline().getId_discipline());
            call.setInt(4, id_Projet);
            call.execute();

            int id_investissement = call.getInt(1);
            investissement.setId_investissement(id_investissement);
            notifyObservers();
            return investissement;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public boolean removeInvestissement(Investissement investissement) {
        String query = "{call SUPPINVESTISSEMENT(?)}";
        try (CallableStatement cstmt = dbConnect.prepareCall(query)) {
            cstmt.setInt(1, investissement.getId_investissement());
            cstmt.execute();
            notifyObservers();
            return true;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public Investissement updateInvestissement(Investissement investissement, int id_projet) {
        try (CallableStatement call = dbConnect.prepareCall("{ call APIMODIFINVESTISSEMENT(?, ?, ?, ?) }")) {
            call.setInt(1, investissement.getId_investissement());
            call.setInt(2, investissement.getQuantiteJH());
            call.setInt(3, investissement.getDiscipline().getId_discipline());
            call.setInt(4, id_projet);
            call.execute();
            notifyObservers();
            return investissement;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
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

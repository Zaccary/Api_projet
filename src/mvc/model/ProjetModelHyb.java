package mvc.model;


import entreprise.gestionProjet.*;
import myconnections.DBConnection;


import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class ProjetModelHyb extends DAOProjet{

    protected Connection dbConnect;

    public ProjetModelHyb(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }
    //trigger si la date de fin est avant la date de début (trigger:check_dates_before_insert)
    @Override
    public Projet addProjet(Projet projet) {
        try (CallableStatement call = dbConnect.prepareCall("{ call APICREATEPROJET(?, ?, ?, ?, ?, ?) }")) {
            call.registerOutParameter(1, Types.INTEGER);
            call.setString(2, projet.getNom());
            call.setDate(3, Date.valueOf(projet.getDateDebut()));
            call.setDate(4, Date.valueOf(projet.getDateFin()));
            call.setBigDecimal(5, projet.getCout());
            call.setInt(6, projet.getChefProjet().getId_emplpoye());
            call.execute();

            int id_projet = call.getInt(1);
            projet.setId_projet(id_projet);
            notifyObservers();
            return projet;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public boolean removeProjet(Projet projet) {
        String query = "{call SUPPPROJET(?)}";
        try (CallableStatement cstmt = dbConnect.prepareCall(query)) {
            cstmt.setInt(1, projet.getId_projet());
            cstmt.execute();
            notifyObservers();
            return true;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public Projet updateProjet(Projet projet) {
        try (CallableStatement call = dbConnect.prepareCall("{ call APIMODIFPROJET(?, ?, ?, ?, ?, ?) }")) {
            call.setInt(1, projet.getId_projet());
            call.setString(2, projet.getNom());
            call.setDate(3, Date.valueOf(projet.getDateDebut()));
            call.setDate(4, Date.valueOf(projet.getDateFin()));
            call.setBigDecimal(5, projet.getCout());
            call.setInt(6, projet.getChefProjet().getId_emplpoye());
            call.execute();
            notifyObservers();
            return projet;
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public Projet readProjet(int idProjet) {
        String query = "select * from APIProjet where id_projet = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idProjet);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String nom = rs.getString(2);
                LocalDate datedebut = rs.getDate(3).toLocalDate();
                LocalDate datefin = rs.getDate(4).toLocalDate();
                BigDecimal cout = rs.getBigDecimal(5);
                Employe chefprojet = new EmployeModelHyb().readEmploye(rs.getInt(6));
                Projet pr = new Projet(idProjet,nom,datedebut,datefin,cout,chefprojet);
                List<Investissement> investissements = getInvestissementForProjet(idProjet);
                pr.setInvestissements(investissements);
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
    public List<Projet> getProjets() {
        List<Projet> lp = new ArrayList<>();
        String query="select * from APIProjet";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idProjet = rs.getInt(1);
                String nom = rs.getString(2);
                LocalDate datedebut = rs.getDate(3).toLocalDate();
                LocalDate datefin = rs.getDate(4).toLocalDate();
                BigDecimal cout = rs.getBigDecimal(5);
                List<Investissement> investissements = getInvestissementForProjet(idProjet);
                Employe chefprojet = new EmployeModelHyb().readEmploye(rs.getInt(6));
                Projet pr = new Projet(idProjet,nom,datedebut,datefin,cout,chefprojet);
                for (Investissement investissement : investissements) {
                    pr.addDiscipline(investissement.getId_investissement(),investissement.getDiscipline(),investissement.getQuantiteJH());
                }
                lp.add(pr);
            }
            return lp;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public List<Investissement> listeDisciplinesEtInvestissement(Projet projet) {
        projet.setInvestissements(getInvestissementForProjet(projet.getId_projet()));
        return  projet.listeDisciplinesEtInvestissement();
    }

    @Override
    public boolean addDisciplines(Projet projet, Disciplines discipline, int niveau) {
        int idEmploye = projet.getId_projet();
        Investissement investissement = new Investissement(0,discipline,niveau);
        investissement = pm4.addInvestissement(investissement,idEmploye);
        if (investissement != null) {
            notifyObservers();
            return true;
        }
        else return false;
    }

    @Override
    public boolean modifDiscipline(Projet projet, Disciplines discipline, int niveau) {
        String query = "select id_investissement from APIInvestissement where Discipline = ? and projet = ?";
        Investissement investissement = null;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,discipline.getId_discipline());
            pstm.setInt(2,projet.getId_projet());
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                investissement= new Investissement(id,discipline,niveau);
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
        }
        investissement = pm4.updateInvestissement(investissement,projet.getId_projet());
        if (investissement != null) {
            notifyObservers();
            return true;
        }
        else return false;
    }

    @Override
    public boolean suppDiscipline(Projet projet, Disciplines discipline) {
        String query = "select id_investissement, quantiteJH from APIInvestissement where Discipline = ? and projet = ?";
        Investissement investissement = null;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,discipline.getId_discipline());
            pstm.setInt(2,projet.getId_projet());
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                int niveau = rs.getInt(2);
                investissement= new Investissement(id,discipline,niveau);
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
        }
        boolean x= pm4.removeInvestissement(investissement);
        if (x) {
            notifyObservers();
        }
        return x;
    }

    @Override
    public List<Competence> niveauxResponsableDisciplines(Projet projet) {
        return projet.niveauResponsableDiscipline();
    }

    private List<Investissement> getInvestissementForProjet(int idProjet) {
        List<Investissement> Investissements = new ArrayList<>();
        String query = "SELECT * FROM ApiInvestissementDisciplinesV2 WHERE projet = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idProjet);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idInvestissement = rs.getInt(1);
                int quantiteJh = rs.getInt(2);
                int idDiscipline = rs.getInt(4);
                String nom = rs.getString(5);
                String description = rs.getString(6);
                Investissement Investissement = new Investissement(idInvestissement, new Disciplines(idDiscipline,nom,description), quantiteJh);
                Investissements.add(Investissement);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return Investissements;
    }

    @Override
    public List getNotification() {
        return getProjets();
    }
}

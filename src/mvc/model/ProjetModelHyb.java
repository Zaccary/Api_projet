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

    @Override
    public Projet addProjet(Projet Projet) {
        String query1 = "insert into APIPROJET(nom,datedebut,datefin,cout,chefprojet) values(?,?,?,?,?)";
        String query2 = "select id_projet from APIPROJET where nom =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, Projet.getNom());
            pstm1.setDate(2, Date.valueOf(Projet.getDateDebut()));
            pstm1.setDate(3, Date.valueOf(Projet.getDateFin()));
            pstm1.setBigDecimal(4, Projet.getCout());
            pstm1.setInt(5, Projet.getChefProjet().getId_emplpoye());
            int n = pstm1.executeUpdate();
            System.out.println(n + " ligne insérée");
            if (n == 1) {
                pstm2.setString(1, Projet.getNom());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int id_projet = rs.getInt(1);
                    System.out.println("id_projet = " + id_projet);
                    notifyObservers();
                    return Projet;
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
    public boolean removeProjet(Projet Projet) {
        String query = "delete from APIProjet where id_Projet = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,Projet.getId_projet());
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
    public Projet updateProjet(Projet Projet) {
        String query = "update APIProjet set nom=?,datedebut=?,datefin=?,cout=?,chefprojet=? where id_projet = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, Projet.getNom());
            pstm.setDate(2, Date.valueOf(Projet.getDateDebut()));
            pstm.setDate(3, Date.valueOf(Projet.getDateFin()));
            pstm.setBigDecimal(4, Projet.getCout());
            pstm.setInt(5, Projet.getChefProjet().getId_emplpoye());
            pstm.setInt(6, Projet.getId_projet());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readProjet(Projet.getId_projet());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql update :" + e);

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
        String query = "select id_investissement, niveau from APIInvestissement where Discipline = ? and projet = ?";
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

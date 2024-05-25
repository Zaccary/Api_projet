package mvc.model;


import entreprise.gestionProjet.*;
import myconnections.DBConnection;


import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EmployeModelHyb extends DAOEmploye{

    protected Connection dbConnect;

    public EmployeModelHyb(){
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
                List<Competence> competences = getCompetencesForEmploye(idEmploye);
                pr.setCompetences(competences);
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
                List<Competence> competences = getCompetencesForEmploye(idEmploye);
                Employe pr = new Employe(idEmploye,matricule,nom,prenom,tel,mail);
                for (Competence competence : competences) {
                    pr.addDiscipline(competence.getId_competence(),competence.getDiscipline(),competence.getNiveau());
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
    public List<Competence> listeDisciplinesEtNiveau(Employe employe) {
        return employe.listeDisciplinesEtNiveau();
    }

    @Override
    public boolean addDisciplines(Employe employe, Disciplines discipline, int niveau) {
        int idEmploye = employe.getId_emplpoye();
        Competence competence = new Competence(0,discipline,niveau);
        competence = pm3.addCompetence(competence,idEmploye);
        if (competence != null) {
            notifyObservers();
            return true;
        }
        else return false;
    }

    @Override
    public boolean modifDiscipline(Employe employe, Disciplines discipline, int niveau) {
        String query = "select id_competence from APICompetence where Discipline = ? and employe = ?";
        Competence competence = null;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,discipline.getId_discipline());
            pstm.setInt(2,employe.getId_emplpoye());
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                competence= new Competence(id,discipline,niveau);
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
        }
        competence = pm3.updateCompetence(competence,employe.getId_emplpoye());
        if (competence != null) {
            notifyObservers();
            return true;
        }
        else return false;
    }

    @Override
    public boolean suppDiscipline(Employe employe, Disciplines discipline) {
        String query = "select id_competence, niveau from APICompetence where Discipline = ? and employe = ?";
        Competence competence = null;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,discipline.getId_discipline());
            pstm.setInt(2,employe.getId_emplpoye());
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                int niveau = rs.getInt(2);
                competence= new Competence(id,discipline,niveau);
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
        }
        boolean x= pm3.removeCompetence(competence);
        if (x) {
            notifyObservers();
        }
        return x;
    }

    @Override
    public List<Projet> listeProjets(Employe employe) {
        List<Projet> lp = new ArrayList<>();
        String query="select id_projet, nomProj, dateDebut, dateFin, cout from APIProjetEmploye where id_employe = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, employe.getId_emplpoye());
            System.out.println(pstm);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                int idProjet = rs.getInt(1);
                String nom = rs.getString(2);
                LocalDate datedebut = rs.getDate(3).toLocalDate();
                LocalDate datefin = rs.getDate(4).toLocalDate();
                BigDecimal cout = rs.getBigDecimal(5);
                Projet pr = new Projet(idProjet,nom,datedebut,datefin,cout,employe);
                lp.add(pr);
            }
            return lp;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    private List<Competence> getCompetencesForEmploye(int idEmploye) {
        List<Competence> competences = new ArrayList<>();
        String query = "SELECT * FROM ApicompetenceDisciplinesV2 WHERE employe = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idEmploye);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idCompetence = rs.getInt(1);
                int niveau = rs.getInt(2);
                int idDiscipline = rs.getInt(4);
                String nom = rs.getString(5);
                String description = rs.getString(6);
                Competence competence = new Competence(idCompetence, new Disciplines(idDiscipline,nom,description), niveau);
                competences.add(competence);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return competences;
    }
    @Override
    public List getNotification() {
        return getEmployes();
    }
}

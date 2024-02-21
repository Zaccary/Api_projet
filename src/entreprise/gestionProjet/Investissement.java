package entreprise.gestionProjet;

import java.util.Objects;
/**
 * classe Investissement de gestion de projet
 * @author Walem Zaccary
 * @version 1.0
 */
public class Investissement {
    /**
     * id auto-incrémenté
     */
    private static int id=1;
    /**
     * id de l'investissement
     */
    protected int id_investissement;
    /**
     * quantité de JH
     */
    protected int quantiteJH;
    /**
     * discipline
     */
    protected Disciplines discipline;
    /**
     * Constructeur de la classe Investissement
     * @param d discipline
     * @param quantite quantité de JH
     */
    public Investissement(Disciplines d,int quantite) {
        this.discipline= d;
        this.quantiteJH = quantite;
        this.id_investissement = id++;
    }

    public Disciplines getDiscipline() {
        return discipline;
    }

    /**
     * retourne l'id de l'investissement
     * @return
     */
    public int getId_investissement() {
        return id_investissement;
    }

    /**
     * modifie l'id de l'investissement
     * @param id_investissement
     */
    public void setId_investissement(int id_investissement) {
        this.id_investissement = id_investissement;
    }
    /**
     * retourne la quantité de JH
     * @return quantité de JH
     */
    public int getQuantiteJH() {
        return quantiteJH;
    }
    /**
     * modifie la quantité de JH
     * @param quantiteJH quantité de JH
     */
    public void setQuantiteJH(int quantiteJH) {
        this.quantiteJH = quantiteJH;
    }
    /**
     * retourne le hashcode de l'investissement
     * @return hashcode de l'investissement
     */
    public void setDiscipline(Disciplines discipline) {
        this.discipline = discipline;
    }

    /**
     * override de la methode toString
     * @return les informations de l'investissement
     */

    @Override
    public String toString() {
        return "Investissement{" +
                "id_investissement=" + id_investissement +
                ", quantiteJH=" + quantiteJH +
                ", discipline=" + discipline +
                '}';
    }
}

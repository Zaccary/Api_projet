package entreprise.gestionProjet;

import java.util.Objects;

public class Disciplines {

    /**
     * id auto-incrémenté
     */
    private static int idAct = 1;
    /**
     * id de la discipline
     */
    protected int id_discipline;
    /**
     * nom de la discipline
     */
    protected String nom;
    /**
     * description de la discipline
     */
    protected String description;
    /**
     * Constructeur de la classe Disciplines alternatif pour les tests
     */
    public Disciplines(){}
    /**
     * Constructeur de la classe Disciplines
     * @param nom nom de la discipline
     * @param description description de la discipline
     */
    public Disciplines(String nom, String description) {
        id_discipline = idAct++;
        this.nom = nom;
        this.description = description;
    }


    /**
     * Constructeur de la classe Disciplines alternatif pour les tests
     * @param nom nom de la discipline
     */
    public Disciplines(String nom) {
        this.nom=nom;
    }
    /**
     * retourne l'id de la discipline
     * @return id de la discipline
     */
    public int getId_discipline() {
        return id_discipline;
    }
    /**
     * modifie l'id de la discipline
     * @param id_discipline
     */
    public void setId_discipline(int id_discipline) {
        this.id_discipline = id_discipline;
    }
    /**
     * retourne le nom de la discipline
     * @return nom de la discipline
     */
    public String getNom() {
        return nom;
    }
    /**
     * modifie le nom de la discipline
     * @param nom nom de la discipline
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * retourne la description de la discipline
     * @return description de la discipline
     */
    public String getDescription() {
        return description;
    }
    /**
     * modifie la description de la discipline
     * @param description description de la discipline
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * retourne le hashcode de la discipline
     * @return hashcode de la discipline
     */
    @Override
    public String toString() {
        return "Disciplines{" +
                "id_discipline=" + id_discipline +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    /**
     * override de la methode equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplines that = (Disciplines) o;
        return id_discipline == that.id_discipline && Objects.equals(nom, that.nom) && Objects.equals(description, that.description);
    }
    /**
     * override de la methode hashCode
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id_discipline, nom, description);
    }
}

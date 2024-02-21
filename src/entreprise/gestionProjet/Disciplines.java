package entreprise.gestionProjet;

import java.util.Objects;

public class Disciplines {

    private static int idAct = 1;
    protected int id_discipline;
    protected String nom;
    protected String description;
    public Disciplines(){}
    public Disciplines(String nom, String description) {
        id_discipline = idAct++;
        this.nom = nom;
        this.description = description;
    }
    public Disciplines(String nom) {
        this.nom=nom;
    }

    public int getId_discipline() {
        return id_discipline;
    }

    public void setId_discipline(int id_discipline) {
        this.id_discipline = id_discipline;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Disciplines{" +
                "id_discipline=" + id_discipline +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplines that = (Disciplines) o;
        return id_discipline == that.id_discipline && Objects.equals(nom, that.nom) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_discipline, nom, description);
    }
}

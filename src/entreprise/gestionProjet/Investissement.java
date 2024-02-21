package entreprise.gestionProjet;

import java.util.Objects;

public class Investissement {
    protected int id_investissement;
    protected int quantiteJH;
    protected Disciplines discipline;
    public Investissement(Disciplines d,int quantite) {
        this.discipline= d;
        this.quantiteJH = quantite;
    }

    public Disciplines getDiscipline() {
        return discipline;
    }

    public int getId_investissement() {
        return id_investissement;
    }

    public void setId_investissement(int id_investissement) {
        this.id_investissement = id_investissement;
    }

    public int getQuantiteJH() {
        return quantiteJH;
    }

    public void setQuantiteJH(int quantiteJH) {
        this.quantiteJH = quantiteJH;
    }

    public void setDiscipline(Disciplines discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "Investissement{" +
                "id_investissement=" + id_investissement +
                ", quantiteJH=" + quantiteJH +
                ", discipline=" + discipline +
                '}';
    }
}

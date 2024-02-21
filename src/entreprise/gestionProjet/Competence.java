package entreprise.gestionProjet;

public class Competence {
    protected int id_competence;
    protected int niveau;
    protected Disciplines discipline;
    public Competence(Disciplines d,int niveau) {
        this.discipline= d;
        this.niveau = niveau;
    }
}
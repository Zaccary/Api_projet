package designPatterns.composite;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class GroupeProjet extends Element {

    private String nom;
    private Set<Element> elts = new HashSet<>();

    public GroupeProjet(int id,String nom){
        super(id);
        this.nom=nom;
    }


    @Override
    public BigDecimal totalCoup() {
        int totalCout = 0;
        for(Element elt : elts){
            totalCout += elt.totalCoup().intValue();
        }
        return BigDecimal.valueOf(totalCout);
    }

    @Override
    public String toString() {
        StringBuilder aff= new StringBuilder(getId()+" "+nom+"\n");

        for(Element e:elts){
            aff.append(e+"\n");
        }
        return aff+"totalCoup " +nom +" = "+totalCoup()+"\n";
    }

    public Set<Element> getElts() {
        return elts;
    }
}

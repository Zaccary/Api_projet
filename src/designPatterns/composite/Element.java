package designPatterns.composite;


import java.math.BigDecimal;

public abstract class Element {
    private int id;
    public Element(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public abstract BigDecimal totalCoup();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Element other = (Element) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


}

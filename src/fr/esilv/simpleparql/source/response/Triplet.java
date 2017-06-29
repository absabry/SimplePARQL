package fr.esilv.simpleparql.source.response;

import fr.esilv.simpleparql.source.model.Triple;
import fr.esilv.simpleparql.source.model.Truc;

/**
 * Created to handle the response
 */
public class Triplet {

    private Truc truc;
    private int indexInVariables;
    private String position;
    private int indexOfFirstInVariables;

    public Triplet(Truc truc, int index, String position) {
        this.truc = truc;
        this.indexInVariables = index;
        this.position = position;
    }

    public Truc getTruc() {
        return truc;
    }

    public int getIndexInVariables() {
        return indexInVariables;
    }

    public void setIndexOfFirstInVariables(int index) {
        indexOfFirstInVariables = index;
    }

    public int getIndexOfFirstInVariables() {
        return indexOfFirstInVariables;
    }

    public String getPosition() {
        return position;
    }

    public boolean isMajor() {
        return indexOfFirstInVariables == indexInVariables;
    }

    @Override
    public boolean equals(Object object) {
        return object != null && object instanceof Triplet
                && ((Triplet) object).getTruc().getCleanedName().equals(this.getTruc().getCleanedName());
    }

    public String toString() {
        return truc.getCleanedName() + "," + position + "," + indexOfFirstInVariables+" and my index is "+indexInVariables;
    }

}
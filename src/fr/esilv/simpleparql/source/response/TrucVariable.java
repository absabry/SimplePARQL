package fr.esilv.simpleparql.source.response;

import fr.esilv.simpleparql.source.model.POSITION;
import fr.esilv.simpleparql.source.model.Truc;
import fr.esilv.simpleparql.source.model.VARIABLES;

/**
 * Created to handle the response
 */
public class TrucVariable {

    private Truc truc;
    private int indexInVariables;
    private VARIABLES position;
    private int indexOfFirstInVariables;

    public TrucVariable(Truc truc, int index, VARIABLES position) {
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
        return position.toString();
    }

    public boolean isMajor() {
        return indexOfFirstInVariables == indexInVariables;
    }

    @Override
    public boolean equals(Object object) {
        return object != null && object instanceof TrucVariable
                && ((TrucVariable) object).getTruc().getCleanedName().equals(this.getTruc().getCleanedName());
    }

    public String toString() {
        return truc.getCleanedName() + "," + position + "," + indexOfFirstInVariables + " and my index is " + indexInVariables;
    }

}
package fr.esilv.simpleparql.source.jenaresult;

import fr.esilv.simpleparql.source.model.Truc;
import fr.esilv.simpleparql.source.model.VARIABLES;

/**
 * Created to handle the result from the Jena API and add it to the JSON object result. <br>
 * This class is to create a structure to manipulate the temporary variables generated for the SimplePARQL trucs. <br>
 */
public class TemporaryTrucVariable {

    private Truc truc;
    private int indexInVariablesList;
    private VARIABLES position;
    private int indexOfFirstInVariablesList;

    public TemporaryTrucVariable(Truc truc, int index, VARIABLES position) {
        this.truc = truc;
        this.indexInVariablesList = index;
        this.position = position;
    }

    public Truc getTruc() {
        return truc;
    }

    public int getIndexInVariablesList() {
        return indexInVariablesList;
    }

    public void setIndexOfFirstInVariablesList(int index) {
        indexOfFirstInVariablesList = index;
    }

    public int getIndexOfFirstInVariablesList() {
        return indexOfFirstInVariablesList;
    }

    public String getPosition() {
        return position.toString();
    }

    public boolean isMajor() {
        return indexOfFirstInVariablesList == indexInVariablesList;
    }


    /**
     * This function is defined to check if the list of TemporaryTrucVariable already contains a variable belonging to this Truc.<br>
     * The field to check is the Truc of the TemporaryTrucVariable of course.
     *
     * @param object other TemporaryTrucVariable
     * @return boolean determining if the truc is equals to the other TemporaryTrucVariable
     */
    @Override
    public boolean equals(Object object) {
        return object != null && object instanceof TemporaryTrucVariable
                && ((TemporaryTrucVariable) object).getTruc().equals(this.getTruc());
    }

    public String toString() {
        return truc.getCleanedName() + "," + position + "," + indexOfFirstInVariablesList + " and my index is " + indexInVariablesList;
    }

}
package fr.esilv.simpleparql.source.jenaresult;

import fr.esilv.simpleparql.source.model.Truc;

import java.util.ArrayList;

/**
 * List of temporaryTrucVariables structure
 */
public class TemporaryTrucVariables {
    private ArrayList<TemporaryTrucVariable> temporaryTrucVariables;

    public TemporaryTrucVariables() {
        temporaryTrucVariables = new ArrayList<>();
    }

    /**
     * Add a TemporaryTrucVariable to the list. Change the indexOfFirstInVariables if it's already exists or not.
     *
     * @param temporaryTrucVariable The new TemporaryTrucVariable that should be added to the list.
     */
    public void add(TemporaryTrucVariable temporaryTrucVariable) {
        if (temporaryTrucVariables.contains(temporaryTrucVariable)) {
            // if the truc exists already in the temporaryTrucVariables list
            // that means it's not the first variable that belongs to the truc to be added here.
            TemporaryTrucVariable original = temporaryTrucVariables.get(temporaryTrucVariables.indexOf(temporaryTrucVariable));
            temporaryTrucVariable.setIndexOfFirstInVariablesList(original.getIndexInVariablesList());
        } else {
            // first variable that belongs to the truc to be added to the list
            temporaryTrucVariable.setIndexOfFirstInVariablesList(temporaryTrucVariable.getIndexInVariablesList());
        }
        this.temporaryTrucVariables.add(temporaryTrucVariable);
    }

    /**
     * Check if the list contains any variable, having the index in original variable list, equals to index (parameteres).
     * If it's, it means this variable is a temporary generated variable, otherwise it's a real SPARQL variable.
     *
     * @param index index of variable in the original list (to be compared to the TemporaryTrucVariable's indexInVariablesList
     * @return boolean determining if it's contained in the list or not.
     */
    public boolean contains(Integer index) {
        for (TemporaryTrucVariable temporaryTrucVariable : temporaryTrucVariables) {
            if (temporaryTrucVariable.getIndexInVariablesList() == index) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get Temporary truc variable, having his index in the original variable list,  equals to index.
     *
     * @param index get object with the index in variables original list.
     * @return the temporary variable truc.
     */
    public TemporaryTrucVariable getTemporaryTrucVariable(int index) {
        for (TemporaryTrucVariable triplet : temporaryTrucVariables) {
            if (triplet.getIndexInVariablesList() == index) {
                return triplet;
            }
        }
        return null;
    }

    public String toString() {
        String result = "";
        for (TemporaryTrucVariable temp : temporaryTrucVariables) {
            result += temp.toString();
        }
        return result;
    }

}

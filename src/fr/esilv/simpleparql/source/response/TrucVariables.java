package fr.esilv.simpleparql.source.response;

import fr.esilv.simpleparql.source.model.Truc;

import java.util.ArrayList;

/**
 * List of responses triplets structure
 */
public class TrucVariables {
    private ArrayList<TrucVariable> triplets;
    private ArrayList<Integer> indexKept;


    public TrucVariables() {
        triplets = new ArrayList<>();
        indexKept = new ArrayList<>();
    }

    public void add(TrucVariable triplet) {
        if (triplets.contains(triplet)) {
            // if the truc exists already in the triplets list
            // that means it's not the first variable type of truc to be added here
            TrucVariable original = triplets.get(triplets.indexOf(triplet));
            triplet.setIndexOfFirstInVariables(original.getIndexInVariables());
        } else {
            triplet.setIndexOfFirstInVariables(triplet.getIndexInVariables());
        }
        triplets.add(triplet);
        indexKept.add(triplet.getIndexInVariables());
    }

    public boolean contains(Truc truc) {
        for (TrucVariable triplet : triplets) {
            if (truc.getCleanedName().equals(triplet.getTruc().getCleanedName())) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Integer index) {
        for (TrucVariable triplet : triplets) {
            if (triplet.getIndexInVariables() == index) {
                return true;
            }
        }
        return false;
    }

    public TrucVariable getTriplet(int index) {
        for (TrucVariable triplet : triplets) {
            if (triplet.getIndexInVariables() == index) {
                return triplet;
            }
        }
        return null;
    }

    public TrucVariable getOriginalTriplet(int index) {
        for (TrucVariable triplet : triplets) {
            if (triplet.getIndexOfFirstInVariables() == index) {
                return triplet;
            }
        }
        return null;
    }

    public ArrayList<Integer> getIndexes() {
        return indexKept;
    }

    public ArrayList<TrucVariable> getTriplets() {
        return triplets;
    }
}

package fr.esilv.simpleparql.source.response;

import fr.esilv.simpleparql.source.model.Truc;

import java.util.ArrayList;

/**
 * List of responses triplets structure
 */
public class Triplets {
    private ArrayList<Triplet> triplets;
    private ArrayList<Integer> indexKept;


    public Triplets() {
        triplets = new ArrayList<>();
        indexKept = new ArrayList<>();
    }

    public void add(Triplet triplet) {
        if (triplets.contains(triplet)) {
            // if the truc exists already in the triplets list
            // that means it's not the first variable type of truc to be added here
            Triplet original = triplets.get(triplets.indexOf(triplet));
            triplet.setIndexOfFirstInVariables(original.getIndexOfFirstInVariables());
        } else {
            triplet.setIndexOfFirstInVariables(triplet.getIndexInVariables());
        }
        triplets.add(triplet);
        indexKept.add(triplet.getIndexInVariables());
    }

    public boolean contains(Truc truc) {
        for (Triplet triplet : triplets) {
            if (truc.getCleanedName().equals(triplet.getTruc().getCleanedName())) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Integer index) {
        for (Triplet triplet : triplets) {
            if (triplet.getIndexInVariables() == index) {
                return true;
            }
        }
        return false;
    }

    public Triplet getTriplet(int index) {
        for (Triplet triplet : triplets) {
            if (triplet.getIndexInVariables() == index) {
                return triplet;
            }
        }
        return null;
    }

    public ArrayList<Integer> getIndexes() {
        return indexKept;
    }

    public ArrayList<Triplet> getTriplets() {
        return triplets;
    }
}

package projectzulu.common.world2.architect;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.blueprint.BlueprintSet;

public class BlueprintSetStockpile {
    private HashMap<String, BlueprintSet> blueprintSetList = new HashMap<String, BlueprintSet>();
    public Random random;

    BlueprintSetStockpile(Random random) {
        this.random = random;
    }

    public void addBlueprintSet(BlueprintSet blueprintSet) {
        if (blueprintSet == null || blueprintSetList.containsKey(blueprintSet.getIdentifier())) {
            throw new IllegalArgumentException("BlueprintSet " + blueprintSet == null ? "cannot be null"
                    : "ID is already taken");
        }
        blueprintSetList.put(blueprintSet.getIdentifier(), blueprintSet);
    }

    public BlueprintSet getBlueprintSet(String setID) {
        return blueprintSetList.get(setID);
    }

    public BlueprintSet getBlueprintSet(MazeCell cell) {
        String[] parts = cell.getBuildingID().split("-");
        String setCategory = parts[0];
        return blueprintSetList.get(setCategory);
    }

    /**
     * Gets a random applicable blueprint for the given BuildCoords
     */
    public BlueprintSet getRandomApplicable(MazeCell[][] cells, Point buildCoords) {
        int totalWeight = 0;
        Collection<BlueprintSet> applicableSets = new ArrayList<BlueprintSet>();
        for (BlueprintSet set : blueprintSetList.values()) {
            if (set.isApplicable(cells, buildCoords, random) && set.getWeight() > 0) {
                totalWeight += set.getWeight();
                applicableSets.add(set);
            }
        }
        totalWeight = random.nextInt(totalWeight + 1);
        for (BlueprintSet set : applicableSets) {
            totalWeight -= set.getWeight();
            if (totalWeight <= 0) {
                return set;
            }
        }
        return null;
    }
}

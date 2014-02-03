package projectzulu.common.world2;

import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;

public class MazeCell {

    /** Size of the Cell. All Cells are by definition square */
    public final int size;

    /*
     * Initial block of this cell in the world
     */
    public final ChunkCoordinates initialPos;

    /**
     * Represents randomizer metadata applied to the Cell. This data is used to help the Architect place buildings.
     * 
     * i.e. In WalledMazeConstruction: 1 means wall, 0 means hallway
     */
    public int rawState = 0;

    /*
     * String Identifier used by the Architect representing the structure to be built here. Typically of the form
     * <blueprintSetID>-<blueprintID>
     */
    private String buildingID = "";

    public String getBuildingID() {
        return buildingID;
    }

    private CellIndexDirection direction;

    public CellIndexDirection getDirection() {
        return direction;
    }

    public void setBuildingProperties(String buildingID, CellIndexDirection direction) {
        if (buildingID == null || direction == null) {
            throw new IllegalArgumentException(buildingID == null ? "buildingID" : "CellDirection" + " cannot be null");
        }
        this.buildingID = buildingID;
        this.direction = direction;
    }

    /* Height of this cell in blocks */
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 1) {
            throw new IllegalArgumentException("Cell Height cannot be less than 1");
        }
        this.height = height;
    }

    public MazeCell(int size, int height, ChunkCoordinates initialPos) {
        this.size = size;
        this.height = height;
        this.initialPos = initialPos;
    }
}

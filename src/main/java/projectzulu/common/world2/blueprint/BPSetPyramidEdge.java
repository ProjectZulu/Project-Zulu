package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;

public class BPSetPyramidEdge implements BlueprintSet {

    final OuterEdge outerWall = new OuterEdge();
    final InnerEdge innerWall = new InnerEdge();

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        String buildName = getIdentifier();
        if (buildCoords.x == 0 || buildCoords.y == 0 || buildCoords.x == cells.length - 1
                || buildCoords.y == cells[0].length - 1) {
            buildName = buildName.concat("-").concat(outerWall.getIdentifier());
        } else {
            buildName = buildName.concat("-").concat(innerWall.getIdentifier());
        }

        cells[buildCoords.x][buildCoords.y].setBuildingProperties(buildName, getDirection(cells, buildCoords));
        cells[buildCoords.x][buildCoords.y].rawState = -1;
        return true;
    }

    private CellIndexDirection getDirection(MazeCell[][] cells, Point buildCoords) {
        if (buildCoords.x == 0 && buildCoords.y == 0 || buildCoords.x == 1 && buildCoords.y == 1) {
            return CellIndexDirection.NorthWestCorner;
        } else if (buildCoords.x == cells.length - 1 && buildCoords.y == 0 || buildCoords.x == cells.length - 2
                && buildCoords.y == 1) {
            return CellIndexDirection.NorthEastCorner;
        } else if (buildCoords.x == 0 && buildCoords.y == cells[0].length - 1 || buildCoords.x == 1
                && buildCoords.y == cells[0].length - 2) {
            return CellIndexDirection.SouthWestCorner;
        } else if (buildCoords.x == cells.length - 1 && buildCoords.y == cells[0].length - 1
                || buildCoords.x == cells.length - 2 && buildCoords.y == cells[0].length - 2) {
            return CellIndexDirection.SouthEastCorner;
        }

        if (buildCoords.x == 0) {
            return CellIndexDirection.WestWall;
        } else if (buildCoords.x == cells.length - 1) {
            return CellIndexDirection.EastWall;
        } else if (buildCoords.y == 0) {
            return CellIndexDirection.NorthWall;
        } else if (buildCoords.y == cells[0].length - 1) {
            return CellIndexDirection.SouthWall;
        }

        if (buildCoords.x == 1) {
            return CellIndexDirection.WestWall;
        } else if (buildCoords.x == cells.length - 2) {
            return CellIndexDirection.EastWall;
        } else if (buildCoords.y == 1) {
            return CellIndexDirection.NorthWall;
        } else if (buildCoords.y == cells[0].length - 2) {
            return CellIndexDirection.SouthWall;
        }

        if (buildCoords.x <= 1) {
            return CellIndexDirection.WestWall;
        } else if (buildCoords.x >= cells.length - 2) {
            return CellIndexDirection.EastWall;
        }

        if (buildCoords.y <= 1) {
            return CellIndexDirection.NorthWall;
        } else if (buildCoords.y >= cells[0].length - 2) {
            return CellIndexDirection.SouthWall;
        }
        return null;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        String blueprintID = buildingID.split("-")[1];
        if (blueprintID.equals(outerWall.getIdentifier())) {
            return outerWall.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
        } else if (blueprintID.equals(innerWall.getIdentifier())) {
            return innerWall.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
        }
        throw new IllegalArgumentException("Blueprint ID parsed from " + buildingID + " does not exist.");
    }

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        if (buildCoords.x <= 1 || buildCoords.y <= 1 || buildCoords.x >= cells.length - 2
                || buildCoords.y >= cells[0].length - 2) {
            return cells[buildCoords.x][buildCoords.y].rawState >= 0;
        }
        return false;
    }

    @Override
    public String getIdentifier() {
        return "PyramidEdge";
    }

    @Override
    public int getWeight() {
        return 0;
    }

    private class OuterEdge implements Blueprint {
        @Override
        public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
                Random random, CellIndexDirection cellIndexDirection) {
            int index = 0;
            if (cellIndexDirection.isCorner()) {
                int posX = piecePos.posX;
                if (cellIndexDirection != CellIndexDirection.NorthWestCorner
                        && cellIndexDirection != CellIndexDirection.SouthWestCorner) {
                    posX = (cellSize - 1) - piecePos.posX;
                }

                int posZ = piecePos.posZ;
                if (cellIndexDirection != CellIndexDirection.NorthWestCorner
                        && cellIndexDirection != CellIndexDirection.NorthEastCorner) {
                    posZ = (cellSize - 1) - piecePos.posZ;
                }

                if (piecePos.posY > posZ + index * cellSize || piecePos.posY > posX + index * cellSize) {
                    return new BlockWithMeta(Blocks.air);
                } else if (piecePos.posY == posZ + index * cellSize || piecePos.posY == posX + index * cellSize) {
                    return new BlockWithMeta(Blocks.sandstone);
                }
            } else {
                int pos = -1;
                switch (cellIndexDirection) {
                case NorthWall:
                    pos = piecePos.posZ;
                    break;
                case SouthWall:
                    pos = (cellSize - 1) - piecePos.posZ;
                    break;
                case WestWall:
                    pos = piecePos.posX;
                    break;
                case EastWall:
                    pos = (cellSize - 1) - piecePos.posX;
                    break;
                default:
                    pos = -1;
                    break;
                }

                if (pos > -1) {
                    if (piecePos.posY > pos + index * cellSize) {
                        return new BlockWithMeta(Blocks.air);
                    } else if (piecePos.posY == pos + index * cellSize) {
                        return new BlockWithMeta(Blocks.sandstone);
                    }
                }
            }
            return new BlockWithMeta(Blocks.sandstone);
        }

        @Override
        public int getWeight() {
            return 1;
        }

        @Override
        public String getIdentifier() {
            return "OuterWall";
        }
    }

    private class InnerEdge implements Blueprint {
        @Override
        public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
                Random random, CellIndexDirection cellIndexDirection) {
            int index = 1;
            if (cellIndexDirection.isCorner()) {
                int posX = piecePos.posX;
                if (cellIndexDirection != CellIndexDirection.NorthWestCorner
                        && cellIndexDirection != CellIndexDirection.SouthWestCorner) {
                    posX = (cellSize - 1) - piecePos.posX;
                }

                int posZ = piecePos.posZ;
                if (cellIndexDirection != CellIndexDirection.NorthWestCorner
                        && cellIndexDirection != CellIndexDirection.NorthEastCorner) {
                    posZ = (cellSize - 1) - piecePos.posZ;
                }

                if (piecePos.posY > posZ + index * cellSize || piecePos.posY > posX + index * cellSize) {
                    return new BlockWithMeta(Blocks.air);
                } else if (piecePos.posY == posZ + index * cellSize || piecePos.posY == posX + index * cellSize) {
                    return new BlockWithMeta(Blocks.sandstone);
                }
            } else {
                int pos = -1;
                switch (cellIndexDirection) {
                case NorthWall:
                    pos = piecePos.posZ;
                    break;
                case SouthWall:
                    pos = (cellSize - 1) - piecePos.posZ;
                    break;
                case WestWall:
                    pos = piecePos.posX;
                    break;
                case EastWall:
                    pos = (cellSize - 1) - piecePos.posX;
                    break;
                default:
                    pos = -1;
                    break;
                }

                if (pos > -1) {
                    if (piecePos.posY > pos + index * cellSize) {
                        return new BlockWithMeta(Blocks.air);
                    } else if (piecePos.posY == pos + index * cellSize) {
                        return new BlockWithMeta(Blocks.sandstone);
                    }
                }
            }
            return new BlockWithMeta(Blocks.sandstone);
        }

        @Override
        public int getWeight() {
            return 1;
        }

        @Override
        public String getIdentifier() {
            return "InnerWall";
        }
    }
}

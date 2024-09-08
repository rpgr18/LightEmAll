import java.util.ArrayList;
import java.awt.Color;
import javalib.worldimages.*;

// to represent a game piece
public class GamePiece implements IUtils {
  // in logical coordinates, with the origin
  // at the top-left corner of the screen
  int row;
  int col;
  // whether this GamePiece is connected to the
  // adjacent left, right, top, or bottom pieces
  boolean left;
  boolean right;
  boolean top;
  boolean bottom;
  // whether the power station is on this piece
  boolean powerStation;
  boolean powered;
  ArrayList<Edge> outEdges;

  // constructor
  GamePiece(int row, int col) {
    this.row = row;
    this.col = col;
    this.left = false;
    this.right = false;
    this.top = false;
    this.bottom = false;
    this.powerStation = false;
    this.powered = false;
    this.outEdges = new ArrayList<Edge>();
  }

  // constructor for testing
  GamePiece(int row, int col, boolean left, boolean right, boolean top, boolean bottom,
            boolean powerStation, boolean powered) {
    this.row = row;
    this.col = col;
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
    this.powerStation = powerStation;
    this.powered = powered;
    this.outEdges = new ArrayList<Edge>();
  }

  // generate an image of this GamePiece
  WorldImage tileImage() {
    // Start tile image off as a blue square with a wire-width square in the middle,
    // to make image "cleaner" (will look strange if tile has no wire, but that can't be)
    Color wireColor;
    if (this.powered) {
      wireColor = Color.yellow;
    }
    else {
      wireColor = Color.lightGray;
    }
    WorldImage image = new OverlayImage(
            new RectangleImage(WIRE_WIDTH, WIRE_WIDTH, OutlineMode.SOLID, wireColor),
            new RectangleImage(TILE_SIZE, TILE_SIZE, OutlineMode.SOLID, Color.DARK_GRAY));
    WorldImage vWire = new RectangleImage(WIRE_WIDTH, (TILE_SIZE + 1) / 2, OutlineMode.SOLID,
            wireColor);
    WorldImage hWire = new RectangleImage((TILE_SIZE + 1) / 2, WIRE_WIDTH, OutlineMode.SOLID,
            wireColor);

    if (this.top) {
      image = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP, vWire, 0, 0,
              image);
    }
    if (this.right) {
      image = new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE, hWire, 0,
              0, image);
    }
    if (this.bottom) {
      image = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM, vWire, 0,
              0, image);
    }
    if (this.left) {
      image = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE, hWire, 0,
              0, image);
    }
    if (this.powerStation) {
      image = new OverlayImage(
              new OverlayImage(
                      new StarImage(TILE_SIZE / 3, 7, OutlineMode.OUTLINE, new Color(255, 128, 0)),
                      new StarImage(TILE_SIZE / 3, 7, OutlineMode.SOLID, new Color(0, 255, 255))),
              image);
    }
    return image;
  }

  // rotates a tile 90 degrees clockwise
  void rotate() {
    boolean finalLeft = false;
    boolean finalRight = false;
    boolean finalTop = false;
    boolean finalBottom = false;
    if (this.left) {
      finalTop = true;
    }
    if (this.top) {
      finalRight = true;
    }
    if (this.right) {
      finalBottom = true;
    }
    if (this.bottom) {
      finalLeft = true;
    }
    this.left = finalLeft;
    this.right = finalRight;
    this.bottom = finalBottom;
    this.top = finalTop;
  }
}

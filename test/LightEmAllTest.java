import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.AboveImage;
import javalib.worldimages.AlignModeX;
import javalib.worldimages.AlignModeY;
import javalib.worldimages.BesideImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.OverlayOffsetAlign;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;
import tester.Tester;

// examples class
public class LightEmAllTest implements IUtils {
  LightEmAll lea1;
  LightEmAll lea2;
  LightEmAll lea3;
  GamePiece gp1;
  GamePiece gp5;
  GamePiece gp8;
  WorldImage im1;
  WorldImage im2;
  WorldImage im3;
  WorldImage im4;
  WorldImage im5;
  WorldImage im6;
  WorldImage im7;
  WorldImage im8;
  WorldImage im9;
  CompareWeights cw;
  Edge e1;
  Edge e2;
  Edge e3;


  // reinitializes all examples
  void reset() {
    this.lea1 = new LightEmAll(9, 6);
    this.lea2 = new LightEmAll(3, 3, new Random(7));
    this.lea3 = new LightEmAll(3, 3, new Random(8));
    this.gp1 = new GamePiece(0, 0, false, false, false, true, false, false);
    this.gp5 = new GamePiece(1, 1, true, true, true, true, true, true);
    this.gp8 = new GamePiece(1, 2, true, false, true, true, false, false);
    this.im1 = new OverlayImage(
            new OverlayImage(
                    new StarImage(TILE_SIZE / 3, 7, OutlineMode.OUTLINE, new Color(255, 128, 0)),
                    new StarImage(TILE_SIZE / 3, 7, OutlineMode.SOLID, new Color(0, 255, 255))),
            new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE, new RectangleImage(
                    (TILE_SIZE + 1) / 2, WIRE_WIDTH, OutlineMode.SOLID, Color.yellow), 0, 0,
                    new OverlayImage(
                            new RectangleImage(WIRE_WIDTH, WIRE_WIDTH, OutlineMode.SOLID, Color.yellow),
                            new RectangleImage(TILE_SIZE, TILE_SIZE, OutlineMode.SOLID, Color.DARK_GRAY))));
    this.im2 = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE, new RectangleImage(
            (TILE_SIZE + 1) / 2, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray), 0,
            0, new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM, new RectangleImage(
            WIRE_WIDTH, (TILE_SIZE + 1) / 2, OutlineMode.SOLID, Color.lightGray), 0, 0,
            new OverlayImage(
                    new RectangleImage(WIRE_WIDTH, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray),
                    new RectangleImage(TILE_SIZE, TILE_SIZE, OutlineMode.SOLID,
                            Color.DARK_GRAY))));
    this.im3 = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE, new RectangleImage(
            (TILE_SIZE + 1) / 2, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray), 0, 0,
            new OverlayImage(
                    new RectangleImage(WIRE_WIDTH, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray),
                    new RectangleImage(TILE_SIZE, TILE_SIZE, OutlineMode.SOLID, Color.DARK_GRAY)));
    this.im4 = new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE, new RectangleImage(
            (TILE_SIZE + 1) / 2, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray), 0,
            0, new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP, new RectangleImage(
            WIRE_WIDTH, (TILE_SIZE + 1) / 2, OutlineMode.SOLID, Color.lightGray), 0, 0,
            new OverlayImage(
                    new RectangleImage(WIRE_WIDTH, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray),
                    new RectangleImage(TILE_SIZE, TILE_SIZE, OutlineMode.SOLID,
                            Color.DARK_GRAY))));
    this.im5 = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE, new RectangleImage(
            (TILE_SIZE + 1) / 2, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray), 0, 0,
            new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM, new RectangleImage(
                    WIRE_WIDTH, (TILE_SIZE + 1) / 2, OutlineMode.SOLID, Color.lightGray), 0, 0,
                    new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP, new RectangleImage(
                            WIRE_WIDTH, (TILE_SIZE + 1) / 2, OutlineMode.SOLID, Color.lightGray), 0, 0,
                            new OverlayImage(
                                    new RectangleImage(WIRE_WIDTH, WIRE_WIDTH, OutlineMode.SOLID,
                                            Color.lightGray),
                                    new RectangleImage(TILE_SIZE, TILE_SIZE, OutlineMode.SOLID,
                                            Color.DARK_GRAY)))));
    this.im6 = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE, new RectangleImage(
            (TILE_SIZE + 1) / 2, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray), 0,
            0, new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM, new RectangleImage(
            WIRE_WIDTH, (TILE_SIZE + 1) / 2, OutlineMode.SOLID, Color.lightGray), 0, 0,
            new OverlayImage(
                    new RectangleImage(WIRE_WIDTH, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray),
                    new RectangleImage(TILE_SIZE, TILE_SIZE, OutlineMode.SOLID,
                            Color.DARK_GRAY))));
    this.im7 = new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE, new RectangleImage(
            (TILE_SIZE + 1) / 2, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray), 0, 0,
            new OverlayImage(
                    new RectangleImage(WIRE_WIDTH, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray),
                    new RectangleImage(TILE_SIZE, TILE_SIZE, OutlineMode.SOLID, Color.DARK_GRAY)));
    this.im8 = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM, new RectangleImage(
            WIRE_WIDTH, (TILE_SIZE + 1) / 2, OutlineMode.SOLID, Color.lightGray), 0,
            0, new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP, new RectangleImage(
            WIRE_WIDTH, (TILE_SIZE + 1) / 2, OutlineMode.SOLID, Color.lightGray), 0, 0,
            new OverlayImage(
                    new RectangleImage(WIRE_WIDTH, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray),
                    new RectangleImage(TILE_SIZE, TILE_SIZE, OutlineMode.SOLID,
                            Color.DARK_GRAY))));
    this.im9 = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM, new RectangleImage(
            WIRE_WIDTH, (TILE_SIZE + 1) / 2, OutlineMode.SOLID, Color.lightGray), 0,
            0, new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE, new RectangleImage(
            (TILE_SIZE + 1) / 2, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray), 0, 0,
            new OverlayImage(
                    new RectangleImage(WIRE_WIDTH, WIRE_WIDTH, OutlineMode.SOLID, Color.lightGray),
                    new RectangleImage(TILE_SIZE, TILE_SIZE, OutlineMode.SOLID,
                            Color.DARK_GRAY))));
    this.cw = new CompareWeights();
    this.e1 = new Edge(this.gp1, this.gp5, 4);
    this.e2 = new Edge(this.gp1, this.gp8, 20);
    this.e3 = new Edge(this.gp8, this.gp5, 10);

    // make lea3 all connected
    this.lea3.onMouseClicked(new Posn(64, 40), "LeftButton");
    this.lea3.onMouseClicked(new Posn(64, 40), "LeftButton");
    this.lea3.onMouseClicked(new Posn(64, 64), "LeftButton");
    this.lea3.onMouseClicked(new Posn(64, 64), "LeftButton");
    this.lea3.onMouseClicked(new Posn(64, 64), "LeftButton");
    this.lea3.onMouseClicked(new Posn(130, 40), "LeftButton");
    this.lea3.onMouseClicked(new Posn(130, 60), "LeftButton");
    this.lea3.onMouseClicked(new Posn(130, 60), "LeftButton");
    this.lea3.onMouseClicked(new Posn(47, 64), "LeftButton");
    this.lea3.onMouseClicked(new Posn(47, 119), "LeftButton");
    this.lea3.onMouseClicked(new Posn(47, 119), "LeftButton");
    this.lea3.onMouseClicked(new Posn(123, 123), "LeftButton");
    this.lea3.onMouseClicked(new Posn(123, 123), "LeftButton");
    this.lea3.onMouseClicked(new Posn(123, 123), "LeftButton");
  }

  // tests the initializeBoard method of the LightEmAll class
  boolean testInitializeBoard(Tester t) {
    this.reset();

    ArrayList<ArrayList<GamePiece>> expected = new ArrayList<ArrayList<GamePiece>>(Arrays.asList(
            new ArrayList<GamePiece>(Arrays.asList(new GamePiece(0, 0), new GamePiece(1, 0),
                    new GamePiece(2, 0))),
            new ArrayList<GamePiece>(Arrays.asList(new GamePiece(0, 1), new GamePiece(1, 1),
                    new GamePiece(2, 1))),
            new ArrayList<GamePiece>(Arrays.asList(new GamePiece(0, 2), new GamePiece(1, 2),
                    new GamePiece(2, 2)))));
    expected.get(0).get(0).powered = true;
    expected.get(0).get(0).powerStation = true;

    return t.checkExpect(this.lea2.initializeBoard(), expected);
  }

  // tests the updateEdges method of the LightEmAll class
  void testUpdateEdges(Tester t) {
    this.reset();

    // test initial conditions
    t.checkExpect(this.lea2.board.get(0).get(0).outEdges, new ArrayList<Edge>());
    t.checkExpect(this.lea2.board.get(0).get(1).outEdges, new ArrayList<Edge>());
    t.checkExpect(this.lea2.board.get(1).get(1).outEdges, new ArrayList<Edge>());

    // make changes to board
    this.lea2.board.get(0).get(1).rotate();
    this.lea2.board.get(0).get(1).rotate();
    this.lea2.board.get(0).get(0).rotate();

    // call the method
    this.lea2.updateEdges();

    // test final conditions
    t.checkExpect(this.lea2.board.get(0).get(0).outEdges, new ArrayList<Edge>(Arrays.asList(
            new Edge(this.lea2.board.get(0).get(0), this.lea2.board.get(0).get(1), 1))));
    t.checkExpect(this.lea2.board.get(0).get(1).outEdges, new ArrayList<Edge>(Arrays.asList(
            new Edge(this.lea2.board.get(0).get(1), this.lea2.board.get(0).get(0), 1),
            new Edge(this.lea2.board.get(0).get(1), this.lea2.board.get(1).get(1), 1))));
    t.checkExpect(this.lea2.board.get(1).get(1).outEdges, new ArrayList<Edge>(Arrays.asList(
            new Edge(this.lea2.board.get(1).get(1), this.lea2.board.get(0).get(1), 1))));
  }

  // tests the onKeyReleased method of the LightEmAll class
  void testOnKeyReleased(Tester t) {
    this.reset();

    // change board
    this.lea2.onMouseClicked(new Posn(60, 30), "LeftButton");
    this.lea2.onMouseClicked(new Posn(60, 20), "LeftButton");

    // test initial conditions
    t.checkExpect(this.lea2.powerCol, 0);
    t.checkExpect(this.lea2.powerRow, 0);
    t.checkExpect(this.lea2.board.get(0).get(0).powerStation, true);
    t.checkExpect(this.lea2.board.get(0).get(0).powered, true);

    // call method
    this.lea2.onKeyReleased("up");
    this.lea2.onKeyReleased("left");

    // test new conditions
    t.checkExpect(this.lea2.powerCol, 0);
    t.checkExpect(this.lea2.powerRow, 0);
    t.checkExpect(this.lea2.board.get(0).get(0).powerStation, true);
    t.checkExpect(this.lea2.board.get(0).get(0).powered, true);

    // call method
    this.lea2.onKeyReleased("right");

    // test new conditions
    t.checkExpect(this.lea2.powerCol, 1);
    t.checkExpect(this.lea2.powerRow, 0);
    t.checkExpect(this.lea2.board.get(1).get(0).powerStation, true);
    t.checkExpect(this.lea2.board.get(1).get(0).powered, true);
    t.checkExpect(this.lea2.board.get(0).get(0).powerStation, false);
    t.checkExpect(this.lea2.board.get(0).get(0).powered, true);

    // call method
    this.lea2.onKeyReleased("down");

    // test new conditions
    t.checkExpect(this.lea2.powerCol, 1);
    t.checkExpect(this.lea2.powerRow, 1);
    t.checkExpect(this.lea2.board.get(1).get(1).powerStation, true);
    t.checkExpect(this.lea2.board.get(1).get(1).powered, true);
    t.checkExpect(this.lea2.board.get(1).get(0).powerStation, false);
    t.checkExpect(this.lea2.board.get(1).get(0).powered, true);
  }

  // tests the updatePower method of the LightEmAll class
  void testUpdatePower(Tester t) {
    this.reset();

    // make changes to board
    this.lea2.board.get(0).get(1).rotate();
    this.lea2.board.get(0).get(1).rotate();
    this.lea2.board.get(0).get(0).rotate();
    this.lea2.updateEdges();

    // test initial conditions
    t.checkExpect(this.lea2.board.get(1).get(1).powered, false);
    t.checkExpect(this.lea2.board.get(0).get(1).powered, false);
    t.checkExpect(this.lea2.board.get(0).get(0).powered, true);
    t.checkExpect(this.lea2.board.get(0).get(2).powered, false);
    t.checkExpect(this.lea2.board.get(1).get(2).powered, false);
    t.checkExpect(this.lea2.board.get(1).get(0).powered, false);
    t.checkExpect(this.lea2.board.get(2).get(2).powered, false);

    // call method
    this.lea2.updatePower();

    // test final conditions
    t.checkExpect(this.lea2.board.get(1).get(1).powered, true);
    t.checkExpect(this.lea2.board.get(0).get(1).powered, true);
    t.checkExpect(this.lea2.board.get(0).get(0).powered, true);
    t.checkExpect(this.lea2.board.get(0).get(2).powered, false);
    t.checkExpect(this.lea2.board.get(1).get(2).powered, false);
    t.checkExpect(this.lea2.board.get(1).get(0).powered, false);
    t.checkExpect(this.lea2.board.get(2).get(2).powered, false);
  }

  // to test the hasPathBetween method of the LightEmAll class
  boolean testHasPathBetween(Tester t) {
    this.reset();

    return t.checkExpect(this.lea3.hasPathBetween(this.lea3.board.get(0).get(0),
            this.lea3.board.get(0).get(2)), true)
            && t.checkExpect(this.lea2.hasPathBetween(this.lea2.board.get(1).get(1),
            this.lea2.board.get(0).get(0)), false)
            && t.checkExpect(this.lea3.hasPathBetween(this.lea3.board.get(0).get(2),
            this.lea3.board.get(0).get(0)), true)
            && t.checkExpect(this.lea2.hasPathBetween(this.lea2.board.get(0).get(0),
            this.lea2.board.get(1).get(1)), false);
  }

  // to test the onMouseClicked method of the LightEmAll class
  void testOnMouseClicked(Tester t) {
    this.reset();

    // test initial conditions
    t.checkExpect(this.lea2.board.get(0).get(0).left, false);
    t.checkExpect(this.lea2.board.get(0).get(0).right, true);
    t.checkExpect(this.lea2.board.get(0).get(0).top, false);
    t.checkExpect(this.lea2.board.get(0).get(0).bottom, false);
    t.checkExpect(this.lea2.board.get(0).get(1).left, true);
    t.checkExpect(this.lea2.board.get(0).get(1).right, false);
    t.checkExpect(this.lea2.board.get(0).get(1).top, false);
    t.checkExpect(this.lea2.board.get(0).get(1).bottom, true);
    t.checkExpect(this.lea2.board.get(0).get(0).powered, true);
    t.checkExpect(this.lea2.board.get(0).get(1).powered, false);
    t.checkExpect(this.lea2.board.get(1).get(1).powered, false);

    // call method
    this.lea2.onMouseClicked(new Posn(20, 20), "LeftButton");
    this.lea2.onMouseClicked(new Posn(20, 67), "LeftButton");
    this.lea2.onMouseClicked(new Posn(20, 67), "LeftButton");

    // test final conditions
    t.checkExpect(this.lea2.board.get(0).get(0).left, false);
    t.checkExpect(this.lea2.board.get(0).get(0).right, false);
    t.checkExpect(this.lea2.board.get(0).get(0).top, false);
    t.checkExpect(this.lea2.board.get(0).get(0).bottom, true);
    t.checkExpect(this.lea2.board.get(0).get(1).left, false);
    t.checkExpect(this.lea2.board.get(0).get(1).right, true);
    t.checkExpect(this.lea2.board.get(0).get(1).top, true);
    t.checkExpect(this.lea2.board.get(0).get(1).bottom, false);
    t.checkExpect(this.lea2.board.get(1).get(1).powered, true);
    t.checkExpect(this.lea2.board.get(0).get(1).powered, true);
    t.checkExpect(this.lea2.board.get(0).get(0).powered, true);
  }

  // to test the allPowered method of the LightEmAll class
  boolean testAllPowered(Tester t) {
    this.reset();

    return t.checkExpect(this.lea2.allPowered(), false)
            && t.checkExpect(this.lea3.allPowered(), true);
  }

  // to test the makeScene method of the LightEmAll class
  boolean testMakeScene(Tester t) {
    this.reset();

    WorldScene scene = new WorldScene(TILE_SIZE * 3, TILE_SIZE * 3);
    scene.placeImageXY(new BesideImage(new BesideImage(new AboveImage(new AboveImage(this.im1,
                    this.im2), this.im3), new AboveImage(new AboveImage(this.im4, this.im5), this.im6)),
                    new AboveImage(new AboveImage(this.im7, this.im8), this.im9)), TILE_SIZE * 3 / 2,
            TILE_SIZE * 3 / 2);

    return t.checkExpect(this.lea2.makeScene(), scene);
  }

  // to test the lastScene method of the LightEmAll class
  boolean testLastScene(Tester t) {
    this.reset();

    WorldScene scene = new WorldScene(TILE_SIZE * 3, TILE_SIZE * 3);
    scene.placeImageXY(new OverlayImage(new TextImage("You win!", TILE_SIZE * 3 / 5,
                    Color.green), new BesideImage(new BesideImage(new AboveImage(new AboveImage(this.im1,
                    this.im2), this.im3), new AboveImage(new AboveImage(this.im4, this.im5), this.im6)),
                    new AboveImage(new AboveImage(this.im7, this.im8), this.im9))), TILE_SIZE * 3 / 2,
            TILE_SIZE * 3 / 2);

    return t.checkExpect(this.lea2.lastScene("You win!"), scene);
  }

  // to test the rotate method of the GamePiece class
  void testRotate(Tester t) {
    this.reset();

    // test initial conditions
    t.checkExpect(this.gp1.left, false);
    t.checkExpect(this.gp1.right, false);
    t.checkExpect(this.gp1.top, false);
    t.checkExpect(this.gp1.bottom, true);
    t.checkExpect(this.gp5.left, true);
    t.checkExpect(this.gp5.right, true);
    t.checkExpect(this.gp5.top, true);
    t.checkExpect(this.gp5.bottom, true);
    t.checkExpect(this.gp8.left, true);
    t.checkExpect(this.gp8.right, false);
    t.checkExpect(this.gp8.top, true);
    t.checkExpect(this.gp8.bottom, true);

    // call method
    this.gp1.rotate();
    this.gp5.rotate();
    this.gp8.rotate();

    // test final conditions
    t.checkExpect(this.gp1.left, true);
    t.checkExpect(this.gp1.right, false);
    t.checkExpect(this.gp1.top, false);
    t.checkExpect(this.gp1.bottom, false);
    t.checkExpect(this.gp5.left, true);
    t.checkExpect(this.gp5.right, true);
    t.checkExpect(this.gp5.top, true);
    t.checkExpect(this.gp5.bottom, true);
    t.checkExpect(this.gp8.left, true);
    t.checkExpect(this.gp8.right, true);
    t.checkExpect(this.gp8.top, true);
    t.checkExpect(this.gp8.bottom, false);
  }

  // tests that the getMST method of the LightEmAll class returns a list with the correct amount
  // of edges
  boolean testGetMST(Tester t) {
    this.reset();

    return t.checkExpect(this.lea2.getMST().size(), this.lea2.nodes.size() - 1);
  }

  // to test the compare function of the CompareWeights class
  boolean testCompare(Tester t) {
    this.reset();

    return t.checkExpect(this.cw.compare(e1, e2), -16)
            && t.checkExpect(this.cw.compare(e3, e2), -10)
            && t.checkExpect(this.cw.compare(e3, e1), 6);
  }

  void testBigBang(Tester t) {
    reset();

    World world = new LightEmAll(10, 8);
    world.bigBang(10 * TILE_SIZE, 8 * TILE_SIZE);
  }
}
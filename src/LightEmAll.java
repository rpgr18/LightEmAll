import java.util.ArrayList;
import javalib.impworld.*;
import java.util.Random;
import javalib.worldimages.*;

// to represent a LightEmAll world
public class LightEmAll extends World implements IUtils {
  // a list of columns of GamePieces,
  // i.e., represents the board in column-major order
  ArrayList<ArrayList<GamePiece>> board;
  // a list of all nodes
  ArrayList<GamePiece> nodes;
  // a list of edges of the minimum spanning tree
  ArrayList<Edge> mst;
  // the width and height of the board
  int width;
  int height;
  // the current location of the power station,
  // as well as its effective radius
  int powerRow;
  int powerCol;
  int radius;
  Random rand;

  // constructor
  LightEmAll(int width, int height) {
    this.width = width;
    this.height = height;
    this.powerRow = 0;
    this.powerCol = 0;
    this.rand = new Random();
    this.nodes = new ArrayList<GamePiece>();
    this.board = this.initializeBoard();
    this.mst = this.getMST();

    for (Edge e : this.mst) {
      if (e.toNode.row - e.fromNode.row == 1) {
        e.fromNode.bottom = true;
        e.toNode.top = true;
      }
      if (e.toNode.row - e.fromNode.row == -1) {
        e.fromNode.top = true;
        e.toNode.bottom = true;
      }
      if (e.toNode.col - e.fromNode.col == 1) {
        e.fromNode.right = true;
        e.toNode.left = true;
      }
      if (e.toNode.col - e.fromNode.col == -1) {
        e.fromNode.left = true;
        e.toNode.right = true;
      }
    }

    this.initialRotations();
    this.updateEdges();
    this.updatePower();
  }

  // constructor for testing
  LightEmAll(int width, int height, Random rand) {
    this.width = width;
    this.height = height;
    this.powerRow = 0;
    this.powerCol = 0;
    this.rand = rand;
    this.nodes = new ArrayList<GamePiece>();
    this.board = this.initializeBoard();
    this.mst = this.getMST();

    for (Edge e : this.mst) {
      if (e.toNode.row - e.fromNode.row == 1) {
        e.fromNode.bottom = true;
        e.toNode.top = true;
      }
      if (e.toNode.row - e.fromNode.row == -1) {
        e.fromNode.top = true;
        e.toNode.bottom = true;
      }
      if (e.toNode.col - e.fromNode.col == 1) {
        e.fromNode.right = true;
        e.toNode.left = true;
      }
      if (e.toNode.col - e.fromNode.col == -1) {
        e.fromNode.left = true;
        e.toNode.right = true;
      }
    }

    this.initialRotations();
    this.updateEdges();
    this.updatePower();
  }

  // creates an ArrayList<ArrayList<GamePiece>> for the initial gameboard
  ArrayList<ArrayList<GamePiece>> initializeBoard() {
    ArrayList<ArrayList<GamePiece>> board = new ArrayList<ArrayList<GamePiece>>();
    for (int i = 0; i < this.width; i++) {
      ArrayList<GamePiece> column = new ArrayList<GamePiece>();
      for (int j = 0; j < this.height; j++) {
        GamePiece temp = new GamePiece(j, i);
        column.add(temp);
        nodes.add(temp);
      }
      board.add(column);
    }
    board.get(this.powerCol).get(this.powerRow).powerStation = true;
    board.get(this.powerCol).get(this.powerRow).powered = true;
    return board;
  }

  // draws the current state of the MinesweeperWorld
  public WorldScene makeScene() {
    WorldImage imageFinal = new EmptyImage();
    for (int i = 0; i < this.width; i++) {
      WorldImage temp = this.board.get(i).get(0).tileImage();
      for (int j = 1; j < this.height; j++) {
        temp = new AboveImage(temp, this.board.get(i).get(j).tileImage());
      }
      imageFinal = new BesideImage(imageFinal, temp);
    }
    WorldScene scene = new WorldScene(TILE_SIZE * this.width, TILE_SIZE * this.height);
    scene.placeImageXY(imageFinal, TILE_SIZE * this.width / 2, TILE_SIZE * this.height / 2);
    return scene;
  }

  // rotates the clicked tile
  public void onMouseClicked(Posn p, String buttonName) {
    int col = p.x / TILE_SIZE;
    int row = p.y / TILE_SIZE;
    if (buttonName.equals("LeftButton")) {
      this.board.get(col).get(row).rotate();
    }

    this.updateEdges();
    this.updatePower();
    if (this.allPowered()) {
      this.endOfWorld("You win!");
    }
  }

  // moves the power station with the arrow keys
  public void onKeyReleased(String keyName) {
    this.board.get(powerCol).get(powerRow).powerStation = false;
    if (keyName.equals("up") && this.board.get(powerCol).get(powerRow).top
            && this.board.get(powerCol).get(powerRow - 1).bottom) {
      this.powerRow--;
    }
    else if (keyName.equals("left") && this.board.get(powerCol).get(powerRow).left
            && this.board.get(powerCol - 1).get(powerRow).right) {
      this.powerCol--;
    }
    else if (keyName.equals("down") && this.board.get(powerCol).get(powerRow).bottom
            && this.board.get(powerCol).get(powerRow + 1).top) {
      this.powerRow++;
    }
    else if (keyName.equals("right") && this.board.get(powerCol).get(powerRow).right
            && this.board.get(powerCol + 1).get(powerRow).left) {
      this.powerCol++;
    }
    this.board.get(powerCol).get(powerRow).powerStation = true;

    this.updatePower();
    if (this.allPowered()) {
      this.endOfWorld("You win!");
    }
  }

  // checks if the board has a path between the two given game pieces
  boolean hasPathBetween(GamePiece from, GamePiece to) {
    ArrayList<GamePiece> alreadySeen = new ArrayList<GamePiece>();
    ArrayList<GamePiece> worklist = new ArrayList<GamePiece>();

    // Initialize the worklist with the from vertex
    worklist.add(from);
    // As long as the worklist isn't empty...
    while (!worklist.isEmpty()) {
      GamePiece next = worklist.remove(worklist.size() - 1);
      if (next.equals(to)) {
        return true; // Success!
      }
      else if (alreadySeen.contains(next)) {
        // do nothing: we've already seen this one
      }
      else {
        // add all the neighbors of next to the worklist for further processing
        for (Edge e : next.outEdges) {
          worklist.add(e.toNode);
        }
        // add next to alreadySeen, since we're done with it
        alreadySeen.add(next);
      }
    }
    // We haven't found the to vertex, and there are no more to try
    return false;
  }

  // updates the edges in the pieces of the board
  void updateEdges() {
    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        ArrayList<Edge> temp = new ArrayList<Edge>();
        GamePiece fromPiece = this.board.get(i).get(j);
        if (j != 0) {
          GamePiece toPiece = this.board.get(i).get(j - 1);
          if (fromPiece.top && toPiece.bottom) {
            temp.add(new Edge(fromPiece, toPiece, 1));
          }
        }
        if (j != this.height - 1) {
          GamePiece toPiece = this.board.get(i).get(j + 1);
          if (fromPiece.bottom && toPiece.top) {
            temp.add(new Edge(fromPiece, toPiece, 1));
          }
        }
        if (i != 0) {
          GamePiece toPiece = this.board.get(i - 1).get(j);
          if (fromPiece.left && toPiece.right) {
            temp.add(new Edge(fromPiece, toPiece, 1));
          }
        }
        if (i != this.width - 1) {
          GamePiece toPiece = this.board.get(i + 1).get(j);
          if (fromPiece.right && toPiece.left) {
            temp.add(new Edge(fromPiece, toPiece, 1));
          }
        }
        fromPiece.outEdges = temp;
      }
    }
  }

  // creates a minimum spanning tree of the board's game pieces using Kruskal's algorithm
  ArrayList<Edge> getMST() {
    ArrayList<Edge> mst = new ArrayList<Edge>();
    ArrayList<Edge> temp = new ArrayList<Edge>();
    HashMap<Posn, Posn> representatives = new HashMap<Posn, Posn>();

    for (GamePiece gp : this.nodes) {
      if (gp.row != 0) {
        temp.add(new Edge(gp, this.board.get(gp.col).get(gp.row - 1),
                (this.rand.nextInt(10) + 1) * 5));
      }
      if (gp.col != 0) {
        temp.add(new Edge(gp, this.board.get(gp.col - 1).get(gp.row),
                (this.rand.nextInt(10) + 1) * 5));
      }
      representatives.put(new Posn(gp.row, gp.col), new Posn(gp.row, gp.col));
    }
    temp.sort(new CompareWeights());

    while (mst.size() < this.nodes.size() - 1) {
      Edge e = temp.remove(0);
      GamePiece fromNode = e.fromNode;
      GamePiece toNode = e.toNode;
      Posn p1 = this.find(new Posn(fromNode.row, fromNode.col), representatives);
      Posn p2 = this.find(new Posn(toNode.row, toNode.col), representatives);
      if (!(p1.x == p2.x && p1.y == p2.y)) {
        mst.add(e);
        representatives.put(representatives.get(p2), representatives.get(p1));
      }
    }

    return mst;
  }

  Posn find(Posn p, HashMap<Posn, Posn> representatives) {
    if (p.x == representatives.get(p).x && p.y == representatives.get(p).y) {
      return p;
    }
    else {
      return this.find(representatives.get(p), representatives);
    }
  }

  // makes any cells connected to the power station powered
  void updatePower() {
    for (GamePiece gp : this.nodes) {
      if (this.hasPathBetween(gp, this.board.get(powerCol).get(powerRow))) {
        gp.powered = true;
      }
      else {
        gp.powered = false;
      }
    }
  }

  // returns true if all cells are powered
  boolean allPowered() {
    boolean temp = true;
    for (GamePiece gp : this.nodes) {
      if (!this.hasPathBetween(gp, this.board.get(powerCol).get(powerRow))) {
        temp = false;
      }
    }
    return temp;
  }

  // randomly rotates all gamepieces on the board
  void initialRotations() {
    for (GamePiece gp : nodes) {
      int temp = this.rand.nextInt(4);
      while (temp > 0) {
        gp.rotate();
        temp--;
      }
    }
  }

  // draws the final scene with the given message
  public WorldScene lastScene(String msg) {
    WorldImage imageFinal = new EmptyImage();
    for (int i = 0; i < this.width; i++) {
      WorldImage temp = this.board.get(i).get(0).tileImage();
      for (int j = 1; j < this.height; j++) {
        temp = new AboveImage(temp, this.board.get(i).get(j).tileImage());
      }
      imageFinal = new BesideImage(imageFinal, temp);
    }
    imageFinal = new OverlayImage(new TextImage(msg, TILE_SIZE * this.width / 5, Color.green),
            imageFinal);
    WorldScene scene = new WorldScene(TILE_SIZE * this.width, TILE_SIZE * this.height);
    scene.placeImageXY(imageFinal, TILE_SIZE * this.width / 2, TILE_SIZE * this.height / 2);
    return scene;
  }
}
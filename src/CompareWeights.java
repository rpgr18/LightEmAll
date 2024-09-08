import java.util.Comparator;

// to compare Edge weights
class CompareWeights implements Comparator<Edge> {
  // compares the two given edges by their weights
  public int compare(Edge e1, Edge e2) {
    return e1.weight - e2.weight;
  }
}
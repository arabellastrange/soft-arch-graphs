package graph;

public interface Edge {

    String getLabel();

    Node origin();

    Node destination();
}
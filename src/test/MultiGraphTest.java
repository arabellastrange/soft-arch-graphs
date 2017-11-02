package test;

import graph.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ioanluca on 01/11/17.
 */
class MultiGraphTest {
    private MultiGraph multiGraph;

    @BeforeEach
    void setUp() {
        multiGraph = new MultiGraph();

        for (int i = 1; i <= 7; i++) {
            multiGraph.addNode(new Station(i));
        }

        multiGraph.addEdge(new Line("l1", new Station(1), new Station(2)));
        multiGraph.addEdge(new Line("l2", new Station(1), new Station(3)));
        multiGraph.addEdge(new Line("l3", new Station(1), new Station(4)));
        multiGraph.addEdge(new Line("l4", new Station(2), new Station(5)));
        multiGraph.addEdge(new Line("l5", new Station(5), new Station(3)));
        multiGraph.addEdge(new Line("l4", new Station(5), new Station(3)));
        multiGraph.addEdge(new Line("cycle 1 2 1", new Station(2), new Station(1)));
        multiGraph.addEdge(new Line("loop 1", new Station(1), new Station(1)));
        multiGraph.addEdge(new Line("l6", new Station(5), new Station(6)));
        multiGraph.addEdge(new Line("l2", new Station(4), new Station(6)));
    }

    @Test
    void getNode_NodeExists_Node() {
        Node existingNode = new Station(1);
        assertEquals(multiGraph.getNode(1), existingNode);
    }

    @Test
    void getNode_NodeNotExists_Null() {
        assertNull(multiGraph.getNode(1233));
    }

    @Test
    void addNode_ExistingNode_False() {
        Node existingNode = new Station(3);
        assertFalse(multiGraph.addNode(existingNode));
    }

    @Test
    void addNode_NewNode_True() {
        Node newNode = new Station(17);
        assertTrue(multiGraph.addNode(newNode));
    }

    @Test
    void getNode_AfterAddNewNodeToEmptyGraph_Node() {
        MultiGraph g = new MultiGraph();
        Node newNode = new Station(6);
        g.addNode(newNode);
        assertEquals(g.getNode(6), newNode);
    }

    @Test
    void addEdge_ExistingEdge_False() {
        Edge existingEdge = new Line("l1", new Station(1), new Station(2));
        assertFalse(multiGraph.addEdge(existingEdge));
    }

    @Test
    void addEdge_NewEdgeSameNodesDifferentLabel_True() {
        Edge differentLabelEdge = new Line("k1", new Station(1), new Station(2));
        assertTrue(multiGraph.addEdge(differentLabelEdge));
    }

    @Test
    void addEdge_NewEdge_True() {
        Edge newEdge = new Line("im a new edge", new Station(2), new Station(3));
        assertTrue(multiGraph.addEdge(newEdge));
    }

    @Test
    void addEdge_NewMultiEdge_True() {
        Edge newMultiEdge = new Line("Im a multiedge", new Station(1), new Station(2));
        assertTrue(multiGraph.addEdge(newMultiEdge));
    }

    @Test
    void addEdge_NewLoop_True() {
        Edge newLoop = new Line("Im a new loop", new Station(1), new Station(1));
        assertTrue(multiGraph.addEdge(newLoop));
    }

    @Test
    void addEdge_NewEdgeNoDest_TrueAndAddsDest() {
        Edge newEdgeNoDest = new Line("I have no valid dest", new Station(2), new Station(404));
        assertTrue(multiGraph.addEdge(newEdgeNoDest));
        assertEquals(multiGraph.getNode(404), new Station(404));
    }

    @Test
    void addEdge_NewEdgeNoOrigin_TrueAndAddsOrigin() {
        Edge newEdgeNoOrigin = new Line("I have no valid origin", new Station(404), new Station(2));
        assertTrue(multiGraph.addEdge(newEdgeNoOrigin));
        assertEquals(multiGraph.getNode(404), new Station(404));
    }

    @Test
    void addEdge_NewEdgeNoBoth_TrueAndAddsBoth() {
        Edge newEdgeNoBoth = new Line("I have no valid nodes", new Station(404), new Station(601));
        assertTrue(multiGraph.addEdge(newEdgeNoBoth));
        assertEquals(multiGraph.getNode(404), new Station(404));
        assertEquals(multiGraph.getNode(601), new Station(601));
    }

    @Test
    void findPath_NoStartNode_Null() {
        assertNull(multiGraph.findPath(new Station(404), new Station(1)));
    }

    @Test
    void findPath_NoDestinationNode() {
        assertNull(multiGraph.findPath(new Station(1), new Station(404)));
    }

    @Test
    void findPath_NoPath1_EmptyDeque() {
        Deque<Edge> path = multiGraph.findPath(new Station(5), new Station(7));
        assertTrue(path.isEmpty());
    }

    @Test
    void findPath_SourceEqualsDestinationAndPath_NonEmptyPath() {
        Deque<Edge> path = multiGraph.findPath(new Station(1), new Station(1));
        assertFalse(path.isEmpty());
    }

    @Test
    void findPath_ValidConnectedNodes1_ValidPath() {
        Node start = new Station(1);
        Node dest = new Station(6);
        Deque<Edge> path = multiGraph.findPath(start, dest);
        assertEquals(start, path.getFirst().origin());
        assertEquals(dest, path.getLast().destination());

        while (path.size() >= 2) {
            Edge head = path.poll();
            Edge afterHead = path.peek();
            assertEquals(head.destination(), afterHead.origin());
        }
    }

    @Test
    void findPath_ValidConnectedNodes2_ValidPath() {
        Node start = new Station(6);
        Node dest = new Station(1);
        Deque<Edge> path = multiGraph.findPath(start, dest);
        assertEquals(start, path.getFirst().origin());
        assertEquals(dest, path.getLast().destination());

        while (path.size() >= 2) {
            Edge head = path.poll();
            Edge afterHead = path.peek();
            assertEquals(head.destination(), afterHead.origin());
        }
    }

    @Test
    void findPath_NoPath2_EmptyDeque() {
        Deque<Edge> path = multiGraph.findPath(new Station(6), new Station(1));
        assertTrue(path.isEmpty());
    }

    @Test
    void findPath_ValidConnectedNodes3_ValidPath() {
        Node start = new Station(2);
        Node dest = new Station(3);
        Deque<Edge> path = multiGraph.findPath(start, dest);
        assertEquals(start, path.getFirst().origin());
        assertEquals(dest, path.getLast().destination());

        while (path.size() >= 2) {
            Edge head = path.poll();
            Edge afterHead = path.peek();
            assertEquals(head.destination(), afterHead.origin());
        }
    }


}
package algorithms;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;
import utils.Point3D;

public class Graph_AlgoTest
{

	@Test
	public void testInitGraph()
	{
		DGraph g1 = new DGraph();
		Graph_Algo ga = new Graph_Algo();
		ga.init(g1);
		assertEquals(g1, ga.g);
	}

	@Test
	public void testInitString()
	{
		DGraph g1 = new DGraph();
		Graph_Algo ga = new Graph_Algo();
		ga.init(g1);
		ga.save("myFile.txt");
		Graph_Algo ga1 = new Graph_Algo();
		ga1.init("myFile.txt");
		System.out.println(ga1.g.getNode(0));
	}

	@Test
	public void testSave()
	{
		DGraph g1 = new DGraph();
		Graph_Algo ga = new Graph_Algo();
		ga.init(g1);
		ga.save("myFile.txt");
	}

	@Test
	public void testIsConnected() {
		fail("Not yet implemented");
	}

	@Test
	public void testShortestPathDist() {
		Graph_Algo ga = new Graph_Algo();
		DGraph dg = new DGraph();
		Node n1 = new Node(0);
		n1.setLocation(new Point3D(0.1,0.9));
		Node n2 = new Node(1);
		n2.setLocation(new Point3D(0.5,0.5));
		Node n3 = new Node(2);
		n3.setLocation(new Point3D(0.2,0.2));
		Node n4 = new Node(3);
		n4.setLocation(new Point3D(0.1,0.4));
		dg.addNode(n1);
		dg.addNode(n2);
		dg.addNode(n3);
		dg.addNode(n4);
		
		dg.connect(0, 1, 50);
		dg.connect(1, 2, 29);
		dg.connect(2, 3, 2);
		dg.connect(3, 0, 6);
		dg.connect(1, 3, 5);
		
		ga.g = dg;
		double result = ga.shortestPathDist(0, 3);
		
		assertEquals(55.0, result,0.0001);
	}

	@Test
	public void testShortestPath()
	{
		Graph_Algo ga = new Graph_Algo();
		DGraph dg = new DGraph();
		Node n1 = new Node(0);
		n1.setLocation(new Point3D(0.1,0.9));
		Node n2 = new Node(1);
		n2.setLocation(new Point3D(0.5,0.5));
		Node n3 = new Node(2);
		n3.setLocation(new Point3D(0.2,0.2));
		Node n4 = new Node(3);
		n4.setLocation(new Point3D(0.1,0.4));
		dg.addNode(n1);
		dg.addNode(n2);
		dg.addNode(n3);
		dg.addNode(n4);
		
		dg.connect(0, 1, 50);
		dg.connect(1, 2, 29);
		dg.connect(2, 3, 2);
		dg.connect(3, 0, 6);
		dg.connect(1, 3, 5);
		
		ga.g = dg;
		List<node_data> result = ga.shortestPath(0, 3);

		for (int i = 0; i < result.size(); i++) 
		{
			System.out.println(result.get(i).getKey());
		}
	}

	@Test
	public void testTSP() {
		fail("Not yet implemented");
	}

	@Test
	public void testCopy() {
		fail("Not yet implemented");
	}

}

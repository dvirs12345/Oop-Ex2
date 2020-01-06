package gui;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import utils.Point3D;
import dataStructure.*;
import gui.Graph_Gui;
public class Graph_GuiTest
{

	@Test
	public void testDisplayGraph()
	{
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
		
		Graph_Gui gg = new Graph_Gui();
		gg.displayGraph(dg);
		
		try
		{
			TimeUnit.SECONDS.sleep(15);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		Graph_Gui.save("test.jpg");
	}
	
	@Test
	public void TestDisplay_shortestPath() 
	{
		//Graph_Gui.display_shortestPath();
	}

	@Test
	public void testSave()
	{
		;
	}

}

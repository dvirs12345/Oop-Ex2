package gui;

import utils.StdDraw;

import java.util.Collection;

import javax.management.RuntimeErrorException;

import dataStructure.*;

public class Graph_Gui
{
	/**
	 * This function 
	 * @param g1
	 */
	public static void displayGraph(graph g1) // Assuming that x and y are between (0,1).
	{	
		Collection<node_data> vertecies = g1.getV();
		
		// The edges drawing part. 
		
		StdDraw.setPenRadius(0.01);
		StdDraw.setPenColor(StdDraw.RED);
		double srcX,srcY,destX,destY;
		
		for (node_data iterable_element : vertecies) // Iterate over all Nodes.
		{
			srcX = iterable_element.getLocation().x();
			srcY = iterable_element.getLocation().y();
			
			Collection<edge_data> edges = g1.getE(iterable_element.getKey());
			if(edges != null) 
			{
				for (edge_data iterable_element2 : edges) // Iterate over the hashMap of edges coming from this Node.
				{
					destX = g1.getNode(iterable_element2.getDest()).getLocation().x();
					destY = g1.getNode(iterable_element2.getDest()).getLocation().y();
					StdDraw.line(srcX, srcY, destX, destY);
				}
			}	
		}
		
		// The Nodes drawing part.
		
		StdDraw.setPenRadius(0.025);
		StdDraw.setPenColor(StdDraw.BLUE);
		double x, y;
		
		for (node_data iterable_element : vertecies) // Iterates over Nodes and makes their points on the graph.
		{
			if(iterable_element.getLocation() == null) 
				throw new RuntimeException("The Location of this node is null: "+iterable_element.getKey()); 
			x = iterable_element.getLocation().x();
			y = iterable_element.getLocation().y();
			StdDraw.point(x, y);
		}
	}
	
	public static void save(String filename) 
	{
		try 
		{
			StdDraw.save(filename);
		}
		catch (Exception e) 
		{
			throw new RuntimeException("filename shouldn't be null! ");
		}
	}
}

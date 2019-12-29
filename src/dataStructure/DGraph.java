package dataStructure;

import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph
{
	// HashMap of <id, Node>.
	public HashMap<Integer , node_data> nodeHM = new HashMap<Integer,node_data>();
	// HashMap of <src, <dest, Edge>>.
	public HashMap<Integer , HashMap<Integer, edge_data>> edgeHM = new HashMap<Integer , HashMap<Integer,edge_data>>();
	// The number of edges in the graph. 
	private int edgeSize = 0;
	// Number of changes made.
	private int MC = 0;
	
	public DGraph() { ; }
	
	public DGraph(HashMap<Integer , node_data> nodeHM, HashMap<Integer , HashMap<Integer,edge_data>> edgeHM) 
	{
		this.nodeHM = nodeHM;
		this.edgeHM = edgeHM;
	}
	
	@Override
	public node_data getNode(int key) 
	{	
		try 
		{
			return this.nodeHM.get(key);
		}
		catch (Exception e) 
		{
			return null;
		}
	}

	@Override
	public edge_data getEdge(int src, int dest) 
	{
		try 
		{
			return this.edgeHM.get(src).get(dest);
		}
		catch (Exception e) 
		{
			return null;
		}
	}

	@Override
	public void addNode(node_data n) 
	{
		this.nodeHM.put(n.getKey(), n);
		this.MC++;
	}

	@Override
	public void connect(int src, int dest, double w) 
	{
		Edge e1 = new Edge(src, dest, w); // Create a new edge with source(src), destination(dest), weight(w).
		
		if(src!=dest && w>0) // If given values are valid.
		{
			
			if(this.edgeHM.get(src) != null) // If the edge exists
				this.edgeHM.get(src).put(dest,e1);
			
			else 
			{
				HashMap<Integer,edge_data> temp = new HashMap<Integer, edge_data>();
				this.edgeHM.put(src, temp);
				this.edgeHM.get(src).put(dest, e1);
			}
			
			this.edgeSize++;
			this.MC++;
		}
	}

	@Override
	public Collection<node_data> getV()
	{
		return this.nodeHM.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) 
	{
		if(this.edgeHM.get(node_id) == null)
			return null;
		return this.edgeHM.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key)
	{ 
		if(this.nodeHM.get(key) == null)
			return null;
		
		node_data nd = this.nodeHM.remove(key);
		
		for (int i = 0; i < this.nodeHM.size(); i++) 
		{
			if(edgeHM.get(i).containsKey(key)) 
			{
				this.edgeHM.get(i).remove(key);
				this.edgeSize--;  // Not sure about this line.
			}
		}
					
		if(nd != null ) 
		{
			this.edgeSize--;
			this.MC++;
		}
		
		return nd;
	}

	@Override
	public edge_data removeEdge(int src, int dest)
	{ 
		if(src!= dest && this.edgeHM.get(src) != null) 
		{
			edge_data ed = this.edgeHM.get(src).remove(dest);
			
			if(ed != null ) 
			{
				this.edgeSize++;
				this.MC++;
			}
			return ed;	
		}
		return null;
	}

	@Override
	public int nodeSize()
	{
		return this.nodeHM.size();
	}

	@Override
	public int edgeSize()
	{
		return this.edgeSize;
	}

	@Override
	public int getMC()
	{
		return this.MC;
	}

}

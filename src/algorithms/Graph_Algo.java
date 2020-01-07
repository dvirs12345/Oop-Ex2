package algorithms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import dataStructure.*;

/**
 * This class represents the set of graph-theory algorithms.
 * @author Dvir Sadon and Tehila Bakshiel.
 */
public class Graph_Algo implements graph_algorithms, Serializable
{
	private static final long serialVersionUID = 1L;
	
	public graph g = null; // The graph the algorithms are used on.
	
	public Graph_Algo() { ; }
	
	@Override
	public void init(graph g)
	{
		this.g = g;
	}

	@Override
	public void init(String file_name)
	{
		try 
		{
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file_name));
			graph ng = (graph) in.readObject(); 
			init(ng);
			in.close();
		}
		
		catch(FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void save(String file_name)
	{
		try
		{
			OutputStream outStream = new FileOutputStream(file_name);
			ObjectOutputStream fileObjectOut = new ObjectOutputStream(outStream);
			fileObjectOut.writeObject(this.g);
			fileObjectOut.close();
			outStream.close();
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			throw new RuntimeException("Error saving to file");
		}
	}

	@Override
	public boolean isConnected() 
	{
		if(g.nodeSize() < 2)
			return true;

		boolean flag = false;
		Iterator<node_data> NODE_Iterator = ((dataStructure.graph) g).getV().iterator();
		Stack<node_data> s1 = new Stack<node_data>();

		while(NODE_Iterator.hasNext())  
		{
			for (node_data nodes : ((dataStructure.graph) g).getV()) // Set all node tags to 0
				nodes.setTag(0);

			node_data currentNode = NODE_Iterator.next();
			s1.add(currentNode);
			currentNode.setTag(1);

			if(!Neighbors(currentNode,s1).isEmpty())
				flag = false;
			else
				flag = true;
		}
		
		return flag;
	}
	
	/**
	 * @param currentNode - The node to make the stack with. 
	 * @param s - The stack to update.
	 * @return A new Stack containing the needed information.
	 */
	private Stack<node_data> Neighbors(node_data currentNode, Stack<node_data> s)
	{
		Iterator<edge_data> EDGE_iterator = ((DGraph) g).getE(currentNode.getKey()).iterator();
		if(s.isEmpty()) 
			return s;
		
		while(EDGE_iterator.hasNext()) 
		{
			edge_data e = EDGE_iterator.next();
			if(((DGraph) g).getNode(e.getDest()).getTag() == 0) 
			{
				s.push(((DGraph) g).getNode(e.getDest()));
				((DGraph) g).getNode(e.getDest()).setTag(1);
				Neighbors(((DGraph) g).getNode(e.getDest()), s);
			}
		}
		
		s.pop();
		return s;
	}
	
	/**
	 * This function updates the weights of the nodes using the dijakstra algorithm 
	 * and then returns the length of the shortest path from src node to dest node.
	 * for the algorithem:
	 * https://fr.wikipedia.org/wiki/Algorithme_de_Dijkstra
	 * @param src - The source node id. 
	 * @param dest - The destenation node id.
	 * @return The length of the shortest path between src and dest. 
	 */
	@Override
	public double shortestPathDist(int src, int dest)
	{	
		if(src == dest)
			return 0;
		
		List<node_data>  notVisited = new LinkedList<node_data>();
		List<node_data>  visited = new LinkedList<node_data>();
		
		for (node_data nodes : g.getV())
		{
			nodes.setWeight(Double.MAX_VALUE);
			notVisited.add(nodes);
		}
		this.g.getNode(src).setWeight(0);
		
		node_data currentNode = this.g.getNode(src);
		
		while(hasMaxVal(this.g) || !notVisited.isEmpty() || currentNode!=null) 
		{
			visited.add(currentNode);
			notVisited.remove(currentNode);
			
			Collection<edge_data> edgesFromcurrent = g.getE(currentNode.getKey());
			
			if(edgesFromcurrent == null) // Testing the case where there are no edges coming out of the current node.  
			{
				currentNode = getminNode(notVisited);
				if(currentNode != null)
					continue;
				else
					break;		
			}
			
			for (edge_data edge:edgesFromcurrent) // iterates over and replaces the weight in every one of the nodes.  
			{
				double isWeight = edge.getWeight()+currentNode.getWeight();
				if(isWeight < this.g.getNode(edge.getDest()).getWeight()) 
				{
					this.g.getNode(edge.getDest()).setInfo(Integer.toString(currentNode.getKey()));
					this.g.getNode(edge.getDest()).setWeight(isWeight);
				}
			}

			currentNode = getminNode(notVisited);	
			if(currentNode == null)
				break;
			
		}
		
		return this.g.getNode(dest).getWeight();
	}
	
	/**
	 * 
	 * @param g1 - the graph.
	 * @return true if the graph has a MAX_VALUE node, false otherwise.
	 */
	private boolean hasMaxVal(graph g1) 
	{
		for (node_data nodes : g.getV())
			if(Double.MAX_VALUE == nodes.getWeight())
				return true;
		return false;
	}
	
	/**
	 * 
	 * @param arr - The array to search
	 * @return The minimum node from the List
	 */
	private node_data getminNode(List<node_data> arr) 
	{
		if(arr.isEmpty()) 
			return null;
		
		double min = Double.MAX_VALUE;
		node_data minNode=null;
		
		for(int i = 0; i < arr.size();i++)
		{
			if(arr.get(i).getWeight()<min)
			{
				min=arr.get(i).getWeight();
				minNode=arr.get(i);
			}
		}
		return minNode;
	} 
	
	/**
	 * @param src - The source node id.
	 * @param dest - The destination node id.
	 * @return List of the shortest path from the src node to dest node.
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest)
	{
		shortestPathDist(src, dest);
		node_data currentNode = g.getNode(dest);
		List<node_data> result = new LinkedList<node_data>();
		
		while(!currentNode.getInfo().isEmpty() || currentNode.getKey() == g.getNode(src).getKey()) 
		{
			result.add(currentNode);
			currentNode = g.getNode(Integer.parseInt(currentNode.getInfo()));
			if(currentNode.getKey()==g.getNode(src).getKey())
				break;
		}
		
		result.add(currentNode);
		
		List<node_data> result2 = new LinkedList<node_data>();
		
		for (int i = result.size(); i > 0 ; i--)
			result2.add(result.get(i-1));
		
		result = result2;
		return result;
	}
	

	@Override
	public List<node_data> TSP(List<Integer> targets)
	{
		if(targets.size() == 0)
			return null;
		else
			if(targets.size() == 1)
				return shortestPath(targets.get(0), targets.get(0));
		
		Collection<node_data> nodes = g.getV();
		List<node_data> targetsN = new LinkedList<node_data>();
		
		for (node_data node:nodes) 
			if(targets.contains(node.getKey()))
				targetsN.add(node);
		
		List<node_data> result = new LinkedList<node_data>();
		List<node_data> temp = new LinkedList<node_data>();
		
		for (node_data node:nodes) 
			node.setTag(1);
		
		boolean flag = false;
		for(int i = 1;i < this.g.nodeSize();i++) 
		{
			flag = false;
			for (int j = 0; j < targetsN.size()-1; j++)
			{
				result.addAll(doesAPath(targetsN.get(j), targetsN.get(j+1)));
				targetsN.get(j+1).setTag(0);
				targetsN.get(j).setTag(0);
			}
			
			for (int j = 0; j < targetsN.size(); j++)
				if(targetsN.get(j).getTag() != 0)
					flag = true;
			
			if(!flag)
				break;
			Collections.shuffle(targetsN);
		}
		
		for (int i = 0; i < result.size()-1; i++) // Remove excess nodes from the path.
			if(result.get(i) == result.get(i+1))
				result.remove(i);
		
		return result;
	}
	
	/**
	 * Checks if a path from node1 to node2 exists, if so, return it. else, returns null.
	 * @param node_data - The source. 
	 * @param node_data2 - The destination.
	 * @return The shortest from node1 to node2. If its null, the null. 
	 */
	private List<node_data> doesAPath(node_data node1, node_data node2) 
	{
		List<node_data> result = shortestPath(node1.getKey(), node2.getKey());
		if(result==null) 
			return null;
		return result;
	}

	/**
	 * Adds the first list to the second one.
	 * @param l1 - The First List
	 * @param l2 - The Second List
	 * @return
	 */
	private List<node_data> mergelists(List<node_data> l1, List<node_data> l2) 
	{
		for (int i = 0; i < l2.size(); i++) 
			l1.add(l2.get(i));
		return l1;
	}
	
	@Override
	public graph copy()
	{
		Graph_Algo ga = new Graph_Algo();
		this.save("temp.txt");
		ga.init("temp.txt");
		return (dataStructure.graph) ga.g;
	}

}

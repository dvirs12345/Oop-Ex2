package dataStructure;

import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph{
	HashMap<Integer , node_data> nodeHM = new HashMap<Integer,node_data>();
	HashMap<Integer , HashMap<Integer,edge_data>> edgeHM = new HashMap<Integer , HashMap<Integer,edge_data>>();
	
	@Override
	public node_data getNode(int key) 
	{	
		return this.nodeHM.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		return this.edgeHM.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		this.nodeHM.put(n.getKey(), n);
	}

	@Override
	public void connect(int src, int dest, double w) {///////-----------------------
		
	}

	@Override
	public Collection<node_data> getV() {
		return this.nodeHM.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return this.edgeHM.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}

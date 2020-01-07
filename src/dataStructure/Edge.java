package dataStructure;
/**
 * A class implementing edge_data.
 * @author Dvir Sadon
 */
public class Edge implements edge_data
{

	int Src; // The source from which the edge comes out of.
	int Dest; // The destination to which the edge is going to.
	double Weight; // The edge's weight. 
	String Info; //The edge's info.
	int Tag; // The edge's tag. 
	
	public Edge() { ; }
	
	public Edge(int Src, int Dest, double weight) 
	{
		this.Weight = weight;
		this.Src = Src;
		this.Dest = Dest;
	}
	
	@Override
	public int getSrc() {
		return this.Src;
	}

	@Override
	public int getDest() {
		return this.Dest;
	}

	@Override
	public double getWeight() {
		return this.Weight;
	}

	@Override
	public String getInfo() {
		return this.Info;
	}

	@Override
	public void setInfo(String s) {
		this.Info = s;
	}

	@Override
	public int getTag() {
		return this.Tag;
	}

	@Override
	public void setTag(int t) {
		this.Tag = t;
	}

}

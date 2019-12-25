package dataStructure;

public class Edge implements edge_data{

	int Src;
	int Dest;
	double Weight;
	String Info;
	int Tag;
	
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

package dataStructure;

import utils.Point3D;

public class Node implements node_data {
	int key = 0;
	Point3D Location;
	double Weight;
	String Info;
	int Tag;
	
	public Node () { ; }
	
	public Node(int key) { this.key = key; }
	
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		return Location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.Location = p;
	}

	@Override
	public double getWeight() {
		return this.Weight;
	}

	@Override
	public void setWeight(double w) {
		this.Weight = w;
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

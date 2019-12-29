package gui;

import utils.StdDraw;

public class help1 {

	public static void main(String[] args)
	{
		
		StdDraw.setPenRadius(0.02); // Width of pen
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(0.7, 0.7); // x,y
		StdDraw.setPenColor(StdDraw.MAGENTA); // Color
		StdDraw.line(0.2, 0.2, 0.8, 0.2); // x,y to x,y
	}

}

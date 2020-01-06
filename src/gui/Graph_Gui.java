package gui;

import utils.StdDraw;

import java.util.Collection;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import algorithms.Graph_Algo;
import dataStructure.*;

/**
 * This class contains functions for drawing graphs.  
 * @author Dvir Sadon and Tehila Bakshiel. 
 */
public class Graph_Gui
{
	graph g1;
	public Graph_Gui() { ; }
	/**
	 * This function displays the given graph.
	 * @param g1 - The given graph
	 */
	public void displayGraph(graph g1) // Assuming that x and y are between (0,1).
	{	
		this.g1 = g1;
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
					
					StdDraw.text((srcX+destX)/2, (srcY+destY)/2, Double.toString(iterable_element2.getWeight()));
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
			
			StdDraw.text(x, y+0.05, Integer.toString(iterable_element.getKey()));
			StdDraw.point(x, y);
		}
	}
	
	
	public static void display_shortestPath() 
	{
		JFrame frame= new JFrame(); 
        frame.setTitle("Enter wanted variables");
         
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
 
        JPanel headingPanel = new JPanel();
         
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(5, 5, 5, 5);     
        constr.anchor = GridBagConstraints.WEST;
 
        constr.gridx=0;
        constr.gridy=0;
  
        // Declare the required Labels
        JLabel userNameLabel = new JLabel("Source :");
        JLabel pwdLabel = new JLabel("Destenation :");
         
        // Declare Text fields
        JTextField userNameTxt = new JTextField(20);
        JTextField pwdTxt = new JPasswordField(20);
         
        panel.add(userNameLabel, constr);
        constr.gridx=1;
        panel.add(userNameTxt, constr);
        constr.gridx=0; constr.gridy=1;
         
        panel.add(pwdLabel, constr);
        constr.gridx=1;
        panel.add(pwdTxt, constr);
        constr.gridx=0; constr.gridy=2;
         
        constr.gridwidth = 2;
        constr.anchor = GridBagConstraints.CENTER;
  
        JButton button = new JButton("Enter");
        // add a listener to button
        button.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
        	  change_graph1(this.g1, Integer.parseInt(userNameTxt.getText()), Integer.parseInt(pwdTxt.getText()));
          }
        });
  
       // Add label and button to panel
       panel.add(button, constr);
  
       mainPanel.add(headingPanel);
       mainPanel.add(panel);
 
        // Add panel to frame
       frame.add(mainPanel);
       frame.pack();
       frame.setSize(400, 400);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
	}
	
	public static void change_graph1(graph g1, int src, int dest) 
	{
		Graph_Algo ga = new Graph_Algo();
		List<node_data> path = ga.shortestPath(src, dest);
		StdDraw.setPenRadius(0.01);
		StdDraw.setPenColor(StdDraw.YELLOW);
		double srcX,srcY,destX,destY;
		
		for (node_data iterable_element : path)
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
					
					StdDraw.text((srcX+destX)/2, (srcY+destY)/2, Double.toString(iterable_element2.getWeight()));
					StdDraw.line(srcX, srcY, destX, destY);
				}
			}	
		}
		
		StdDraw.setPenRadius(0.025);
		StdDraw.setPenColor(StdDraw.BLUE);
		double x, y;
		
		for (node_data iterable_element : path) // Iterates over Nodes and makes their points on the graph.
		{
			if(iterable_element.getLocation() == null) 
				throw new RuntimeException("The Location of this node is null: "+iterable_element.getKey()); 
			
			x = iterable_element.getLocation().x();
			y = iterable_element.getLocation().y();
			
			StdDraw.text(x, y+0.05, Integer.toString(iterable_element.getKey()));
			StdDraw.point(x, y);
		}
		
	} 
	
	
	/**
	 * This function saves the graph as filename.
	 * @param filename - The name of the file.
	 */
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

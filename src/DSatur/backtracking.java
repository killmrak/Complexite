/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DSatur;

/*import Graphe.Arret;
import Graphe.Sommet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public  class backtracking extends Graphe.Graphe implements Comparator<Sommet> {
//final int V = 4;
int color[];
  private static List<Sommet> grapheSommetNonColorie;
public backtracking(){
     this.sommets=new LinkedList<Sommet>();
}
	boolean isSafe(Sommet v, Collection<Sommet>, HashSet<Integer> couleurDifferente,
				int c)
	{
		for (int i = 0; i < V; i++)
			if (graph[v][i] == 1 && c == color[i])
				return false;
		return true;
	}
	
	boolean graphColoringUtil(int graph[][], int m,
							int color[], int v)
	{	
		if (v == V)
			return true;

		for (int c = 1; c <= m; c++)
		{			
			if (isSafe(v, graph, color, c))
			{
				color[v] = c;

				if (graphColoringUtil(graph, m,
									color, v + 1))
					return true;
				color[v] = 0;
			}
		}

		return false;
	}

	boolean graphColoring(int graph[][], int m)
	{
		color = new int[V];
		for (int i = 0; i < V; i++)
			color[i] = 0;
		if (!graphColoringUtil(graph, m, color, 0))
		{
			System.out.println("Solution does not exist");
			return false;
		}
		return true;
	}

	public static void main(String args[])
	{
		backtracking Coloring = new backtracking();
		/* Create following graph and test whether it is
		3 colorable
		(3)---(2)
		| / |
		| / |
		| / |
		(0)---(1)
		*/
		/*int graph[][] = {{0, 1, 1, 1},
			{1, 0, 1, 0},
			{1, 1, 0, 1},
			{1, 0, 1, 0},
		};
		int m = 3; // Number of colors
		Coloring.graphColoring(graph, m);
	}       
    @Override
    public int compare(Sommet o1, Sommet o2) {
       /* if(dSat(o2)-dSat(o1)!=0)
            return dSat(o2)-dSat(o1);
        else return this.degree(o2)-this.degree(o1);
       return 1;
    }

}
*/
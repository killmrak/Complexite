/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DSatur;

import Graphe.Arret;
import Graphe.Sommet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author yassine
 */
public class GrapheDSat extends Graphe.Graphe implements Comparator<Sommet>{
    private static List<Sommet> grapheSommetNonColorie;
    public GrapheDSat(){
        this.sommets=new LinkedList<Sommet>();
    }

    public int dSat(Sommet s){
        if(s==null) return -1;
        Iterator<Sommet> it= getSommetsEnRelation(s).iterator();
        HashSet<Integer> couleurDifferente=new HashSet<Integer>();
        while(it.hasNext()){
            Sommet test=it.next();
            if(s.getCouleur()!=0) 
            	couleurDifferente.add(test.getCouleur());
        }
        if(couleurDifferente.size()==0) return degree(s);
        else return couleurDifferente.size();
    }
   /* public Sommet sommetdeDSatMax(){
        return getSommets().iterator().next();
    }*/
    private boolean rC(Collection<Sommet> c, int x){
        Iterator<Sommet> it=c.iterator();
        while(it.hasNext())
            if(it.next().getCouleur()==x)
                return false;
        return true;
    }
    private int plusPetiteCouleur(Collection<Sommet> voisins){
        int i=1;
        while(true){
            if(rC(voisins,i)){
                return i;
            }
            i++;
        }
    }
   
	boolean graphColoringUtil(Collection<Sommet> voisins, int m, int color[], int v)
	{	
		if (v == voisins.size())
			return true;

		for (int c = 1; c <= m; c++)
		{			
			if (rC(voisins, c))
			{
				color[v] = c;

				if (graphColoringUtil(voisins, m , color, v + 1))
					return true;
				color[v] = 0;
			}
		}

		return false;
	}

	boolean graphColoring(int graph[][], int m)
	{
		int []color = new int[V];
		for (int i = 0; i < V; i++)
			color[i] = 0;
		if (!graphColoringUtil(graph, m, color, 0))
		{
			System.out.println("Solution does not exist");
			return false;
		}
		return true;
	}
        public void colorierBack(){
        grapheSommetNonColorie=new LinkedList<Sommet>();
        grapheSommetNonColorie.addAll(sommets);
        Sommet s=grapheSommetNonColorie.get(0);
        s.setCouleur(1);
        grapheSommetNonColorie.remove(0);
        Collections.sort(grapheSommetNonColorie,this);
        while(grapheSommetNonColorie.size()>0){
            s=grapheSommetNonColorie.get(0);
            Collection<Sommet> col=getSommetsEnRelation(s);
            int ppc=plusPetiteCouleur(col);
            s.setCouleur(ppc);
            grapheSommetNonColorie.remove(0);
            Collections.sort(grapheSommetNonColorie,this);
        }
            
        }
    public void colorier(){
        Collections.sort(sommets,this);
        grapheSommetNonColorie=new LinkedList<Sommet>();
        grapheSommetNonColorie.addAll(sommets);
        Sommet s=grapheSommetNonColorie.get(0);
        s.setCouleur(1);
        grapheSommetNonColorie.remove(0);
        Collections.sort(grapheSommetNonColorie,this);
        while(grapheSommetNonColorie.size()>0){
            s=grapheSommetNonColorie.get(0);
            Collection<Sommet> col=getSommetsEnRelation(s);
            int ppc=plusPetiteCouleur(col);
            s.setCouleur(ppc);
            grapheSommetNonColorie.remove(0);
            Collections.sort(grapheSommetNonColorie,this);
        }
    }



    public int compare(Sommet o1, Sommet o2) {
        if(dSat(o2)-dSat(o1)!=0)
            return dSat(o2)-dSat(o1);
        else return this.degree(o2)-this.degree(o1);
    }
    /*public static void main(String[] args){
        GrapheDSat g=new GrapheDSat();
        g.ajouterSommet(new Sommet(1, 0));
        g.ajouterSommet(new Sommet(2, 0));
        g.ajouterSommet(new Sommet(3, 0));
        g.ajouterArret(new Arret(g.sommets.get(0),g.sommets.get(1) ));
        g.ajouterArret(new Arret(g.sommets.get(2),g.sommets.get(0) ));
        g.ajouterArret(new Arret(g.sommets.get(1),g.sommets.get(2) ));
        g.colorier();
        System.out.print(g.toString());
    }*/


}

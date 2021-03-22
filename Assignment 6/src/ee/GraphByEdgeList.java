package ee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
public class GraphByEdgeList {
	ArrayList<ArrayList<Vertex>> graphEdgeList;
	int graphSize=4;
	ArrayList<ArrayList<Vertex>> spanningTreeEdgeList;
	ArrayList<ArrayList<Vertex>>  spanningGraphMatrix;

	public HashSet<Integer> isConnected(int vertex,HashSet<Integer> allVertexList)
	{
		for(int i=0;i<graphEdgeList.get(vertex).size();i++)
		{
			if(!allVertexList.contains(graphEdgeList.get(vertex).get(i).connectedVertex))
				{
					allVertexList.add(graphEdgeList.get(vertex).get(i).connectedVertex);
					if(allVertexList.size()==graphSize)
					{
						break;
					}
					allVertexList=this.isConnected(graphEdgeList.get(vertex).get(i).connectedVertex, allVertexList);
				}
				
			}
		return allVertexList;
	}

	public void reachable(int vertex)
	{
		System.out.println("vertex "+vertex+" is reachable to");
		for(int i=0;i<graphEdgeList.get(vertex).size();i++)
		{	
				System.out.println("vertex " +graphEdgeList.get(vertex).get(i).connectedVertex);
		}
	}
	
	public void createGraph()
	{
		graphEdgeList=new ArrayList<ArrayList<Vertex>>();
		spanningTreeEdgeList=new ArrayList<ArrayList<Vertex>>();
		graphEdgeList.add(null);
		ArrayList<Vertex> vertex1=new ArrayList<Vertex>();
		vertex1.add(new Vertex(1,2,1));
		vertex1.add(new Vertex(1,4,4));
		vertex1.add(new Vertex(1,3,5));
		graphEdgeList.add(vertex1);
		ArrayList<Vertex> vertex2=new ArrayList<Vertex>();
		vertex2.add(new Vertex(2,1,1));
		vertex2.add(new Vertex(2,3,2));
		graphEdgeList.add(vertex2);
		ArrayList<Vertex> vertex3=new ArrayList<Vertex>();
		vertex3.add(new Vertex(3,2,2));
		vertex3.add(new Vertex(3,4,3));
		vertex3.add(new Vertex(3,1,5));
		graphEdgeList.add(vertex3);
		ArrayList<Vertex> vertex4=new ArrayList<Vertex>();
		vertex4.add(new Vertex(4,1,4));
		vertex4.add(new Vertex(4,3,3));
		graphEdgeList.add(vertex4);
		spanningTreeEdgeList.add(null);
		ArrayList<Vertex> vrtex1=new ArrayList<Vertex>();
		ArrayList<Vertex> vrtex2=new ArrayList<Vertex>();
		ArrayList<Vertex> vrtex3=new ArrayList<Vertex>();
		ArrayList<Vertex> vrtex4=new ArrayList<Vertex>();
		spanningTreeEdgeList.add(vrtex1);
		spanningTreeEdgeList.add(vrtex2);
		spanningTreeEdgeList.add(vrtex3);
		spanningTreeEdgeList.add(vrtex4);

		spanningGraphMatrix=new ArrayList<ArrayList<Vertex>>();
		spanningGraphMatrix.add(null);
		ArrayList<Vertex> vrtx1=new ArrayList<Vertex>();
		vrtx1.add(new Vertex(1,2,1));
		spanningGraphMatrix.add(vrtx1);
		ArrayList<Vertex> vrtx2=new ArrayList<Vertex>();
		vrtx2.add(new Vertex(2,3,2));
		spanningGraphMatrix.add(vrtx2);
		ArrayList<Vertex> vrtx3=new ArrayList<Vertex>();
		vrtx3.add(new Vertex(3,4,3));
		vrtx3.add(new Vertex(3,1,5));
		spanningGraphMatrix.add(vrtx3);
		ArrayList<Vertex> vrtx4=new ArrayList<Vertex>();
		vrtx4.add(new Vertex(4,1,4));
		spanningGraphMatrix.add(vrtx4);	
	}
	/**
	 * this method find the node which has minimum distance in the list
	 * @param dist the list that holds the distances of node
	 * @param traversedVertex list of traversed vertex
	 * @return the minimum distance 
	 */
	public int minDistance(int dist[],int traversedVertex[])
	{
		int min=10000,minIndex=-1;
		for(int i=1;i<=graphSize;i++)
		{
			if(min>dist[i] && traversedVertex[i]==0)
			{
				min=dist[i];
				minIndex=i;
			}
		}
		return minIndex;
	}

	public int[] minmumValueIndex()
	{
		int min=10000;
		int mini=-1;
		int minj=-1;
		for(int i=1;i<=graphSize;i++)
		{
			for(int j=0;j<spanningGraphMatrix.get(i).size();j++)
			{
				if( spanningGraphMatrix.get(i).get(j).distance<min)
				{
					min=spanningGraphMatrix.get(i).get(j).distance;
					mini=i;
					minj=j;
				}
			}
		}
		int arr[]= {mini,minj};
		return arr;
	}
	
	public Boolean checkLoop(int checkVertex,int callVertex)
	{
		Boolean returnAnswer=false;
		
			for(int j=0;j<spanningTreeEdgeList.get(callVertex).size();j++)
			{
				if(checkVertex==spanningTreeEdgeList.get(callVertex).get(j).connectedVertex)
				{
					returnAnswer= true;
				}
				else
				{
					returnAnswer=this.checkLoop(checkVertex,spanningTreeEdgeList.get(callVertex).get(j).connectedVertex);
				}
				if(returnAnswer==true)
				{
					break;
				}
			}
		
			return returnAnswer;
	}
	
	public HashMap<Integer,Integer> minimumSpanningTree()
	{
		HashMap<Integer,Integer> spanningTreeVertex=new  HashMap<Integer,Integer>();
		while(spanningTreeVertex.size()<((graphSize-1)))
		{
		int min[]=this.minmumValueIndex();
		int indexi=min[0];
		int indexj=min[1];
		
		spanningTreeEdgeList.get(indexi).add(new Vertex(indexi,spanningGraphMatrix.get(indexi).get(indexj).connectedVertex,spanningGraphMatrix.get(indexi).get(indexj).distance));
		Boolean loop=false;
		for(int i=1;i<=graphSize;i++)
		{
			loop=this.checkLoop(i, i);
			if(loop==true)
			{
				break;
			}
		}
		if(loop==false)
		{
			spanningTreeVertex.put(indexi,spanningGraphMatrix.get(indexi).get(indexj).connectedVertex);
			spanningGraphMatrix.get(indexi).remove(indexj);
		}
		if(loop==true)
		{
			spanningTreeEdgeList.get(indexi).remove(spanningTreeEdgeList.get(indexi).size()-1);
			spanningGraphMatrix.get(indexi).remove(indexj);

		}
		}
		return spanningTreeVertex;
	}
	
	public int shortestPath(int src,int end)
	{
		int dist[]=new int[graphSize+1];
		int traversedVertex[]=new int[graphSize+1];
		for(int i=1;i<=graphSize;i++)
		{
			dist[i]=10000;
			traversedVertex[i]=0;
		}
		dist[src]=0;
		for(int i=1;i<=graphSize;i++)
		{
			int u=this.minDistance(dist,traversedVertex);
			traversedVertex[i]=1;
			for(int j=0;j<graphEdgeList.get(i).size();j++)
			{
				if(traversedVertex[graphEdgeList.get(i).get(j).connectedVertex]==0  && dist[u]!=10000 && dist[u]+graphEdgeList.get(u).get(j).distance<dist[graphEdgeList.get(i).get(j).connectedVertex])
				{
					dist[graphEdgeList.get(i).get(j).connectedVertex]=dist[u]+graphEdgeList.get(u).get(j).distance;
				}
			}
		}
		
		return dist[end];
	}
	public static void main(String []args)
	{
		GraphByEdgeList graph=new GraphByEdgeList();
		graph.createGraph();
		HashSet<Integer> allVertexList=new HashSet<Integer>();
		allVertexList=graph.isConnected(1,allVertexList);
		 if(allVertexList.size()==graph.graphSize)
		 {
			 System.out.println("Graph is connected");
		 }
		 else
		 {
			 System.out.println("Graph is not connected");
		 }
		 graph.reachable(1);
		 System.out.println("minimum distance between 1 and 4 vertex is "+graph.shortestPath(1,4));
		 HashMap<Integer,Integer> spanningTree=graph.minimumSpanningTree();
		 System.out.println("Spanning tree is between vertex ");
		 for(Entry<Integer,Integer> entry:spanningTree.entrySet())
			{
			 System.out.println("vertex "+entry.getKey()+" and vertex "+entry.getValue());
		 }
	}
}

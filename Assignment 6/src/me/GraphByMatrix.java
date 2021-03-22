package me;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
public class GraphByMatrix {
	int graphMatrix[][];
	int graphSize=4;
	int spanningTreeMatrix[][];
	int spanningGraphMatrix[][];

	public HashSet<Integer> isConnected(int vertex,HashSet<Integer> allVertexList)
	{
		for(int i=1;i<=graphSize;i++)
		{
			if(graphMatrix[vertex][i]>0)
			{
				if(!allVertexList.contains(i))
				{
					allVertexList.add(i);
					if(allVertexList.size()==graphSize)
					{
						break;
					}
					allVertexList=this.isConnected(i, allVertexList);
				}			
			}
		}
		return allVertexList;
	}
	/**
	 * this method print the directly reachable node of linked list
	 * @param vertex to get reachable node
	 */
	public void reachable(int vertex)
	{
		System.out.println("vertex "+vertex+" is reachable to");
		for(int i=1;i<=graphSize;i++)
		{
			if(graphMatrix[vertex][i]>0)
			{
				System.out.println("vertex " +i);
			}
		}
	}
	/**
	 * this method create graph 
	 */
	public void createGraph()
	{
		graphMatrix=new int[graphSize+1][graphSize+1];
		spanningTreeMatrix=new int[graphSize+1][graphSize+1];
		spanningGraphMatrix=new int[graphSize+1][graphSize+1];

		graphMatrix[1][2]=1;
		graphMatrix[1][4]=4;
		graphMatrix[2][1]=1;
		graphMatrix[4][1]=4;
		graphMatrix[2][3]=2;
		graphMatrix[3][2]=2;
		graphMatrix[3][4]=3;
		graphMatrix[4][3]=3;
		graphMatrix[1][3]=5;
		graphMatrix[3][1]=5;
	
		spanningGraphMatrix[1][2]=1;
		spanningGraphMatrix[4][1]=4;
		spanningGraphMatrix[2][3]=2;
		spanningGraphMatrix[3][4]=3;
		spanningGraphMatrix[3][1]=5;
    }
	
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
			for(int j=1;j<=graphSize;j++)
			{
				if(spanningGraphMatrix[i][j]>0 && spanningGraphMatrix[i][j]<min)
				{
					min=spanningGraphMatrix[i][j];
					mini=i;
					minj=j;
				}
			}
		}
		int arr[]= {mini,minj};
		return arr;
	}
	
	public int checkLoop(int checkIndex,int callIndex)
	{
		int returnAnswer=0;
			for(int j=1;j<=graphSize;j++)
			{
				if(spanningTreeMatrix[callIndex][j]>0 && checkIndex==j)
				{
					returnAnswer= 1;
				}
				else if(spanningTreeMatrix[callIndex][j]>0)
				{
					returnAnswer=this.checkLoop(checkIndex,j);
				}
				if(returnAnswer==1)
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
		
		spanningTreeMatrix[indexi][indexj]=spanningGraphMatrix[indexi][indexj];
		int loop=0;
		for(int i=1;i<=graphSize;i++)
		{
			loop=this.checkLoop(i, i);
			if(loop==1)
			{
				break;
			}
		}
		if(loop==0)
		{
			spanningTreeVertex.put(indexi,indexj);
			spanningGraphMatrix[indexi][indexj]=-1;
		}
		if(loop==1)
		{
			spanningTreeMatrix[indexi][indexj]=0;
			spanningGraphMatrix[indexi][indexj]=-1;

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
			for(int j=1;j<=graphSize;j++)
			{
				if(traversedVertex[j]==0 && graphMatrix[u][j]!=0 && dist[u]!=10000 && dist[u]+graphMatrix[u][j]<dist[j])
				{
					dist[j]=dist[u]+graphMatrix[u][j];
				}
			}
		}
		
		return dist[end];
	}
	public static void main(String []args)
	{
		GraphByMatrix graph=new GraphByMatrix();
		graph.createGraph();
		HashSet<Integer> allVertexList=new HashSet<Integer>();
		allVertexList=graph.isConnected(3,allVertexList);
		if(allVertexList.size()==graph.graphSize)
		{
			System.out.println("Graph is connected");
		}
		else
		{
			System.out.println("Graph is not connected");
		}
		graph.reachable(1);
		System.out.println("minimum distance between 1 and 3 vertex is "+graph.shortestPath(1,3));
		HashMap<Integer,Integer> spanningTree=graph.minimumSpanningTree();
		System.out.println("Spanning tree is between vertex ");
		for(Entry<Integer,Integer> entry:spanningTree.entrySet())
		{
			System.out.println("vertex "+entry.getKey()+" and vertex "+entry.getValue()+" of weight "+graph.spanningTreeMatrix[entry.getKey()][entry.getValue()]);
		}
	}
}
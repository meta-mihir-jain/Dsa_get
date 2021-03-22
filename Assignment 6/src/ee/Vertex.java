package ee;

public class Vertex{
	int startingVertex;
	int connectedVertex;
	int distance;
	public Vertex(int startingVertex,int connectedVertex,int distance)
	{	
		this.startingVertex=startingVertex;
		this.connectedVertex=connectedVertex;
		this.distance=distance;
	}
}

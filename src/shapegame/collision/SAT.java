package shapegame.collision;

import shapegame.dev.DevEntity;
import shapegame.engine.Handler;
import shapegame.engine.Vector2;

public class SAT {
	
	private Handler handler;
	private DevEntity obj1, obj2;
	private Vector2[] verticesA, verticesB;
	private Vector2 proj, p1, p2, p3, p4;
	
	public SAT(Handler handler){
		this.handler = handler;
		
	}
	
	public Vector2[] getEdges(Vector2[] vertices){
		Vector2[] axis = new Vector2[vertices.length];
		for(int i = 0; i < vertices.length; i++){
			
			Vector2 e1 = vertices[i];
			
			Vector2 e2 = vertices[i + 1 == vertices.length ? 0 : i + 1];
			
			Vector2 edge = e1.subtract(e2);
			
			Vector2 perp = edge.perp(edge);

			axis[i] = perp;
			
		}
		return axis;
		
	}
	
	public Vector2 projectAxis(Vector2[] vertices, Vector2 axis){
		Vector2 norm = axis.normalize(axis);
		
		double min = norm.dot(vertices[0]);
		double max = min;
		
		for(int i = 1; i < vertices.length; i++){
			double proj = norm.dot(vertices[i]);
			
			if(proj < min){
				min = proj;
			}
			if(proj > max){
				max = proj;
			}
		}
		
			proj = new Vector2(min, max);
		return proj;
	}
	
	public boolean separatingAxisTheorem(Vector2[] axisA, Vector2[] axisB, DevEntity object1, DevEntity object2){
		this.obj1 = object1;
		this.obj2 = object2;
		
		verticesA = obj1.getVertices();
		verticesB = obj2.getVertices();
		
		
				Vector2[] axes1 = getEdges(axisA);
				Vector2[] axes2 = getEdges(axisB);

				for(int i = 0; i < axes1.length; i++){
					Vector2 axis = axes1[i];
				
					p1 = new Vector2(projectAxis(verticesA, axis));
					p2 = new Vector2(projectAxis(verticesB, axis));
				
					if(!p1.overlap(p2)){
						return false;
					}
				}
			
				for(int i = 0; i < axes2.length; i++){
					Vector2 axis = axes2[i];
				
					p3 = new Vector2(projectAxis(verticesA, axis));
					p4 = new Vector2(projectAxis(verticesB, axis));
					
					if(!p3.overlap(p4)){
						return false;
					}
				}
			return true;
		}	
	
}

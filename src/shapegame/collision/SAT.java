package shapegame.collision;

import shapegame.dev.DevEntity;
import shapegame.engine.Handler;
import shapegame.engine.Vector2;
import shapegame.entity.Entity;

public class SAT {
	
	private Handler handler;
	private Entity obj1, obj2;
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
	
	public boolean separatingAxisTheorem(Vector2[] axisA, Vector2[] axisB, Entity object1, Entity object2){
		this.obj1 = object1;
		this.obj2 = object2;
		
		verticesA = axisA;
		verticesB = axisB;

		if(!obj2.checkVulnerability()){
			return false;
		}
		
		if(object2.isCircle()){
			if(polyToCircle(verticesA, (double) obj2.getX(), (double) obj2.getY(), obj2.getRadius())){
				return true;
			}else{
				return false;
			}
		}else{
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
			}
			return true;
		}
	
	public boolean polyToCircle(Vector2[] vertices, double centerX, double centerY, double radius){
		int next = 0;
		for(int i = 0; i < vertices.length; i++){
			 next = i + 1;
				if(next == vertices.length){
					next = 0;
				}
				
			Vector2 vc = vertices[i];
			Vector2 vn = vertices[next];
			
			if(lineCircle(vc.x, vc.y, vn.x, vn.y, centerX, centerY, radius) || circleToCircle(centerX, centerY, radius, vc.x, vc.y, 0))
				return true;
		}
		
		return false;
		
	}

	private boolean lineCircle(double x1, double y1, double x2, double y2, double cx, double cy, double r) {
		
		double distX = x2 - x1;
		double distY = y2 - y1;
		
		double len = Math.sqrt((distX * distX) + (distY * distY));
		
		double dot = ( ((cx - x1) * (x2 - x1)) + ((cy - y1) * (y2 - y1)) ) / (len * len);
		
		double closestX = (float) (x1 + (dot * distX));
		double closestY = (float) (y1 + (dot * distY));
		
		if(!linePoint(x1, y1, x2, y2, closestX, closestY)){
			return false;
		}
		
		double distanceX = closestX - cx;
		double distanceY = closestY - cy;
		
		double distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
		
		if(distance < r){
			return true;
		}else{
			return false;
		}
	
	}

	private boolean linePoint(double x1, double y1, double x2, double y2, double px, double py) {
		
		double d1 = dist(px, py, x1, y1);
		double d2 = dist(px, py, x2, y2);
		
		double lineLen = dist(x1,y1, x2, y2);
		
		double buffer = 0.1;
		
		if(d1 + d2 > lineLen-buffer && d1 + d2 <= lineLen + buffer){
			return true;
		}
		
		return false;
	}
	
	public boolean circleToCircle(double x1, double y1, double r1, double x2, double y2, double r2){
		
		double dx = x2 - x1;
		double dy = y2 - y1;
		
		double rr = r1 + r2;
		
		if((dx * dx) + (dy * dy) < (rr * rr)){
			return true;
		}else{
			return false;
		}
		
	}

	private float dist(double px, double py, double x1, double y1) {
		
		return (float) Math.sqrt((px - x1) * (px - x1) + (py - y1) * (py - y1));
	}
	
	
}

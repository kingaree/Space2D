package shapegame.dev;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Vector;

import shapegame.engine.Handler;
import shapegame.engine.Vector2;

public abstract class DevEntity {
	
	protected Handler handler;
	protected int n;
	protected float x, y;
	protected boolean active = true;
	protected Vector2[] vertices, axis;
	protected Vector2 proj, p1, p2, p3, p4;
	protected boolean collision;
	
	
	public DevEntity(Handler handler, float x, float y, int n){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.n = n;
		

		vertices = new Vector2[n];

	}
	
	public void obtainEdges(){
		axis = new Vector2[vertices.length];
		for(int i = 0; i < vertices.length; i++){
			
			Vector2 e1 = vertices[i];
			
			Vector2 e2 = vertices[i + 1 == vertices.length ? 0 : i + 1];
			
			Vector2 edge = e1.subtract(e2);
			
			Vector2 perp = edge.perp(edge);

			axis[i] = perp;
		}
	}
	
	public Vector2 projectAxis(Vector2 axis){
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
	
	public boolean separatingAxisTheorem(){
		
		for(DevEntity e : handler.getDevWorld().getDevM().getDevEntities()){	
			
			obtainEdges();
			
			if(!e.equals(this)){
				
				Vector2[] axes1 = axis;
				Vector2[] axes2 = e.axis;

				for(int i = 0; i < axes1.length; i++){
					Vector2 axis = axes1[i];
				
					p1 = new Vector2(projectAxis(axis));
					p2 = new Vector2(e.projectAxis(axis));
				
					if(!p1.overlap(p2)){
						return false;
					}
				}
			
				for(int i = 0; i < axes2.length; i++){
					Vector2 axis = axes2[i];
				
					p3 = new Vector2(projectAxis(axis));
					p4 = new Vector2(e.projectAxis(axis));
					if(!p3.overlap(p4)){
						return false;
					}
				}
			}	
		}
		return true;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();

}

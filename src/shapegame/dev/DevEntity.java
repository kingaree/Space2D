package shapegame.dev;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import shapegame.engine.Handler;
import shapegame.engine.Vector2;

public abstract class DevEntity {
	
	protected Handler handler;
	protected boolean active = true;
	protected float x, y;
	protected int n;
	protected Vector2[] vertices, axis;
	
	public DevEntity(Handler handler, float x, float y, int n){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.n = n;
		

		vertices = new Vector2[n];

	}
	
	public Vector2[] getVertices() {
		return vertices;
	}

	public void setVertices(Vector2[] vertices) {
		this.vertices = vertices;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();

}

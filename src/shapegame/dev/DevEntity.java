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
	protected Vector2[] axis;
	protected Vector2[] vertices, vertices2;
	
	public DevEntity(Handler handler, float x, float y, int n, int n2){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.n = n;
		

		vertices = new Vector2[n];
		vertices2 = new Vector2[n2];

	}
	
	public Vector2[] getVertices() {
		return vertices;
	}

	public void setVertices(Vector2[] vertices) {
		this.vertices = vertices;
	}
	

	public Vector2[] getVertices2() {
		return vertices2;
	}

	public void setVertices2(Vector2[] vertices2) {
		this.vertices2 = vertices2;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public abstract boolean isCircle();
	
	public abstract double getRadius();
	
	public abstract boolean isConcave();

}

package shapegame.engine;

import java.util.Vector;

public class Vector2 {
	
	public double x, y;

	
	public Vector2(double x, double y){
		this.x = x;
		this.y = y;

	}
	
	public Vector2(Vector2 vec){
		this.x = vec.x;
		this.y = vec.y;

	}
	
	public double dot(Vector2 b){
		double d = (x * b.x + y * b.y);
		return d;
	}
	
	public double dot(double e, double f){
		double d = (e * x + f * y);
		return d;
	}
	
	public Vector2 normalize(Vector2 vec){
		double mag = Math.sqrt(vec.x * vec.x + vec.y * vec.y);
		Vector2 b = new Vector2(vec.x/mag, vec.y/mag);
		return b;
	}
	
	public Vector2 subtract(Vector2 vec){
		Vector2 s = new Vector2(x - vec.x, y - vec.y);
		return s;
	}
	
	public Vector2 subtract(float x, float y){
		return new Vector2(this.x - x, this.y - y);
	}
	
	public Vector2 perp(Vector2 vec){
		Vector2 p = new Vector2(-vec.y, vec.x);
		return p;
	}
	
	public boolean overlap(Vector2 vec){
		if(!(y < vec.x || vec.y < x))
		    return true;
		else
			return false;
	}

	

}

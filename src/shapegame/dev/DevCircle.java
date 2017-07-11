package shapegame.dev;

import java.awt.Graphics;
import java.util.Random;

import shapegame.engine.Assets;
import shapegame.engine.Handler;
import shapegame.engine.Vector2;



public class DevCircle extends DevEntity{
	private boolean up, left;
	private int speed = 3;
	private double radius = 30.0;
	private Random rand;
	private Vector2 center;

	public DevCircle(Handler handler, float x, float y) {
		super(handler, x, y, 1, 0);

		init();
		
	
	}

	private void init() {
		rand = new Random();
		up = rand.nextBoolean();
		left = rand.nextBoolean();
		center = new Vector2(x, y);
		
	}

	@Override
	public void tick() {
		
	//	move();
		
		
	}
	
	public void move(){
		//balls renders to center 
		if(x <= 32)
			left = false;
		if(x >= 1279 - 32)
			left = true;
		if(left){
			x -= speed;
		}else{
			x += speed;
		}
		
		if(y <= 32)
			up = false;
		if(y >= 719 - 32)
			up = true;
		if(up){
			y -= speed;
		}else{
			y += speed;
		}
	}
	

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.circle, (int) x - 32, (int) y - 32, null);
		
		
	}

	@Override
	public void die() {
		active = false;
		
	}

	@Override
	public boolean isCircle() {
		// TODO Auto-generated method stub
		return true;
	}

	
	public double getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Vector2 getCenter() {
		return center;
	}

	public void setCenter(Vector2 center) {
		this.center = center;
	}

	@Override
	public boolean isConcave() {
		// TODO Auto-generated method stub
		return false;
	}



}

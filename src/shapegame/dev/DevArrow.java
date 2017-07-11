package shapegame.dev;

import java.awt.Graphics;
import java.util.Random;

import shapegame.engine.Animation;
import shapegame.engine.Assets;
import shapegame.engine.Handler;
import shapegame.engine.Vector2;
import shapegame.entity.ActiveEntity;



public class DevArrow extends DevEntity{
	
	private boolean left;
	private int speed = 5;
	private long lastInvulnTimer, invulnCoolDown = 900, invulnTimer = invulnCoolDown;


	private Animation spawnLeft, spawnRight;
	
	private Random rand;

	public DevArrow(Handler handler, float x, float y) {
		super(handler, x, y, 4, 4);
		
		
		rand = new Random();
		left = rand.nextBoolean();
		
		setVertices();
		
	}

	@Override
	public void render(Graphics g) {
		if(left)
			g.drawImage(Assets.arrowLeft, (int) x, (int) y, null);
		else{
			g.drawImage(Assets.arrowRight, (int) x, (int) y, null);
		}
		
	}

	@Override
	public void tick() {
		//Move();
		setVertices();
	}
	
	public void Move(){
		if(x <= 0)
			left = false;
		if(x >= 1280 - 64)
			left = true;
		if(left){
			x -= speed;
		}else{
			x += speed;
		}		
	}
	
	public void setVertices(){
		if(left){
			vertices[0] = new Vector2(x + 0, y + 33);
			vertices[1] = new Vector2(x + 59, y + 5);
			vertices[2] = new Vector2(x + 63, y + 5);
			vertices[3] = new Vector2(x + 42, y + 33);
			
			vertices2[0] = new Vector2(x + 0, y + 33);
			vertices2[1] = new Vector2(x + 56, y + 61);
			vertices2[2] = new Vector2(x + 63, y + 61);
			vertices2[3] = new Vector2(x + 42, y + 33);
		}else{
			vertices[0] = new Vector2(x + 64, y + 33);
			vertices[1] = new Vector2(x + 23, y + 33);
			vertices[2] = new Vector2(x + 0, y + 5);
			vertices[3] = new Vector2(x + 9, y + 5);
			
			vertices2[0] = new Vector2(x + 64, y + 33);
			vertices2[1] = new Vector2(x + 9, y + 61);
			vertices2[2] = new Vector2(x + 0, y + 61);
			vertices2[3] = new Vector2(x + 23, y + 33);
		}
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	
	

	public Vector2[] getVertices2() {
		return vertices2;
	}

	public void setVertices2(Vector2[] vertices2) {
		this.vertices2 = vertices2;
	}

	@Override
	public boolean isCircle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isConcave() {
		// TODO Auto-generated method stub
		return true;
	}
	

}

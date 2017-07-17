package shapegame.dev;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import shapegame.collision.SAT;
import shapegame.engine.Assets;
import shapegame.engine.Handler;
import shapegame.engine.Vector2;
import shapegame.entity.Entity;

public class DevPlayer extends DevEntity{
	
	private double speed = 4.5;
	private SAT sat;
	private DevEntity collide = null;

	public DevPlayer(Handler handler, float x, float y) {
		super(handler, x, y, 4, 0);
	
		vertices[0] = new Vector2(0 + x, 0 + y);
		vertices[1] = new Vector2(64 + x, 0 + y);
		vertices[2] = new Vector2(64 + x, 64 + y);
		vertices[3] = new Vector2(0 + x, 64 + y);
		
		sat = new SAT(handler);
	}

	public void tick() {
		getInput();
	
		vertices[0] = new Vector2(0 + x, 0 + y);
		vertices[1] = new Vector2(64 + x, 0 + y);
		vertices[2] = new Vector2(64 + x, 64 + y);
		vertices[3] = new Vector2(0 + x, 64 + y);
		
	

					
		
		
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) x, (int) y, null);

		for(int i = 0; i < vertices.length; i++){
			g.setColor(Color.BLUE);


		}
	}

	private void getInput() {
		if(handler.getKeyManager().left){
			x -= speed;
		}
		
		if(handler.getKeyManager().right){
			x += speed;
		}
		
		if(handler.getKeyManager().down){
			y += speed;
		}
		
		if(handler.getKeyManager().up){
			y -= speed;
		}
		
	}



	@Override
	public void die() {
		active = false;
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
		return false;
	}

}

package shapegame.dev;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import shapegame.engine.Assets;
import shapegame.engine.Handler;
import shapegame.engine.Vector2;

public class DevPlayer extends DevEntity{
	
	private double speed = 4.5;


	public DevPlayer(Handler handler, float x, float y) {
		super(handler, x, y, 4);
	
		vertices[0] = new Vector2(0 + x, 0 + y);
		vertices[1] = new Vector2(64 + x, 0 + y);
		vertices[2] = new Vector2(64 + x, 64 + y);
		vertices[3] = new Vector2(0 + x, 64 + y);

	}

	public void tick() {
		getInput();
	
		vertices[0] = new Vector2(0 + x, 0 + y);
		vertices[1] = new Vector2(64 + x, 0 + y);
		vertices[2] = new Vector2(64 + x, 64 + y);
		vertices[3] = new Vector2(0 + x, 64 + y);
		
		System.out.println(separatingAxisTheorem());
		
		if(separatingAxisTheorem()){
			die();
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) x, (int) y, null);

		for(int i = 0; i < vertices.length; i++){
			g.setColor(Color.BLUE);
			g.drawLine((int) (vertices[i].x),(int) (vertices[i].y),(int) (axis[i].x + x),(int) (axis[i].y + y));


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

}

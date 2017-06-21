package shapegame.dev;

import java.awt.Graphics;

import shapegame.engine.Assets;
import shapegame.engine.Handler;
import shapegame.engine.Vector2;



public class DevCircle extends DevEntity{


	public DevCircle(Handler handler, float x, float y) {
		super(handler, x, y, 3);
		
		
	
	}

	@Override
	public void tick() {
		

		
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.circle, (int) x, (int) y, null);
		
		
	}

	@Override
	public void die() {
		active = false;
		
	}



}

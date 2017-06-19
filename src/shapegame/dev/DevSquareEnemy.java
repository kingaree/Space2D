package shapegame.dev;

import java.awt.Color;
import java.awt.Graphics;

import shapegame.engine.Assets;
import shapegame.engine.Handler;
import shapegame.engine.Vector2;

public class DevSquareEnemy extends DevEntity{

	public DevSquareEnemy(Handler handler, float x, float y) {
		super(handler, x, y, 4);
		
		vertices[0] = new Vector2(0 + x, 0 + y);
		vertices[1] = new Vector2(64 + x, 0 + y);
		vertices[2] = new Vector2(64 + x, 64 + y);
		vertices[3] = new Vector2(0 + x, 64 + y);
	
	}

	@Override
	public void tick() {
		vertices[0] = new Vector2(0 + x, 0 + y);
		vertices[1] = new Vector2(64 + x, 0 + y);
		vertices[2] = new Vector2(64 + x, 64 + y);
		vertices[3] = new Vector2(0 + x, 64 + y);

		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.square, (int) x, (int) y, null);
		
		for(int i = 0; i < vertices.length; i++){

		}
	}

	@Override
	public void die() {
		active = false;
		
	}

}

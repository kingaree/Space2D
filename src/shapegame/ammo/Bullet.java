package shapegame.ammo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;





import java.util.ArrayList;
import java.util.Vector;

import shapegame.engine.Assets;
import shapegame.engine.Handler;
import shapegame.engine.Player;
import shapegame.engine.Spawner;
import shapegame.engine.World;
import shapegame.entity.Entity;

public class Bullet extends AmmoEntity{
	
	
	private float speed = 10;
	
	private float dirX, dirY;
	

	
	public Bullet(Handler handler, float x, float y, float targetX, float targetY){
		super(handler, x, y, targetX, targetY, 1, 0);
		this.handler = handler;
		
		dirX = targetX - x;
		dirY = targetY - y;
	}
	
	public void tick(){
		 vectorShoot();
		 checkAmmoCollisions();
		 checkBulletLocation();
	}
	
	public void render(Graphics g){
		g.drawImage(Assets.bullet, (int) x, (int) y, null);
		
		
	}
	
	public void die(){
		active = false;
		World.bulletCount -= 1;
	}
	
	public void vectorShoot(){
		

		double dl = Math.sqrt(dirX * dirX + dirY * dirY);
		dirX = (float) (dirX/dl);
		dirY = (float) (dirY/dl);
		
	
		x = x + (dirX * speed);
		y = y + (dirY * speed);

	}

	@Override
	public boolean isFriendly() {
		return false;
	}

	@Override
	public boolean isCircle() {
		return true;
	}

	@Override
	public boolean isConcave() {
		return false;
	}

}

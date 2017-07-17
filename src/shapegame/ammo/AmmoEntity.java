package shapegame.ammo;

import java.awt.Graphics;
import java.awt.Rectangle;

import shapegame.collision.SAT;
import shapegame.engine.Handler;
import shapegame.engine.Vector2;
import shapegame.entity.EnemyCircle;
import shapegame.entity.Entity;

public abstract class AmmoEntity{
	
	protected float x, y, targetX, targetY;
	protected int radius = 5, n, n2;
	protected Handler handler;
	protected SAT sat;
	protected Vector2[] vertices;
	
	
	protected boolean active = true;

	public AmmoEntity(Handler handler, float x, float y, float targetX, float targetY, int n, int n2){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.targetX = targetX;
		this.targetY = targetY;
		this.n = n;
		this.n2 = n2;
	
		vertices = new Vector2[n];
		sat = new SAT(handler);
		
	}
	
	public void checkAmmoCollisions(){
		
		if(handler.getWorld().getEntityManager().getEntities() != null)	
			for(Entity e : handler.getWorld().getEntityManager().getEntities()){
				if(!e.isFriendly() && e.checkVulnerability()){
					if(e instanceof EnemyCircle){
						if(sat.circleToCircle(x + 32, y + 32, radius, e.getX() + 32, e.getY() + 32, e.getRadius())){
							die();
							e.die();
						}
					}else
				if(e.isConcave()){
					if(sat.polyToCircle(e.getVertices(), x + 32, y + 32, radius) || (sat.polyToCircle(e.getVertices2(), x + 32, y + 32, radius))){
						die();
						e.die();
					}
				}else	
					if(sat.polyToCircle(e.getVertices(), x + 32, y + 32, radius)){
						die();
						e.die();
				}
			}
		}	
	}
	
	public void checkBulletLocation(){
		if(x > 1279 || x < 0 - 100){
			die();
		}
			
		if(y > 719 || y <= 0 - 100){
			die();
		}
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public abstract boolean isFriendly();
	
	public abstract boolean isCircle();
	
	
	public abstract boolean isConcave();
	
	public abstract void die();

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

	
}

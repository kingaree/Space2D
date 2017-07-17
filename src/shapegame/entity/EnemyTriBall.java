package shapegame.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import shapegame.engine.Assets;
import shapegame.engine.Handler;
import shapegame.engine.Vector2;
import shapegame.engine.World;

public class EnemyTriBall extends ActiveEntity{
	
	private float xl, yl;
	private double dl, xn, yn;
	private double angle, angleRad, angleRot = 0;
	private AffineTransformOp op;
	private AffineTransform af;
	private boolean rotating = true;
	private int speed = 10;

	public EnemyTriBall(Handler handler, float x, float y) {
		super(handler, x, y, 3, 6);
		
		vertices[0] = new Vector2(x + 25, y + 13);
		vertices[1] = new Vector2(x + 64, y + 33);
		vertices[2] = new Vector2(x + 25, y + 53);
		
		rotateToPlayer();
		
	}
	
	@Override
	public void tick() {
		rotateVertices();
		checkLimits();
		if(checkVulnerability()){
			if(rotating){
				rotateToPlayer();
			}else{
				x += xn * speed;
				y += yn * speed;	
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		
		af = AffineTransform.getTranslateInstance(x, y);
		af.rotate(angleRot, 32, 32);
	
		//System.out.println(angleRot + "   " + angle);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(Assets.triBall, af, null);
		
		
	}

	private void rotateToPlayer(){
		 angle = Math.atan2(handler.getWorld().getEntityManager().getPlayer().getY() + 32  - y, 
				handler.getWorld().getEntityManager().getPlayer().getX() + 32 - x);

		 
		 angleRad = Math.toRadians(angle);
		 
		 checkRotations();
		 
		 if(angle < 0){
			 if(angleRot - angle < 0.02){
			 	rotating = false;
			 	chargeToPlayer();
		 	}
		 }else{
			 if(angle - angleRot < 0.02){
				 	rotating = false;
				 	chargeToPlayer();
			 	}
		 }
	}
	
	private void checkRotations(){
		 if(angleRot < angle){
			 angleRot += 0.021;
		 }
		 
		 if(angleRot > angle){
			 angleRot -= 0.021;
		 }
	}

	private void chargeToPlayer(){
		rotating = false;
		
		xl = handler.getWorld().getEntityManager().getPlayer().getX() + 32 - x;
		yl = handler.getWorld().getEntityManager().getPlayer().getY() + 32 - y;
		
		dl = Math.sqrt((xl * xl) + (yl * yl));
		
		xn = xl/dl;
		yn = yl/dl;
		
		
	}
	
	public void rotateVertices(){
		double cos = Math.cos(angleRot);
		double sin = Math.sin(angleRot);
		
		double cosRot = x * cos - sin;
		double sinRot = y * sin + cos;
		
		vertices[0] = new Vector2(((x + 25) - 32) * cosRot, ((y + 13) - 32) * sinRot + 32);
		vertices[1] = new Vector2(((x + 64) - 32) * cosRot, ((y + 33) - 32) * sinRot + 32);
		vertices[2] = new Vector2(((x + 25) - 32) * cosRot, ((y + 53) - 32) * sinRot + 32);
		 
	}
	
	public void checkLimits(){
		if(x <= 0){
			x = 1;
			rotating = true;
		}
		if(x >= 1279 - 64){
			x = 1279 - 65;
			rotating = true;
		}
		
		if(y <= 0){
			y = 1;
			rotating = true;
		}
		if(y >= 719 - 64){
			y = 719 - 65;
			rotating = true;
		}
	}

	@Override
	public void die() {
		active = false;
		sfx.get("explosion").play();
		World.enemyCount -= 1;
		World.score += 70;
		
	}

	@Override
	public boolean isCircle() {
		return false;
	}

	@Override
	public double getRadius() {
		return 0;
	}

	@Override
	public boolean isConcave() {
		return false;
	}

	@Override
	public boolean isFriendly() {
		return false;
	}

}

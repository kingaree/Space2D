package shapegame.dev;

import java.awt.Graphics;

import shapegame.ammo.BulletManager;
import shapegame.engine.Handler;
import shapegame.entity.EnemySquare;
import shapegame.entity.EntityManager;

public class DevWorld{

	private Handler handler;
	private DevEntityManager devM;
	
	public DevWorld(Handler handler){
		this.handler = handler;
		
		init();
	}
	
	public void init(){
		devM = new DevEntityManager(handler, new DevPlayer(handler, (1270/2) - 32, (720/2) - 32));
		
		devM.addDevEntity(new DevSquareEnemy(handler, 200, 400));
		devM.addDevEntity(new DevTriEnemy(handler, 200, 100));
	}
	
	public void tick(){
		devM.tick();
	}
	
	public void render(Graphics g){
		devM.render(g);
	}

	public DevEntityManager getDevM() {
		return devM;
	}

	public void setDevM(DevEntityManager devM) {
		this.devM = devM;
	}
	
	
	
}

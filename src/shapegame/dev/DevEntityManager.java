package shapegame.dev;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import shapegame.engine.Handler;
import javazoom.jl.player.Player;

public class DevEntityManager {
	
	private Handler handler;
	private DevPlayer devPlayer;
	private List<DevEntity> devEntities = new ArrayList<DevEntity>();
	
	public DevEntityManager(Handler handler, DevPlayer devPlayer){
		this.handler = handler;
		this.devPlayer = devPlayer;
		devEntities.add(devPlayer);
	}
	
	public void tick(){
		for(DevEntity e : devEntities){
			e.tick();
			if(!e.active){
				removeDevEntity(e);
			}
			
		}
		
	}
	
	public void render(Graphics g){
		for(DevEntity e : devEntities){
			e.render(g);
		}
	}
	
	public void addDevEntity(DevEntity de){
		devEntities.add(de);
	}
	
	public void removeDevEntity(DevEntity de){
		devEntities.remove(de);
	}

	public DevPlayer getDevPlayer() {
		return devPlayer;
	}

	public void setDevPlayer(DevPlayer devPlayer) {
		this.devPlayer = devPlayer;
	}

	public List<DevEntity> getDevEntities() {
		return devEntities;
	}

	public void setDevEntities(List<DevEntity> devEntities) {
		this.devEntities = devEntities;
	}
	
	

}

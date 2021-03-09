package managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import scenes.GameScene;

public class GameSceneManager {
	
	private List<GameScene> gameScenes;
	
	public GameSceneManager() {
		this.gameScenes = new ArrayList<GameScene>();
	}
	public void push(GameScene gameScene) {
		this.gameScenes.add(gameScene);
	}
	public void set(GameScene gameScene) {
		this.gameScenes.get(this.gameScenes.size()-1).dispose();
		this.gameScenes.remove(this.gameScenes.size()-1);
		this.gameScenes.add(gameScene);
	}
	public void update(float dt) {
		this.gameScenes.get(this.gameScenes.size()-1).update(dt);
	}
	public void render(SpriteBatch sb) {
		this.gameScenes.get(this.gameScenes.size()-1).render(sb);
	}
	
	
}

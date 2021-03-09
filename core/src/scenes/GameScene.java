package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import managers.GameSceneManager;
import sprites.GameObject;

public abstract class GameScene {

	
	protected OrthographicCamera cam;
	protected GameSceneManager gsm;
	protected Texture background;
	protected Music sceneMusic;
	public Array<GameObject> gameObjects;
	
	public GameScene(GameSceneManager gsm) {
		this.cam = new OrthographicCamera();
		this.gsm = gsm;
		this.gameObjects = new Array<GameObject>();	
	}
	protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
    
	public void setBackground(Texture background) {
		this.background = background;
	}
	public void setSceneMusic(String pathMusic) {
		this.sceneMusic = Gdx.audio.newMusic(Gdx.files.internal(pathMusic));
		this.sceneMusic.setLooping(true);
	}
  
}

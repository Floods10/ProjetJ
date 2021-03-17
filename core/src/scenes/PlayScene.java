package scenes;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import managers.GameSceneManager;
import sprites.Bonus;
import sprites.GameObject;
import sprites.Heros;
import sprites.Monstre;
import utils.Constants;
import utils.Resizer;

public class PlayScene extends GameScene {

	private Texture background;
	private Array<Monstre> monstres;
	private Array<Monstre> monstres2;
	private Array<Bonus> bonuss;
	private long lastBonusTime;
	
	private Array<String> tagsE1;
	private Array<String> tagsE2;
	private Array<String> tags;
	private Heros heros;
	
	public PlayScene(GameSceneManager gsm) {
		super(gsm);
		this.setSceneMusic("sceneMusic.mp3");
		if (Constants.PLAY_MUSIC) {
			this.sceneMusic.play();}
		this.background = Resizer.resize("PNG/bck.png");
		
		this.monstres = new Array<Monstre>();
		this.monstres2 = new Array<Monstre>();
		
		tagsE1 = new Array<String>();
		tagsE1.add("Equipe 1");
		tagsE2 = new Array<String>();
		tagsE2.add("Equipe 2");
		tags = new Array<String>();
		tags.add("bonus");
		
		this.monstres.add(new Monstre(this, 900, 900, tagsE1));
		this.bonuss = new Array<Bonus>();
		this.heros = new Heros(this, tagsE1);
	}

	@Override
	protected void handleInput() {
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			this.monstres.add(new Monstre(this, 900, 900, tagsE1));
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			System.out.println("S");
			this.monstres2.add(new Monstre(this, 900, 900, tagsE2));
			this.monstres2.get(this.monstres2.size-1).setTexture(new Texture("PNG/greenery_10.png"));
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {this.gsm.set(new Home(this.gsm));}
		/*if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {this.monstre.moveLeft();}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {this.monstre.moveRight();}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {this.monstre.moveUp();;}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {this.monstre.moveDown();}*/
		
		//if(Gdx.input.isKeyPressed(Input.Keys.W)) {this.monstre.setPosition(0.0f, 0.0f);}
		//if(Gdx.input.isKeyPressed(Input.Keys.X)) {this.monstre.setPosition(64.0f, 64.0f);}
	}

	@Override
	public void update(float dt) {
		this.handleInput();
		for(GameObject go : this.gameObjects) {
			go.update(dt);
		}
		
		if(false || ((TimeUtils.millis() - lastBonusTime)/1000 > Constants.TIME_BONUS && bonuss.size<Constants.MAX_BONUS)) {
			this.lastBonusTime = TimeUtils.millis();
			bonuss.add(new Bonus(this, MathUtils.random(0, Constants.MAX_X), MathUtils.random(0, Constants.MAX_Y),tags));
			if(Constants.PLAY_SOUND) {
				bonuss.get(bonuss.size-1).playApparitionSound();
			}
		}
	
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(this.background, 0,0);
		for(GameObject go : this.gameObjects) {
			go.draw(sb);
		}
		sb.end();

	}

	@Override
	public void dispose() {
		this.background.dispose();
		this.sceneMusic.dispose();
		
		for(GameObject go : this.gameObjects) {
			go.dispose();
		}
	}

	public Array<Bonus> getBonuss() {
		return bonuss;
	}
	
	

}

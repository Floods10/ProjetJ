package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import scenes.GameScene;
import scenes.PlayScene;
import utils.Constants;
import utils.Transform;

public class Bonus extends GameObject {

	private Sound disparitionSound;
	private Sound apparitionSound;
	private Circle body;
	private long lastBonusTime;
	private Array<Bonus> bonuss;
	private Array<String> tags = new Array<String>();
	

	public Bonus(GameScene scene, float x, float y, Array<String> tags) {
		super(scene, tags);
		this.texture = new Texture(Gdx.files.internal("PNG/decor_7.png"));
		this.apparitionSound = Gdx.audio.newSound(Gdx.files.internal("appearBonusSound.wav"));
		this.disparitionSound = Gdx.audio.newSound(Gdx.files.internal("bonus2Sound.wav"));
		this.transform = new Transform(new Vector2(x, y));
		this.body = new Circle();
		this.body.radius = Math.min(texture.getHeight(),texture.getWidth())/2;
	    this.body.setPosition(this.transform.getPosition());
	    tags.add("bonus");
	    
	}
	public Bonus(GameScene scene, float x, float y) {
		this(scene, x, y, new Array<String>());
	}

	
	public void update(float dt) {	
		super.update(dt);
	}
	
	@Override
	public void dispose() {
		this.texture.dispose();
		this.apparitionSound.dispose();
		this.disparitionSound.dispose();
	}

	
	public boolean overlaps(Circle c) {
		return this.body.overlaps(c);
	}
	
	public void playDisparitionSound() {
		this.disparitionSound.play();
	}
	
	public void playApparitionSound() {
		this.apparitionSound.play();
	}
	public Circle getBody() {
		return body;
	}

	
	
	
	
	
}

package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import gui.GuiElement;
import scenes.GameScene;
import scenes.Home;
import utils.Constants;
import utils.Transform;

public final class Heros extends Vivant {

	//private Circle body;

	public Heros(GameScene scene, float x, float y, Array<String> tags) {
		super(scene, Constants.VIE_HEROS, Constants.ATTAQUE_HEROS, Constants.VITESSE_HEROS, tags);
		this.texture = new Texture("PNG/stones_6.png");
		this.transform = new Transform(new Vector2(x, y)); // position initiale du heros
		float radius = Math.min(this.texture.getHeight(),this.texture.getWidth())/2;
		this.body = new Circle(this.transform.getPosition(), radius);	
	}

	public Heros(GameScene scene, Array<String> tags) {
		this(scene, Constants.WINDOW_WIDTH/4, Constants.WINDOW_HEIGH/2, tags);
	}

	public Heros() {
	}

	@Override
	public void dispose() 
	{
		super.dispose();
		this.texture.dispose();
	}

	@Override
	public void update(float dt) 
	{
		super.update(dt); //isDead
		handleInput();
		updatePosition(dt);
	}


	public void handleInput() 
	{
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				this.force.nor();
				this.force.add(-1, 0);
				this.force.nor();
				this.force.scl(Constants.ACCELERATION);
			}
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				this.force.nor();
				this.force.add(1, 0);
				this.force.nor();
				this.force.scl(Constants.ACCELERATION);
			}
			if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
				this.force.nor();
				this.force.add(0, 1);
				this.force.nor();
				this.force.scl(Constants.ACCELERATION);
			}
			if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				this.force.nor();
				this.force.add(0, -1);
				this.force.nor();
				this.force.scl(Constants.ACCELERATION);
			}
		} else {
			this.force.setZero();
		}
	}

	@Override
	public String toString() {
		return "Heros [vie=" + vie + ", attaque=" + attaque + ", vitesse=" + vitesse + ", texture=" + texture
				+ ", tags=" + tags + ", isDead=" + isDead + "]";
	}

}

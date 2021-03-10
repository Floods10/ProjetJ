package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import scenes.GameScene;
import scenes.Home;
import utils.Constants;
import utils.Transform;

public final class Heros extends Vivant {

	//private Circle body;

	public Heros(GameScene scene, float x, float y, Array<String> tags) 
	{
		super(scene, Constants.VIE_HEROS, Constants.ATTAQUE_HEROS, Constants.VITESSE_HEROS, tags);
		this.texture = new Texture("PNG/stones_6.png");
		this.transform = new Transform(new Vector2(x, y)); // position initiale du heros
		float radius = Math.min(this.texture.getHeight(),this.texture.getWidth())/2;
		this.body = new Circle(this.transform.getPosition(), radius);	
	}

	public Heros(GameScene scene, Array<String> tags) 
	{
		this(scene, Constants.WINDOW_WIDTH/4, Constants.WINDOW_HEIGH/2, tags);
	}


	public Heros() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() 
	{
		this.texture.dispose();
	}

	@Override
	public void update(float dt) 
	{
		super.update(dt); //isDead
		handleInput();
	}


	public void handleInput() 
	{
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {this.moveLeft();}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {this.moveRight();}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {this.moveUp();}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {this.moveDown();}
	}

	public void moveRight() {
		this.transform.setX(this.transform.getX()+Constants.SPEED * Gdx.graphics.getDeltaTime());
	}
	public void moveLeft() {
		this.transform.setX(this.transform.getX()-Constants.SPEED * Gdx.graphics.getDeltaTime());
	}
	public void moveUp() {
		this.transform.setY(this.transform.getY()+Constants.SPEED * Gdx.graphics.getDeltaTime());
	}
	public void moveDown() {
		this.transform.setY(this.transform.getY()-Constants.SPEED * Gdx.graphics.getDeltaTime());
	}

}

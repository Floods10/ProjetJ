package sprites;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import gui.GuiElement;
import scenes.GameScene;
import utils.Constants;
import utils.NewtonPhysic;
import utils.Transform;


public abstract class GameObject {
	
	protected GameScene scene;
	protected Texture texture;
	protected AnimationManager am;
	protected Transform transform;
	//protected Array<String> tags;
	protected HashMap<String, String> tags;
	protected boolean isDead;
	protected float masse;
	protected Vector2 force;
	protected GuiElement text;
	
	protected NewtonPhysic newtonPhysic = new NewtonPhysic(this);

	public GameObject() {}
	
	public GameObject(GameScene scene, HashMap<String, String> tags ) {
		this.scene = scene;
		this.tags = tags;
		this.scene.gameObjects.add(this);
		this.isDead = false ;
		this.text = new GuiElement("fonts/orange juice 2.0.ttf", "", 24);
		this.force = new Vector2(0, 0);
		this.masse = 20;
	}
	
	public void update(float dt)
	{	
		
		if(isDead)
		{
			this.scene.gameObjects.removeValue(this, false);
			if (this.am!=null) {
				//
			}
		}
		else {
			if (this.text!=null) {
				this.text.setTransform(this.transform.getX(), this.transform.getY()+100);
			}
			this.force.setZero();
		}
	}
	public void updatePosition(float dt)
	{
		this.newtonPhysic.updatePosition(dt);
	}
	
	public void draw(SpriteBatch sb) {
		if(this.texture!=null) {
			sb.draw(this.getTexture(), this.getTexturePosition().x, this.getTexturePosition().y);
		}
		if (this.am!=null) {
			this.getAm().renderInit(sb);
			this.getAm().draw(sb);
		}
		if (this.text!=null) {
			this.text.draw(sb);
		}
	}
	
	public void drawWithRotation(SpriteBatch sb, BouleDeFeu fireBall)
	{
		//sb.draw(new TextureRegion(fireBall.texture), fireBall.getX(), fireBall.getY(), fireBall.originX, fireBall.originY, (float) fireBall.texture.getWidth(), (float) fireBall.texture.getHeight(), 1.0f, 1.0f, (float) (fireBall.rotation*180/Math.PI));
		sb.draw(new TextureRegion(fireBall.texture), fireBall.getX(), fireBall.getY(), 0.0f, 0.0f, (float) fireBall.texture.getWidth(), (float) fireBall.texture.getHeight(), 1.0f, 1.0f, (float) (fireBall.rotation*180/Math.PI));
		//sb.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
	}
	
	public void dispose() {
		if (this.text!=null) {
			this.text.dispose();
		}
	}
	
	public void moveToward(GameObject go) {
		if (go!=null) {
			Vector2 direction = new Vector2(go.getPosition());
			direction.sub(this.getPosition()); 
			direction.nor();
			direction.scl(Constants.SPEED * Gdx.graphics.getDeltaTime());
			direction.add(this.getPosition());
			//this.setPosition(direction);
			this.force.add(direction);
			this.am.setAnim("front_Walk");
		}
	}
	
	public GameObject nearest(String tagKey, String tagValue) {
		Array<GameObject> lgo = this.scene.gameObjects;
		GameObject tmp = null;
		float min=0, dist;
		int i = 0;
		boolean minGot = false;
		
		if (lgo.size>1) {
			do {
				if (lgo.get(i).tags.keySet().contains(tagKey) && lgo.get(i).tags.get(tagKey).equals(tagValue) && !lgo.get(i).equals(this)) {
					if (!minGot) {
						min = this.transform.getPosition().dst(lgo.get(i).transform.getPosition());
						tmp = lgo.get(i);
						minGot = true;	
					}
					dist = this.transform.getPosition().dst(lgo.get(i).transform.getPosition());
					if (dist<min) {
						min=dist;
						tmp = lgo.get(i);
					}
				}
				i++;
			} while (i<lgo.size);
			return tmp;
		} return null;
	}


	public float getMasse() {
		return masse;
	}

	public Vector2 getForce() {
		return force;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public Texture getTexture() {
		return texture;
	}
	public Vector2 getTexturePosition() {
		return new Vector2(this.getX()-(this.texture.getWidth()/2), this.getY()-(this.texture.getHeight())/2);
	}
	
	public Vector2 getPosition() {
		return this.transform.getPosition();
	}
	public float getX() {
		return this.transform.getX();
	}
	public float getY() {
		return this.transform.getY();
	}
	
	public void setPosition(Vector2 position) {
		this.transform.setPosition(position);
	}
	public void setPosition(float x, float y) {
		this.transform.setPosition(x, y);
	}
	public void setX(float  x) {
		this.transform.setX(x);
	}
	public void setY(float y) {
		this.transform.setY(y);
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}
	
	public void rebond(GameObject go) {
		Vector2 direction = new Vector2(go.getPosition());
		direction.sub(this.getPosition());
		direction.nor();
		direction.scl(-1 * Constants.COEFF_REBOND * Gdx.graphics.getDeltaTime());
		direction.add(this.getPosition());
		this.force=direction;
		
	}
    
	public void attaquer(Vivant v, float attaque)
	{
		v.decreaseLife(attaque);
	}

	public AnimationManager getAm() {
		return am;
	}

	public void setAm(AnimationManager am) {
		this.am = am;
	}

	public HashMap<String, String> getTags() {
		return tags;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	/*
	public void setPosition(Vector2 position) {
		this.newtonPhysic.setPosition(position);
	}
	public void setPosition(float x, float y) {
		this.newtonPhysic.setPosition(x, y);
	}
	public void setX(float  x) {
		this.newtonPhysic.setX(x);
	}
	public void setY(float y) {
		this.newtonPhysic.setY(y);
	}
	*/


}

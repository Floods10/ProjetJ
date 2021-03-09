package sprites;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import scenes.GameScene;
import utils.Constants;
import utils.Transform;


public abstract class GameObject {
	
	protected GameScene scene;
	protected Texture texture;
	protected Transform transform;
	protected Array<String> tags;
	

	public GameObject() {}
	
	public GameObject(GameScene scene, Array<String> tags) {
		this.scene = scene;
		this.tags = tags;
		this.scene.gameObjects.add(this);
	}
	
	public abstract void update(float dt);
	
	public void draw(SpriteBatch sb) {
		sb.draw(this.getTexture(), this.getTexturePosition().x, this.getTexturePosition().y);
	}
	
	public abstract void dispose();
	
	public void moveToward(GameObject go) {
		if (go!=null) {
			Vector2 direction = new Vector2(go.getPosition());
			direction.sub(this.getPosition()); 
			direction.nor();
			direction.scl(Constants.SPEED * Gdx.graphics.getDeltaTime());
			direction.add(this.getPosition());
			this.setPosition(direction);
		}
	}
	
	public GameObject nearest(String tag) {
		Array<GameObject> lgo = this.scene.gameObjects;
		GameObject tmp = null;
		float min=0, dist;
		int i = 0;
		boolean minGot = false;
		
		if (lgo.size>1) {
			do {
				if ((lgo.get(i).tags.contains(tag,false) || tag.equals("")) && !lgo.get(i).equals(this)) {
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


}

package sprites;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import scenes.GameScene;
import scenes.PlayScene;
import utils.Constants;
import utils.Transform;

public class Monstre extends Vivant{

	//private Circle body;
	private GameObject nearestBonus;
	private GameObject nearestEnnemis;


	public Monstre(GameScene scene, float x, float y, HashMap<String, String> tags) {
		super(scene, Constants.VIE_MONSTRE, Constants.ATTAQUE_MONSTRE, Constants.VITESSE_MONSTRE, tags);
		this.texture = new Texture("PNG/greenery_2.png");
		this.transform = new Transform(new Vector2(x, y));
		//float radius = Math.min(this.texture.getHeight(),this.texture.getWidth())/2;
		float radius =120.0f;
		this.body = new Circle(this.transform.getPosition(), radius);	
	}

	public Monstre(GameScene scene, float x, float y) {
		this(scene, x, y, new HashMap<String, String>());
	}

	@Override
	public void update(float dt) {
		super.update(dt); // Appelle le update de Vivant ==> de Game Object
		
		
		nearestBonus = this.nearest("equipe", "bonus");
		if(this.tags.get("equipe").equals("Equipe1")) {
			nearestEnnemis = this.nearest("equipe", "Equipe2");
		}
		if(this.tags.get("equipe").equals("Equipe2")) {
			nearestEnnemis = this.nearest("equipe", "Equipe1");
		}
		if (nearestBonus!=null) {
			this.moveToward(nearestBonus);
		}
		else if (nearestBonus==null && nearestEnnemis!=null) {
			this.moveToward(nearestEnnemis);
		}

		updatePosition(dt);
	}

	@Override
	public void dispose() {
		super.dispose();
		this.texture.dispose();
	}

	

	public Circle getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "Monstre [vie=" + vie + ", attaque=" + attaque + ", vitesse=" + vitesse + ", texture=" + texture
				+ ", tags=" + tags + ", isDead=" + isDead + "]";
	}

	
}

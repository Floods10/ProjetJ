package sprites;

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


	public Monstre(GameScene scene, float x, float y, Array<String> tags) {
		super(scene, Constants.VIE_MONSTRE, Constants.ATTAQUE_MONSTRE, Constants.VITESSE_MONSTRE, tags);
		this.texture = new Texture("PNG/greenery_2.png");
		this.transform = new Transform(new Vector2(x, y));
		float radius = Math.min(this.texture.getHeight(),this.texture.getWidth())/2;
		this.body = new Circle(this.transform.getPosition(), radius);	
	}

	public Monstre(GameScene scene, float x, float y) {
		this(scene, x, y, new Array<String>());
	}

@Override
	public void update(float dt) {
		super.update(dt); // Appelle le update de Vivant ==> de Game Object
		
		System.out.println("update monstre");
		
		nearestBonus = this.nearest("bonus");
		if(this.tags.contains("Equipe1", false)) {
			nearestEnnemis = this.nearest("Equipe2");
		}
		if(this.tags.contains("Equipe2", false)) {
			nearestEnnemis = this.nearest("Equipe1");
		}
		if (nearestBonus!=null) {
			this.moveToward(nearestBonus);
		}
		else if (nearestBonus==null && nearestEnnemis!=null) {
			this.moveToward(nearestEnnemis);
		}

		
		for (Iterator<Bonus> iter = ((PlayScene)this.scene).getBonuss().iterator(); iter.hasNext(); ) {
			Bonus bonus = iter.next();

			if(bonus.overlaps(this.getBody())) {
				bonus.playDisparitionSound();
				this.scene.gameObjects.removeValue(bonus, false);
				iter.remove();
			}	
		}



		if(this.transform.getY() < Constants.MIN_Y) {
			this.transform.setY(Constants.MIN_Y);
		}
		else if(this.transform.getY() > Constants.MAX_Y) {
			this.transform.setY(Constants.MAX_Y);
		}
		else if(this.transform.getX() > Constants.MAX_X) {
			this.transform.setX(Constants.MAX_X);
		}
		else if(this.transform.getX() < Constants.MIN_X) {
			this.transform.setX(Constants.MIN_X);
		}

		this.body.setPosition(this.transform.getPosition());
	}

	@Override
	public void dispose() {
		this.texture.dispose();
	}

	

	public Circle getBody() {
		return body;
	}


}

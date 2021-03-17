package sprites;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import scenes.GameScene;
import utils.Constants;

public abstract class Vivant extends GameObject {

	protected float vie;
	protected float attaque;
	protected float vitesse;
	protected Circle body;
	
	
	public Vivant(GameScene scene, float vie, float attaque, float vitesse, HashMap<String, String> tags) {
		super(scene, tags);
		this.vie = vie;
		this.attaque = attaque;
		this.vitesse = vitesse;
		
	}
	
	public Vivant()
	{
		
	}
	
	public Circle getBody() {
		return body;
	}
	
	public float getVie() {
		return vie;
	}

	public void setVie(float vie) {
		this.vie = vie;
	}

	public float getAttaque() {
		return attaque;
	}

	public void setAttaque(float attaque) {
		this.attaque = attaque;
	}


	public float getVitesse() {
		return vitesse;
	}


	public void setVitesse(float vitesse) {
		this.vitesse = vitesse;
	}


	public void attaqueEpee()
	{
		new Epee(this);
	}
	
	public void attaquer(Vivant v)
	{
		super.attaquer(v, v.attaque);
	}
	public void attraperBonus(Bonus b) 
	{
		b.isDead = true ;
		if(Constants.PLAY_SOUND) {
		    b.playDisparitionSound();
		}
	}
	public void increaseLife(float valeurBonus) 
	{
		float vieNow, vieAfter;
		
		vieNow = this.getVie();
		vieAfter = vieNow + valeurBonus ;
		this.setVie(vieAfter);
	}
	
	public void decreaseLife(float valeurBonus)
	{
		increaseLife(-valeurBonus);
		if(this.getVie() <= 0.0)
		{
			isDead = true ;
		}
	}

		// Vu que vivant est une classe abstraite, pas besoin de coder les methodes update et dispose 
	public void rebond(Vivant v) {
		Vector2 direction = new Vector2(v.getPosition());
		direction.sub(this.getPosition());
		direction.nor();
		direction.scl(-1 * Constants.COEFF_REBOND * Gdx.graphics.getDeltaTime());
		direction.add(this.getPosition());
		this.force=direction;

	}
	@Override
	public void update(float dt)
	{
		super.update(dt);
		this.text.setText(this.tags.get(0)+"\nAtt: "+Float.toString(this.attaque)+"\nVie: "+Float.toString(this.vie));
		
		Array<GameObject> lgo = this.scene.gameObjects;
		for (int i = 0; i<lgo.size; i++) {
			if (!lgo.get(i).equals(this)) {
				
				if(lgo.get(i) instanceof Vivant)
				{
					if(((Vivant) lgo.get(i)).body.overlaps(this.body)) {
						rebond((Vivant) lgo.get(i));
						if(!lgo.get(i).tags.get("equipe").equals(this.tags.get("equipe"))) {
							
							this.attaquer(((Vivant) lgo.get(i)));
							System.out.println(">>>>>>>"+this.getClass());
						}
					}
				}
				
				else if (lgo.get(i) instanceof Bonus) {
					if(((Bonus) lgo.get(i)).getBody().overlaps(this.body)) {
						this.attraperBonus((Bonus) lgo.get(i));
						System.out.println(">>>i>>>>>"+this.getClass());
					}
				}
			}
		}
		
		if(this.transform.getY() < Constants.MIN_Y) {
			this.newtonPhysic.setY(Constants.MIN_Y);
		}
		else if(this.transform.getY() > Constants.MAX_Y) {
			this.newtonPhysic.setY(Constants.MAX_Y);
		}
		else if(this.transform.getX() > Constants.MAX_X) {
			this.newtonPhysic.setX(Constants.MAX_X);
		}
		else if(this.transform.getX() < Constants.MIN_X) {
			this.newtonPhysic.setX(Constants.MIN_X);
		}
		
		this.body.setPosition(this.transform.getPosition());
	}

	



	

}

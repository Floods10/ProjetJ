package sprites;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;

import scenes.GameScene;

public abstract class Vivant extends GameObject {

	protected float vie;
	protected float attaque;
	protected float vitesse;
	protected Circle body;
	
	public Vivant(GameScene scene, float vie, float attaque, float vitesse, Array<String> tags) {
		super(scene, tags);
		this.vie = vie;
		this.attaque = attaque;
		this.vitesse = vitesse;
	}
	
	public Vivant()
	{
		
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


	public void attaquer(Vivant v)
	{
		v.decreaseLife(this.attaque);
	}
	public void attraperBonus(Bonus b) 
	{
		b.isDead = true ;
		//Coder bonus
		
		// Supprimer le bonus de la liste des GO
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
		if(this.getVie() < 0.0)
		{
			isDead = true ;
		}
	}

		// Vu que vivant est une classe abstraite, pas besoin de coder les methodes update et dispose 
	
	public void update()
	{
		for (GameObject go : this.scene.gameObjects) {
			
			if(go instanceof Vivant)
			{
				if(((Vivant) go).body.overlaps(this.body)) {
					this.attaquer(((Vivant) go));
				}
			}
			
		}
	}

}

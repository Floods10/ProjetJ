package sprites;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;

import scenes.GameScene;
import utils.Constants;

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
		b.playDisparitionSound();
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
		if(this.getVie() <= 0.0)
		{
			isDead = true ;
		}
	}

		// Vu que vivant est une classe abstraite, pas besoin de coder les methodes update et dispose 
	
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
						if(!lgo.get(i).tags.contains(this.tags.get(0), false)) { // A finir: ca marche que s'il y a un seul tag dans tags
							this.attaquer(((Vivant) lgo.get(i)));
							System.out.println(">>>>>>>"+this.getClass());
						}
					}
				}
				
				else if (lgo.get(i) instanceof Bonus) {
					if(((Bonus) lgo.get(i)).getBody().overlaps(this.body)) {
						this.attraperBonus((Bonus) lgo.get(i));
						System.out.println(">>>>>>>>"+this.getClass());
					}
				}
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

	



	

}

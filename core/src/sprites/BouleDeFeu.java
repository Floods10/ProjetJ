package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import utils.Transform;

public class BouleDeFeu extends Attaque
{
	
	private Rectangle bodyFireBall;
	private Vector2 centerFireBall;
	private Vector2 directionFireBall;
	private int compteurSpriteFireBall;

	public BouleDeFeu(Vivant owner) 
	{
		super(10, owner);
		this.texture = new Texture("PNG/bdf_init.png");
		bodyFireBall = new Rectangle(this.owner.getX()-this.texture.getWidth()/2, this.owner.getY()-this.texture.getHeight()/2, this.texture.getWidth(), this.texture.getHeight());
		centerFireBall = this.owner.getPosition().cpy();
		compteurSpriteFireBall = 0;
		this.transform = new Transform(centerFireBall);
	}
	
	public void update(float dt)
	{
		super.update(dt);
		compteurSpriteFireBall++;
		if(compteurSpriteFireBall%10<5)
		{
			this.texture = new Texture("PNG/bdf_1.png");
		}
		else
		{
			this.texture = new Texture("PNG/bdf_2_grosse.png");
		}
		
		this.setX(this.getX()+10); // A changer apres selon la direction
		this.setY(this.getY()+0); // A changer apres selon la direction
		Array<GameObject> lgo = this.scene.gameObjects;
		for (int i = 0; i<lgo.size; i++) {
			if (!lgo.get(i).equals(this) && !lgo.get(i).equals(owner)) {

				if(lgo.get(i) instanceof Vivant)
				{
					if(Intersector.overlaps(((Vivant) lgo.get(i)).body, this.bodyFireBall)) {
						lgo.get(i).rebond(this); //TODO LE REBOND NE MARCHE PAS
						super.attaquer(((Vivant) lgo.get(i)), this.valeurAttaque);
						System.out.println(">>>>>>>"+this.getClass());
						this.texture = new Texture("PNG/bdf_final_2.png");
						isDead = true;
					}
				}
			}
		}
		this.bodyFireBall.setPosition(this.transform.getPosition());
	
		
	}

}

package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import utils.Transform;

public class Epee extends Attaque {
	
	private Rectangle bodyEpee ;
	//private Vector2 centreRotation ; // Centre de rotation = position de l'owner = owner.getPosition()
	private Vector2 centreEpee ; // Centre de l'épée = this.getPosition()
	private Vector2 directionEpee;
	private Vector2 initialDirectionEpee;

	
	public Epee(Vivant owner) 
	{
		super(1, owner);
		this.texture = new Texture("PNG/sprite_epee.png");
		//deltaX et Y correspondent a la différence entre le x de l'owner et le x de l'épée
		double deltaX = Math.cos(((owner.getTransform().getRotation()-90) /360*2*Math.PI)%(2*Math.PI)) * (owner.getBody().radius/2) + (this.texture.getHeight()/2);
		double deltaY = Math.sin(((owner.getTransform().getRotation()-90) /360*2*Math.PI)%(2*Math.PI)) * (owner.getBody().radius/2) + (this.texture.getHeight()/2);		
		//double deltaX = 100;
		//double deltaY = 100;
		directionEpee = new Vector2((float) deltaX,(float) deltaY);
		this.bodyEpee = new Rectangle((owner.getX()+(float) deltaX),(owner.getY()+(float) deltaY), this.texture.getWidth(), this.texture.getHeight());
		this.centreEpee = owner.getPosition().cpy();
		centreEpee.add(directionEpee);
		initialDirectionEpee=directionEpee.cpy();
		this.transform = new Transform(centreEpee);
	}
	
	public void update(float dt)
	{
		super.update(dt);
		if(directionEpee.angleDeg(initialDirectionEpee) < 180)
		{	
			directionEpee.setAngleDeg(directionEpee.angleDeg()+9);			
			this.centreEpee = owner.getPosition().cpy();
			centreEpee.add(directionEpee);		
			setPosition(centreEpee);
			
			Array<GameObject> lgo = this.scene.gameObjects;
			for (int i = 0; i<lgo.size; i++) {
				if (!lgo.get(i).equals(this) && !lgo.get(i).equals(owner)) {

					if(lgo.get(i) instanceof Vivant)
					{
						if(Intersector.overlaps(((Vivant) lgo.get(i)).body, this.bodyEpee)) {
							lgo.get(i).rebond(this); //TODO LE REBOND NE MARCHE PAS
							super.attaquer(((Vivant) lgo.get(i)), this.valeurAttaque);
							System.out.println(">>>>>>>"+this.getClass());
						}
					}
				}
			}
			this.bodyEpee.setPosition(this.transform.getPosition());
		}
		else
		{
			this.isDead=true;
		}
		
	}

		
	
	

}

package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import utils.Constants;
import utils.Transform;

public class BouleDeFeu extends Attaque
{
	
	private Rectangle bodyFireBall;
	private Vector2 centerFireBall;
	private Vector2 directionFireBall;
	private int compteurSpriteFireBall;
	private boolean almostDead;
	private long timeInit ;
	private boolean endFireBallInit;
	float originX;
	float originY;
	float rotation;

	public BouleDeFeu(Vivant owner) 
	{
		super(10, owner);
		this.texture = new Texture("PNG/fireball/bdf_init.png");
		bodyFireBall = new Rectangle(this.owner.getX()-this.texture.getWidth()/2, this.owner.getY()-this.texture.getHeight()/2, this.texture.getWidth(), this.texture.getHeight());
		centerFireBall = this.owner.getPosition().cpy();
		compteurSpriteFireBall = 0;
		this.transform = new Transform(centerFireBall);
		almostDead = false;
		endFireBallInit= false;
		this.transform.setRotation(this.owner.transform.getRotation());
		originX=this.owner.getX();
		originY=this.owner.getY();
		rotation = this.owner.transform.getRotation();
		Vector3 Coords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		this.scene.getCam().unproject(Coords);
		//rotation = (float) Math.atan( (((Coords.y+1)/2*1800) - originY) / ((Coords.x+1)/2*3000) - originX);
		directionFireBall = new Vector2( ((Coords.x+1)/2*3000) - originX, ((Coords.y+1)/2*1800) - originY);
		directionFireBall.nor();
		//System.out.println("Angle2="+(Math.atan2(directionFireBall.y, directionFireBall.x))%(2*Math.PI));
		if(Math.atan2(directionFireBall.y, directionFireBall.x)%(2*Math.PI) < 0)
		{
			//System.out.println("Angle2="+(2*Math.PI+Math.atan2(directionFireBall.y, directionFireBall.x))%(2*Math.PI));
			rotation = (float) ((2*Math.PI+Math.atan2(directionFireBall.y, directionFireBall.x))%(2*Math.PI));
			//owner.transform.setRotation(rotation);
		}
		else
		{
			System.out.println("Angle2="+(Math.atan2(directionFireBall.y, directionFireBall.x))%(2*Math.PI));
			rotation = (float) (Math.atan2(directionFireBall.y, directionFireBall.x)%(2*Math.PI));
			//owner.transform.setRotation(rotation);
		}

		directionFireBall.x*=Constants.FIREBALL_VELOCITY_MULTIPLIER; // FB velocity multiplier
		directionFireBall.y*=Constants.FIREBALL_VELOCITY_MULTIPLIER; // FB velocity multiplier
//		System.out.println("DirFB = "+directionFireBall);
		//System.out.println("Coords X = "+((Coords.x+1)/2)*3000+" Y = "+(Coords.y+1)/2*1800); //XY coordinates to the game cam coordinate system
	}
	
	public void update(float dt)
	{
		super.update(dt);
		compteurSpriteFireBall++;
		if(!(compteurSpriteFireBall < 30))
		{
			if(compteurSpriteFireBall%10<5)
			{
				this.texture = new Texture("PNG/fireball/bdf_1.png");
				bodyFireBall.setWidth(this.texture.getWidth());
				bodyFireBall.setHeight(this.texture.getHeight());
			}
			else
			{
				this.texture = new Texture("PNG/fireball/bdf_2_grosse.png");
				bodyFireBall.setWidth(this.texture.getWidth());
				bodyFireBall.setHeight(this.texture.getHeight());
			}
		}
		

		centerFireBall.add(directionFireBall); // Dreiction of the fireball
		
		Array<GameObject> lgo = this.scene.gameObjects;
		for (int i = 0; i<lgo.size; i++) {
			if (!lgo.get(i).equals(this) && !lgo.get(i).equals(owner)) {

				if(lgo.get(i) instanceof Vivant)
				{
					if(Intersector.overlaps(((Vivant) lgo.get(i)).body, this.bodyFireBall)) {
						lgo.get(i).rebond(this); //TODO LE REBOND NE MARCHE PAS
						super.attaquer(((Vivant) lgo.get(i)), this.valeurAttaque);
						System.out.println(">>>>>>>"+this.getClass());
						timeInit = TimeUtils.millis();
						almostDead = true;
					}
				}
			}
		}
		this.bodyFireBall.setPosition(this.transform.getPosition());
		
		//TODO CA MARCHE PAS
		if(almostDead && (!endFireBallInit))
		{
			endFireBall();
			endFireBallInit = false;
			//almostDead = false;
		}
		
		//SI UNE BOULE DE FEU SORT DE LECRAN CA DISPOSE
		if(centerFireBall.x < 100 || centerFireBall.x > Constants.WINDOW_WIDTH-100|| centerFireBall.y < 100 || centerFireBall.y > Constants.WINDOW_HEIGH-100)
		{
			isDead = true;
		}
	}
	
	public void dispose()
	{
		super.dispose();
		this.texture.dispose();
	}
	
	public void endFireBall()
	{
		long spentTimeDead=TimeUtils.millis()-timeInit;
		endFireBallInit = true;
		this.valeurAttaque=0;
		//System.out.println("timespentdead= "+spentTimeDead);
		if(spentTimeDead < 50)
		{
			this.texture = new Texture("PNG/fireball/bdf_final_1.png");
		}
		else if(spentTimeDead < 150)
		{
			this.texture = new Texture("PNG/fireball/bdf_final_2.png");
		}
		else if(spentTimeDead < 250)
		{
			this.texture = new Texture("PNG/fireball/bdf_final_3.png");
		}
		else if(spentTimeDead < 350)
		{
			this.texture = new Texture("PNG/fireball/bdf_final_4.png");
		}
		else
		{
			this.texture = new Texture("PNG/fireball/bdf_final_4.png");
			isDead=true;
		}
	}

}

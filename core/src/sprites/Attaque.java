package sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import scenes.GameScene;
import utils.Transform;

//Creer une classe attaque qui instancie un GO épée (épée est du meme tag que héros). Epee a un “corps” rectangulaire pour déterminer si elle touche un monstre
//Quand contact épée-monstre, épée appelle perdrevie
//Initialiser l’attribut rotation et le faire concorder avec le vecteur déplacement du héros





public class Attaque extends GameObject {
	
	//private Texture dans GO
	protected float valeurAttaque ;
	protected Vivant owner ;

	public Attaque(float valeurAttaque, Vivant owner) {
		super(owner.scene, owner.tags);
		this.valeurAttaque = valeurAttaque;
		this.owner = owner ;
	}
	
	
	
	

}

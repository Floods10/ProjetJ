package sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import scenes.GameScene;
import utils.Transform;

//Creer une classe attaque qui instancie un GO �p�e (�p�e est du meme tag que h�ros). Epee a un �corps� rectangulaire pour d�terminer si elle touche un monstre
//Quand contact �p�e-monstre, �p�e appelle perdrevie
//Initialiser l�attribut rotation et le faire concorder avec le vecteur d�placement du h�ros





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

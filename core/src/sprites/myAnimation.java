package sprites;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import utils.Constants;
import utils.Resizer;

public class myAnimation {
	private static final int dWidth = 360;
	private static final int dHeight = 245;

	private GameObject owner;
	private String animFolder;
	private boolean isLoop;

	private int nombre_image;
	private int largeur_image;
	private int hauteur_image;
	private float duree_animation;


	private Animation animation;
	private TextureRegion regionCourante;
	private TextureRegion tabRegion[];
	private float temps;

	public myAnimation() {
	}

	public myAnimation(GameObject owner, String animFolder, float duree_animation, int width, int height, boolean isLoop) {
		this.owner=owner;
		this.animFolder=animFolder;
		this.duree_animation=duree_animation;
		this.isLoop=isLoop;
		int index = 0;
		
		
		File[] animFrameList = new File(Constants.ASSETS_PATH+animFolder).listFiles();
		this.nombre_image = animFrameList.length;
		tabRegion = new TextureRegion[nombre_image];
		for (File animFrame : animFrameList) {
		    if (animFrame.isFile()) {
		        tabRegion[index] = new TextureRegion(new Texture(animFolder+"/"+animFrame.getName()));
		    	//tabRegion[index] = new TextureRegion(Resizer.resize(animFolder+"/"+animFrame.getName(),width,height));
		        largeur_image = tabRegion[index].getTexture().getWidth();
		        hauteur_image = tabRegion[index].getTexture().getHeight();
		        index++;
		    }
		}
		animation = new Animation(duree_animation/nombre_image, tabRegion);
		temps=0.0f;
	}
	public myAnimation(GameObject owner, String animFolder, float duree_animation, boolean isLoop) {
		this(owner, animFolder, duree_animation, dWidth, dHeight, isLoop);
	}

	public void renderInit(SpriteBatch sb) {
		temps += Gdx.graphics.getDeltaTime();                   
		regionCourante = (TextureRegion) animation.getKeyFrame(temps, true);  
		regionCourante.setRegion(regionCourante, 0, 0, largeur_image, hauteur_image);

	}
	public void draw(SpriteBatch sb) {
		sb.draw(regionCourante, this.owner.getX()-largeur_image/2, this.owner.getY()-hauteur_image/2);
	}

	public float getDuree_animation() {
		return duree_animation;
	}

	public boolean isLoop() {
		return isLoop;
	}
	
}





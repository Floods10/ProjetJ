package sprites;

import java.util.HashMap;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class AnimationManager {

	private GameObject owner;
	private HashMap<String, myAnimation> anims;
	
	private String defaultAnim="idle";
	private String currentKey=defaultAnim;
	private long animStartTime;

	public AnimationManager(GameObject owner) {
		this.owner=owner;
		anims = new HashMap<String, myAnimation>();
	}
	

	public void addAnim(String key, String animFolder, float duree_animation, boolean isLoop) {
		anims.put(key, new myAnimation(this.owner, animFolder, duree_animation, isLoop));
	}

	public void renderInit(SpriteBatch sb) {
		if (isEndAnim()) {
			setAnim(this.defaultAnim);
		}
		if (keys().contains(this.currentKey)) {
			anims.get(this.currentKey).renderInit(sb);
		}
	}
	public void draw(SpriteBatch sb) {
		if (isEndAnim()) {
			setAnim(this.defaultAnim);
		}
		if (keys().contains(this.currentKey)) {
			anims.get(this.currentKey).draw(sb);
		}
	}
	public Set<String> keys() {
		return this.anims.keySet();
	}
	public void setAnim(String key) {
		if (this.anims.keySet().contains(key)) {
		animStartTime = TimeUtils.millis();
		this.currentKey=key;
		}
		else {
			System.out.println("Le manageur d'animation "+this.getClass().getSimpleName()+" ne possede pas de clé: "+key);
		}
	}
	
	public boolean isEndAnim() {
		if (this.anims.get(this.currentKey).isLoop()) {
			return false;
		}
		float duree = this.anims.get(this.currentKey).getDuree_animation();
		if ((TimeUtils.millis()-this.animStartTime)/1000>=duree) {
			return true;
		}
		return false;
		
	}

	public String getDefaultAnim() {
		return defaultAnim;
	}


	public void setDefaultAnim(String defaultAnim) {
		if (keys().contains(defaultAnim)) {
			this.defaultAnim = defaultAnim;
		}
	}
}

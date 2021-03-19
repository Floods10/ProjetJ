package sprites.animLib;

import java.io.File;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import sprites.AnimationManager;
import sprites.GameObject;
import utils.Constants;

public class M3AM extends AnimationManager {

	public M3AM(GameObject owner) {
		/*
		super(owner);

		File[] animFrameList = new File(Constants.ASSETS_PATH+"Animations/M3/").listFiles();


		for (File animFrame : animFrameList) {
			this.addAnim(animFrame.getName().toLowerCase(), "Animations/M3/"+animFrame.getName(), 0.8f, false);
			System.out.println(animFrame.getName());
		}
		this.addAnim("idle", "Animations/M3/front_Idle", 0.8f, true);
		this.setDefaultAnim("front_Idle");
		this.setAnim("front_Idle");
		*/
		super(owner);
		String pathFolder = "Animations/M3/";

		this.addAnim("front_Attack_1", pathFolder+"front_Attack_1", 0.6f, false);
		this.addAnim("front_Died", pathFolder+"front_Died", 1.0f, false);
		this.addAnim("front_Hurt", pathFolder+"front_Hurt", 0.8f, false);
		this.addAnim("front_Idle", pathFolder+"front_Idle", 0.8f, true);
		this.addAnim("front_Walk", pathFolder+"front_Walk", 0.8f, true);
		
		this.setDefaultAnim("front_Idle");
		this.setAnim("front_Idle");
	}

}

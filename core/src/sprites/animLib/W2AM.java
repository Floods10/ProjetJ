package sprites.animLib;

import java.io.File;

import sprites.AnimationManager;
import sprites.GameObject;
import utils.Constants;

public class W2AM extends AnimationManager {

	public W2AM(GameObject owner) {
		/*
		super(owner);

		File[] animFrameList = new File(Constants.ASSETS_PATH+"Animations/Wraith_02/").listFiles();


		for (File animFrame : animFrameList) {
			this.addAnim(animFrame.getName().toLowerCase(), "Animations/Wraith_02/"+animFrame.getName(), 0.8f, false);
			System.out.println(animFrame.getName());
		}
		this.addAnim("idle", "Animations/Wraith_02/Idle", 0.8f, true);
		this.setDefaultAnim("idle");
		*/
		super(owner);
		String pathFolder = "Animations/Wraith_02/";

		this.addAnim("front_Attack_1", pathFolder+"front_Attack_1", 0.6f, false);
		this.addAnim("front_Attack_2", pathFolder+"front_Attack_1", 0.6f, false);
		this.addAnim("front_Died", pathFolder+"front_Died", 1.0f, false);
		this.addAnim("front_Hurt", pathFolder+"front_Hurt", 0.8f, false);
		this.addAnim("front_Idle", pathFolder+"front_Idle", 0.8f, true);
		this.addAnim("front_Walk", pathFolder+"front_Walk", 0.8f, true);
		
		this.setDefaultAnim("front_Idle");
		this.setAnim("front_Idle");
	}

}

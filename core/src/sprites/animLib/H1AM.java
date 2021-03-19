package sprites.animLib;

import java.io.File;

import sprites.AnimationManager;
import sprites.GameObject;
import utils.Constants;

public class H1AM extends AnimationManager {

	public H1AM(GameObject owner) {
		super(owner);
		String pathFolder = "Animations/H1/";

		this.addAnim("back_Attack_1", pathFolder+"back_Attack_1", 0.6f, false);
		this.addAnim("back_Attack_2", pathFolder+"back_Attack_2", 0.6f, false);
		this.addAnim("back_Died", pathFolder+"back_Died",1.0f, false);
		this.addAnim("back_Hurt", pathFolder+"back_Hurt", 0.8f, false);
		this.addAnim("back_Idle", pathFolder+"back_Idle", 0.8f, true);
		this.addAnim("back_Run", pathFolder+"back_Run", 0.8f, true);
		this.addAnim("back_Walk", pathFolder+"back_Walk", 0.8f, true);
		
		this.addAnim("front_Attack_1", pathFolder+"front_Attack_1", 0.6f, false);
		this.addAnim("front_Attack_2", pathFolder+"front_Attack_2", 0.6f, false);
		this.addAnim("front_Died", pathFolder+"front_Died", 1.0f, false);
		this.addAnim("front_Hurt", pathFolder+"front_Hurt", 0.8f, false);
		this.addAnim("front_Idle", pathFolder+"front_Idle", 0.8f, true);
		this.addAnim("front_Run", pathFolder+"front_Run", 0.8f, true);
		this.addAnim("front_Walk", pathFolder+"front_Walk", 0.8f, true);
		
		this.addAnim("left_Attack_1", pathFolder+"left_Attack_1", 0.6f, false);
		this.addAnim("left_Attack_2", pathFolder+"left_Attack_2", 0.6f, false);
		this.addAnim("left_Died", pathFolder+"left_Died", 1.0f, false);
		this.addAnim("left_Hurt", pathFolder+"left_Hurt", 0.8f, false);
		this.addAnim("left_Idle", pathFolder+"left_Idle", 0.8f, true);
		this.addAnim("left_Run", pathFolder+"left_Run", 0.8f, true);
		this.addAnim("left_Walk", pathFolder+"left_Walk", 0.8f, true);
		
		this.addAnim("right_Attack_1", pathFolder+"right_Attack_1", 0.6f, false);
		this.addAnim("right_Attack_2", pathFolder+"right_Attack_2", 0.6f, false);
		this.addAnim("right_Died", pathFolder+"right_Died", 1.0f, false);
		this.addAnim("right_Hurt", pathFolder+"right_Hurt", 0.8f, false);
		this.addAnim("right_Idle", pathFolder+"right_Idle", 0.8f, true);
		this.addAnim("right_Run", pathFolder+"right_Run", 0.8f, true);
		this.addAnim("right_Walk", pathFolder+"right_Walk", 0.8f, true);
		
		this.setDefaultAnim("front_Idle");
		this.setAnim("front_Idle");
		
/*
 		File[] animFrameList = new File(Constants.ASSETS_PATH+pathFolder).listFiles();
		for (File animFrame : animFrameList) {
			this.addAnim(animFrame.getName().toLowerCase(), "Animations/H1/"+animFrame.getName(), 0.8f, false);
			System.out.println(animFrame.getName());
		}
		this.addAnim("idle", "Animations/H1/front_Idle", 0.8f, true);

		this.setDefaultAnim("front_idle");*/
	}

}

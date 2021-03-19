package scenes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import gui.GuiElement;
import managers.GameSceneManager;
import sprites.myAnimation;
import sprites.animLib.M2AM;
import sprites.animLib.M3AM;
import sprites.animLib.W2AM;
import sprites.AnimationManager;
import sprites.Bonus;
import sprites.BouleDeFeu;
import sprites.GameObject;
import sprites.Heros;
import sprites.Monstre;
import utils.Constants;
import utils.Resizer;

public class PlayScene extends GameScene {

	private Texture background;
	private Texture guiBoard;
	private GuiElement recapGoText;
	private GuiElement recapGoTitleText;
	private GuiElement heroTitleText;
	private GuiElement heroLifeText;
	private GuiElement heroScoreText;
	
	private Array<Monstre> monstres;
	private Array<Monstre> monstres2;
	private Array<Bonus> bonuss;
	private long lastBonusTime;

	private HashMap<String, String> tagsE1;
	private HashMap<String, String> tagsE2;
	private HashMap<String, String> tags;
	
	private Heros heros;
	private int j;
	
	// PERMET DE MESURER UNE DISTANCE DANS LA SCENE EN PIXEL A L'AIDE DE 2 CLIQUES //
	private Vector3 touchPos1 = new Vector3();
	private Vector3 touchPos2 = new Vector3();
	private int o;
	/////////////////////////////////////////////////////////////////////////////////

	public PlayScene(GameSceneManager gsm) {
		super(gsm);
		this.setSceneMusic("sceneMusic.mp3");
		if (Constants.PLAY_MUSIC) {
			this.sceneMusic.play();}
		this.background = Resizer.resize("PNG/bck.png");
		this.guiBoard = new Texture("PNG/gui.png");
		this.monstres = new Array<Monstre>();
		this.monstres2 = new Array<Monstre>();
		
		String fontPath = "fonts/arial_narrow_7.ttf";		
		this.recapGoTitleText = new GuiElement(90, 350, fontPath, "", 45); //text gui
		this.recapGoText = new GuiElement(110, 300, fontPath, "", 40); //text gui
		
		this.heroTitleText = new GuiElement(2500, 330, fontPath, "Hero", 80); //text gui
		this.heroLifeText = new GuiElement(2600, 230, fontPath, "", 60); //text gui
		this.heroScoreText = new GuiElement(2600, 150, fontPath, "", 60); //text gui
		
		tagsE1 = new HashMap<String, String>();
		tagsE1.put("equipe", "Equipe1");
		tagsE2 = new HashMap<String, String>();
		tagsE2.put("equipe", "Equipe2");
		tags = new HashMap<String, String>();
		tags.put("equipe", "bonus");;


		Monstre m = new Monstre(this, 37, 293, tagsE1);//
		m.setAm(new M2AM(m));
		this.monstres.add(m);
		
		m = new Monstre(this, 127, 357, tagsE1);//
		m.setAm(new M2AM(m));
		this.monstres.add(m);
		
		m = new Monstre(this, 216, 422, tagsE1);//
		m.setAm(new M2AM(m));
		this.monstres.add(m);
		
		m = new Monstre(this, 326, 66, tagsE2);
		m.setAm(new M3AM(m));
		this.monstres2.add(m);
		
		m = new Monstre(this, 487, 93, tagsE2);
		m.setAm(new M3AM(m));
		this.monstres2.add(m);
		
		m = new Monstre(this, 584, 211, tagsE2);
		m.setAm(new M3AM(m));
		this.monstres2.add(m);
		
		
		
		this.bonuss = new Array<Bonus>();
		this.heros = new Heros(this, tagsE1);
		//this.heros.setAm(new W2AM(this.heros));
		
		
		
		
		this.j = 0;
		

	}

	@Override
	protected void handleInput() {
		
		// PERMET DE MESURER UNE DISTANCE DANS LA SCENE EN PIXEL A L'AIDE DE 2 CLIQUES //
		o++;
		if(Gdx.input.justTouched() && touchPos1.equals(new Vector3())) {
			touchPos1.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			System.out.println("Clique 1 :"+touchPos1);
			o=0;
		}
		if(Gdx.input.justTouched() && touchPos2.equals(new Vector3()) && o>20) {
			touchPos2.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			System.out.println("Clique 2 :"+touchPos2);
			System.out.print("dx: "+Math.abs(touchPos2.x-touchPos1.x));
			System.out.print(" dy: "+Math.abs(touchPos2.y-touchPos1.y));
			System.out.println(" dst: "+touchPos2.dst(touchPos1)+"\n\n");
			touchPos1.setZero();
			touchPos2.setZero();
			o=0;
		}
        /////////////////////////////////////////////////////////////////////////////////
		/*
		if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
//			Object[] keys = this.monstres.get(0).getAm().keys().toArray();
			Object[] keys = this.heros.getAm().keys().toArray();
			System.out.println((String) keys[j]);
			for(Monstre m : this.monstres) {
				m.getAm().setAnim((String) keys[j]);}
			for(Monstre m : this.monstres2) {
				m.getAm().setAnim((String) keys[j]);}
			this.heros.getAm().setAnim((String) keys[j]);
			System.out.println((String) keys[j]);
			j++;
			j=j%keys.length;
		}*/
		if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
			for(GameObject go : this.gameObjects) {
				System.out.println(go.getClass().getSimpleName()+"("+go.getTags().get("equipe")+")"+":"+go.getPosition());
			}
			System.out.println("GameObject présent dans la scène: "+this.gameObjects.size+"\n");
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.N)) {
			for(GameObject go : this.gameObjects) {
				if(!(go instanceof Heros)) {
					go.setDead(true);}
			}
			this.monstres.get(0).getAm().setAnim("front_Idle");
			this.heros.getAm().setAnim("front_Idle");
		}
		
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
//			this.monstres.add(new Monstre(this, MathUtils.random(Constants.MIN_X, Constants.MAX_X), Constants.WINDOW_HEIGH/2, tagsE1));
			this.monstres.add(new Monstre(this, 1500, 900, tagsE2));
			this.monstres.get(this.monstres.size-1).setAm(new M2AM(this.monstres.get(this.monstres.size-1)));
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			//this.monstres2.add(new Monstre(this, MathUtils.random(Constants.MIN_X, Constants.MAX_X), Constants.WINDOW_HEIGH/2, tagsE2));
			this.monstres2.add(new Monstre(this, 1500, 900, tagsE2));
			//this.monstres2.get(this.monstres2.size-1).setTexture(new Texture("PNG/greenery_10.png"));
			this.monstres2.get(this.monstres2.size-1).setAm(new M3AM(this.monstres2.get(this.monstres2.size-1)));
		}

		if(Gdx.input.isKeyPressed(Input.Keys.A)) {this.gsm.set(new Home(this.gsm));}
	}

	@Override
	public void update(float dt) {
		this.handleInput();
		for(GameObject go : this.gameObjects) {
			go.update(dt);
		}

		if(false || ((TimeUtils.millis() - lastBonusTime)/1000 > Constants.TIME_BONUS && bonuss.size<Constants.MAX_BONUS)) {
			this.lastBonusTime = TimeUtils.millis();
			bonuss.add(new Bonus(this, MathUtils.random(Constants.MIN_Y, Constants.MAX_X), MathUtils.random(Constants.MIN_Y, Constants.MAX_Y),tags));
			if(Constants.PLAY_SOUND) {
				bonuss.get(bonuss.size-1).playApparitionSound();
			}
		}
		this.recapGoTitleText.setText("GameObject(s) présent(s) dans la scène: "+this.gameObjects.size);
		String recap="";
		for(GameObject go : this.gameObjects) {
			recap += go.getClass().getSimpleName()+"("+go.getTags().get("equipe")+")"+": ("+Math.round(go.getX())+", "+Math.round(go.getY())+")\n";
		}
		this.recapGoText.setText(recap);
		
		this.heroLifeText.setText("Life: "+Math.round(this.heros.getVie())+"/250");
		this.heroScoreText.setText("Score: "+this.heros.getScore());
		
	}
	
    
	@Override
	public void render(SpriteBatch sb) {
		   
	   
		//this.monstres.get(0).getAm().renderInit(sb);
	    
		sb.begin();
				
		sb.draw(this.background, 0,0);
		sb.draw(this.guiBoard, 0,0);
		this.recapGoText.draw(sb);
		this.recapGoTitleText.draw(sb);
		this.heroTitleText.draw(sb);
		this.heroLifeText.draw(sb);
		this.heroScoreText.draw(sb);
		
		for(GameObject go : this.gameObjects) {
			if(!(go instanceof BouleDeFeu))
			{
				go.draw(sb);
			}
			else
			{
				go.drawWithRotation(sb,(BouleDeFeu) go);
			}
		}
	
	
		//this.monstres.get(0).getAm().draw(sb);
		
		sb.end();
	}

	@Override
	public void dispose() {
		this.background.dispose();
		this.guiBoard.dispose();
		this.sceneMusic.dispose();
		this.recapGoText.dispose();
		this.recapGoTitleText.dispose();
		this.heroTitleText.dispose();
		this.heroLifeText.dispose();
		this.heroScoreText.dispose();

		for(GameObject go : this.gameObjects) {
			go.dispose();
		}
	}

	public Array<Bonus> getBonuss() {
		return bonuss;
	}



}

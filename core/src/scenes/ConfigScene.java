package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gui.Bouton;
import gui.GuiElement;
import managers.GameSceneManager;
import utils.Constants;
import utils.Resizer;

public class ConfigScene extends GameScene {

	private GuiElement pageTitleText;
	private GuiElement pageTitleText2;
	private GuiElement vitesseText;
	private GuiElement attaqueText;
	private GuiElement vieText;
	
	private Bouton boutonstart;
	private Bouton boutonquit;
	private Bouton boutonback;
	private Bouton boutonplus;
	private Bouton boutonplus2;
	private Bouton boutonplus3;
	private Bouton boutonmoins;
	private Bouton boutonmoins2;
	private Bouton boutonmoins3;
	
	private BitmapFont fontTexte;
	private SpriteBatch batch;

	static Texture buttonUnActive = new Texture("PNG/button_unclick.png");
	static Texture buttonActive = new Texture("PNG/button_click.png");
	static Texture buttonDisable = new Texture("PNG/button_disable.png");
	static Texture backClick = new Texture("PNG/back_unclick.png");
	static Texture backUnclick = new Texture("PNG/back_click.png");
	Sound btSound=Gdx.audio.newSound(Gdx.files.internal("boutonSound.wav"));
	
	static Texture sqbuttonUnActive = new Texture("PNG/sqbutton_unclick.png");
	static Texture sqbuttonActive = new Texture("PNG/sqbutton_click.png");
	static Texture sqbuttonDisable = new Texture("PNG/sqbutton_disable.png");
	
	public ConfigScene(GameSceneManager gsm) {
		super(gsm);
		this.cam.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGH);
		this.setBackground(Resizer.resize("PNG/Settings.png"));
		this.setSceneMusic("homeMusic.mp3");
		if (Constants.PLAY_MUSIC) {
			this.sceneMusic.play();}

		batch = new SpriteBatch();
		fontTexte= new BitmapFont();
		String fontPath = "fonts/AgentOrange.ttf";
		String fontPath2 = "fonts/Andreas.ttf";

		this.pageTitleText = new GuiElement(null, 1500, fontPath, "Caracteristiques", 120);
		this.pageTitleText2 = new GuiElement(null, 1300, fontPath, "du hero", 120);
		this.vitesseText = new GuiElement(800, 1000, fontPath2, "Vitesse", 80);
		this.attaqueText = new GuiElement(800, 800, fontPath2, "Attaque", 80);
		this.vieText = new GuiElement(800, 600, fontPath2, "Vie", 80);

		boutonstart= new Bouton(this,buttonUnActive,900,220,"START",fontPath2, 100);
		boutonstart.setTexDown(buttonActive);
		boutonstart.setTexOver(buttonActive);
		boutonstart.setTexDisable(buttonDisable);
		boutonstart.setSound(btSound);

		boutonplus= new Bouton(this,sqbuttonUnActive,1500,1000,"+",fontPath2, 150);
		boutonplus.setTexDown(sqbuttonActive);
		boutonplus.setTexOver(sqbuttonActive);
		boutonplus.setTexDisable(sqbuttonDisable);
		boutonplus.setSound(btSound);

		
		boutonback= new Bouton(this,backClick,2300,120);
		boutonback.setTexDown(backUnclick);
		boutonback.setTexOver(backUnclick);
		//boutonback.setTexDisable(buttonDisable);
		boutonback.setSound(btSound);
		
		
		boutonquit= new Bouton(this,buttonUnActive,1500,220,"QUIT",fontPath2, 100);
		boutonquit.setTexDown(buttonActive);
		boutonquit.setTexOver(buttonActive);
		boutonquit.setTexDisable(buttonDisable);
		boutonstart.setSound(btSound);
		
	}


	@Override
	protected void handleInput() {

		boutonstart.isOver();
		boutonquit.isOver();
		boutonback.isOver();
		boutonplus.isOver();

		if(boutonstart.isclicked()) {
			System.out.println("Start Active");
			this.gsm.set(new PlayScene(this.gsm));
		}

		if(boutonback.isclicked()) {
			this.gsm.set(new Home(this.gsm));
		}
		
		if(boutonquit.isclicked()) {
			System.out.println("Exit Active");
			Gdx.app.exit();
		}

	}

	@Override
	public void update(float dt) {
		this.handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {

		batch.begin();
		sb.begin();
		sb.setProjectionMatrix(this.cam.combined);
		sb.draw(this.background,0,0);
		this.pageTitleText.draw(sb);
		this.pageTitleText2.draw(sb);
		this.vitesseText.draw(sb);
		this.attaqueText.draw(sb);
		this.vieText.draw(sb);
		this.boutonstart.draw(sb);
		this.boutonquit.draw(sb);
		this.boutonback.draw(sb);
		//this.boutonplus.draw(sb);
		fontTexte.draw(batch, "test", 100, 100); 
		sb.end();
		batch.end();

	}

	@Override
	public void dispose() {
		this.background.dispose();
		this.pageTitleText.dispose();
		this.pageTitleText2.dispose();
		this.vitesseText.dispose();
		this.attaqueText.dispose();
		this.vieText.dispose();
		//this.boutonplus.dispose();
		this.boutonstart.dispose();
		this.boutonquit.dispose();
		this.boutonback.dispose();
		this.sceneMusic.dispose();
	}
}

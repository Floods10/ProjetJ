package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import gui.Bouton;
import gui.GuiElement;
import managers.GameSceneManager;
import utils.Constants;
import utils.Resizer;

public class TestScene extends GameScene {

	private GuiElement gameTitleText;
	private Bouton boutonstart;
	private Bouton boutonquit;

	static Texture exitButtonActive = new Texture("PNG/exit_unclick.png");
	static Texture startButtonActive = new Texture("PNG/start_unclick.png");
	static Texture buttonUnActive = new Texture("PNG/button_unclick.png");
	static Texture buttonActive = new Texture("PNG/button_click.png");
	static Texture buttonDisable = new Texture("PNG/button_disable.png");
	//	private BitmapFont fontTexte;
	private SpriteBatch batch;

	//Texture exitButtonInactive;

	public TestScene(GameSceneManager gsm) {
		
		super(gsm);
		this.cam.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGH);
		this.setBackground(Resizer.resize("PNG/home.png"));
		this.setSceneMusic("homeMusic.mp3");
		if (Constants.PLAY_MUSIC) {
			this.sceneMusic.play();}
		batch = new SpriteBatch();
		//		fontTexte= new BitmapFont();
		String fontPath = "fonts/AgentOrange.ttf";
		//		this.gameTitleText = new GuiElement(null, EXIT_BUTTON_Y+750, fontPath, Constants.GAME_TITLE, 180);
		//		this.gameTitleText.setTexture(exitButtonActive);
		this.boutonstart= new Bouton(this,exitButtonActive,null,1000);
		this.boutonquit= new Bouton(this,buttonUnActive,null,700,"Quit",null, 70);
		boutonquit.setTexDown(buttonActive);
		boutonquit.setTexOver(buttonActive);
		boutonquit.setTexDisable(buttonDisable);

		Sound sound=Gdx.audio.newSound(Gdx.files.internal("boutonSound.wav"));
		boutonquit.setSound(sound);

	}


	@Override
	protected void handleInput() {

		boutonquit.isOver();
		
		if(boutonstart.isclicked()) {
			System.out.println("disable Active");
			if(!boutonquit.isDisabled()) {boutonquit.setDisabled(true);}
			else {boutonquit.setDisabled(false);}
		}

		if(boutonquit.isclicked()) {
			System.out.println("Exit Active");
			//Gdx.app.exit();
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
		//		this.gameTitleText.draw(sb);
		this.boutonstart.draw(sb);
		this.boutonquit.draw(sb);
		//fontTexte.draw(batch, "test", 100, 100); 
		sb.end();
		batch.end();

	}

	@Override
	public void dispose() {
		this.background.dispose();
		//		this.gameTitleText.dispose();
		this.sceneMusic.dispose();
		this.boutonstart.dispose();
		this.boutonquit.dispose();
	}
}

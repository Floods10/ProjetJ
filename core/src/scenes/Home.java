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

public class Home extends GameScene {

	private GuiElement gameTitleText;
	private Bouton boutonstart;
	private Bouton boutonquit;
	private BitmapFont fontTexte;
	private SpriteBatch batch;

	private static final int EXIT_BUTTON_Y = 400;
	static Texture buttonUnActive = new Texture("PNG/button_unclick.png");
	static Texture buttonActive = new Texture("PNG/button_click.png");
	static Texture buttonDisable = new Texture("PNG/button_disable.png");
	Sound btSound=Gdx.audio.newSound(Gdx.files.internal("boutonSound.wav"));

	public Home(GameSceneManager gsm) {
		super(gsm);
		
		this.cam.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGH);
		this.setBackground(Resizer.resize("PNG/home2.png"));
		this.setSceneMusic("homeMusic.mp3");
		if (Constants.PLAY_MUSIC) {
			this.sceneMusic.play();}
		
		batch = new SpriteBatch();
		fontTexte= new BitmapFont();
		String fontPath = "fonts/AgentOrange.ttf";
		String fontPath2 = "fonts/Andreas.ttf";
		
		this.gameTitleText = new GuiElement(null, 1600, fontPath, Constants.GAME_TITLE, 180);
		
		boutonstart= new Bouton(this,buttonUnActive,null,EXIT_BUTTON_Y+300,"START",fontPath2, 100);
		boutonstart.setTexDown(buttonActive);
		boutonstart.setTexOver(buttonActive);
		boutonstart.setTexDisable(buttonDisable);
		boutonstart.setSound(btSound);
		
		boutonquit= new Bouton(this,buttonUnActive,null,EXIT_BUTTON_Y,"QUIT",fontPath2, 100);
		boutonquit.setTexDown(buttonActive);
		boutonquit.setTexOver(buttonActive);
		boutonquit.setTexDisable(buttonDisable);
		boutonstart.setSound(btSound);

		
	}


	@Override
	protected void handleInput() {

		boutonstart.isOver();
		boutonquit.isOver();
		

		if(boutonstart.isclicked()) {
			System.out.println("Start Active");
			this.gsm.set(new ConfigScene(this.gsm));
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
		this.gameTitleText.draw(sb);
		this.boutonstart.draw(sb);
		this.boutonquit.draw(sb);
		fontTexte.draw(batch, "test", 100, 100); 
		sb.end();
		batch.end();

	}

	@Override
	public void dispose() {
		this.background.dispose();
		this.gameTitleText.dispose();
		this.boutonstart.dispose();
		this.boutonquit.dispose();
		this.sceneMusic.dispose();
	}
}

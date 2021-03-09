package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import gui.GuiElement;
import managers.GameSceneManager;
import utils.Constants;
import utils.Resizer;

public class Home extends GameScene {

	private GuiElement gameTitleText;
	private GuiElement startText;
	private GuiElement quitText;
	
	public Home(GameSceneManager gsm) {
		super(gsm);
		this.cam.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGH);
		this.setBackground(Resizer.resize("PNG/home.png"));
<<<<<<< Updated upstream
		this.setSceneMusic("homeMusic.mp3");
=======
        this.setSceneMusic("homeMusic.mp3");
>>>>>>> Stashed changes
		if (Constants.PLAY_MUSIC) {
			this.sceneMusic.play();}
		String fontPath = "fonts/AgentOrange.ttf";
		this.startText = new GuiElement(null, 250, fontPath, "Commencer", 32);  
		this.quitText = new GuiElement(null, 150, fontPath, "Quitter", 32);
		this.gameTitleText = new GuiElement(null, 450, fontPath, Constants.GAME_TITLE, 80);	
	}

	@Override
	protected void handleInput() {
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
		    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		    System.out.println(touchPos);
		    this.cam.unproject(touchPos);
			System.out.println(touchPos);
			this.gsm.set(new PlayScene(this.gsm));
		}	
	}

	@Override
	public void update(float dt) {
		this.handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.setProjectionMatrix(this.cam.combined);
		sb.draw(this.background,0,0);
		this.gameTitleText.draw(sb);
		this.startText.draw(sb);
		this.quitText.draw(sb);
        sb.end();
	}

	@Override
	public void dispose() {
		this.background.dispose();
		this.gameTitleText.dispose();
		this.startText.dispose();
		this.quitText.dispose();
		this.sceneMusic.dispose();
	}
}

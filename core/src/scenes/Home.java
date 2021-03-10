package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

	static Texture exitButtonActive = new Texture("PNG/exit_unclick.png");
	static Texture startButtonActive = new Texture("PNG/start_unclick.png");
	private static final int EXIT_BUTTON_Y = 150;
	private static final int EXIT_BUTTON_X = (Constants.WINDOW_WIDTH/2)-(exitButtonActive.getWidth()/2);

	//Texture exitButtonInactive;

	public Home(GameSceneManager gsm) {
		super(gsm);
		this.cam.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGH);
		this.setBackground(Resizer.resize("PNG/home.png"));
		this.setSceneMusic("homeMusic.mp3");
		if (Constants.PLAY_MUSIC) {
			this.sceneMusic.play();}

		String fontPath = "fonts/AgentOrange.ttf";
		this.startText = new GuiElement(EXIT_BUTTON_X-10, EXIT_BUTTON_Y+170, fontPath, "", 32);  
		this.startText.setTexture(startButtonActive);
		this.quitText = new GuiElement(EXIT_BUTTON_X,EXIT_BUTTON_Y, fontPath, "", 32);
		this.quitText.setTexture(exitButtonActive);
		this.gameTitleText = new GuiElement(null, 640, fontPath, Constants.GAME_TITLE, 100);

	}


	@Override
	protected void handleInput() {


		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			System.out.println(touchPos);
			this.cam.unproject(touchPos);
			System.out.println(touchPos);

			//Start button
			int x = Constants.WINDOW_WIDTH/2 - exitButtonActive.getWidth()/2;
			if (touchPos.x < x+ (exitButtonActive.getWidth())  && touchPos.x > x &&  touchPos.y > EXIT_BUTTON_Y+170  && touchPos.y < EXIT_BUTTON_Y+170+ (exitButtonActive.getHeight())) {
				this.gsm.set(new PlayScene(this.gsm));
			}
			//Exit button
			if (touchPos.x < x+ (exitButtonActive.getWidth())  && touchPos.x > x &&  touchPos.y > EXIT_BUTTON_Y  && touchPos.y < EXIT_BUTTON_Y+ (exitButtonActive.getHeight())) {
				Gdx.app.exit();
			}


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

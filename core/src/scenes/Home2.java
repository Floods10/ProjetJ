package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import gui.GuiElement;
import managers.GameSceneManager;
import utils.Constants;
import utils.Resizer;

public class Home2 extends GameScene {

	
	
	private GuiElement gameTitleText;
	private GuiElement startText;
	private GuiElement quitText;
	
	 //Gestion bouttons
	//public Rectangle hitbox;
	//public ArrayList<Button> buttons;
	static Texture exitButtonActive = new Texture("PNG/stones_6.png");
	private static final int EXIT_BUTTON_WIDTH = exitButtonActive.getWidth();
	private static final int EXIT_BUTTON_HEIGHT = exitButtonActive.getHeight();
	private static final int EXIT_BUTTON_Y = 450;

	Texture exitButtonInactive;
	
	public Home2(GameSceneManager gsm) {
		super(gsm);
		this.cam.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGH);
		this.setBackground(Resizer.resize("PNG/home.png"));
        this.setSceneMusic("homeMusic.mp3");
		if (Constants.PLAY_MUSIC) {
			this.sceneMusic.play();}
		exitButtonActive = new Texture("PNG/stones_6.png");
		exitButtonInactive = new Texture("PNG/greenery_10.png");
		//buttons = new ArrayList<Button>();
		
		String fontPath = "fonts/AgentOrange.ttf";
		this.startText = new GuiElement(null, 400, fontPath, "Commencer", 32);  
		this.quitText = new GuiElement(null, 300, fontPath, "Quitter", 32);
		quitText.setTexture(exitButtonActive);
		this.gameTitleText = new GuiElement(null, 620, fontPath, Constants.GAME_TITLE, 100);	
	}

	
	@Override
	protected void handleInput() {
		
	
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
		    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		    System.out.println(touchPos);
		    this.cam.unproject(touchPos);
			System.out.println(touchPos);
			
			
			
				//Exit button
				int x = Constants.WINDOW_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
				if (touchPos.x < x + EXIT_BUTTON_WIDTH && touchPos.x > x && Constants.WINDOW_HEIGH - touchPos.y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Constants.WINDOW_HEIGH - touchPos.y > EXIT_BUTTON_Y) {
					//Home2.dispose();
					Gdx.app.exit();
				}
				
			//this.gsm.set(new PlayScene(this.gsm));
			
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

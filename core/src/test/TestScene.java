package test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.OnscreenKeyboardType;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import gui.Bouton;
import gui.GuiElement;
import managers.GameSceneManager;
import scenes.GameScene;
import utils.Constants;

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
	private String texteInitial;
	private String titreBoiteDialogue;
	private String message;
	private boolean afficher;
	private BitmapFont font;
	private String message2;
    private String caractereAffiche;
    private BitmapFont fontmessage2 ;
    private BitmapFont fontcaractere ;

	Stage stage ;
	private TextField.TextFieldStyle textFieldStyle;
	private BitmapFont bitmapFont;

	//Texture exitButtonInactive;

	public TestScene(GameSceneManager gsm) {

		super(gsm);
		stage =new Stage();
		this.cam.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGH);
		//this.setBackground(Resizer.resize("PNG/home.png"));
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

		bitmapFont = new  BitmapFont();
		textFieldStyle = new TextField.TextFieldStyle();
		textFieldStyle.font = bitmapFont;
		textFieldStyle.fontColor = Color.WHITE;
		TextField username = new TextField("Username",textFieldStyle);
		TextField password = new TextField("Password",textFieldStyle);
		password.setPosition(400, 400);
		username.setPosition(400, 500);
		stage.addActor(username);
		stage.addActor(password);

		TextField text1=new TextField(message, textFieldStyle);

		texteInitial = "Entrer votre nom complet";
		titreBoiteDialogue = "Bienvenu";
		afficher = false;
		font = new BitmapFont();

		Gdx.input.getTextInput(new TextInputListener() {
			@Override
			public void input(String texteSaisi) {
				//				message = "Bonjour Monsieur <"+texteSaisi+"> Bienvenu au monde des developpeurs";
				//				System.out.println(texteSaisi);
				//				afficher =true;
			}
			@Override
			public void canceled() {
				//				message = "Au revoir Monsieur et tant pis pour vous";
				//				afficher =true;
			}

		}, titreBoiteDialogue, texteInitial,"");
		
		
		fontmessage2     = new BitmapFont();   // Image contient: police du 2eme msg
        fontcaractere    = new BitmapFont();   // Image contient: police du caractère
		message2         = "Codes des cles apuyees";   // 2eme message
        caractereAffiche = "---";
		
		
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
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(afficher)
		{

			font.draw(batch, message, 10, 200);

		}

		

		
		 fontmessage2.draw(batch, message2, 10, 200);           // dessiner 1er msg
	        fontcaractere.draw(batch, caractereAffiche, 10, 150);   // dessiner caractère généré
		sb.setProjectionMatrix(this.cam.combined);
		//sb.draw(this.background,0,0);
		//		this.gameTitleText.draw(sb);
		this.boutonstart.draw(sb);
		this.boutonquit.draw(sb);
		//fontTexte.draw(batch, "test", 100, 100); 
		sb.end();
		batch.end();

	}

	@Override
	public void dispose() {
		//this.background.dispose();
		//		this.gameTitleText.dispose();
		this.sceneMusic.dispose();
		this.boutonstart.dispose();
		this.boutonquit.dispose();
	}
}

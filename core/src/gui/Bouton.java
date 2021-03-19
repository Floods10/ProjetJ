package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector3;

import scenes.GameScene;
import utils.Constants;
import utils.Transform;

public class Bouton{


	private Texture texture;
	private Texture texUp,texDown,texOver,texDisable;
	private Sound sound;
	boolean isChecked, isDisabled;
	protected GameScene scene;
	private Transform transform;
	private BitmapFont font;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	private GlyphLayout glyph;
	private String text;
	private float add=0;

	public Bouton() {}

	public Bouton(GameScene scene ,Texture up, Integer x, Integer y) {
		int realX=0, realY=0;
		this.scene= scene;
		this.texture = up;
		this.texUp = up;
		realX = (x==null)? (int)(Constants.WINDOW_WIDTH/2)-(texture.getWidth()/2) : (int)x;
		realY = (y==null)? (int)(Constants.WINDOW_HEIGH)-(texture.getHeight()/2) : (int)y;
		this.transform = new Transform(realX, realY);
	}

	public Bouton(GameScene scene ,Texture up, Integer x, Integer y, String text, String fontPath, int sizeText) {
		int realX=0, realY=0;
		this.scene= scene;
		this.texture = up;
		this.texUp = up;
		this.text=text;
		this.generator = (fontPath==null)? new FreeTypeFontGenerator(Gdx.files.internal("fonts/Wild.otf")) : new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
		this.parameter = new FreeTypeFontParameter();
		this.parameter.size=sizeText;
		this.font = this.generator.generateFont(this.parameter);
		this.glyph = new GlyphLayout();
		this.glyph.setText(this.font, this.text);
		realX = (x==null)? (int)(Constants.WINDOW_WIDTH/2)-(texture.getWidth()/2) : (int)x;
		realY = (y==null)? (int)(Constants.WINDOW_HEIGH)-(texture.getHeight()/2) : (int)y;
		this.transform = new Transform(realX, realY);
	}

	
	public boolean isDisabled () {
		return isDisabled;
	}

	public void setDisabled (boolean isDisabled) {
		if(texDisable != null && isDisabled) {this.texture=texDisable;}
		else {this.texture=texUp;}
		this.isDisabled = isDisabled;
	}
	
	public boolean isclicked() {

		boolean test=false;

		if(!isDisabled) {
			if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				Vector3 Coords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				scene.getCam().unproject(Coords);
				if (Coords.x < (this.transform.getX()+ texture.getWidth())  && Coords.x > this.transform.getX() &&  Coords.y > this.transform.getY()  && Coords.y < (this.transform.getY()+texture.getHeight())) {
					test=true;
					add=7;
					if(texDown != null && texture!=texDown) {this.texture=texDown;}
					else{this.texture=texUp;add=0;}}
				if(this.sound!=null) {sound.play();}
			}}	

		return test;
	}

	public boolean isOver() {
		boolean test=false;
		if(!isDisabled) {
		Vector3 Coords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		scene.getCam().unproject(Coords);
		if (Coords.x < (this.transform.getX()+ texture.getWidth())  && Coords.x > this.transform.getX() &&  Coords.y > this.transform.getY()  && Coords.y < (this.transform.getY()+texture.getHeight())) {
			test=true;
			add=7;
			if(texOver != null && texture!=texOver && texture!=texDisable ) {this.texture=texOver;}}
		else{this.texture=texUp;add=0;}}
		return test;
	}

	
	public void draw(SpriteBatch sb) {
		if(this.texture!=null) {
			sb.draw(this.texture, this.transform.getX(), this.transform.getY());
		}
		if(this.font!=null){draw(sb, this.transform.getX(), this.transform.getY());}
	}

	public void draw(SpriteBatch sb, float x, float y) {
		sb.draw(this.texture, x, y);
		this.font.draw(sb, this.glyph, x+texture.getWidth()/2-this.glyph.width/2+add, y+this.texture.getHeight()-this.glyph.height+add);
	}

	public void setTexte (String text) {
		this.glyph.setText(this.font, text);
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public void setTexUp(Texture up) {
		this.texture = up;
		this.texUp = up;
	}

	public void setTexDown(Texture texDown) {
		this.texDown = texDown;
	}

	public void setTexOver(Texture texOver) {
		this.texOver = texOver;
	}

	public void setTexDisable(Texture texDisable) {
		this.texDisable = texDisable;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}

	public void dispose() {
		this.generator.dispose();
		this.texture.dispose();
		this.font.dispose();
		
	}
}

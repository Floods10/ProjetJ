package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import utils.Constants;
import utils.Transform;

public class GuiElement {

	private Transform transform;
	private Texture texture;
	private BitmapFont font;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	private GlyphLayout glyph;
	

	public GuiElement() {}


	public GuiElement(Integer x, Integer y, String fontPath, String text, int sizeText) {
		int realX=0, realY=0;
		this.generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
		this.parameter = new FreeTypeFontParameter();
		this.parameter.size=sizeText;
		this.font = this.generator.generateFont(this.parameter);
		this.glyph = new GlyphLayout();
		this.glyph.setText(this.font, text);
		realX = (x==null)? (int)(Constants.WINDOW_WIDTH-this.glyph.width)/2 : (int)x;
		realY = (y==null)? (int)(Constants.WINDOW_HEIGH-this.glyph.width)/2 : (int)y;
		this.transform = new Transform(realX, realY);
	}

	public GuiElement(Float x, Float y, String fontPath, String text, int sizeText) {
		float realX=0, realY=0;
		this.generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
		this.parameter = new FreeTypeFontParameter();
		this.parameter.size=sizeText;
		this.font = this.generator.generateFont(this.parameter);
		this.glyph = new GlyphLayout();
		this.glyph.setText(this.font, text);
		realX = (x==null)? (float)(Constants.WINDOW_WIDTH-this.glyph.width)/2 : (float)x;
		realY = (y==null)? (float)(Constants.WINDOW_HEIGH-this.glyph.width)/2 : (float)y;
		this.transform = new Transform(realX, realY);
	}
	public GuiElement(String fontPath, String text, int sizeText) {
		
		this.generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
		this.parameter = new FreeTypeFontParameter();
		this.parameter.size=sizeText;
		this.font = this.generator.generateFont(this.parameter);
		this.glyph = new GlyphLayout();
		this.glyph.setText(this.font, text);
		this.transform = new Transform(0, 0);
	}

	public float getWidth() {
		return this.glyph.width;
	}
	
	public void draw(SpriteBatch sb) {
		if(this.texture!=null) {
			sb.draw(this.texture, this.transform.getX(), this.transform.getY());
		}
		draw(sb, this.transform.getX(), this.transform.getY());
	}
	
	public void draw(SpriteBatch sb, float x, float y) {
		if(this.texture!=null) {
			sb.draw(this.texture, x, y);
		}
		this.font.draw(sb, this.glyph, x, y);
	}


	public void dispose() {
		this.generator.dispose();
		this.font.dispose();
	}


	public GlyphLayout getGlyph() {
		return glyph;
	}


	public void setTexture(Texture texture) {
		this.texture = texture;
	}


	public void setTransform(float x, float y) {
		this.transform.setPosition(x, y);
	}
	
	public void setText(String text) {
		this.glyph.setText(this.font, text);
	}
	

}

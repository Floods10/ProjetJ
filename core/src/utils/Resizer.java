package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Resizer {

	public Resizer() {
		
	}
	static public Texture resize(String path, int width,int height) {
		Pixmap pixmapRaw = new Pixmap(Gdx.files.internal(path));
		double rawRatio = pixmapRaw.getWidth()/pixmapRaw.getHeight();
		int w = (int)(width*rawRatio);
		
		Pixmap pixmap = new Pixmap(w, height, pixmapRaw.getFormat());
		
		pixmap.drawPixmap(pixmapRaw,
		        0, 0, pixmapRaw.getWidth(), pixmapRaw.getHeight(),
		        0, 0, pixmap.getWidth(), pixmap.getHeight()
		);
		Texture texture = new Texture(pixmap);
		pixmapRaw.dispose();
		pixmap.dispose();
		return texture;
	}
	static public Texture resize(String path) {
		return resize(path, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGH);
	}
	static public Texture resize(String path, double scale) {
		Pixmap pixmapRaw = new Pixmap(Gdx.files.internal(path));
		int w = (int)(scale*pixmapRaw.getWidth()*scale);
		int h = (int)(scale*pixmapRaw.getHeight()*scale);
		return resize(path, w, h);
	}

}

package utils;

import com.badlogic.gdx.math.Vector2;

public class Transform {
	
	private Vector2 position;
	private float rotation;
	private float scale;
	
	public Transform() {
	}

	public Transform(Vector2 position, float rotation, float scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	public Transform(Vector2 position) {
		this(position, 0.0f, 1.0f);
	}
	public Transform(float x, float y) {
		this(new Vector2(x,y), 0.0f, 1.0f);
	}
//	public Transform(float x, float y) {
//		this(new Vector2(x,y), 0.0f, 1.0f);
//	}

	public Vector2 getPosition() {
		return position;
	}
	public float getX() {
		return position.x;
	}
	public float getY() {
		return position.y;
	}
	public void setX(float  x) {
		position.x =x;
	}
	public void setY(float y) {
		position.y=y;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public void setPosition(float x, float y) {
		this.setPosition(new Vector2(x, y));
	}
	

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	


}

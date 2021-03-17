package utils;

import com.badlogic.gdx.math.Vector2;

import sprites.GameObject;

public class NewtonPhysic {

	private GameObject go;
	private Vector2 a_n;
	private Vector2 v_n;
	private Vector2 v_n_1;
	private Vector2 x_n;


	public NewtonPhysic(GameObject go) {
		this.go = go;
		this.a_n = new Vector2(0, 0);
		this.v_n = new Vector2(0, 0);
		this.v_n_1 = new Vector2(0, 0);
		this.x_n = new Vector2(0, 0);

	}

	public void stop() {
		this.a_n = new Vector2(0, 0);
		this.v_n = new Vector2(0, 0);
		this.v_n_1 = new Vector2(0, 0);
	}
	public void updatePosition(float dt) {

		a_n.x = go.getForce().x/go.getMasse();
		a_n.y = go.getForce().y/go.getMasse();

		Vector2 frottement = new Vector2(v_n);
		frottement.nor();
		frottement.scl(-Constants.FRICTION*v_n.len()/go.getMasse());
		a_n.add(frottement);

		v_n.x = a_n.x * dt + v_n_1.x;
		v_n.y = a_n.y * dt + v_n_1.y;

		x_n.x = v_n.x * dt + go.getX();
		x_n.y = v_n.y * dt + go.getY();

		v_n_1.x = v_n.x;
		v_n_1.y = v_n.y;

		go.setPosition(x_n);



	}

	public void setPosition(Vector2 v) {
		this.a_n = new Vector2(0, 0);
		this.v_n = new Vector2(0, 0);
		this.v_n_1 = new Vector2(0, 0);
		this.x_n = v;
	}
	public void setPosition(float x, float y) {
		this.setPosition(x,y);
	}
	public void setX(float x) {
		this.a_n = new Vector2(0, 0);
		this.v_n = new Vector2(0, 0);
		this.v_n_1 = new Vector2(0, 0);
		this.x_n.x = x;
	}
	public void setY(float y) {
		this.a_n = new Vector2(0, 0);
		this.v_n = new Vector2(0, 0);
		this.v_n_1 = new Vector2(0, 0);
		this.x_n.y = y;
	}
	@Override
	public String toString() {
		return "NewtonPhysic [a_n=" + a_n + ", v_n=" + v_n + ", v_n_1=" + v_n_1 + ", x_n=" + x_n + "]";
	}

}


























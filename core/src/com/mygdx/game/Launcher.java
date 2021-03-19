package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import managers.GameSceneManager;
import scenes.Home;
import test.TestScene;

public class Launcher extends ApplicationAdapter {
	
	private GameSceneManager gsm;
	private SpriteBatch sb;
	
	@Override
	public void create () {
		this.gsm = new GameSceneManager();
		this.sb = new SpriteBatch();
		
		this.gsm.push(new Home(this.gsm));
		//this.gsm.push(new TestAnim(this.gsm));
	}

	@Override
	public void render () {
		this.gsm.update(Gdx.graphics.getDeltaTime());
		this.gsm.render(this.sb);
	}
	
	@Override
	public void dispose () {
		this.sb.dispose();
	}
}

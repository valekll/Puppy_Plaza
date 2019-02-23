package puppyplaza;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Actor {
	private String name;
	private String dia;
	protected Sprite[] sprites; //0: idle | 1: init | 2: death
	protected int mode; //dictates which sprite is being shown
	
	/**
	 * @param n name
	 * @param d description
	 * @param s sprites
	 */
	protected Actor(String n, String d) {
		name = n;
		dia = d;
		mode = 0; //init phase
	} //Actor()
	
	/**
	 * @param n name
	 * @param d description
	 * @param s sprites
	 */
	public Actor(String n, String d, Sprite ... s) {
		name = n;
		dia = d;
		sprites = s;
		mode = 0; //init phase
	} //Actor()
	
	/**
	 * Set the click action for the whole Actor.
	 * @param value the eventhandler
	 */
	public final void setOnMouseClicked(EventHandler<? super MouseEvent> value) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].setOnMouseClicked(value);
		} //for
	} //setOnMouseClicked()
	
	/**
	 * @return next Sprite for Animation sequence
	 */
	public ImageView nextSprite() {
		return sprites[mode].next();
	} //nextSprite()
	
	public Sprite[] getSprites() {
		return sprites;
	} //getSprites()

	public void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	} //setSprites()

	public String getName() {
		return name;
	} //getName()

	public void setName(String name) {
		this.name = name;
	} //setName()

	public String getDia() {
		return dia;
	} //getDia()

	public void setDia(String dia) {
		this.dia = dia;
	} //setDia()
	
	public void setModeIdle() {
		mode = 0;
	} //setModeIdle()
	
} //Actor

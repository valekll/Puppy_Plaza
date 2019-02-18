package puppyplaza;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Actor {
	private Sprite[] sprites; //0: idle | 1: init | 2: death
	private String name;
	private String dia;
	private int mode; //dictates which sprite is being shown
	private boolean isMobile;
	
	public Actor(String n, String d, Sprite ... s) {
		name = n;
		dia = d;
		sprites = s;
		for(Sprite sp : sprites) {
			if(sp instanceof MobileSprite) {
				isMobile = true;
			} //if
		} //for
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
	
	public ImageView nextSprite() {
		ImageView imgv = sprites[mode].next();
		return imgv;
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

	public boolean isMobile() {
		return isMobile;
	}
} //Actor

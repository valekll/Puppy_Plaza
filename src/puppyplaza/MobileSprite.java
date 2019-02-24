package puppyplaza;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MobileSprite extends Sprite {
	protected int dir; //direction
	protected int dirnum; //number of directions
	protected int start;
	protected int end;
	protected double speed;
	
	public MobileSprite(double x, double y, double s, int cyclesPerSec, int dn, Image ... images) {
		this(x, y, s, cyclesPerSec, dn, imageToImageViewArr(images));
	} //MobileSprite()

	public MobileSprite(double x, double y, double s, int cyclesPerSec, int dn, ImageView ... imageViews) {
		super(x, y, cyclesPerSec, imageViews);
		dir = 0;
		dirnum = dn;
		start = 0;
		speed = s;
		totalFrames = totalFrames / dn;
		end = start + totalFrames;
	} //MobileSprite()
	
	/**
	 * Cycles to next ImageView sprite
	 * @return the next ImageView
	 */
	@Override
	public ImageView next() {
		cc++;
		if(cc == cps) { //once every 10 frames
			cc = 0; //reset cps
			//boolean check = !chDir(d);
			//if(check) { //check for changes in direction
			cf++;
			if(cf >= end) {
				cf = start;
			} //if
			//} //if
			move();
		} //if
		ImageView img = imgvs[cf];
		img.setX(xax);
		img.setY(yax);
		return img;
	} //next()
	
	/**
	 * Move 10 pixels in a set direction
	 */
	private void move() {
		if(dir == 0) { //down
			yax += speed;
		} //if
		else if(dir == 1) { //left
			xax -= speed;
		} //else if
		else if(dir == 2) { //right
			xax += speed;
		} //else if
		else if(dir == 3) { //up
			yax -= speed;
		} //else if
	} //move()
	
	/**
	 * Gives directions for a clockwise circuit
	 * @param d current direction
	 * @return new direction
	 */
	public static int rdlu(int d) {
		if(d == 0) { //down
			d = 1;
		} //if
		else if(d == 1) { //left
			d = 3;
		} //else if
		else if(d == 2) { //right
			d = 0;
		} //else if
		else if(d == 3) { //up
			d = 2;
		} //else if
		return d;
	} //rdlu
	
	/**
	 * Gives directions for a counter-clockwise circuit
	 * @param d current direction
	 * @return new direction
	 */
	public static int ldru(int d) {
		if(d == 0) { //down
			d = 2;
		} //if
		else if(d == 1) { //left
			d = 0;
		} //else if
		else if(d == 2) { //right
			d = 3;
		} //else if
		else if(d == 3) { //up
			d = 1;
		} //else if
		return d;
	} //ldru
	
	/**
	 * Changes direction of movement d>l>r>u>etc
	 */
	private void chDir() {
		if(dir == 3) {
			dir = 0;
		} //if
		else {
			dir++;
		} //else
	} //chDir()
	
	/**
	 * Changes direction of movement.
	 * @return true if successful
	 */
	public boolean chDir(int d) {
		if(dir <= 3 && dir >= 0 && dir != d && dirnum == 4) {
			dir = d;
			if(dir == 0) { //down
				start = 0;
			} //if
			else if(dir == 1) { //left
				start = totalFrames;
			} //else if
			else if(dir == 2) { //right
				start = totalFrames * 2;
			} //else if
			else if(dir == 3) { //up
				start = totalFrames * 3;
			} //else if
			end = start + totalFrames;
			cf = start;
			return true;
		} //if
		else if(dir <= 3 && dir >= 0 && dir != d && dirnum == 1) {
			dir = d;
			return true;
		} //else if
		return false;
	} //chDir()
	
	public int getDir() {
		return dir;
	} //getDir()
} //MobileSprite

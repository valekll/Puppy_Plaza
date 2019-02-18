package puppyplaza;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MobileSprite extends Sprite {
	protected int dir; //direction
	protected int start;
	protected int end;
	
	public MobileSprite(double x, double y, int cyclesPerSec, Image ... images) {
		super(x, y, cyclesPerSec, images);
		dir = 0;
		start = 0;
		totalFrames = totalFrames / 4;
		end = start + totalFrames;
	} //MobileSprite()

	public MobileSprite(double x, double y, int cyclesPerSec, ImageView ... imageViews) {
		super(x, y, cyclesPerSec, imageViews);
		dir = 0;
		start = 0;
		totalFrames = totalFrames / 4;
		end = start + totalFrames;
	} //MobileSprite()
	
	/**
	 * Cycles to next ImageView sprite
	 * @return the next ImageView
	 */
	public ImageView cycle(int d) {
		cc++;
		if(cc == cps) { //once every 10 frames
			cc = 0; //reset pps
			boolean check = !chDir(d);
			if(check) { //check for changes in direction
				cf++;
				if(cf >= end) {
					cf = start;
				} //if
			} //if
			move(check);
		} //if
		ImageView img = imgvs[cf];
		img.setX(xax);
		img.setY(yax);
		return img;
	} //cycle()
	
	/**
	 * Move 10 pixels in a set direction
	 */
	private boolean move(boolean mv) {
		if(mv) {
			if(dir == 0) { //down
				yax += 10.0;
			} //if
			else if(dir == 1) { //left
				xax -= 10.0;
			} //else if
			else if(dir == 2) { //right
				xax += 10.0;
			} //else if
			else if(dir == 3) { //up
				yax -= 10.0;
			} //else if
		} //if
		return mv;
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
	 * Changes direction of movement
	 */
	public boolean chDir(int d) {
		if(dir <= 3 && dir >= 0 && dir != d) {
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
		return false;
	} //chDir()
	
	public int getDir() {
		return dir;
	} //getDir()
} //MobileSprite

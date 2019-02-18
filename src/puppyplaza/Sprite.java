package puppyplaza;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Sprite {
	protected int cc; //current cycle
	protected int cps; //cycles per second
	protected double xax; //x and y axis
	protected double yax;
	protected Image[] imgs; 
	protected ImageView[] imgvs;
	protected int cf; //current frame 
	protected int totalFrames; //total frames
	
	public Sprite(double x, double y, int cyclesPerSec, Image ... images) {
		this(x, y, cyclesPerSec, imageToImageViewArr(images));
		imgs = images;
	} //Sprite()
	
	public Sprite(double x, double y, int cyclesPerSec, ImageView ... imageViews) {
		cf = 0;
		cc = 0;
		cps = cyclesPerSec;
		xax = x;
		yax = y;
		imgvs = imageViews;
		totalFrames = imgvs.length;
	} //Sprite()
	
	/**
	 * Set the click action for the whole Sprite.
	 * @param value the eventhandler
	 */
	public final void setOnMouseClicked(EventHandler<? super MouseEvent> value) {
		for(int i = 0; i < imgvs.length; i++) {
			imgvs[i].setOnMouseClicked(value);
		} //for
	} //setOnMouseClicked()
	
	/**
	 * Change arr of Image to ImageView
	 * @param img the Image array
	 * @return ImageView Array
	 */
	public static ImageView[] imageToImageViewArr(Image ... img) {
		ImageView[] imgv = new ImageView[img.length];
		for(int i = 0; i < img.length; i++) {
			imgv[i] = new ImageView(img[i]);
			System.out.println("Adding Sprite Image: " + img[i]);
		} //for
		return imgv;
	} //imageToImageViewArr()
	
	/**
	 * Cycles to next ImageView sprite
	 * @return the next ImageView
	 */
	public ImageView next() {
		cc++;
		if(cc == cps) {
			cc = 0;
			cf++;
			if(cf >= totalFrames) {
				cf = 0;
			} //if
		} //if
		ImageView img = imgvs[cf];
		img.setX(xax);
		img.setY(yax);
		return img;
	} //next()
	
	/**
	 * @return number of frames
	 */
	public int getCycleLength() {
		return imgvs.length;
	} //getCycleLength()

	/**
	 * @return number of cycles per second
	 */
	public int getCPS() {
		return cps;
	} //getCycleLength()
	
	/**
	 * Sets the number of cycles per second
	 */
	public void setCPS(int cyclesPerSec) {
		cps = cyclesPerSec;
	} //getCycleLength()
	
	public double getXax() {
		return xax;
	} //getXax()

	public void setXax(double xax) {
		this.xax = xax;
	} //setXax()

	public double getYax() {
		return yax;
	} //getYax()

	public void setYax(double yax) {
		this.yax = yax;
	} //setYax()
} //Sprite

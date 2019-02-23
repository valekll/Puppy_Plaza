package puppyplaza;

import javafx.scene.image.ImageView;

public class MobileActor extends Actor {
	private int dir; //direction
	private int cps;
	private int orbitrate; //number of frames between orbit dir changes
	private MobileSprite[] msprites;
	private boolean orbit; //orbit on (t) or off (f)
	private boolean orbitmode; //clockwise (t) or counterclockwise (f)
	
	/**
	 * @param n name
	 * @param d description
	 * @param s sprites
	 */
	public MobileActor(String n, String d, MobileSprite ... s) {
		super(n, d);
		msprites = s;
		dir = 0;
		cps = 0;
		orbitrate = 180;
		orbit = false; //don't orbit unless told otherwise
		orbitmode = true; //start clockwise
		alignSpriteDirs();
	} //MobileActor()

	/**
	 * Toggles orbiting on and off.
	 * @return current state of orbit
	 */
	public boolean toggleOrbit() {
		if(orbit) {
			orbit = false;
		} //if
		else {
			orbit = true;
		} //else
		return orbit;
	} //toggleOrbit()
	
	/**
	 * Toggles orbit circuit direction. Clockwise (T) or Counterclockwise (F)
	 * @return current orbit circuit
	 */
	public boolean toggleOrbitMode() {
		if(orbitmode) {
			orbitmode = false;
		} //if
		else {
			orbitmode = true;
		} //else
		return orbitmode;
	} //toggleOrbitMode()
	
	/**
	 * Changes the direction according to current orbit pathway.
	 * @return true if successful
	 */
	public boolean orbit() {
		cps++;
		if(cps == orbitrate) {
			cps = 0;
			if(orbitmode) {
				return chDir(MobileSprite.rdlu(dir));
			} //if
			return chDir(MobileSprite.ldru(dir));
		} //if
		return false;
	} //orbit()
	
	/**
	 * @return next Sprite for Animation sequence
	 */
	@Override
	public ImageView nextSprite() {
		return msprites[mode].next();
	} //nextMSprite()
	
	/**
	 * @return The direction of the Mobile Actor
	 */
	public int getDir() {
		alignSpriteDirs();
		return dir;
	} //getDir()
	
	/**
	 * Changes direction of movement.
	 * @return true if successful
	 */
	public boolean chDir(int d) {
		if(d <= 3 && d >= 0 && dir != d) {
			dir = d;
			alignSpriteDirs();
			return true;
		} //if
		return false;
	} //chDir()
	
	/**
	 * Sets the orbit rate.
	 * @return true if successful
	 */
	public boolean setOrbitRate(int or) {
		if(or <=10) {
			orbitrate = or;
			return true;
		} //if
		return false;
	} //setOrbitRate()
	
	/**
	 * Makes sure all Sprites are facing the same direction.
	 */
	private void alignSpriteDirs() {
		for(int i = 0; i < msprites.length; i++) {
			msprites[i].chDir(dir);
		} //for 
	} //alignSpriteDirs()
} //MobileActor

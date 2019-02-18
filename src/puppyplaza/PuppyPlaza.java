package puppyplaza;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.shape.StrokeType;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import java.util.Random;


public class PuppyPlaza extends Application {
	Random random;
	AnimationTimer timer;
	Group group; //main group
	Stage puppyStage; //the main stage
	ArrayList<MobileActor> mactors; //arraylist of actors existing capable of moving
	ArrayList<Actor> actors; // arraylist of actors existing
	ArrayList<ImageView> images;
	ArrayList<ImageView> mimages;
	ArrayList<ImageView> background;
	int cps; //cycles per sec
	int money; //player money
	double mouseX;
	double mouseY;
	double t; //keep track of time
   

	public List<ImageView> getSprites() {
		return group.getChildren().stream()
									.map(node->(ImageView)node)
									.collect(Collectors.toList());
	} //getSprites()

	/**
	 * A cycle update. Refreshes all current information and renders a frame.
	 */
	public void update() {
		
		///Put Actors in place
		for(int i = 0; i < actors.size(); i++) {
			if(images.get(i) != null) { //remove images from last scene
				group.getChildren().remove(images.get(i));
			} //if
			images.set(i, actors.get(i).nextSprite());	
			group.getChildren().add(images.get(i));
		} //for
		///
		
		///Put Mobile Actors in place
		for(int i = 0; i < mactors.size(); i++) {
			mactors.get(i).orbit();
			if(mimages.get(i) != null) { //remove images from last scene
				group.getChildren().remove(mimages.get(i));
			} //if
			mimages.set(i, mactors.get(i).nextSprite());
			group.getChildren().add(mimages.get(i));
		} //for
		///
	} //update()

	/**
	 * Rectangle highlighting
	 */
	public void checkMouseHover() {
		for(int i = 0; i < background.size(); i++) {
			final int mrbreincarnate = i;
			background.get(i).setOnMouseMoved(e-> background.get(mrbreincarnate).setImage(new Image("grass_selected.png")));
			background.get(i).setOnMouseExited(e-> background.get(mrbreincarnate).setImage(new Image("grass.png")));
			background.get(i).setOnMouseClicked(e-> {
				mouseX = e.getX();
				mouseY = e.getY();
				spawnPupper(); 
			});
		} //4dd2ff
	} //checkMouseHover
	
	
	/**
	 * Spawns a puppy at current mouse location.
	 */
	public void spawnPupper() { 
		MobileActor pupper = makePupper(mouseX - 20.0, mouseY - 20.0, 8.0 + (random.nextDouble() * 4.0));
		if(random.nextDouble() >= .5) { //random orbit mode
			pupper.toggleOrbitMode();
		} //if
		pupper.setOrbitRate(random.nextInt(121) + 120);
		pupper.chDir(random.nextInt(4)); //random direction
		mactors.add(pupper);
		mimages.add(mactors.get(mactors.indexOf(pupper)).nextSprite());
		group.getChildren().add(mimages.get(mimages.size() - 1));
	} //spawnPupper()
  
	/**
	 * Generates a puppy Mobile Actor at a given loc.
	 * @param x x loc
	 * @param y y loc
	 */
	public static MobileActor makePupper(double x, double y, double s) {
		Image img = new Image("https://i.imgur.com/JgPQLX6.png"); //http://i.imgur.com/HnW7KqH.png original
		ImageView[] temp = new ImageView[12];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 3; j++) {
				temp[i * 3 + j] = new ImageView(img);
				temp[i * 3 + j].setViewport(new Rectangle2D(j * 48, i * 46, 47, 46));
				temp[i * 3 + j].setOnMouseClicked(e -> System.out.println("bork"));
			} //for
		} //for 
		MobileSprite psprite = new MobileSprite(x, y, s, 10, temp);
		MobileActor pupper = new MobileActor("Puppy", "An Innocent Little Pupper.", psprite);
		return pupper;
	} //makePupper()
	
	/**
	 * Generates a grass Actor at a given loc.
	 * @param x x loc
	 * @param y y loc
	 */
	public static ImageView makeGrass(double x, double y) {
		Image img = new Image("grass.png");
		ImageView temp = new ImageView(img);
		temp.setX(x);
		temp.setY(y);
		return temp;
	} //makeGrass
	
	/**
	 * Generates a grass selected Actor at a given loc.
	 * @param x x loc
	 * @param y y loc
	 */
	public static ImageView makeGrassSelected(double x, double y) {
		Image img = new Image("grass_selected.png");
		ImageView temp = new ImageView(img);
		temp.setX(x);
		temp.setY(y);
		return temp;
	} //makeGrass
	
	/**
	 * creates a button that lets you build things
	 */
	/*public void buttonStuff() {
		Button button = new Button("  Build!  ");
		button.setOnAction(e -> buildPhase());
		button.setLayoutX(740);
		button.setLayoutY(60);
		group.getChildren().add(button);
	} //buttonStuff
	 */
	
	/**
	 * run when in build phase. 
	 */
	public void buildPhase() {
		//     boolean build = true;
		//     while (build) {
		//     	for (Rectangle r: inner) {
		//           r.setOnMouseClicked(e->
		//	       if (build) {setInnerBuilding(); build=false;});
		//	}//for
		//     }//while
		System.out.println("build phase!");
	}//buildPhase

	//  public void setInnerBuilding(boolean built){
	//     if (build) {
	//        r.setFill(Color.web("ff3434");
	//     } else {money += 10;}
	//  }//setInnerBuildings

	/**
	 * sets up the nodes and adds them to the group
	 */
	public void setUp() {
	   cps = 0;
	   random = new Random();
	   
	   mimages = new ArrayList<ImageView>();
	   images = new ArrayList<ImageView>();
	   background = new ArrayList<ImageView>();
	   
	   /// History is written here.
	   //Image[] img = new Image[3]; 
	   //img[0] = new Image("https://m.media-amazon.com/images/M/MV5BMTQ5NTUzNDE5OV5BMl5BanBnXkFtZTgwMjAwOTE1MDE@._V1_.jpg", 160, 160, false, false);
	   //img[1] = new Image("https://pbs.twimg.com/media/DfzhghPX0AA4vHO.jpg", 160, 160, false, false);
	   //ImageView[] imgv = new ImageView[3];
	   //for(int i = 0; i < 3; i++) {
	   //   imgv[i] = new ImageView(img);
	   //   imgv[i].setViewport(new Rectangle2D(i * 48, 0, 48, 46));
	   //} //for 
	   ///
	   
	   MobileActor pupper = makePupper(270.0, 250.0, 10.0);
	   mactors = new ArrayList<MobileActor>();
	   mactors.add(pupper);
	   mimages.add(mactors.get(0).nextSprite());
	   //group.getChildren().add(images.get(0));

	   actors = new ArrayList<Actor>();
	   
	   for(int i = 0; i < 15; i++) { //cols
	        for(int j = 0; j < 8; j++) { //rows
	        	background.add(makeGrass(90.0 * (double)i, 90.0 * (double)j + 1.0));
	        } //for
	   } //for
	   
	   //for(int i = 0; i < images.size(); i++) {
	   //group.getChildren().add(images.get(i));
	   //} //for
	   
	   group.getChildren().addAll(background);
	   group.getChildren().addAll(images);
	   group.getChildren().addAll(mimages);
	   
       checkMouseHover();
	   //buttonStuff();
	   
   } //setUp

	/**
	 * the start method. sets up group and scene and bg
	 * @param stage
	 */
	public void start(Stage stage) {
		puppyStage = stage;
		group = new Group();
		setUp();
		timer = new AnimationTimer() {
			private long frames = 0;
			private long lu; //last update
			private long lu2; //last update
			private final double SPEED = .016666666666; //controls fps
			private double oldframe;
			private double elapsed2;
			private double[] elapsedSet = new double[60];
			private int fnum = 0;
    	  
			@Override
			public void start() {
				for(double d : elapsedSet) {
					d = -1.0;
				} //for
				lu = System.nanoTime();
				lu2 = lu;
				super.start();
			} //start()
    	  
			@Override
			public void handle(long now) {
				double elapsed = (now - lu) / 1_000_000_000.0; //elapsed seconds since last frame
				//oldframe = elapsed2;
				//elapsed2 = (now - lu2) / 1_000_000_000.0; //elapsed seconds
				if(stage.isShowing()//) { 
						&& ((elapsed > SPEED - .005) && (elapsed < SPEED + .005))) {
					/*frames++;
    			  System.out.println("frame: " + frames);
    			  if(frames % 60 == 0 && frames > 60) {
    				  elapsedSet[fnum] = elapsed2 - oldframe;
    				  if(elapsed2 >= 60.0 || frames == 3600) {
    					  double avgFPS = 0.0;
    					  double num4avg = 0.0;
    					  for(double d : elapsedSet) {
    						  if(d > 0) {
    							  System.out.println(d);
    							  avgFPS += d;
    							  num4avg++;
    						  } //if
    					  } //for
    					  avgFPS = avgFPS / num4avg;
    					  System.out.println("Time: " + elapsed2);
    					  System.out.println("1 Minute FPS Avg: " + avgFPS);
    					  System.exit(0);
    				  } //
    				  ++fnum;
    			  } //if
    			  */
					update();
				} //if
				lu = System.nanoTime();
			} //handle()
		}; //AnimationTimer
		timer.start();
		Scene scene = new Scene(group, 1280, 720, Color.BLACK);
		puppyStage.setTitle("Puppy Plaza");
		puppyStage.setScene(scene);
		puppyStage.sizeToScene();
		puppyStage.show();
	} //start

  /**
   * The main entry point for starting this application
   * @param args an array of String params given on the command line
   */
   public static void main(String[] args) {
      try {
         Application.launch(args);
      } catch (UnsupportedOperationException e) { 
         System.out.println(e);
         System.err.println("If this is a DISPLAY problem, then your X server connection");
         System.err.println("has likely timed out. This can generally be fixed by logging");
         System.err.println("out and logging back in. -MEC");
         System.exit(1);
      } // try
   } // main
} //fhbgame

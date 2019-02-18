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
	AnimationTimer timer;
	Group group; //main group
	Stage puppyStage; //the main stage
	ArrayList<Actor> actors; // arraylist of characters existing
	ArrayList<ImageView> images;
	int cps;
	int money; //player money
	double mouseX;
	double mouseY;
	double t; //keep track of time
   

	public List<ImageView> getSprites() {
		return group.getChildren().stream()
									.map(node->(ImageView)node)
									.collect(Collectors.toList());
	} //getSprites()

  public void update() {
	  cps++;
	  boolean udog = false;
	  if(cps == 180) {
		  cps = 0;
		  udog = true;
	  } //if
	
	  for (int i = 0; i < images.size(); i++) {
		  if(udog && actors.get(i).isMobile()) {
			  ((MobileSprite)sprites.get(i)).chDir(MobileSprite.rdlu(((MobileSprite)sprites.get(i)).getDir()));
		  } //if
		  if (images.get(i) != null) {
			  group.getChildren().remove(images.get(i));
		  } //if
		  if(actors.get(i).isMobile()) {
			  images.set(i, ((MobileSprite)sprites.get(i)).cycle(((MobileSprite)sprites.get(i)).getDir()));
			  images.get(i).setOnMouseClicked(e -> System.out.println("bork"));
		  } //if
		  else {
			  images.set(i, sprites.get(i).cycle());
		  } //else
		  group.getChildren().add(images.get(i));
	  } //for
  } //update()

 /**
  * rectangle highlighting
  */
  public void checkMouseHover() {
     for(int i = 0; i < recs.size(); i++) {
    	final int mrbreincarnate = i;
        recs.get(i).setOnMouseMoved(e-> recs.get(mrbreincarnate).setFill(Color.web("4DD2FF")));
        recs.get(i).setOnMouseExited(e -> recs.get(mrbreincarnate).setFill(Color.web("F7F7F7")));
        inner.get(i).setOnMouseMoved(e-> recs.get(mrbreincarnate).setFill(Color.web("4DD2FF")));
        inner.get(i).setOnMouseExited(e -> recs.get(mrbreincarnate).setFill(Color.web("F7F7F7")));
        recs.get(i).setOnMouseClicked(e-> {

        	mouseX = e.getX();
        	mouseY = e.getY();
        	spawnPupper(); 
        });
        inner.get(i).setOnMouseClicked(e-> {

        	mouseX = e.getX();
        	mouseY = e.getY();
        	spawnPupper();
        });
     } //4dd2ff
  } //checkMouseHover

  public void spawnPupper() {
	  double randX = (int)(Math.random() * 640.0);
	  double randY = (int)(Math.random() * 640.0);
	  Image img = new Image("https://i.imgur.com/JgPQLX6.png"); //http://i.imgur.com/HnW7KqH.png original
	   ImageView[] temp = new ImageView[12];
	   for(int i = 0; i < 4; i++) {
		   for(int j = 0; j < 3; j++) {
			   temp[i * 3 + j] = new ImageView(img);
			   temp[i * 3 + j].setViewport(new Rectangle2D(j * 48, i * 46, 47, 46));
		   } //for
	   } //for 
	  Sprite pupper = new Sprite(mouseX - 20, mouseY - 20, temp, temp, temp);
	  sprites.add(pupper);
	  images.add(sprites.get(sprites.indexOf(pupper)).cycle());
	  group.getChildren().add(images.get(images.size() - 1));
  } //spawnPupper()
  
 /**
  * creates a button that lets you build things
  */
  public void buttonStuff() {
     Button button = new Button("  Build!  ");
     button.setOnAction(e -> buildPhase());
     button.setLayoutX(740);
     button.setLayoutY(60);
     group.getChildren().add(button);
  }//buttonStuff

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
	   pps = 0;
	   //Image[] img = new Image[3]; 
	   //img[0] = new Image("https://m.media-amazon.com/images/M/MV5BMTQ5NTUzNDE5OV5BMl5BanBnXkFtZTgwMjAwOTE1MDE@._V1_.jpg", 160, 160, false, false);
	   //img[1] = new Image("https://pbs.twimg.com/media/DfzhghPX0AA4vHO.jpg", 160, 160, false, false);
	   Image img = new Image("https://i.imgur.com/JgPQLX6.png"); //http://i.imgur.com/HnW7KqH.png original
	   imgv = new ImageView[12];
	   for(int i = 0; i < 4; i++) {
		   for(int j = 0; j < 3; j++) {
			   imgv[i * 3 + j] = new ImageView(img);
			   imgv[i * 3 + j].setViewport(new Rectangle2D(j * 48, i * 46, 47, 46));
		   } //for
	   } //for 
	   /*ImageView[] imgv = new ImageView[3];
	   for(int i = 0; i < 3; i++) {
		   imgv[i] = new ImageView(img);
		   imgv[i].setViewport(new Rectangle2D(i * 48, 0, 48, 46));
	   } //for */
	   x = new MobileSprite(270, 250, imgv, imgv, imgv);
	   sprites = new ArrayList<Sprite>();
	   sprites.add(x);
	   images = new ArrayList<ImageView>();
	   images.add(sprites.get(0).cycle());
	   group.getChildren().add(images.get(0));
	   //SQUARES
	   recs = new ArrayList<Rectangle>();
	   inner = new ArrayList<Rectangle>();
	   for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	        	recs.add(new Rectangle(80 * i, 80 * j + 1, 79, 79));
	        	inner.add(new Rectangle(80 * i + 20, 80 * j + 21, 39, 39));
	        	recs.get((i * 8) + j).setStroke(Color.BLACK);
	        	recs.get((i * 8) + j).setFill(Color.web("F7F7F7"));
	        	inner.get((i * 8) + j).setStroke(Color.BLACK);
	        	inner.get((i * 8) + j).setFill(Color.web("FFB3B3")); //FFB3B3 red //98E698 green
				group.getChildren().add(recs.get((i * 8) + j));
	        	group.getChildren().add(inner.get((i * 8) + j));
	        }//for
	   }//for
       checkMouseHover();
	   buttonStuff();
   } //setUp

 /**
 * the start method. sets up group and scene and bg
 * @param stage
 */
   public void start(Stage stage) {
	  fhbStage = stage;
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
      Scene scene = new Scene(group, 880, 640, Color.BLACK);
      fhbStage.setTitle("Farm. Hack. Build.");
      fhbStage.setScene(scene);
      fhbStage.sizeToScene();
      fhbStage.show();
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
         System.err.println("out and logging back in.");
         System.exit(1);
      } // try
   } // main
} //fhbgame

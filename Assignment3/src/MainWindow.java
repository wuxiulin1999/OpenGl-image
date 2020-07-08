
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;
import objects3D.TexCube;
import objects3D.TexSphere;
import objects3D.champion;
import objects3D.Cylinder;
import objects3D.Grid;
import objects3D.Human;
import objects3D.Human2;
import objects3D.Human3;
import objects3D.Human4;
import objects3D.Sphere; 

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {

	private  boolean MouseOnepressed = true;
	private boolean  dragMode=false;
	private boolean BadAnimation = true;
	private boolean LegAnimation;
	private boolean Earth= false;
	/** position of pointer */
	float x = 400, y = 300,za=0,zb=0,zc=0;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	long  myDelta =0 ; //to use for animation
	float Alpha =0 ; //to use for animation
	long StartTime; // beginAnimiation 

	Arcball MyArcball= new Arcball();
	
	boolean DRAWGRID =false;
	boolean waitForKeyrelease= true;
	/** Mouse movement */
	int LastMouseX = -1 ;
	int LastMouseY= -1;
	
	 float pullX = 0.0f; // arc ball  X cord. 
	 float pullY = 0.0f; // arc ball  Y cord. 

	 
	int OrthoNumber = 4000; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2 // do not change this for assignment 3 but you can change everything for your project 
	
	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };
	
	Sphere sphere= new Sphere();
	Cylinder cylinder= new Cylinder();
	TexSphere texsphere=new TexSphere();

	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	//support method to aid in converting a java float array into a Floatbuffer which is faster for the opengl layer to process 
	ArrayList<Texture> texturelist=new ArrayList<Texture>();//create a texture list for the human
	ArrayList<Texture> texturelist1=new ArrayList<Texture>();
	ArrayList<Texture> texturelist2=new ArrayList<Texture>();
	ArrayList<Texture> texturelist3=new ArrayList<Texture>();
	ArrayList<Texture> texturelist4=new ArrayList<Texture>();
	ArrayList<Texture> texturelist5=new ArrayList<Texture>();
	ArrayList<Texture> texturelist6=new ArrayList<Texture>();
	public void start() {
		
		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
		 
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	public void update(int delta) {
		// rotate quad
		//rotation += 0.01f * delta;
		  
		  
		int MouseX= Mouse.getX();
		  int MouseY= Mouse.getY();
		  int WheelPostion = Mouse.getDWheel();
	  
		  
		  boolean  MouseButonPressed= Mouse.isButtonDown(0);
		  
		 
		  
		  if(MouseButonPressed && !MouseOnepressed )
		  {
			  MouseOnepressed =true;
			//  System.out.println("Mouse drag mode");
			  MyArcball.startBall( MouseX, MouseY, 1200, 800);
			  dragMode=true;
				
				
		  }else if( !MouseButonPressed)
		  { 
				// System.out.println("Mouse drag mode end ");
			  MouseOnepressed =false;
			  dragMode=false;
		  }
		  
		  if(dragMode)
		  {
			  MyArcball.updateBall( MouseX  , MouseY  , 1200, 800);
		  }
		  
		  if(WheelPostion>0)
		  {
			  OrthoNumber += 10;
			 
		  }
		  
		  if(WheelPostion<0)
		  {
			  OrthoNumber -= 10;
			  if( OrthoNumber<610)
			  {
				  OrthoNumber=610;
			  }
			  
			//  System.out.println("Orth nubmer = " +  OrthoNumber);
			  
		  }
		  
		  /** rest key is R*/
		  if (Keyboard.isKeyDown(Keyboard.KEY_R))
			  MyArcball.reset();
		  
		  /* bad animation can be turn on or off using A key)*/
		
		if (Keyboard.isKeyDown(Keyboard.KEY_E))
		{
			Earth=!Earth;
		} 
		
		 if(waitForKeyrelease) // check done to see if key is released 
		 {
		if (Keyboard.isKeyDown(Keyboard.KEY_G))
		{
			
			DRAWGRID = !DRAWGRID;
			Keyboard.next();
			if(Keyboard.isKeyDown(Keyboard.KEY_G))
			{
				waitForKeyrelease=true;
			}else
			{
				waitForKeyrelease=false;
				
			}
		}
		 }
		 
		 /** to check if key is released */
		 if(Keyboard.isKeyDown(Keyboard.KEY_G)==false)
			{
				waitForKeyrelease=true;
			}else
			{
				waitForKeyrelease=false;
				
			}
		 
		 
		 
			

		// keep quad on the screen
//		if (x < 0)
//			x = 0;
//		if (x > 1200)
//			x = 1200;
//		if (y < 0)
//			y = 0;
//		if (y > 800)
//			y = 800;

		updateFPS(); // update FPS Counter
		
		LastMouseX= MouseX;
		LastMouseY= MouseY ;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			rotation -= 0.15f * delta;
			if(rotation<0) {
				rotation+=360;
			 }
			 if(rotation>360) {
				rotation-=360;
			 }
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			rotation += 0.15f * delta;
			if(rotation<0) {
				rotation+=360;
			}
			if(rotation>360) {
				rotation-=360;
			}
		}
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		changeOrth();
		MyArcball.startBall(0, 0, 1200,800);
		GL11.glRotatef(-30, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(-30, 0.0f, 1.0f, 0.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();

		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPos); // specify the
																	// position
																	// of the
																	// light
		// GL11.glEnable(GL11.GL_LIGHT0); // switch light #0 on // I've setup specific materials so in real light it will look abit strange 

		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos); // specify the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT1); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(spot));

		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPos3); // specify
																	// the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT2); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos4); // specify
																	// the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT3); // switch light #0 on
		 GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

		GL11.glEnable(GL11.GL_LIGHTING); // switch lighting on
		GL11.glEnable(GL11.GL_DEPTH_TEST); // make sure depth buffer is switched
											// on
	 	GL11.glEnable(GL11.GL_NORMALIZE); // normalize normal vectors for safety
	 	GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		
		   GL11.glEnable(GL11.GL_BLEND);
       GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
          try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //load in texture
          

	}

	 

	public void changeOrth() {

		 GL11.glMatrixMode(GL11.GL_PROJECTION);
		 GL11.glLoadIdentity();
		  GL11.glOrtho(1200 -  OrthoNumber , OrthoNumber, (800 - (OrthoNumber  * 0.66f)) , (OrthoNumber * 0.66f), 100000, -100000);
		 	GL11.glMatrixMode(GL11.GL_MODELVIEW); 
		 	
		 	FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16); 
		 	GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, CurrentMatrix);
		 
		 //	if(MouseOnepressed)
		 //	{
		  
		 	//MyArcball.getMatrix(CurrentMatrix); 
		 //	}
		 	
		    GL11.glLoadMatrix(CurrentMatrix);
		 	
	}

	/*
	 * You can edit this method to add in your own objects /  remember to load in textures in the INIT method as they take time to load 
	 * 
	 */
	public void renderGL() { 
		changeOrth();
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glClearColor(blue[0], blue[1], blue[2], 1.0f);
		GL11.glColor3f(0.5f, 0.5f, 1.0f); 
		 
		 myDelta =   getTime() - StartTime; 
		  float delta =((float) myDelta)/10000; 
		   
		  // code to aid in animation 
		 float theta = (float) (delta * 2 * Math.PI);
		 float thetaDeg = delta * 360; 
		  float posn_x = (float) Math.cos(theta); // same as your circle code in your notes 
		  float posn_y  = (float) Math.sin(theta);
		  
		  /*
		   * This code draws a grid to help you view the human models movement 
		   *  You may change this code to move the grid around and change its starting angle as you please 
		   */
		if(DRAWGRID)
		{
		GL11.glPushMatrix();
		Grid MyGrid = new Grid();
		GL11.glTranslatef(600, 400, 0); 
		GL11.glScalef(200f, 200f,  200f); 
		 MyGrid.DrawGrid();
		GL11.glPopMatrix();
		}
		if(delta*20<18) {
			BadAnimation=false;
			GL11.glPushMatrix();
			Human2 MyHuman2 = new Human2();
			GL11.glTranslatef(500, 500,500 ); 
			GL11.glScalef(90f, 90f,  90f);
			GL11.glTranslatef(0.0f,0.0f, -delta*20);//this set the circle for the people walking
//				 GL11.glRotatef(-thetaDeg, 0.0f,1.0f, 0.0f);//this changes the rotation of the human
			
			MyHuman2.DrawHuman(delta, !BadAnimation, texturelist);// give a delta for the Human object ot be animated 
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			champion MyChampion = new champion();
			GL11.glTranslatef(500,650,-1550); 
			GL11.glScalef(90f, 90f,  90f);
			MyChampion.drawChampion(champion);
			GL11.glPopMatrix();
		}
		if(delta*20>=18&&(delta*20-18)*15<=180) {
			BadAnimation=true;
			GL11.glPushMatrix();
			Human MyHuman = new Human();
			GL11.glTranslatef(500, 500,-1200f);
			GL11.glRotatef((delta*20-18)*15, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(90f, 90f, 90f);
			MyHuman.DrawHuman(delta, !BadAnimation, texturelist);// give a delta for the Human object ot be animated 
			GL11.glPopMatrix();
		}
		if((delta*20-18)*15>180&&delta*20<65) {
			BadAnimation=false;
			GL11.glPushMatrix();
			Human MyHuman = new Human();
			GL11.glTranslatef(500, 500,-1200f); 
			GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(90f, 90f,  90f);
			GL11.glTranslatef(0.0f,0.0f,-(float)((delta-1.5)*20));//this set the circle for the people walking
//			GL11.glRotatef(-thetaDeg, 0.0f,1.0f, 0.0f);//this changes the rotation of the human
			
			MyHuman.DrawHuman(delta, !BadAnimation, texturelist);// give a delta for the Human object ot be animated 
			GL11.glPopMatrix();
		}
		if(delta*20>=65&&(delta-3.25)*500<=180) {
			BadAnimation=true;
			GL11.glPushMatrix();
			Human MyHuman = new Human();
			GL11.glTranslatef(500, 500,-1200f); 
			GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(90f, 90f,  90f);
			GL11.glTranslatef(0.0f,0.0f,-(float)((3.25-1.5)*20));//this set the circle for the people walking
//			GL11.glRotatef(-thetaDeg, 0.0f,1.0f, 0.0f);//this changes the rotation of the human
			GL11.glRotatef((float) (-(delta-3.25)*500),0.0f,1.0f,0.0f);
			MyHuman.DrawHuman(delta, !BadAnimation, texturelist);// give a delta for the Human object ot be animated 
			GL11.glPopMatrix();
		}
		if((delta-3.25)*500>180) {
			GL11.glPushMatrix();
			Human4 MyHuman = new Human4();
			GL11.glTranslatef(500+x-400, 500+2*zc,-1200f+y-280); 
			GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(90f, 90f,  90f);
			GL11.glTranslatef(0.0f,0.0f,-(float)((3.25-1.5)*20));
			GL11.glRotatef(180+rotation, 0, 1, 0);
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				BadAnimation=false;
				LegAnimation=true;
				y += 20.0f*Math.cos(rotation/180*Math.PI+Math.PI);
				x += 20.0f*Math.sin(rotation/180*Math.PI+Math.PI);
			}else {
				LegAnimation=false;
				BadAnimation=false;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
				za +=8.0f;
				if(za<200) {
					zc = za;
				}else if(za<300){
					zc-=8.0f;
					if(zc<=0) {
						zc=0;
					}
				}else {
					za=0;
				}
			}
			if(zc>0) {
				zc-=8.0f;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_N)) {
				x=0;
				y=0;
				zc=0;
			}
			MyHuman.DrawHuman(delta, !BadAnimation,LegAnimation, texturelist);// give a delta for the Human object ot be animated 
			GL11.glPopMatrix();
			
		}
		
		
		GL11.glPushMatrix();
		Human3 MyHuman3 = new Human3();
		GL11.glTranslatef(1000, 500+2*zb,2500 ); 
		GL11.glScalef(90f, 90f,  90f);
		GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			za +=8.0f;
			if(za<200) {
				zb = za;
			}else if(za<300){
				zb-=8.0f;
				if(zb<=0) {
					zb=0;
				}
			}else {
				za=0;
			}
		}
		if(zb>0) {
			zb-=8.0f;
		}
//			 GL11.glRotatef(-thetaDeg, 0.0f,1.0f, 0.0f);//this changes the rotation of the human
		
		MyHuman3.DrawHuman(delta, true, texturelist1);// give a delta for the Human object ot be animated 
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		Human3 MyHuman4 = new Human3();
		GL11.glTranslatef(1000, 500+2*zb,2000 ); 
		GL11.glScalef(90f, 90f,  90f);
		GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			za +=8.0f;
			if(za<200) {
				zb = za;
			}else if(za<300){
				zb-=8.0f;
				if(zb<=0) {
					zb=0;
				}
			}else {
				za=0;
			}
		}
		if(zb>0) {
			zb-=8.0f;
		}
//			 GL11.glRotatef(-thetaDeg, 0.0f,1.0f, 0.0f);//this changes the rotation of the human
		
		MyHuman4.DrawHuman(delta, true, texturelist2);// give a delta for the Human object ot be animated 
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		Human3 MyHuman5 = new Human3();
		GL11.glTranslatef(1000, 500+2*zb,1500 ); 
		GL11.glScalef(90f, 90f,  90f);
		GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			za +=8.0f;
			if(za<200) {
				zb = za;
			}else if(za<300){
				zb-=8.0f;
				if(zb<=0) {
					zb=0;
				}
			}else {
				za=0;
			}
		}
		if(zb>0) {
			zb-=8.0f;
		}
//			 GL11.glRotatef(-thetaDeg, 0.0f,1.0f, 0.0f);//this changes the rotation of the human
		
		MyHuman5.DrawHuman(delta, true, texturelist3);// give a delta for the Human object ot be animated 
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		Human3 MyHuman6 = new Human3();
		GL11.glTranslatef(1000, 500+2*zb,1000 ); 
		GL11.glScalef(90f, 90f,  90f);
		GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			za +=8.0f;
			if(za<200) {
				zb = za;
			}else if(za<300){
				zb-=8.0f;
				if(zb<=0) {
					zb=0;
				}
			}else {
				za=0;
			}
		}
		if(zb>0) {
			zb-=8.0f;
		}
//			 GL11.glRotatef(-thetaDeg, 0.0f,1.0f, 0.0f);//this changes the rotation of the human
		
		MyHuman6.DrawHuman(delta, true, texturelist4);// give a delta for the Human object ot be animated 
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		Human3 MyHuman7 = new Human3();
		GL11.glTranslatef(1000, 500+2*zb,500 ); 
		GL11.glScalef(90f, 90f,  90f);
		GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			za +=8.0f;
			if(za<200) {
				zb = za;
			}else if(za<300){
				zb-=8.0f;
				if(zb<=0) {
					zb=0;
				}
			}else {
				za=0;
			}
		}
		if(zb>0) {
			zb-=8.0f;
		}
//			 GL11.glRotatef(-thetaDeg, 0.0f,1.0f, 0.0f);//this changes the rotation of the human
		
		MyHuman7.DrawHuman(delta, true, texturelist5);// give a delta for the Human object ot be animated 
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		TexCube groundT=new TexCube();
		GL11.glTranslatef(500,350,500);
		GL11.glScalef(1024f, 0f, 2400f);
		GL11.glTexParameteri(
				GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
				GL11.GL_CLAMP);
	  
		Color.white.bind();
		ground.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
		GL11.glClearColor(0, 0, (float)139/255,1 );
	    groundT.DrawTexCube();
	    GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		
		GL11.glColor3f(grey[0],grey[1],grey[2]);
		GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
		GL11.glPushMatrix();
		GL11.glTranslatef(1500, 350, 500);
		GL11.glRotatef(-90.0f,1.0f, 0.0f, 0.0f);
		cylinder.DrawCylinder(15f, 350f, 32);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		TexCube noticeT=new TexCube();
		GL11.glTranslatef(1500, 1050, 500);
		GL11.glScalef(0, 400f, 700f);
		GL11.glTexParameteri(
				GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
				GL11.GL_CLAMP);
	  
		Color.white.bind();
		notice.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	    noticeT.DrawTexCube();
	    GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		
		GL11.glPushMatrix();
		TexCube championT=new TexCube();
		GL11.glTranslatef(500, 1050,3000);
		GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
		GL11.glScalef(700f, 400f, 0);
		GL11.glTexParameteri(
				GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
				GL11.GL_CLAMP);
	  
		Color.white.bind();
		championr.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	    championT.DrawTexCube();
	    GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		
		GL11.glColor3f(grey[0],grey[1],grey[2]);
		GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
		GL11.glPushMatrix();
		GL11.glTranslatef(600, 250, 3000);
		GL11.glRotatef(-90.0f,1.0f, 0.0f, 0.0f);
		cylinder.DrawCylinder(10f, 400f, 32);
		GL11.glPopMatrix();
		
		GL11.glColor3f(grey[0],grey[1],grey[2]);
		GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
		GL11.glPushMatrix();
		GL11.glTranslatef(500, 350, -1500);
		GL11.glRotatef(-90.0f,1.0f, 0.0f, 0.0f);
		cylinder.DrawCylinder(150f, 350f, 32);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		TexCube FPX1=new TexCube();
		GL11.glTranslatef(400,2800,0);
		GL11.glScalef(350f,350f,350f);
		GL11.glTexParameteri(
				GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
				GL11.GL_CLAMP);
	  
		Color.white.bind();
		fpx.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	    FPX1.DrawTexCube();
	    GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		
	}
		  
	public static void main(String[] argv) {
		MainWindow hello = new MainWindow();
		hello.start();
	}
	 
	Texture texture,notice,ground,champion,championr,fpx;
	 
	/*
	 * Any additional textures for your assignment should be written in here. 
	 * Make a new texture variable for each one so they can be loaded in at the beginning 
	 */
	public void init() throws IOException {
	         
	  texture = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/cube.jpg"));
	  System.out.println("Texture loaded okay ");//set the texture for the cube
	  Texture face= TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/face.png"));
	  texturelist.add(face);
	  Texture gimgoon= TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gimgoon.png"));
	  texturelist1.add(gimgoon);
	  Texture tian= TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/tian.png"));
	  texturelist2.add(tian);
	  Texture xinyi= TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/xinyi.png"));
	  texturelist3.add(xinyi);
	  Texture lwx= TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/lwx.png"));
	  texturelist4.add(lwx);
	  Texture crisp= TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/crisp.png"));
	  texturelist5.add(crisp);
	  Texture faker= TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/faker.jpg"));
	  texturelist6.add(faker);
	  Texture clothes=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/clothes.png"));
	  texturelist.add(clothes);
	  texturelist1.add(clothes);
	  texturelist2.add(clothes);
	  texturelist3.add(clothes);
	  texturelist4.add(clothes);
	  texturelist5.add(clothes);
	  Texture skt= TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/skt.jpeg"));
	  texturelist6.add(skt);
	  notice=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/notice.png"));
	  ground=TextureLoader.getTexture("JPG",ResourceLoader.getResourceAsStream("res/ground.jpg"));
	  champion=TextureLoader.getTexture("JPEG",ResourceLoader.getResourceAsStream("res/champion.jpeg"));
	  texturelist.add(champion);
	  championr=TextureLoader.getTexture("JPG",ResourceLoader.getResourceAsStream("res/championr.jpg"));
	  fpx= TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/fpx.jpeg"));
	}
}

package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Utils;

public class champion {
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
		
		public champion() {
			
		}
		
		public void drawChampion(Texture texture) {
			Sphere sphere= new Sphere();
			Cylinder cylinder= new Cylinder();
			TexSphere texsphere=new TexSphere();
			GL11.glPushMatrix();{
					 GL11.glColor3f(blue[0] ,blue[1], blue[2]);
					 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
					 GL11.glTranslatef(0.0f,0.7f,0.0f);
					 cylinder.DrawCylinder(0.15f, 0.7f, 32);
				 GL11.glPushMatrix();{
					 GL11.glColor3f(blue[0], blue[1], blue[2]);
					 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(grey));
					 GL11.glTranslatef(0.0f,0.0f,0.3f);
					 GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
					 cylinder.DrawCylinder(0.15f, 1.0f, 32);
				 }GL11.glPopMatrix();
			     GL11.glTranslatef(0.0f,1.0f,0.3f);
//			     GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);
			     GL11.glTexParameteri(
     					GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
     					GL11.GL_CLAMP);
			     Color.white.bind();
    			 texture.bind();//get the texture and add a take place function
    			 GL11.glEnable(GL11.GL_TEXTURE_2D);//enable to set the texture
 			     GL11.glDisable(GL11.GL_LIGHTING);
 			     GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
 		         GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);  //white color    
                 texsphere.DrawTexSphere(0.5f, 32, 32,texture); // set the texture and draw the ball
                 GL11.glDisable(GL11.GL_TEXTURE_2D);
    			 GL11.glEnable(GL11.GL_LIGHTING);
//			     sphere.DrawSphere(0.5f, 32, 32);
			}GL11.glPopMatrix();
		}

}

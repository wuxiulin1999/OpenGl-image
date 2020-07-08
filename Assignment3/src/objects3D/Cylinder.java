package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void DrawCylinder(float radius, float height, int nSegments ) 
	{
		GL11.glBegin(GL11.GL_TRIANGLES);
		 for(float i=0;i<nSegments;i++) {
			 double angle=Math.PI*i*2/nSegments;//this is for get one point of triangle
			 double nextAngle=Math.PI*(i+1)*2/nSegments;//get another point
			 float x1=(float) (Math.sin(angle)*radius);//one point
			 float y1=(float) (Math.cos(angle)*radius);//one point
			 float x2=(float) (Math.sin(nextAngle)*radius);//one point
			 float y2=(float) (Math.cos(nextAngle)*radius);//one point
			 GL11.glNormal3f(x1,y1,0);//point for the face be smoother
			 GL11.glVertex3f(x1,y1,0);//set the point for triangle
			 GL11.glNormal3f(x2,y2,0);
			 GL11.glVertex3f(x2,y2,height);
			 GL11.glNormal3f(x1,y1,0);
			 GL11.glVertex3f(x1,y1,height);
			 GL11.glNormal3f(x1,y1,0);
			 GL11.glVertex3f(x1,y1,0);
			 GL11.glNormal3f(x2,y2,0);
			 GL11.glVertex3f(x2,y2,0);
			 GL11.glNormal3f(x2,y2,0);
			 GL11.glVertex3f(x2,y2,height);
			 
			 Vector4f v=new Vector4f(x1,y1,0f,0f);
			 Vector4f v1=new Vector4f(x2,y2,0f,0f);
			 Vector4f n=v.cross(v1).Normal();
			 GL11.glNormal3f(n.x,n.y,n.z);//the face can be smoother
			 GL11.glVertex3f(x1, y1, 0);
			 GL11.glVertex3f(x2, y2, 0);
			 GL11.glVertex3f(0, 0, 0);//set a triangle for the face

			 GL11.glNormal3f(-n.x, -n.y, -n.z);
			 GL11.glVertex3f(x1, y1, height);
			 GL11.glVertex3f(x2, y2, height);
			 GL11.glVertex3f(0,0,height);//set another triangle

		 }
		 GL11.glEnd();

	}
}

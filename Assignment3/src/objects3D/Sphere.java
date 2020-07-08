package objects3D;

import org.lwjgl.opengl.GL11;

public class Sphere {

	
	public Sphere() {

	}
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	// 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works 
	public void DrawSphere(float radius,float nSlices,float nSegments) {
		float inctheta=(float) ((2.0f*Math.PI)/(float)nSlices);
		float incphi=(float) (Math.PI/(float)nSegments);
		 GL11.glBegin(GL11.GL_QUADS);//this is a quad
		for(float theta=(float) -Math.PI;theta<Math.PI;theta+=inctheta) {
			for(float phi=(float) -(Math.PI/2.0f);phi<Math.PI/2.0f;phi+=incphi) {
				float z=(float) (radius*Math.sin(phi));
				float x=(float) (radius*Math.cos(phi)*Math.cos(theta));
				float y=(float) (radius*Math.cos(phi)*Math.sin(theta));
				//set a point for one quad
				
				float z1=(float) (radius*Math.sin(phi));
				float x1=(float) (radius*Math.cos(phi)*Math.cos(theta+inctheta));
				float y1=(float) (radius*Math.cos(phi)*Math.sin(theta+inctheta));
				//set a point for one quad

				
				float z2=(float) (radius*Math.sin(phi+incphi));
				float x2=(float) (radius*Math.cos(phi+incphi)*Math.cos(theta+inctheta));
				float y2=(float) (radius*Math.cos(phi+incphi)*Math.sin(theta+inctheta));
				//set a point for one quad

				
				float z3=(float) (radius*Math.sin(phi+incphi));
				float x3=(float) (radius*Math.cos(phi+incphi)*Math.cos(theta));
				float y3=(float) (radius*Math.cos(phi+incphi)*Math.sin(theta));
				//set a point for one quad

				GL11.glNormal3f(x, y, z);//set normal vector
				GL11.glVertex3f(x, y, z);
				GL11.glVertex3f(x1, y1, z1);
				GL11.glVertex3f(x2, y2, z2);
				GL11.glVertex3f(x3, y3, z3);//set a quad
				
			}
		}
		GL11.glEnd();
	}
}

 
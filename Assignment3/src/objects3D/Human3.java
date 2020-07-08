package objects3D;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Utils;

public class Human3 {
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
	
	public Human3() {

	}
	
	public void DrawHuman(float delta,boolean GoodAnimation,ArrayList<Texture> texturelist) 
	 { //input a texture list ,so that the object in the class can use textures
			 float theta = (float) (delta * 25 * Math.PI);
			  float LimbRotation;
			  if(GoodAnimation) {
				  LimbRotation=(float)Math.cos(theta)*45;
			  }else {
				  LimbRotation=0;
			  }
			Sphere sphere= new Sphere();
			Cylinder cylinder= new Cylinder();
			TexSphere texsphere=new TexSphere();
			champion champion=new champion();
	 
	 
			 GL11.glPushMatrix(); 
			 
			 {
				 GL11.glColor3f(black[0], black[1], black[2]);
	             GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
				  GL11.glTranslatef(0.0f,0.5f,0.0f);
				 sphere.DrawSphere(0.5f, 32, 32); 
			        //  chest
				 GL11.glColor3f(green[0], green[1], green[2]);
				 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(green));
				 GL11.glPushMatrix(); {
			            GL11.glTranslatef(0.0f,0.5f,0.0f);
			            GL11.glRotatef(90f, 1.0f, 0.0f, 0.0f);
			            GL11.glTexParameteri(
	        					GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
	        					GL11.GL_CLAMP);
	        		  
	        			 Color.white.bind();
	        			 texturelist.get(1).bind();//get the texture and add a take place function
	        			 GL11.glEnable(GL11.GL_TEXTURE_2D);//enable to set the texture
	     			    GL11.glDisable(GL11.GL_LIGHTING);
	     			    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
	     		        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);  //white color    
	                    texsphere.DrawTexSphere(0.5f, 32, 32,texturelist.get(1)); // set the texture and draw the ball
	                    GL11.glDisable(GL11.GL_TEXTURE_2D);
	        			GL11.glEnable(GL11.GL_LIGHTING);//for diaplay the human in the correct color
	        			//texture for chest


			            // neck
			       	 GL11.glColor3f(orange[0], orange[1], orange[2]);
			           GL11.glMaterial( GL11.GL_FRONT,  GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
			           GL11.glPushMatrix(); {
			                GL11.glTranslatef(0.0f,0.0f, 0.0f);
			                GL11.glRotatef(180.0f,1.0f,0.0f,0.0f);
			                //                    GL11.glRotatef(45.0f,0.0f,1.0f,0.0f); 
			                cylinder.DrawCylinder(0.15f,0.7f,32);


			                // head
			           	 GL11.glColor3f(red[0], red[1], red[2]);
			               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
			                GL11.glPushMatrix(); {
			           		    GL11.glTranslatef(0.0f,0.0f,1.0f);
			           		    GL11.glRotatef(180, 1.0f, 0.0f, 0.0f);
			                    GL11.glRotatef(-90, 0.0f, 0.0f, 1.0f);
			                    GL11.glTexParameteri(
			        					GL11.GL_TEXTURE_2D, 	GL11.GL_TEXTURE_WRAP_T, 
			        					GL11.GL_CLAMP);
			        		  
			        			 Color.white.bind();
			        			 texturelist.get(0).bind();//same as before
			        			 GL11.glEnable(GL11.GL_TEXTURE_2D);
			     			    GL11.glDisable(GL11.GL_LIGHTING);
			     			    GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
			     		        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);   
			                    texsphere.DrawTexSphere(0.5f, 32, 32,texturelist.get(0)); 
			                    GL11.glDisable(GL11.GL_TEXTURE_2D);
			        			GL11.glEnable(GL11.GL_LIGHTING);
			                    GL11.glPopMatrix();
			                } GL11.glPopMatrix();


			                // left shoulder
			           	 GL11.glColor3f(red[0],red[1], red[2]);
			               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(red));
			                GL11.glPushMatrix(); {
			                    GL11.glTranslatef(0.5f,0.0f,-0.4f);
			                    GL11.glRotatef(90.0f,0.0f,1.0f,0.0f);
			                    GL11.glRotatef(90.0f,0.0f,0.0f,1.0f);
			                    sphere.DrawSphere(0.25f, 32, 32); 
			                    // left arm
			               	 GL11.glColor3f(black[0], black[1], black[2]);
			                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
			                    GL11.glPushMatrix(); {
			                        GL11.glTranslatef(0.0f,0.0f,0.0f);
			                        GL11.glRotatef(-60.0f,1.0f,0.0f,0.0f);
			                        GL11.glRotatef(-90.0f,0.0f,0.0f,1.0f);
			                        cylinder.DrawCylinder(0.15f,0.7f,32);


			                        // left elbow
			                   	 GL11.glColor3f(red[0], red[1], red[2]);
			                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                        GL11.glPushMatrix(); {
			                            GL11.glTranslatef(0.0f,0.0f,0.75f);
			                            sphere.DrawSphere(0.2f, 32, 32); 
			                            
			                            //left forearm
			                       	 GL11.glColor3f(black[0], black[1], black[2]);
			                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
			                            GL11.glPushMatrix(); {
			                                GL11.glTranslatef(0.0f,0.0f,0.0f);
			                                GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
			       
			                                //this function is for changing the round of the forearm
			                                //GL11.glRotatef(90.0f,0.0f,1.0f,0.0f); 
			                                cylinder.DrawCylinder(0.1f,0.7f,32);

			                                // left hand
			                           	 GL11.glColor3f(red[0], red[1], red[2]);
			                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                                GL11.glPushMatrix(); {
			                                    GL11.glTranslatef(0.0f,0.0f,0.75f);
			                                    sphere.DrawSphere(0.2f, 32, 32); 
			                                   
			                                }GL11.glPopMatrix();
			                                } GL11.glPopMatrix();
			                            } GL11.glPopMatrix();
			                        } GL11.glPopMatrix();
			                } GL11.glPopMatrix ();
			                // to chest

			                // right shoulder
			           	 GL11.glColor3f(red[0],red[1], red[2]);
			               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                GL11.glPushMatrix(); {
			                    GL11.glTranslatef(-0.5f,0.0f,-0.4f);
			                    GL11.glRotatef(-90.0f,1.0f,0.0f,0.0f);
			                    sphere.DrawSphere(0.25f, 32, 32);
			                    // right arm
			                    GL11.glColor3f(black[0], black[1], black[2]);
				                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
				                    GL11.glPushMatrix(); {
				                        GL11.glTranslatef(0.0f,0.0f,0.0f);
				                        GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
				                        GL11.glRotatef(-90.0f,0.0f,1.0f,0.0f);
				                        GL11.glRotatef(LimbRotation/2,0.0f,1.0f,0.0f);

				                        GL11.glRotatef(29.0f,0.0f,-1.0f,0.0f);  
				                        cylinder.DrawCylinder(0.15f,0.7f,32);
			               
			                        // right elbow
				                        GL11.glColor3f(red[0], red[1], red[2]);
					                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
					                        GL11.glPushMatrix(); {
					                            GL11.glTranslatef(0.0f,0.0f,0.75f);
					                            sphere.DrawSphere(0.2f, 32, 32); 
			                           
			                            //right forearm
					                            GL11.glColor3f(black[0], black[1], black[2]);
						                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
						                            GL11.glPushMatrix(); {
						                                GL11.glTranslatef(0.0f,0.0f,0.0f);
						                                if(LimbRotation<0) {
						                                	 GL11.glRotatef(LimbRotation/2,0.0f,1.0f,0.0f);
						                                }
						                               
//						                                GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
						                             //   GL11.glRotatef(90.0f,0.0f,1.0f,0.0f); 
						       		                  
						       		                             
						                                         //changing the round of the forearm
						                                cylinder.DrawCylinder(0.1f,0.7f,32);
			                                // right hand
						                                GL11.glColor3f(red[0], red[1], red[2]);
							                               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
							                                GL11.glPushMatrix(); {
							                                    GL11.glTranslatef(0.0f,0.0f,0.75f);
							                                    sphere.DrawSphere(0.2f, 32, 32);
							                                
							                                } GL11.glPopMatrix();
						                            } GL11.glPopMatrix();
						                        } GL11.glPopMatrix();
						                    } GL11.glPopMatrix ();
			                }GL11.glPopMatrix ();
			                                    
			                                   
			                         

			                //chest


			            } GL11.glPopMatrix();


			            // pelvis

			            // left hip
			       	 GL11.glColor3f(red[0], red[1], red[2]);
			           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			            GL11.glPushMatrix(); {
			                GL11.glTranslatef(-0.5f,-0.2f,0.0f);
			               
			                sphere.DrawSphere(0.25f, 32, 32); 


			                // left high leg
			           	 GL11.glColor3f(black[0], black[1], black[2]);
			               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
			                GL11.glPushMatrix(); {
			                    GL11.glTranslatef(0.0f,0.0f,0.0f);
			                    GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
			                
			                    
			                  
			                    //GL11.glRotatef(0.0f,1.0f,0.0f,0.0f); 
			                    cylinder.DrawCylinder(0.15f,0.7f,32);


			                    // left knee
			               	 GL11.glColor3f(red[0], red[1], red[2]);
			                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                    GL11.glPushMatrix(); {
			                        GL11.glTranslatef(0.0f,0.0f,0.75f);
			                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
			                        sphere.DrawSphere(0.25f, 32, 32); 

			                        //left low leg
			                   	 GL11.glColor3f(black[0], black[1], black[2]);
			                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
			                        GL11.glPushMatrix(); {
			                            GL11.glTranslatef(0.0f,0.0f,0.0f);
			                           
			                            //changing the low leg,so it can be more realistic
			                           
			                            //GL11.glRotatef(60.0f,1.0f,0.0f,0.0f);
			                          //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
			                            cylinder.DrawCylinder(0.15f,0.7f,32);

			                            // left foot
			                       	 GL11.glColor3f(red[0], red[1], red[2]);
			                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
			                            GL11.glPushMatrix(); {
			                                GL11.glTranslatef(0.0f,0.0f,0.75f);
			                                sphere.DrawSphere(0.3f, 32, 32);  

			                            } GL11.glPopMatrix();
			                        } GL11.glPopMatrix();
			                    } GL11.glPopMatrix();
			                } GL11.glPopMatrix();
			            } GL11.glPopMatrix();

			            // pelvis


			            // right hip
			            GL11.glColor3f(red[0], red[1], red[2]);
				           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
				            GL11.glPushMatrix(); {
				                GL11.glTranslatef(0.5f,-0.2f,0.0f);
				               
				                sphere.DrawSphere(0.25f, 32, 32); 
				             // right high leg
				                GL11.glColor3f(black[0], black[1], black[2]);
					               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
					                GL11.glPushMatrix(); {
					                    GL11.glTranslatef(0.0f,0.0f,0.0f);
					                    GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
					                
					                    
					                   
					                            //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f); 
					                    cylinder.DrawCylinder(0.15f,0.7f,32);
					                 // right knee
						               	 GL11.glColor3f(red[0], red[1], red[2]);
						                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
						                    GL11.glPushMatrix(); {
						                        GL11.glTranslatef(0.0f,0.0f,0.75f);
						                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
						                        sphere.DrawSphere(0.25f, 32, 32); 
						                      //right low leg
							                   	 GL11.glColor3f(black[0], black[1], black[2]);
							                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
							                        GL11.glPushMatrix(); {
							                            GL11.glTranslatef(0.0f,0.0f,0.0f);
							                           
							                            //changing the low leg,so it can be more realistic
							                           // GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
							                          //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f); 
							                            cylinder.DrawCylinder(0.15f,0.7f,32);

							                            // right foot
							                       	 GL11.glColor3f(red[0], red[1], red[2]);
							                           GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
							                            GL11.glPushMatrix(); {
							                                GL11.glTranslatef(0.0f,0.0f,0.75f);
							                                sphere.DrawSphere(0.3f, 32, 32);  

							                            } GL11.glPopMatrix();
							                        } GL11.glPopMatrix();
							                    } GL11.glPopMatrix();
							                } GL11.glPopMatrix();
							            } GL11.glPopMatrix();
			        
			        } GL11.glPopMatrix();
			         
			    } 

			

		}

}

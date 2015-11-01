/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invincibagel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.microedition.m3g.*;
import javax.media.j3d.Canvas3D;

/**
 *
 * @author Chris
 */
public final class Mars {
	public static World getRoot(Canvas3D aCanvas) {
		//Camera Camera
		Camera BL2 = new Camera();
		float[] BL2_matrix = {
			0.685880541801f,-0.317370116711f,0.654861867428f,7.48113155365f,
			0.727633774281f,0.312468618155f,-0.610665619373f,-6.50763988495f,
			-0.0108167808503f,0.895343244076f,0.445245355368f,5.34366512299f,
			0.0f,0.0f,0.0f,1.0f
		};
		
		Transform BL2_transform = new Transform();
		BL2_transform.set(BL2_matrix);
		BL2.setTransform(BL2_transform);
		
		BL2.setPerspective(60.000000f,  //Field of View
				(float)aCanvas.getWidth()/(float)aCanvas.getHeight(),
				0.10000000149f, //Near Clipping Plane
				100.0f); //Far Clipping Plane
		//Background:
		Background BL3 = new Background();
		BL3.setColor(0x000E3866);
		
		// VertexArray 
		short[] BL4_array = {
			32766,32766,-32766,32766,-32766,-32766,-32766,-32766,-32766,-32766,32766,-32766,
			32766,32766,32766,-32766,32766,32766,-32766,-32766,32766,32766,-32766,32766,
			32766,32766,-32766,32766,32766,32766,32766,-32766,32766,32766,-32766,-32766,
			32766,-32766,-32766,32766,-32766,32766,-32766,-32766,32766,-32766,-32766,-32766,
			-32766,-32766,-32766,-32766,-32766,32766,-32766,32766,32766,-32766,32766,-32766,
			32766,32766,32766,32766,32766,-32766,-32766,32766,-32766,-32766,32766,32766
		};
		
		VertexArray BL4 = new VertexArray(BL4_array.length/3,3,2);
		BL4.set(0,BL4_array.length/3,BL4_array);
		
		// VertexArray 
		byte[] BL5_array = {
			0,0,-127,0,0,-127,0,0,-127,0,0,-127,
			0,0,127,0,0,127,0,0,127,0,0,127,
			127,0,0,127,0,0,127,0,0,127,0,0,
			0,-127,0,0,-127,0,0,-127,0,0,-127,0,
			-127,0,0,-127,0,0,-127,0,0,-127,0,0,
			0,127,0,0,127,0,0,127,0,0,127,0
		};
		
		VertexArray BL5 = new VertexArray(BL5_array.length/3,3,1);
		BL5.set(0,BL5_array.length/3,BL5_array);
		
		//VertexBuffer
		VertexBuffer BL6 = new VertexBuffer();
		float BL6_Bias[] = { 0.000000f, -0.000000f, 0.000000f};
		BL6.setPositions(BL4,0.000031f,BL6_Bias);
		BL6.setNormals(BL5);
		//length of TriangleStrips
		int[] BL7_stripLength ={4,4,4,4,4,4};
		
		//IndexBuffer
		int[] BL7_Indices = {
		1,2,0,3,5,6,4,7,9,10,8,11,13,14,12,15,17,18,16,19,21,22,20,23};
		
		IndexBuffer BL7=new TriangleStripArray(BL7_Indices,BL7_stripLength);
		
		PolygonMode BL8 = new PolygonMode();
		BL8.setCulling(162);
		BL8.setShading(165);
		BL8.setWinding(168);
		BL8.setTwoSidedLightingEnable(false);
		BL8.setLocalCameraLightingEnable(false);
		BL8.setPerspectiveCorrectionEnable(false);
		
		//Material: Material
		Material BL9 = new Material();
		BL9.setColor(Material.AMBIENT,0x00000000);
		BL9.setColor(Material.SPECULAR,0x00000000);
		BL9.setColor(Material.DIFFUSE,0xFFCCCCCC);
		BL9.setColor(Material.EMISSIVE,0x00000000);
		BL9.setShininess(0.000000f);
		BL9.setVertexColorTrackingEnable(false);
		//Appearance
		Appearance BL10 = new Appearance();
		BL10.setPolygonMode(BL8);
		BL10.setMaterial(BL9);
		
		
		//Mesh:Cube
		Mesh BL11 = new Mesh(BL6,BL7,BL10);
		float[] BL11_matrix = {
			1.0f,0.0f,0.0f,-0.0370948575437f,
			0.0f,1.0f,0.0f,0.0370948575437f,
			0.0f,0.0f,1.0f,0.0f,
			0.0f,0.0f,0.0f,1.0f
		};
		
		Transform BL11_transform = new Transform();
		BL11_transform.set(BL11_matrix);
		BL11.setTransform(BL11_transform);
		
		
		//Light: Lamp
		Light BL12 = new Light();
		BL12.setMode(130);
		BL12.setAttenuation(0.000000f, 0.066667f,0.000000f);
		BL12.setColor(0x00FFFFFF);
		BL12.setIntensity(1.000000f);
		float[] BL12_matrix = {
			-0.290864646435f,-0.771100819111f,0.566393196583f,4.07624530792f,
			0.95517116785f,-0.19988335669f,0.21839119494f,1.00545394421f,
			-0.0551890581846f,0.604524731636f,0.794672250748f,5.90386199951f,
			0.0f,0.0f,0.0f,1.0f
		};
		
		Transform BL12_transform = new Transform();
		BL12_transform.set(BL12_matrix);
		BL12.setTransform(BL12_transform);
		
		
		//World:
		World BL13 = new World();
		BL13.addChild(BL11);
		BL13.addChild(BL12);
		BL13.addChild(BL2);
		BL13.setBackground(BL3);
		BL13.setActiveCamera(BL2);
		
	
	return BL13;
}}
package org.flixel.plugin.flxbox2d.collision.shapes;

import org.flixel.FlxG;
import org.flixel.plugin.flxbox2d.B2FlxB;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * A polygon shape which can be a convex or concave. The vertices
 * must be in CCW order for a right-handed coordinates system.
 * 
 * @author Ka Wing Chin
 */
public class B2FlxPolygon extends B2FlxShape
{
	/**
	 * Holds the vertices.
	 */
	private float[][][] _vertices;
	/**
	 * Holds the shapes. Needed for createFixture and createFixtureFromPolygon.
	 */
	public PolygonShape[] shapes;

	/**
	 * This creates an polygon.
	 * @param x			The X-coordinate of the point in space.
	 * @param y			The Y-coordinate of the point in space.
	 * @param vertices	The vertices of the polygon.
	 */
	public B2FlxPolygon(float x, float y, float[][][] vertices)
	{
		super(x, y);
		if(vertices != null)
			_vertices = vertices;
		else
			FlxG.log("no vertices has been set, default is used.");
		createShape();
	}
	
	/**
	 * This creates an polygon.
	 * @param x			The X-coordinate of the point in space.
	 * @param y			The Y-coordinate of the point in space.
	 */
	public B2FlxPolygon(float x, float y)
	{
		this(x, y, null);
	}
	
	/**
	 * This creates an polygon.
	 * @param x			The X-coordinate of the point in space.
	 */
	public B2FlxPolygon(float x)
	{
		this(x, 0, null);
	}
	
	/**
	 * This creates an polygon.
	 */
	public B2FlxPolygon()
	{
		this(0, 0, null);
	}
	
	/**
	 * The default polygon is a box. A message will be logged so
	 * you'll know the default has been used.
	 */
	@Override
	public void setDefaults()
	{
		/*
		 * p1----p2
		 * |	 |
		 * |	 |
		 * p4----p3
		 */
		_vertices = new float[][][]{{{-10, -10}, {10, -10}, {10, 10}, {-10, 10}}};
	}
	
	/**
	 * Creates the shape.
	 */
	@Override
	public void createShape()
	{		
		Vector2[] vector;
		int vertexCount = 0;
		int indicesCount = _vertices.length;
		shapes = new PolygonShape[indicesCount];
		for(int i = 0; i < indicesCount; i++) 
		{
			vertexCount = _vertices[i].length;
			if(vertexCount < 3 || vertexCount > 8)
				throw new Error("vertexCount < 3 || vertexCount > 8");
			vector = new Vector2[vertexCount];
			for (int j = 0; j < vertexCount; j++) 
			{		
				vector[j] = new Vector2((float)_vertices[i][j][0] / B2FlxB.RATIO, (float)_vertices[i][j][1] / B2FlxB.RATIO);					
			}
			shape = new PolygonShape();
			((PolygonShape)shape).set(vector);
			shapes[i] = (PolygonShape) shape;
		}
	}

	/**
	 * Creates the body.
	 * @return This object. Handy for chaining stuff together.
	 */
	@Override
	public void createBody()
	{	
		bodyDef.position.x = x / B2FlxB.RATIO;
		bodyDef.position.y = y / B2FlxB.RATIO;
		position = bodyDef.position;
		body = B2FlxB.world.createBody(bodyDef);
		PolygonShape s;
		for(int i = 0; i < shapes.length; i++)
		{			
			s = shapes[i];
			fixtureDef.shape = s;
			body.createFixture(fixtureDef);
			s.dispose();
			s = null;
		}
		shape = null;
	}
	
	@Override
	public B2FlxPolygon create()
	{
		super.create();
		return this;
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		_vertices = null;
		shapes = null;
	}
	
	@Override
	public B2FlxPolygon setType(BodyType type){super.setType(type);return this;}	
	@Override
	public B2FlxPolygon setFixtureDef(FixtureDef fixtureDef){super.setFixtureDef(fixtureDef);return this;}	
	@Override
	public B2FlxPolygon setLinearDamping(float linearDamping){super.setLinearDamping(linearDamping);return this;}
	@Override
	public B2FlxPolygon setLinearVelocity(Vector2 linearVelocity){super.setLinearVelocity(linearVelocity);return this;}	
	@Override
	public B2FlxPolygon setLinearVelocity(float x, float y){super.setLinearVelocity(x, y);return this;}	
	@Override
	public B2FlxPolygon setAngularDamping(float angularDamping){super.setAngularDamping(angularDamping);return this;}	
	@Override
	public B2FlxPolygon setAngularVelocity(float angularVelocity){super.setAngularVelocity(angularVelocity);return this;}	
	@Override
	public B2FlxPolygon setBullet(boolean bullet){super.setBullet(bullet);return this;}	
	@Override
	public B2FlxPolygon setFixedRotation(boolean fixedRotation){super.setFixedRotation(fixedRotation);return this;}	
	@Override
	public B2FlxPolygon setAllowSleep(boolean allowSleep){super.setAllowSleep(allowSleep);return this;}	
	@Override
	public B2FlxPolygon setActive(boolean active){super.setActive(active);return this;}	
	@Override
	public B2FlxPolygon setAwake(boolean awake){super.setAwake(awake);return this;}	
	@Override
	public B2FlxPolygon setDensity(float density){super.setDensity(density);return this;}	
	@Override
	public B2FlxPolygon setFriction(float friction){super.setFriction(friction);return this;}	
	@Override
	public B2FlxPolygon setRestitution(float restitution){super.setRestitution(restitution);return this;}	
	@Override
	public B2FlxPolygon setPosition(Vector2 position){super.setPosition(position);return this;}	
	@Override
	public B2FlxPolygon setAngle(float angle){super.setAngle(angle);return this;}	
	@Override
	public B2FlxPolygon setGravityScale(float gravityScale){super.setGravityScale(gravityScale);return this;}	
	@Override
	public B2FlxPolygon setMaskBits(short maskBits){super.setMaskBits(maskBits);return this;}	
	@Override
	public B2FlxPolygon setCategoryBits(short categoryBits){super.setCategoryBits(categoryBits);return this;}	
	@Override
	public B2FlxPolygon setGroupIndex(short groupIndex){super.setGroupIndex(groupIndex);return this;}	
	@Override
	public B2FlxPolygon setSensor(boolean sensor){super.setSensor(sensor);return this;}
	@Override
	public B2FlxPolygon setResetAngle(boolean resetAngle){super.setResetAngle(resetAngle);return this;}	
	@Override
	public B2FlxPolygon setDraggable(boolean draggable){super.setDraggable(draggable);return this;}
	@Override
	public B2FlxPolygon setSurvive(boolean survive){super.setSurvive(survive);return this;}
}

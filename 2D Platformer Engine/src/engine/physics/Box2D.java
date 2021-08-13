package engine.physics;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import engine.Input;
import engine.KeyCode;
import engine.gameObjects.GameObject;

public class Box2D {

	/*/ 
	 	 -  30 pixel'i 1 metre olarak hesaplýyor Box2D fizik motoru.
	 	 -  30'a bölüp her 1 pixeli 1 metreye çevirdim.
	/*/
	
	private final List<Body> bodies = new ArrayList<Body>();
	
	private final World world = new World(new Vec2(0f, -9.8f), false); // <- 2. parametre -9.8f deðilse yap.
	
	public void addDynamicBox(GameObject object) {
		
		BodyDef boxDef = new BodyDef();
		boxDef.position.set(object.getPosition().x/30, object.getPosition().y/30);
		boxDef.type = BodyType.DYNAMIC;
		//boxDef.bullet = true; // <- Çok hýzlý giderse...
		
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(object.getScale().x/2, object.getScale().y/2);
		
		Body box = world.createBody(boxDef);
		
		FixtureDef boxFixture = new FixtureDef();
		boxFixture.density = 0.2f;
		boxFixture.friction = 0.3f;
		boxFixture.shape = boxShape;
		box.createFixture(boxFixture);
		
		bodies.add(box);
		
	}
	
	public void addStaticBox(GameObject object) {
		BodyDef staticboxDef = new BodyDef();
		staticboxDef.position.set(object.getPosition().x/30, object.getPosition().y/30);
		staticboxDef.type = BodyType.STATIC;
		//staticboxDef.angle = 45;
		
		PolygonShape staticboxShape = new PolygonShape();
		staticboxShape.setAsBox(object.getScale().x/2, object.getScale().y/2);
		
		Body staticbox = world.createBody(staticboxDef);
		
		FixtureDef staticboxFixture = new FixtureDef();
		staticboxFixture.restitution = 3.3f;
		staticboxFixture.density = 0f;
		staticboxFixture.shape = staticboxShape;
		staticbox.createFixture(staticboxFixture);
	}
	
	public void updateDynamicObject(GameObject object) {
		for (Body body : bodies) {
			if(body.getType() == BodyType.DYNAMIC) {				
				//Vec2 bodyPosition = body.getPosition();
				Vec2 bodyPosition = body.getPosition().mul(2);
				object.setPosition(bodyPosition);
				object.setRotation(Math.toDegrees(body.getAngle()), new Vec3(0, 0, 1));
			}
			
			if(Input.isKeyPressed(KeyCode.KEY_RIGHT)) {
				body.applyAngularImpulse(0.1f);
			}
			
		}
	}

	public void updatePhysicsWorld() {		
		world.step(1 / 60f, 8, 3);
	}
	
	public void deleteObjects() {
		bodies.clear();
	}
}

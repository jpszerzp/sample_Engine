package com.base.engine.physics;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.base.engine.core.Vector3f;
import com.base.game.TestGame;

/** 
 * @author p
 * Collider with shape of sphere.
 */
public class SphericalCollider extends Collider {
	private final static Logger logger = Logger.getLogger(TestGame.class.getName());
	
	private Vector3f center;
	private float radius;
	
	public SphericalCollider (Vector3f ct, float r) {
		super(ColliderType.TYPE_SPHERE);
		center = ct;
		radius = r;
	}
	
	public IntersectionData intersectSphericalCollider (SphericalCollider other) {
		float intersectThreshold = radius + other.getRadius();
		Vector3f direction = other.getCenter().Sub(center);
		float centerDistance = direction.Length();
		direction = direction.Div(centerDistance);
		
		float surfaceDistance = centerDistance - intersectThreshold;
		return new IntersectionData(surfaceDistance < 0, direction.Mul(surfaceDistance));
	}
	
	public IntersectionData intersectPlaneCollider (PlaneCollider other) {
		// Same as process of plane collider intersecting with spherical collider
		float distanceToPlane = Math.abs(other.getNormal().Dot(center) + (-other.getDistance()));
		float surfaceDistanceToPlane = distanceToPlane - radius;
		return new IntersectionData (surfaceDistanceToPlane < 0, other.getNormal().Mul(surfaceDistanceToPlane));
	}
	
	public void translate (Vector3f translation) {
		center = center.Add(translation);
	} 
	
	public Vector3f getCenter() { return center; }
	
	public float getRadius() { return radius; }
	
	public static void test() {
		SphericalCollider sphereCol1 = new SphericalCollider (new Vector3f(0.0f, 0.0f, 0.0f), 1.0f);
		SphericalCollider sphereCol2 = new SphericalCollider (new Vector3f(0.0f, 3.0f, 0.0f), 1.0f);
		SphericalCollider sphereCol3 = new SphericalCollider (new Vector3f(0.0f, 0.0f, 2.0f), 1.0f);
		SphericalCollider sphereCol4 = new SphericalCollider (new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);
		
		IntersectionData sphere1IntersectSphere2 = sphereCol1.intersectSphericalCollider(sphereCol2);
		IntersectionData sphere1IntersectSphere3 = sphereCol1.intersectSphericalCollider(sphereCol3);
		IntersectionData sphere1IntersectSphere4 = sphereCol1.intersectSphericalCollider(sphereCol4);
		
		assert(sphere1IntersectSphere2.isIntersected() == false);
		assert(sphere1IntersectSphere2.getDistance() == 1.0f);

		assert(sphere1IntersectSphere3.isIntersected() == false);
		assert(sphere1IntersectSphere3.getDistance() == 0.0f);

		assert(sphere1IntersectSphere4.isIntersected() == true);
		assert(sphere1IntersectSphere4.getDistance() == 1.0f);
	}
}

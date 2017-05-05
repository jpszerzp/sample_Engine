package com.base.engine.physics;
import com.base.engine.core.MathUtil;
import com.base.engine.core.Vector3f;

/** 
 * @author p
 * Collider with shape of bounded plane.
 * Assumes the plane has a "thickness".
 */
public class PlaneCollider extends Collider {
	private Vector3f center;
	private Vector3f normal;	// already normalized
	private float distance;	
	
	public PlaneCollider (Vector3f n, float d) {
		super(ColliderType.TYPE_PLANE);
		normal = n;
		distance = d;
		
		// users can input unnormalized normals, do it here
		// further normalizations does not cause problems
		normalize();
		
		center = normal.Mul(distance);
	}
	
	public void normalize () {
		normal = normal.Div(normal.Length());
	}
	
	public void worldNormalize () {
		distance /= normal.Length();
		normalize();
		center = normal.Mul(distance);
	}
	
	/**
	 * Translate the plane collider wrt center.
	 * Change distance and decide true center.
	 */
	public void translate (Vector3f translation) {
		Vector3f tempCenter = center.Add(translation);
		distance = tempCenter.Dot(normal);
		center = normal.Mul(distance);
	}
	
	/**
	 * Get center of plane.
	 */
	public Vector3f getCenter() { return center; }
	
	/**
	 * Returns intersection data with the other spherical collider.
	 * @param other the intersected spherical collider
	 * @return intersection data from collision
	 */
	public IntersectionData intersectSphericalCollider (SphericalCollider other) {
		// vertical distance from plane to center of sphere collider
		float distanceToSphereCenter = Math.abs(normal.Dot(other.getCenter()) + (-distance));

		// vertical distance from plane to surface of sphere collider
		float distanceToSphereSurface = distanceToSphereCenter - other.getRadius();
		
		// create intersection data given above
		return new IntersectionData (distanceToSphereSurface < 0, normal.Mul(distanceToSphereSurface));
	}
	
	public IntersectionData intersectBoxCollider (BoxCollider other) {
		float distanceToBoxCenter = Math.abs(normal.Dot(other.getCenter()) + (-distance));
		float distanceToBoxSurface = distanceToBoxCenter - other.getHeight() / 2;
		return new IntersectionData (distanceToBoxSurface < 0, normal.Mul(distanceToBoxSurface));
	}
	
	public IntersectionData intersectPlaneCollider (PlaneCollider other) {
		float projection = normal.Dot(other.getNormal());
		boolean parallel = (projection == 1 || projection == -1);
		return new IntersectionData (!parallel, MathUtil.up);
	}
	
	public Vector3f getNormal () { return normal; }
	public float getDistance () { return distance; }
	
	/**
	 * Test code separated from rendering engine.
	 */
	public static void test () {
		SphericalCollider sphere1 = new SphericalCollider (new Vector3f(0.0f, 0.0f, 0.0f), 1.0f);
		SphericalCollider sphere2 = new SphericalCollider (new Vector3f(0.0f, 3.0f, 0.0f), 1.0f);
		SphericalCollider sphere3 = new SphericalCollider (new Vector3f(0.0f, 0.0f, 2.0f), 1.0f);
		SphericalCollider sphere4 = new SphericalCollider (new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);

		PlaneCollider plane1 = new PlaneCollider (new Vector3f(0.0f, 1.0f, 0.0f), 0.0f);
		
		IntersectionData plane1IntersectSphere1 = plane1.intersectSphericalCollider(sphere1);
		IntersectionData plane1IntersectSphere2 = plane1.intersectSphericalCollider(sphere2);
		IntersectionData plane1IntersectSphere3 = plane1.intersectSphericalCollider(sphere3);
		IntersectionData plane1IntersectSphere4 = plane1.intersectSphericalCollider(sphere4);

		assert(plane1IntersectSphere1.isIntersected() == true);
		assert(plane1IntersectSphere1.getDistance() == 1.0f);

		assert(plane1IntersectSphere2.isIntersected() == false);
		assert(plane1IntersectSphere2.getDistance() == 2.0f);

		assert(plane1IntersectSphere3.isIntersected() == true);
		assert(plane1IntersectSphere3.getDistance() == 1.0f);
		
		assert(plane1IntersectSphere4.isIntersected() == true);
		assert(plane1IntersectSphere4.getDistance() == 1.0f);
	}
}

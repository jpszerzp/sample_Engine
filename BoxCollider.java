package com.base.engine.physics;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.base.engine.core.MathUtil;
import com.base.engine.core.Vector3f;
import com.base.game.TestGame;

/**
 * @author p
 * Collider with shape of box.
 */
public class BoxCollider extends Collider {
	private final static Logger logger = Logger.getLogger(TestGame.class.getName());
	
	private Vector3f closeEnd;
	private float depth;	// in z direction
	private float height;	// in y direction
	private float width;	// in x direction
	private Vector3f center;
	
	private Quaternion orientation;
	private Transform localTransform;
	
	public BoxCollider (Vector3f end, float d, float h, float w) {
		super(ColliderType.TYPE_BOX);
		this.closeEnd = end;
		this.depth = d;
		this.height = h;
		this.width = w;
		
		Vector3f halfDepthVector = new Vector3f (0.0f, 0.0f, depth / 2);
		Vector3f halfHeightVector = new Vector3f (0.0f, height / 2, 0.0f);
		Vector3f halfWidthVector = new Vector3f (width / 2, 0.0f, 0.0f);
		center = closeEnd.Add(halfDepthVector).Add(halfHeightVector).Add(halfWidthVector);
	}
	
	public Vector3f getCenter () { return center; }
	public void translate (Vector3f translation) { 
		center = center.Add(translation); 
		closeEnd = closeEnd.Add(translation);
	}
	public Vector3f getCloseEnd () { return closeEnd; }
	public float getDepth () { return depth; }
	public float getHeight () { return height; }
	public float getWidth () { return width; }
	
	public IntersectionData intersectCollider (Collider other) {
		IntersectionData res = new IntersectionData();
		
		switch (other.getType()) {
		case TYPE_BOX:
			BoxCollider boxCol = (BoxCollider)other;
			Vector3f copyFarEnd = boxCol.getCloseEnd().Add(new Vector3f(0.0f, 0.0f, boxCol.getDepth()))
					.Add(new Vector3f(0.0f, boxCol.getHeight(), 0.0f))
					.Add(new Vector3f(boxCol.getWidth(), 0.0f, 0.0f));
			Vector3f selfFarEnd = closeEnd.Add(new Vector3f(0.0f, 0.0f, depth))
					.Add(new Vector3f(0.0f, height, 0.0f))
					.Add(new Vector3f(width, 0.0f, 0.0f));
			Vector3f displacement1 = closeEnd.Sub(copyFarEnd);
			Vector3f displacement2 = boxCol.getCloseEnd().Sub(selfFarEnd);
			Vector3f displacement = displacement1.Length() >= displacement2.Length() ? displacement2 : displacement1; 
			res = new IntersectionData (displacement.Dot(MathUtil.up) < 0, displacement);
			break;
		case TYPE_SPHERE:
			// TODO:¡¡Box collider intersects with sphere collider
			logger.log(Level.WARNING, "Collision not implemented between BoxCollider and SphericalCollider; BoxCollider, intersectCollider(Collider other)");
			res = null;
			break;
		case TYPE_PLANE:
			PlaneCollider planeCol = (PlaneCollider)other;
			float distanceToPlane = Math.abs(center.Dot(planeCol.getNormal()) + (-planeCol.getDistance()));
			float surfaceDistanceToPlane = distanceToPlane - height / 2;
			res = new IntersectionData (surfaceDistanceToPlane < 0, planeCol.getNormal().Mul(surfaceDistanceToPlane));
			break;
		default:
			logger.log(Level.WARNING, "Collider type unknown; BoxCollider, intersectCollider(Collider other)");
			break;
		}
		
		return res;
	}
}

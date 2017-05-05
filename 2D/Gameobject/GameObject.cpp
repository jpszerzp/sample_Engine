#include "GameObject.h"

namespace johnengine
{
	namespace go
	{
		GameObject::GameObject(graphics::Renderable2D* renderable, Body* body) 
			: m_renderable(renderable), m_body(body)
		{
			
		}

		GameObject::~GameObject()
		{
			delete m_renderable;
			delete m_body;
		}

		maths::Vec3 GameObject::fromBodyToRenderable() const
		{
			Mat22 rot(m_body->rotation);
			Vec2 halfSize = 0.5f * m_body->width;

			Vec2 rotated = rot * Vec2(-halfSize.x, -halfSize.y);
			maths::Vec3 vec3Rotated = maths::Vec3(rotated.x, rotated.y, 0);

			Vec2 center = m_body->position;
			maths::Vec3 vec3Center = maths::Vec3(center.x, center.y, 0);

			// Bottom left corner is considered as position of renderable 
			return vec3Center + vec3Rotated;
		}

		maths::Vec3 GameObject::fromBodyToRenderable_v2() const
		{
			Mat22 rot(m_body->rotation);
			Vec2 halfSize = 0.5f * m_body->width;

			Vec2 rotated = rot * Vec2(-halfSize.x, halfSize.y);
			maths::Vec3 vec3Rotated = maths::Vec3(rotated.x, rotated.y, 0);

			Vec2 center = m_body->position;
			maths::Vec3 vec3Center = maths::Vec3(center.x, center.y, 0);

			// upper left corner
			return vec3Center + vec3Rotated;
		}

		maths::Vec3 GameObject::fromBodyToRenderable_v3() const
		{
			Mat22 rot(m_body->rotation);
			Vec2 halfSize = 0.5f * m_body->width;

			Vec2 rotated = rot * Vec2(halfSize.x, halfSize.y);
			maths::Vec3 vec3Rotated = maths::Vec3(rotated.x, rotated.y, 0);

			Vec2 center = m_body->position;
			maths::Vec3 vec3Center = maths::Vec3(center.x, center.y, 0);

			// upper right
			return vec3Center + vec3Rotated;
		}

		maths::Vec3 GameObject::fromBodyToRenderable_v4() const
		{
			Mat22 rot(m_body->rotation);
			Vec2 halfSize = 0.5f * m_body->width;

			Vec2 rotated = rot * Vec2(halfSize.x, -halfSize.y);
			maths::Vec3 vec3Rotated = maths::Vec3(rotated.x, rotated.y, 0);

			Vec2 center = m_body->position;
			maths::Vec3 vec3Center = maths::Vec3(center.x, center.y, 0);

			// bottom right
			return vec3Center + vec3Rotated;
		}
	}
}
#pragma once

#include "Renderable2D.h"
#include "Body.h"

namespace johnengine
{
	namespace go
	{
		class GameObject
		{
		private:
			graphics::Renderable2D* m_renderable;
			Body* m_body;

		public:
			GameObject(graphics::Renderable2D* renderable, Body* body);
			~GameObject();
			inline graphics::Renderable2D* getSprite() const { return m_renderable; }
			inline Body* getBody() const { return m_body; }

			maths::Vec3 fromBodyToRenderable() const;
			maths::Vec3 fromBodyToRenderable_v2() const;
			maths::Vec3 fromBodyToRenderable_v3() const;
			maths::Vec3 fromBodyToRenderable_v4() const;
		};
	}
}
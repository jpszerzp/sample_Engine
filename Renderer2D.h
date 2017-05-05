#pragma once

#include <vector>
#include <GL/glew.h>
#include "Math.h"

namespace johnengine {
	namespace graphics
	{
		class Renderable2D;

		class Renderer2D
		{
		protected:
			std::vector<maths::Mat4> m_transformationstack;
			const maths::Mat4* m_transformationback;	// cache the back matrix for performance
		protected:
			Renderer2D()
			{
				m_transformationstack.push_back(maths::Mat4::identity());
				m_transformationback = &m_transformationstack.back();
			}

		public:
			void push(const maths::Mat4& matrix, bool override = false)
			{
				if (override)
				{
					m_transformationstack.push_back(matrix);
				}
				else
				{
					m_transformationstack.push_back(m_transformationstack.back() * matrix);

				}
				m_transformationback = &m_transformationstack.back();
			}

			void pop()
			{
				// TODO: add log system!
				if (m_transformationstack.size() > 1)
					m_transformationstack.pop_back();

				m_transformationback = &m_transformationstack.back();
			}

			virtual void begin() {}
			virtual void submit(const Renderable2D* renderable) = 0;
			virtual void drawString(const std::string& text, const maths::Vec3& position, const maths::Vec4& color) { }
			virtual void end() {}
			virtual void flush() = 0;
			virtual void flush(bool debug) = 0;
		};
	}
}
#pragma once

#include <GL/glew.h>

namespace johnengine {
	namespace graphics {

		class IndexBuffer
		{
		private:
			GLuint m_bufferID;
			GLuint m_Count;

		public:
			IndexBuffer(GLushort* data, GLsizei count);
			IndexBuffer(GLuint* data, GLsizei count);
			~IndexBuffer();
			void bind() const;
			void unbind() const;

			GLuint getCount() const { return m_Count; }
		};

	}
}
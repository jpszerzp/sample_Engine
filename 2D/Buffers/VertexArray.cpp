#include "VertexArray.h"

namespace johnengine
{
	namespace graphics
	{

		VertexArray::VertexArray()
		{
			glGenVertexArrays(1, &m_ArrayID);
		}

		VertexArray::~VertexArray()
		{
			for (int i = 0; i < m_Buffers.size(); i++)
			{
				delete m_Buffers[i];
			}

			glDeleteVertexArrays(1, &m_ArrayID);
		}

		// see location in shade files, the shaderindex
		// should allow shader fetch the buffer at given index
		void VertexArray::addBuffers(Buffer* buffer, GLuint indexforshader)
		{
			bind();
			buffer->bind();

			glEnableVertexAttribArray(indexforshader);
			glVertexAttribPointer(indexforshader,  buffer->getComponentCount(), GL_FLOAT, false, 0, 0);

			buffer->unbind();
			unbind();
		}

		void VertexArray::bind() const
		{
			glBindVertexArray(m_ArrayID);
		}

		void VertexArray::unbind() const
		{
			glBindVertexArray(0);
		}
	}
}

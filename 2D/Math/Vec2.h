#pragma once

#include <iostream>

namespace johnengine
{
	namespace maths
	{
		struct vec2
		{
			float x, y;

			vec2();
			vec2(const float& x, const float& y);
			inline void set(float x_, float y_) { x = x_; y = y_; }

			vec2& add(const vec2& other);
			vec2& subtract(const vec2& other);
			vec2& multiply(const vec2& other);
			vec2& divide(const vec2& other);

			friend vec2 operator+(vec2 left, const vec2& right);
			friend vec2 operator-(vec2 left, const vec2& right);
			friend vec2 operator*(vec2 left, const vec2& right);
			friend vec2 operator*(float scale, const vec2& vec);
			friend vec2 operator/(vec2 left, const vec2& right);
			//friend bool operator==(const Vec2& left, const Vec2& right);

			bool operator!=(const vec2& other);
			bool operator==(const vec2& other);
			vec2& operator+=(const vec2& other);
			vec2& operator-=(const vec2& other);
			vec2& operator*=(const vec2& other);
			vec2& operator/=(const vec2& other);

			friend std::ostream& operator<<(std::ostream& stream, const vec2& vector);
		};
	}
}
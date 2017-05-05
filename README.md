# sample_Engine

This is repo of some sample code of my personal game engine project. The repo starts with components from collision systems, and is subject to more released code as project grows.

The project supports 2D AND 3D features now accordingly to the bottom-up working pattern from the easier 2D world to 3D world. Please refer to respective folder for released features.

## Brief Doc
## 3D
### BoxCollider.cs
Box collider implementing Collider interface. Simulate behaviors of box-shape colliders in physics environment.

### SphericalCollider.cs
Sphere collider implementing Collider interface. Simulate behaviors of spherical colliders in physics environment.

### PlaneCollider.cs
Plane collider implementing Collider interface. Simulate behaviors of plane colliders in physics environment.

## 2D

### Fileutils.cpp
File utilities for importing information from files like shaders and general txt files.

### GameObject.cpp
Game Object abstraction of the game world. Each game object has encapsulation for rendering and physics component.

### Math.h (and the general math module)
Math support of the engine. Support manipulations on Vec2, 3 and 4, as well as general matrix operation. 

### Renderer2D.cpp 
2D renderer abstract class. The class is extended by fast batch renderer, naive shape-per-buffer renderer, and debugging wireframe renderer.

### VertexArray.cpp
The abstraction of vertex array object (VAO). Supports the batch-sending of vertices information to OpenGL buffer.

### IndexBuffer.cpp
Considering complexity and encapsulation of vertex data, this buffer respects extra info of vertices like colors and texture coord. By nature, this is another abstraction for OpenGL Index Buffer object (IBO).

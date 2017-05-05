#pragma once

#include <string>
#include <fstream>

namespace johnengine
{
	class Fileutils
	{
	public:
		static std::string c_read_file(const char* path)
		{
			FILE* file = fopen(path, "rt");
			fseek(file, 0, SEEK_END);
			unsigned long length = ftell(file);
			char* data = new char[length + 1];
			memset(data, 0, length + 1);
			fseek(file, 0, SEEK_SET);
			fread(data, 1, length, file);
			fclose(file);
			//data += '\0';

			std::string result(data);
			delete[] data;
			return result;
		}

		static std::string cpp_read_file(const std::string& path)
		{
			std::ifstream ifs(path.c_str());
			std::string content(
				std::istreambuf_iterator<char>(ifs.rdbuf()),
				std::istreambuf_iterator<char>()
			);

			//content += '\0';

			return content;
		}
	};
}
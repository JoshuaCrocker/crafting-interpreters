# Makefile adapted from https://github.com/google/googletest/blob/release-1.8.1/googletest/make/Makefile

CC = gcc

# Root of Google Test, relative to this file
GTEST_DIR = googletest/googletest

# Where to find user code
USER_DIR = ../../src

# Set system rood to include dir
CPPFLAGS += -isystem $(GTEST_DIR)/include

# Flags passed to the C++ compiler
CXXFLAGS += -g -Wall -Wextra -pthread

GTEST_HEADERS = $(GTEST_DIR)/include/gtest/*.h \
				$(GTEST_DIR)/include/gtest/internal/*.h
GTEST_SRCS_ = $(GTEST_DIR)/src/*.cc $(GTEST_DIR)/src/*.h $(GTEST_HEADERS)


clean:
	rm -f gtest.a gtest_main.a *.o

gtest-all.o: $(GTEST_SRCS_)
	$(CXX) $(CPPFLAGS) -I$(GTEST_DIR) $(CXXFLAGS) -c $(GTEST_DIR)/src/gtest-all.cc

gtest_main.o: $(CTEST_SRCS_)
	$(CXX) $(CPPFLAGS) -I$(GTEST_DIR) $(CXXFLAGS) -c $(GTEST_DIR)/src/gtest_main.cc

gtest.a : gtest-all.o
	$(AR) $(ARFLAGS) $@ $^

gtest_main.a : gtest-all.o gtest_main.o
	$(AR) $(ARFLAGS) $@ $^

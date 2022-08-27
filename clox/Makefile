ifeq ($(OS),Windows_NT)
TARGET ?= clox.exe
TEST_TARGET ?= clox_test
else
TARGET ?= clox
TEST_TARGET ?= clox_test
endif


BUILD_DIR ?= ./build
SRC_DIR ?= ./src
TEST_DIR ?= ./test

SRCS := $(shell find $(SRC_DIR) -name *.c -or -name *.h) # Do we need .h?
TEST_SRCS := $(shell find $(TEST_DIR) -name *.c -or -name *.cpp -or -name *.h) # Do we need .h?
OBJS := $(SRCS:%=$(BUILD_DIR)/%.o)
DEPS := $(OBJS:.o=.d) # https://www.gnu.org/software/make/manual/make.html#Automatic-Prerequisites

GTEST_DIR ?= ./vendor/gtest/googletest/googletest
GTEST_HEADERS := $(shell find $(GTEST_DIR)/include -name *.h)
GTEST_SRCS := $(shell find $(GTEST_DIR)/src -name *.h -or -name *.cc)

INC_DIRS := $(shell find $(SRC_DIR) -type d)
INC_DIRS += $(GTEST_HEADERS)
INC_FLAGS := $(addprefix -I,$(INC_DIRS))

CC = g++

# compiler flags:
#  -g    adds debugging information to the executable file
#  -Wall turns on most, but not all, compiler warnings
CPPFLAGS ?= $(INC_FLAGS) -MMD -MP -g -Wall -isystem $(GTEST_DIR)/include

.PHONY: build_gtest
build_gtest:
	cd vendor/gtest && make gtest.a gtest_main.a

$(BUILD_DIR)/$(TARGET): $(SRCS) $(GTEST_HEADERS)
	$(MKDIR_P) $(BUILD_DIR)
	$(CC) $(CPPFLAGS) $(CFLAGS) -o $(BUILD_DIR)/$(TARGET) $(SRCS)

$(BUILD_DIR)/$(TEST_TARGET): $(BUILD_DIR)/$(TARGET) $(SRCS) $(TEST_SRCS) $(GTEST_HEADERS)
	$(MKDIR_P) $(BUILD_DIR)
	$(CC) $(CPPFLAGS) $(CFLAGS) -o $(BUILD_DIR)/$(TEST_TARGET) -c $(TEST_SRCS)

$(BUILD_DIR)/test: $(BUILD_DIR)/$(TARGET) $(BUILD_DIR)/$(TEST_TARGET) vendor/gtest/gtest_main.a
	$(CC) $(CPPFLAGS) $(CFLAGS) -pthread $^ -o $@

.PHONY: run
run:
	./build/clox.exe

.PHONY: clean
clean:
	$(RM) -r $(BUILD_DIR)

.PHONY: docs
docs:
	doxygen Doxyfile

-include $(DEPS)

MKDIR_P ?= mkdir -p

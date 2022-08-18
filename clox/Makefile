TARGET ?= clox.exe # Make it cross-platform?

BUILD_DIR ?= ./build
SRC_DIR ?= ./src

SRCS := $(shell find $(SRC_DIRS) -name *.c -or -name *.h) # Do we need .h?
OBJS := $(SRCS:%=$(BUILD_DIR)/%.o)
DEPS := $(OBJS:.o=.d) # https://www.gnu.org/software/make/manual/make.html#Automatic-Prerequisites

INC_DIRS := $(shell find $(SRC_DIRS) -type d)
INC_FLAGS := $(addprefix -I,$(INC_DIRS))

CC = gcc

# compiler flags:
#  -g    adds debugging information to the executable file
#  -Wall turns on most, but not all, compiler warnings
CPPFLAGS ?= $(INC_FLAGS) -MMD -MP -g -Wall

build: $(SRCS)
	$(MKDIR_P) $(BUILD_DIR)
	$(CC) $(CPPFLAGS) $(CFLAGS) -o $(BUILD_DIR)/$(TARGET) $(SRCS) 

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

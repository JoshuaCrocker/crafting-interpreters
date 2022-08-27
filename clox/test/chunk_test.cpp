#include <gtest/gtest.h>

#include "chunk.h"

TEST(clox, initChunk) {
    Chunk chunk;
    initChunk(&chunk);

    ASSERT_EQ(chunk.count, 0);
    ASSERT_EQ(chunk.capacity, 0);
    
    // FIXME ASSERT_EQ(chunk.code, NULL);
    // FIXME ASSERT_EQ(chunk.lines, NULL);
}

TEST(clox, freeChunk) {
    Chunk chunk;
    initChunk(&chunk);

    // Add data to chunk...
    int constant = addConstant(&chunk, 1.2);
    writeChunk(&chunk, OP_CONSTANT, 123);
    writeChunk(&chunk, constant, 123);

    // ...then free
    freeChunk(&chunk);

    ASSERT_EQ(chunk.count, 0);
    ASSERT_EQ(chunk.capacity, 0);
    
    // FIXME ASSERT_EQ(chunk.code, NULL);
    // FIXME ASSERT_EQ(chunk.lines, NULL);
}

TEST(clox, writeChunk) {
    Chunk chunk;
    initChunk(&chunk);

    // Add data to chunk...
    int constant = addConstant(&chunk, 1.2);
    writeChunk(&chunk, OP_CONSTANT, 123);
    writeChunk(&chunk, constant, 123);

    ASSERT_EQ(chunk.count, 2);
    ASSERT_EQ(chunk.capacity, 8);
    // FIXME ASSERT_EQ(chunk.code, NULL);
    // FIXME ASSERT_EQ(chunk.lines, NULL);
}

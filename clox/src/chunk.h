#ifndef CHUNK_H_
#define CHUNK_H_

#include "common.h"

/**
 * @brief OpCodes
 * 
 * Represent an instruction for the Virtual Machine to execute. 
 */
typedef enum {
    OP_RETURN,
} OpCode;

/**
 * @brief Chunk Data Structure.
 * 
 * Represents a dynamic array, which will be automatically sized as data is 
 * added.
 * 
 * Holds the:
 * * `count`: Number of bytes within the chunk.
 * * `capacity`: Spaces available within the block of memory reserved for this chunk.
 * * `code`: The bytecode contained within the chunk.
 */
typedef struct {
    int count;
    int capacity;
    uint8_t* code;
} Chunk;

/**
 * @brief Initialise an empty chunk.
 * 
 * @param chunk The chunk.
 */
void initChunk(Chunk* chunk);

/**
 * @brief Free up the resources used by the given chunk.
 * 
 * @param chunk The chunk to free up.
 */
void freeChunk(Chunk* chunk);

/**
 * @brief Write a byte to the end of the given chunk.
 * 
 * @param chunk The chunk to write to.
 * @param byte The byte to add to the end of the chunk.
 */
void writeChunk(Chunk* chunk, uint8_t byte);

#endif // CHUNK_H_

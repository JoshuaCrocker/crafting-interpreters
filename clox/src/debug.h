#ifndef DEBUG_H_
#define DEBUG_H_

#include "chunk.h"

/**
 * @brief Write a chunk and its data to STDOUT.
 * 
 * @param chunk Chunk to disassemble.
 * @param name Name used to identify the chunk.
 */
void disassembleChunk(Chunk* chunk, const char* name);

/**
 * @brief Write the OpCode instruction to STDOUT.
 * 
 * @param chunk Chunk to read from.
 * @param offset Offset from the start of the chunk at which the instruction begins.
 * @return int Offset from the start of the chunk to the next instruction.
 */
int disassembleInstruction(Chunk* chunk, int offset);

#endif // DEBUG_H_

#ifndef VM_H_
#define VM_H_

#include "chunk.h"
#include "value.h"

#define STACK_MAX 256

typedef struct {
    Chunk* chunk;
    uint8_t* ip;
    Value stack[STACK_MAX];
    Value* stackTop;
} VM;

typedef enum {
    INTERPRET_OK,
    INTERPRET_COMPILE_ERROR,
    INTERPRET_RUNTIME_ERROR,
} InterpretResult;

void initVM();
void freeVM();

/**
 * @brief Interpret a chunk of bytecode.
 * 
 * Further reading to make this method more efficient:
 * * Direct Threaded Code
 * * Jump Table
 * * Computed goto
 * 
 * @param chunk 
 * @return InterpretResult 
 */
InterpretResult interpret(Chunk* chunk);

/**
 * @brief Place a value on top of the VM stack.
 * 
 * @param value The value to place on the stack.
 */
void push(Value value);

/**
 * @brief Retrieve the value on top of the VM stack.
 * 
 * @return Value The value.
 */
Value pop();

#endif // VM_H_

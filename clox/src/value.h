#ifndef VALUE_H_
#define VALUE_H_

#include "common.h"

/**
 * @brief Value Type.
 * 
 * Abstracts out how Lox values are concretely represented in C. This can be
 * changed if we need to update how values are represnted without needing to
 * update code elsewhere. 
 */
typedef double Value;

/**
 * @brief ValueArray Data Structure.
 * 
 * Represents a dynamic array, which will be automatically sized as data is 
 * added.
 * 
 * Holds the:
 * * `count`: Number of values within the value array.
 * * `capacity`: Spaces available within the block of memory reserved for this value array.
 * * `code`: The values contained within the value array.
 */
typedef struct {
    int capacity;
    int count;
    Value* values;
} ValueArray;

/**
 * @brief Initialise an empty array.
 * 
 * @param array The array.
 */
void initValueArray(ValueArray* array);

/**
 * @brief Free up the resources used by the given array.
 * 
 * @param array The array to free up.
 */
void freeValueArray(ValueArray* array);

/**
 * @brief Write a value to the end of the given array.
 * 
 * @param array The array to write to.
 * @param value The value to add to the end of the array.
 */
void writeValueArray(ValueArray* array, Value value);

void printValue(Value value);

#endif // VALUE_H_

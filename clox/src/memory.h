#ifndef MEMORY_H_
#define MEMORY_H_

#define GROW_CAPACITY(capacity) \
    ((capacity) < 8 ? 8 : (capacity) * 2)

#define GROW_ARRAY(type, pointer, oldCount, newCount) \
    (type*)reallocate(pointer, sizeof(type) * (oldCount), sizeof(type) * (newCount))

#define FREE_ARRAY(type, pointer, oldCount) \
    reallocate(pointer, sizeof(type) * (oldCount), 0)

/**
 * @brief Reallocate memory.
 * 
 * Used as the single point for all dynamic memory management (to allow for
 * garbage collection later).
 * 
 * The oldSize and newSize arguments determine which operation the reallocate
 * method will perform:
 * 
 *     | oldSize  | newSize   | Operation                   |
 *     +----------+-----------+-----------------------------+
 *     | 0        | Non-zero  | Allocate new block.         |
 *     | Non-zero | 0         | Free allocation.            |
 *     | Non-zero | > oldSize | Shrink existing allocation. |
 *     | Non-zero | < oldSize | Grow existing allocation.   |
 * 
 * @param pointer 
 * @param oldSize 
 * @param newSize 
 * @return void* 
 */
void* reallocate(void* pointer, size_t oldSize, size_t newSize);

#endif // MEMORY_H_

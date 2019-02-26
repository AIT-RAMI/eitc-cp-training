import random
import sys

size = int(sys.argv[1])
print(size)
for _ in range(size):
  print(random.randint(-1000000, 1000000))

import os

add = os.fsencode("D:/Coding/Java/Scint/src/main/resources/media/data")
for file in os.listdir(add):
    filename = os.fsdecode(file)
    print(filename)

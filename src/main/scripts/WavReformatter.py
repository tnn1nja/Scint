import os
add = "D:/Coding/Java/Scint/src/main/resources/media/data"
os.chdir(add)
for filename in os.listdir(add):
    basename = filename.replace(".wav","")
    if filename.endswith(".wav"):
        os.system("ffmpeg -i " + filename +
                  " -vn -ar 44100 -ac 2 -b:a 192k " + basename + ".mp3")
        os.system("del /f " + filename)
        
        

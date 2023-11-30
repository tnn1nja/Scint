import os
add = "D:/Coding/Java/Scint/src/main/resources/snd"
os.chdir(add)
for filename in os.listdir(add):
    basename = filename.replace(".mp3","")
    if filename.endswith(".mp3"):
        os.system("ffmpeg -i " + filename +
                  " -acodec pcm_u8 -ar 22050 " + basename + ".wav")
        os.system("del /f " + filename)
        
        

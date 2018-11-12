import numpy as np
from PIL import Image

for i in range(0,10):
        filename = str(i) + '.png'
        im = Image.open(filename)
        data = np.array(im)

        r1, g1, b1 = 0, 0, 0 # Original value - leave as black unless you're changing the filename above
        r2, g2, b2 = 150, 20, 20 # Value that we want to replace it with

        red, green, blue = data[:,:,0], data[:,:,1], data[:,:,2]
        mask = (red == r1) & (green == g1) & (blue == b1)
        data[:,:,:3][mask] = [r2, g2, b2]

        im = Image.fromarray(data)
        im.save('deepRed' + filename) # Change the string in this line to the replacement color

# Atlas Generator

Texture Atlas Generator

Program where you can create texture atlas from images. Program gives you final png and json file. In json there'll be all coordinates of imagaes.
Program takes all images from choosen directory even from subfolders.

Algorythm source: https://blackpawn.com/texts/lightmaps/default.html

Usage: 
AtlasGenerator "name" "width" "height" "padding" "border" "unitCoordinates" 'directory"

- "name": Name of the texture atlas file
- "width": Atlas width
- "height": Atlas height
- "padding": Padding between images in the final texture atlas.
- "border": Every image will have a 1 pixel border"
- "unitCoordinates": Coordinates will be saved to atlas txt file in 0..1 range instead of 0..width, 0..height range
- "directory": Path to directory with images.
  
Example: 
AtlasGenerator atlas 2048 2048 5 1 1 images

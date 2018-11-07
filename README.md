This is a collection of pseudo UGens that don't warrant their own repository, but that I tend to have to write over and over again. This will hopefully save some time in creating future SynthDefs, and allow focus on the unique aspects of a sound.

## Collection Descriptions
As this repo has the potential to get very messy, use these guidelines to place UGens in folders. If there are more than a few UGens in a folder, split em up.

- Audio Rate: Place UGens that return audio rate signals here.
  - Effects: UGens that take an existing sound and transform it. The original sound is still discernable in most cases.
  - Sources: UGens that create new sound based off of a control rate source, or that transform an audio rate source so much that it is unrecognizable.
  - Utilities: UGens that perform simple but code-intensive tasks that are typical of a plurality of SynthDefs. NOT FUN UGENS.
- Control Rate: Place UGens that return control rate signals here.
  - Algorithmic: UGens that follow some kind of algorithm to provide an output. All UGens technically use algorithms, but these algorithms should be fairly transparent.
  - Lfo: Related to AlgorithmicUGens, but there will probably be enough of these to stick em in their own folder so let's just start with them in their own folder.
  - Triggers: Place UGens that return triggers here.

## Style Guidelines
- Each class should have its own file, and the filename should match the class (so you can easily see what UGens you've already written - none of that typical SuperCollider nonsense where you wrap twenty UGens into a single file [although more power to those that do, I'm sure they've figured a way out to organize it]).
- All class names should be prefixed by "Dz" to prevent collisions, as I'm sure many other people have created similar collections.
- Each class should be a kindOf the UGen superclass.
- !IMPORTANT! A class that extends an existing class should be in its own file with the same name as the class it extends. Why are you hitting yourself?? Do not do this to yrself.
- UGens that accept symbols as arguments should have multiple different overlapping symbols that will work. For example: \\dir, \\direction, \\directional should all map to the same value.
- All effects types of UGens should follow the same argument pattern: in, mix, [channels], [everything else]

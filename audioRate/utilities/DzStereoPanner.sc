/**
 * A panner designed for stereo inputs and outputs.
 * All panning goes from [-1..1] but there are two algorithms:
 * 	 \direction: -1 pans both sides left, 1 pans both sides right. 0 is
 *	   unaffected.
 *   \rotate: -1 is unaffected, 0 pans both sides to the center, 1 reverses
 *	   sides.
 **/
DzStereoPanner : UGen {
	*ar {
		arg in, pan = 0, panAlgo = \direction;
		var audio = in;
		if (audio.asArray().size() != 2) {
			"DzStereoPanner requires a stereo audio input.".warn;
		};

		// I want the symbol that works to be guessable.
		panAlgo = switch(panAlgo,
			\direction, {0},
			\dir, {0},
			\directional, {0},
			\rotate, {1}
			\rot, {1}
			{ panAlgo }
		);
		pan = Select.kr(panAlgo, [[-1, 1] + (pan * 2), [-1, 1] * pan]).clip(-1, 1);
		audio = LinPan2.ar(audio[0], pan[0]) + LinPan2.ar(audio[1], pan[1]);
		^ audio;
	}
}

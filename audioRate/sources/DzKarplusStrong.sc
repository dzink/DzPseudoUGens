/**
 * Implements a Karplus-Strong algorithm to make a string-like sound. Does the
 * heavy lifting of making the sound sample-accurate and filtering out unusable frequencies.
 */
DzKarplusStrong : UGen {
	*ar {
		arg trigger, noise = nil, freq = 440, impulseLength = 0.01, decay = 1;
		var period, impulse, audio;
		noise = noise ? PinkNoise.ar;
		period = freq.clip(10, DzNyquist.ir).reciprocal - SampleDur.ir;
		impulse = noise * EnvGen.kr(Env([0, 0, 1], [0, impulseLength]));
		audio = CombC.ar(impulse, 0.1, period, decay);
		^ audio;
	}
}

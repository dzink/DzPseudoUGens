/**
 * Like DzDetune, only for stereo signals, where channel 0 is detuned down and
 * channel 1 is detuned up.
 */
DzDetune2 : DzDetune {
	*kr {
		arg base = 0, detune = 0.1, trigger = 1;
		^ base + (DzDetune2.offset(detune, trigger) * [-1, 1]);
	}
}

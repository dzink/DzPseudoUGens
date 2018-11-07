+ Object {

	/**
	 * Given a number or UGen, detunes it by a bit.
	 */
	detune {
		arg detune = 0.1, trigger = 1;
		^ DzDetune.kr(this, detune, trigger);
	}

	/**
	 * Given a number or UGen, detunes it by a bit (stereo).
	 */
	detune2 {
		arg detune = 0.1, trigger = 1;
		^ DzDetune2.kr(this, detune, trigger);
	}
}

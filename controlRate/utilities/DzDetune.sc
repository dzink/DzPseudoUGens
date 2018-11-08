/**
 * Adds a linear detuning factor to a base number.
 */
DzDetune : UGen {
	*kr {
		arg base = 0, detune = 0.1, trigger = 1;
		^ base + DzDetune.offset(detune, trigger);
	}

	/**
	 * Returns a detune amount scaled to [-1..-0.5, 0.5..1]
	 */
	*offset {
		arg detune = 0.1, trigger = 1;
		var random;
		random = TRand.kr(0, 1, trigger);
		random = Select.kr(BinaryOpUGen('<', random, 0.5), [random, random - 1]);
		^ detune * random;
	}
}

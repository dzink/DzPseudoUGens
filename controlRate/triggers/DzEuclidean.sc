/**
 * Euclidean rhythm generator.
 */
DzEuclidean : UGen {
	*kr {
		arg impulse, reset = 0, hits = 3, length = 8, offset = 0;
		var trigger;
		trigger = Stepper.kr(impulse, reset, 1, length) % length + offset;
		trigger = trigger % (length / hits);
		trigger = BinaryOpUGen('<', trigger, 1);
		trigger = trigger * impulse;
		^ trigger;
	}
}

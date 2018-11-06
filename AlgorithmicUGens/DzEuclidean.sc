/**
 * Euclidean rhythm generator.
 */
DzEuclidean : UGen {
	*kr {
		arg impulse, hits, length, offset;
		var trigger;
		trigger = Stepper.kr(impulse, 0, 1, length) % length + offset;
		trigger = trigger % (length / hits);
		trigger = BinaryOpUGen('<', trigger, 1);
		trigger = trigger * impulse;
		^ trigger;
	}
}

DzLoopPhaseDetector {
	*kr {
		arg phase, length = 1, width = 0.1, center = 0.5, reverse = false;
		var trig, loc;
		phase.rate.postln;
		if (phase.rate == \audio) {
			phase = A2K.kr(phase);
		};
		^ DzLoopDetector.detect(phase, length, width, center, reverse.asBoolean());
	}
}

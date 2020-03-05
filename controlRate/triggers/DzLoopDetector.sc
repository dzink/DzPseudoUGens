/**
 * Sends a trigger when a phasor has looped. To debounce, the phasor must pass the center to be reset.
 */
DzLoopDetector {
	*kr {
		arg phase, length = 1, width = 0.1, center = 0.5, reverse = false;
		var trig, loc;
		phase.rate.postln;
		if (phase.rate == \audio) {
			phase = A2K.kr(phase);
		};
		#trig, loc = DzLoopDetector.detect(phase, length, width, center, reverse.asBoolean());
		^ trig;
	}

	*detect {
		arg phase, length = 1, width = 0.1, center = 0.5, reverse = false;
		var loc, startTrig, endTrig, centerTrig, loopTrig;
		loc = phase / length % 1;
		startTrig = BinaryOpUGen('<', loc, width);
		centerTrig = BinaryOpUGen('<', abs(loc - center), width);
		if (reverse) {
			startTrig = max(SetResetFF.kr(startTrig, centerTrig), 0);
			endTrig = BinaryOpUGen('>', loc, 1 - width);
			endTrig = max(SetResetFF.kr(endTrig, centerTrig), 0);
			loopTrig = startTrig * endTrig;
		} {
			loopTrig = max(SetResetFF.kr(startTrig, centerTrig), 0);
		};
		^ [loopTrig, loc];
	}
}

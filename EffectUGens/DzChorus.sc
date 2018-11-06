DzChorus : UGen {
	*ar {
		arg in, channels = 1, stages = 4, speed = 5, centerNote = 70, width = 12, walk = nil, decayTime = 0.1, spread = 1;
		var lfoWalk, lfoPhase, stageSpread, audio;
		audio = in;

		lfoWalk = if (walk.isNil().not(), {
			LFNoise1.kr(speed).range(walk.neg, walk) * pi;
		}, {
			0;
		});

		lfoPhase = (0..(channels - 1)).linlin(0, channels, 0, 2pi);

		stageSpread = if ((stages > 1), {
			(2pi * spread) / stages;
		}, {
			0;
		});

		stages.asInteger().do {
			arg i;
			var lfo, stagePhase, stageFreq, stagePeriod;
			stagePhase = lfoPhase + (i * stageSpread) + lfoWalk;
			lfo = SinOsc.kr(speed, stagePhase);
			stageFreq = (centerNote + lfo.range(width.neg, width)).midicps;
			stagePeriod = stageFreq.reciprocal - SampleDur.ir;
			audio = audio - CombC.ar(audio, 0.01, stagePeriod, decayTime);
		};
		^ audio;
	}
}

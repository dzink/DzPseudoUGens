/**
 * Multi-stage chorus.
 */
DzChorus : UGen {
	*ar {
		arg in, mix = 0.5, channels = 1, stages = 4, speed = 5, centerNote = 70, width = 12, walk = nil, decayTime = 0.01, spread = 1;
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
			lfo = if (speed.isNil.not) {
				SinOsc.kr(speed, stagePhase).range(width.neg, width);
			} {
				0;
			};
			stageFreq = (centerNote + lfo).clip(30, 130).midicps;
			stagePeriod = stageFreq.reciprocal - SampleDur.ir;
			audio = audio + CombC.ar(in, 0.01, stagePeriod, decayTime);
		};

		audio = audio / stages.sqrt;
		audio = SelectX.ar(mix, [in, audio]);
		^ audio;
	}
}

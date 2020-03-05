DzDrumEnv : UGen {
	*kr {
		arg trig = 1, attack = 0.1, sustain = 0.5, release = 0.1, dur = 1;
		var env = Env.new([0, 1, 1, 0], [attack, sustain, release]);
		env = EnvGen.ar(env, trig, timeScale: dur);
		^ env;
	}
}

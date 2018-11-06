DzPercuss : UGen {
	*ar {
		arg trigger, noise, decay = 0.1, bf1Freq = 210, bf1Decay = 1, bf1Volume = 1, bf2Freq = 700, bf2Decay = 1.5, bf2Volume = 1, hfFreq = 4200, hfDecay = 1.5, hfVolume = 1, oscFreq = 60, oscSweepFreq = 20, oscDecay = 1, oscSweepDecay = 0.2, oscVolume = 1;
		var env, bf1, bf2, hf, osc, oscNote, audio;
		env = Env([0.0, 1.0, 0.0], [0.0, decay]);
		audio = 0;
		bf1 = BPF.ar(noise, bf1Freq, 0.1, bf1Volume) * EnvGen.kr(env, trigger, timeScale: bf1Decay);
		bf2 = BPF.ar(noise, bf2Freq, 0.1, bf2Volume) * EnvGen.kr(env, trigger, timeScale: bf2Decay);
		hf = RHPF.ar(noise, hfFreq, 0.1, hfVolume) * EnvGen.kr(env, trigger, timeScale: hfDecay);
		oscNote = EnvGen.kr(env, trigger, timeScale: oscSweepDecay).linlin(0, 1, oscFreq, oscFreq + oscSweepFreq);
		osc = SinOsc.ar(oscNote.midicps, [0, pi], oscVolume) * EnvGen.kr(env, trigger, timeScale: oscDecay);
		^ bf1 + bf2 + hf + osc;
	}
}

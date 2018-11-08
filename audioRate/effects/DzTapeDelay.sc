/**
 * A tape style delay; high and low frequencies are filtered out on each loop.
 * @param regen
 *   The regeneration factor for each delay loop.
 * @param [loCut]
 *   A value in Hz at which to cut low frequencies.
 * @param [hiCut]
 *   A value in Hz at which to cut high frequencies (this is where all the
 *     analog-style magic happens, but is also slightly more CPU intensive).
 * @param [pong]
 *   An optional mix value between the result and the result shifted 1 array
 *     index to the left.
 */
DzTapeDelay : UGen {
	*ar {
		arg in, mix = 0.5, maxDelay = 0.2, delay = 0.2, regen = 0.5, loCut = nil, hiCut = nil, pong = nil;
		var audio = in;
		audio = DzFeedbackSandwich.ar({
			arg delayAudio;
			delayAudio = audio + (delayAudio * regen);
			delayAudio = Select.ar(delayPingPong, [delayAudio, delayAudio.rotate]);
			delayAudio = DelayC.ar(delayAudio, maxDelay, delay);
			if (loCut.isNil().not) {
				delayAudio = HPF.ar(delayAudio, loCut);
			};
			if (hiCut.isNil().not) {
				delayAudio = LPF.ar(delayAudio, hiCut);
			};
			if (pong.isNil().not) {
				delayAudio = SelectX.kr(pong, [delayAudio, delayAudio.rotate(1)]);
			};
			delayAudio;
		}, in.asArray().size());
		^ SelectX.ar(mix, [in, audio]);
	}
}

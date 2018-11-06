DzMultiLfo {
  *kr {
    arg speed = 1, phase = nil, width = nil, multi = nil, slew = nil, t_sync = 0, algo = 0, algos = [\saw];
    var lfo, lfo0, doubleLfo, random, dust, out;
    var waveforms = [];

    lfo = Phasor.kr(t_sync, ControlDur.ir * speed, 0, 1, 0);
    if (phase.isNil.not) {
      lfo = (lfo + phase) % 1;
    };
    if (width.isNil.not) {
      lfo = (lfo / max(0.0001, width)).clip(0, 1);
    };
    if (multi.isNil.not) {
      lfo = (lfo * max(1, multi)) % 1;
    };

    // A few different ranges to simplify calculations.
    // SuperCollider will drop any of these that aren't used
    // at compile time.
    lfo0 = lfo; // Range [0, 1]
    doubleLfo = lfo * 4 - 1; // Range [-1, 3]
    lfo = lfo * 2 - 1; // Range [-1, 1]
    dust = TRand.kr(-1, 1, Dust.kr(speed));
    random = TRand.kr(-1, 1, Slope.kr(lfo));

    algos.do {
      arg thisAlgo;
      var waveform = switch (thisAlgo,
        \saw, {
          lfo;
        },
        \square, {
          lfo.sign;
        },
        \pulse, {
          max(0, lfo.sign);
        },
        \sine, {
          SinOsc.kr(0, lfo * pi);
        },
        \cosine, {
          SinOsc.kr(0, (lfo + 0.5) * pi);
        },
        \triangle, {
          doubleLfo.fold(-1, 1);
        },
        \dust, {
          dust.asArray.at(0);
        },
        \stereoDust, {
          dust;
        },
        \random, {
          random.asArray.at(0);
        },
        \stereoRandom, {
          random;
        },
        {
          (thisAlgo ++ " not supported by DzMultiLfo").warn;
        }
      );
      waveforms = waveforms.add(waveform);
    };

    out = Select.kr(algo, waveforms);
    if (slew.isNil.not) {
      out = out.lag(slew);
    };
    ^ out;
  }
}

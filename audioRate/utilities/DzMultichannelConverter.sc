/**
 * Sane-ish multichannel converter.
 * Takes an input and maps it up or down to a number of channels.
 * @param float [-1..1] rotate
 *   Will rotate the output channels.
 * @param float [0..2] focus
 *   Determines how focused around the center the output should be. 0 will mix
 *   the input down to a single channel. 1 will spread the output across the
 *   full output spectrum, but will not wrap around (like Pan2). 2 will spread
 *   the output across the full output spectrum, and will wrap (like PanAz).
 * @param float width
 *   A scale for expanding or narrowing the width of each mapped channel. < 1
 *   will create some empty channels. > 1 will create overlap between channels.
 * @param float orientation
 *   @see SplayAz
 */
DzMultichannelConverter : UGen {
	*ar {
		arg channels, in, rotate = 0, focus = 1, width = 1, level = 1, orientation = 0.5, levelComp = true;
		var center, spread, inChannels;
		in = in.asArray();
		inChannels = in.size;
		spread = Select.kr(focus, [0, 0.5, (inChannels - 1 / inChannels)]);
		rotate = rotate + 1;
		center = rotate - (2 / channels);
		width = width * max(channels / inChannels, 2);

		in = SplayAz.ar(channels, in, spread: spread, level: level, width: width, center: center, orientation: orientation, levelComp: levelComp);
		^ in;
	}
}

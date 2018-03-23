package org.stevenlowes.tools.lifxcontroller.values

enum class Waveform(val id: Int) {
    SAWTOOTH(0),
    SINUSOID(1),
    HALF_SINE(2),
    TRIANGLE(3),
    PULSE(4);

    val binaryString: String = Integer.toBinaryString(id)
}

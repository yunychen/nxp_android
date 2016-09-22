/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Don't edit this file!  It is auto-generated by frameworks/rs/api/generate.sh.

package android.renderscript.cts;

import android.renderscript.Allocation;
import android.renderscript.RSRuntimeException;
import android.renderscript.Element;

import java.util.Arrays;

public class TestNativeAtan2pi extends RSBaseCompute {

    private ScriptC_TestNativeAtan2pi script;
    private ScriptC_TestNativeAtan2piRelaxed scriptRelaxed;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        script = new ScriptC_TestNativeAtan2pi(mRS);
        scriptRelaxed = new ScriptC_TestNativeAtan2piRelaxed(mRS);
    }

    public class ArgumentsFloatFloatFloat {
        public float inNumerator;
        public float inDenominator;
        public Target.Floaty out;
    }

    private void checkNativeAtan2piFloatFloatFloat() {
        Allocation inNumerator = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0x694fc108l, false);
        Allocation inDenominator = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0x853dc01l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            script.set_gAllocInDenominator(inDenominator);
            script.forEach_testNativeAtan2piFloatFloatFloat(inNumerator, out);
            verifyResultsNativeAtan2piFloatFloatFloat(inNumerator, inDenominator, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testNativeAtan2piFloatFloatFloat: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            scriptRelaxed.set_gAllocInDenominator(inDenominator);
            scriptRelaxed.forEach_testNativeAtan2piFloatFloatFloat(inNumerator, out);
            verifyResultsNativeAtan2piFloatFloatFloat(inNumerator, inDenominator, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testNativeAtan2piFloatFloatFloat: " + e.toString());
        }
    }

    private void verifyResultsNativeAtan2piFloatFloatFloat(Allocation inNumerator, Allocation inDenominator, Allocation out, boolean relaxed) {
        float[] arrayInNumerator = new float[INPUTSIZE * 1];
        Arrays.fill(arrayInNumerator, (float) 42);
        inNumerator.copyTo(arrayInNumerator);
        float[] arrayInDenominator = new float[INPUTSIZE * 1];
        Arrays.fill(arrayInDenominator, (float) 42);
        inDenominator.copyTo(arrayInDenominator);
        float[] arrayOut = new float[INPUTSIZE * 1];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 1 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inNumerator = arrayInNumerator[i];
                args.inDenominator = arrayInDenominator[i];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeNativeAtan2pi(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 1 + j], 0.0005)) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inNumerator: ");
                        appendVariableToMessage(message, args.inNumerator);
                        message.append("\n");
                        message.append("Input inDenominator: ");
                        appendVariableToMessage(message, args.inDenominator);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 1 + j]);
                        if (!args.out.couldBe(arrayOut[i * 1 + j], 0.0005)) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkNativeAtan2piFloatFloatFloat" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkNativeAtan2piFloat2Float2Float2() {
        Allocation inNumerator = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 2, 0x8853bce2l, false);
        Allocation inDenominator = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 2, 0xc19e58bl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            script.set_gAllocInDenominator(inDenominator);
            script.forEach_testNativeAtan2piFloat2Float2Float2(inNumerator, out);
            verifyResultsNativeAtan2piFloat2Float2Float2(inNumerator, inDenominator, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testNativeAtan2piFloat2Float2Float2: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            scriptRelaxed.set_gAllocInDenominator(inDenominator);
            scriptRelaxed.forEach_testNativeAtan2piFloat2Float2Float2(inNumerator, out);
            verifyResultsNativeAtan2piFloat2Float2Float2(inNumerator, inDenominator, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testNativeAtan2piFloat2Float2Float2: " + e.toString());
        }
    }

    private void verifyResultsNativeAtan2piFloat2Float2Float2(Allocation inNumerator, Allocation inDenominator, Allocation out, boolean relaxed) {
        float[] arrayInNumerator = new float[INPUTSIZE * 2];
        Arrays.fill(arrayInNumerator, (float) 42);
        inNumerator.copyTo(arrayInNumerator);
        float[] arrayInDenominator = new float[INPUTSIZE * 2];
        Arrays.fill(arrayInDenominator, (float) 42);
        inDenominator.copyTo(arrayInDenominator);
        float[] arrayOut = new float[INPUTSIZE * 2];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 2 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inNumerator = arrayInNumerator[i * 2 + j];
                args.inDenominator = arrayInDenominator[i * 2 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeNativeAtan2pi(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 2 + j], 0.0005)) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inNumerator: ");
                        appendVariableToMessage(message, args.inNumerator);
                        message.append("\n");
                        message.append("Input inDenominator: ");
                        appendVariableToMessage(message, args.inDenominator);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 2 + j]);
                        if (!args.out.couldBe(arrayOut[i * 2 + j], 0.0005)) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkNativeAtan2piFloat2Float2Float2" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkNativeAtan2piFloat3Float3Float3() {
        Allocation inNumerator = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 3, 0x2b1fa8e3l, false);
        Allocation inDenominator = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 3, 0xdef578c4l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            script.set_gAllocInDenominator(inDenominator);
            script.forEach_testNativeAtan2piFloat3Float3Float3(inNumerator, out);
            verifyResultsNativeAtan2piFloat3Float3Float3(inNumerator, inDenominator, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testNativeAtan2piFloat3Float3Float3: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            scriptRelaxed.set_gAllocInDenominator(inDenominator);
            scriptRelaxed.forEach_testNativeAtan2piFloat3Float3Float3(inNumerator, out);
            verifyResultsNativeAtan2piFloat3Float3Float3(inNumerator, inDenominator, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testNativeAtan2piFloat3Float3Float3: " + e.toString());
        }
    }

    private void verifyResultsNativeAtan2piFloat3Float3Float3(Allocation inNumerator, Allocation inDenominator, Allocation out, boolean relaxed) {
        float[] arrayInNumerator = new float[INPUTSIZE * 4];
        Arrays.fill(arrayInNumerator, (float) 42);
        inNumerator.copyTo(arrayInNumerator);
        float[] arrayInDenominator = new float[INPUTSIZE * 4];
        Arrays.fill(arrayInDenominator, (float) 42);
        inDenominator.copyTo(arrayInDenominator);
        float[] arrayOut = new float[INPUTSIZE * 4];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 3 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inNumerator = arrayInNumerator[i * 4 + j];
                args.inDenominator = arrayInDenominator[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeNativeAtan2pi(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j], 0.0005)) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inNumerator: ");
                        appendVariableToMessage(message, args.inNumerator);
                        message.append("\n");
                        message.append("Input inDenominator: ");
                        appendVariableToMessage(message, args.inDenominator);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 4 + j]);
                        if (!args.out.couldBe(arrayOut[i * 4 + j], 0.0005)) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkNativeAtan2piFloat3Float3Float3" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    private void checkNativeAtan2piFloat4Float4Float4() {
        Allocation inNumerator = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 4, 0xcdeb94e4l, false);
        Allocation inDenominator = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 4, 0xb1d10bfdl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            script.set_gAllocInDenominator(inDenominator);
            script.forEach_testNativeAtan2piFloat4Float4Float4(inNumerator, out);
            verifyResultsNativeAtan2piFloat4Float4Float4(inNumerator, inDenominator, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testNativeAtan2piFloat4Float4Float4: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            scriptRelaxed.set_gAllocInDenominator(inDenominator);
            scriptRelaxed.forEach_testNativeAtan2piFloat4Float4Float4(inNumerator, out);
            verifyResultsNativeAtan2piFloat4Float4Float4(inNumerator, inDenominator, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testNativeAtan2piFloat4Float4Float4: " + e.toString());
        }
    }

    private void verifyResultsNativeAtan2piFloat4Float4Float4(Allocation inNumerator, Allocation inDenominator, Allocation out, boolean relaxed) {
        float[] arrayInNumerator = new float[INPUTSIZE * 4];
        Arrays.fill(arrayInNumerator, (float) 42);
        inNumerator.copyTo(arrayInNumerator);
        float[] arrayInDenominator = new float[INPUTSIZE * 4];
        Arrays.fill(arrayInDenominator, (float) 42);
        inDenominator.copyTo(arrayInDenominator);
        float[] arrayOut = new float[INPUTSIZE * 4];
        Arrays.fill(arrayOut, (float) 42);
        out.copyTo(arrayOut);
        StringBuilder message = new StringBuilder();
        boolean errorFound = false;
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 4 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inNumerator = arrayInNumerator[i * 4 + j];
                args.inDenominator = arrayInDenominator[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeNativeAtan2pi(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j], 0.0005)) {
                    valid = false;
                }
                if (!valid) {
                    if (!errorFound) {
                        errorFound = true;
                        message.append("Input inNumerator: ");
                        appendVariableToMessage(message, args.inNumerator);
                        message.append("\n");
                        message.append("Input inDenominator: ");
                        appendVariableToMessage(message, args.inDenominator);
                        message.append("\n");
                        message.append("Expected output out: ");
                        appendVariableToMessage(message, args.out);
                        message.append("\n");
                        message.append("Actual   output out: ");
                        appendVariableToMessage(message, arrayOut[i * 4 + j]);
                        if (!args.out.couldBe(arrayOut[i * 4 + j], 0.0005)) {
                            message.append(" FAIL");
                        }
                        message.append("\n");
                        message.append("Errors at");
                    }
                    message.append(" [");
                    message.append(Integer.toString(i));
                    message.append(", ");
                    message.append(Integer.toString(j));
                    message.append("]");
                }
            }
        }
        assertFalse("Incorrect output for checkNativeAtan2piFloat4Float4Float4" +
                (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), errorFound);
    }

    public void testNativeAtan2pi() {
        checkNativeAtan2piFloatFloatFloat();
        checkNativeAtan2piFloat2Float2Float2();
        checkNativeAtan2piFloat3Float3Float3();
        checkNativeAtan2piFloat4Float4Float4();
    }
}

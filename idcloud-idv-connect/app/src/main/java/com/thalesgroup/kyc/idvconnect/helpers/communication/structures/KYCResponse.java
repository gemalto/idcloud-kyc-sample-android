/*
 * MIT License
 *
 * Copyright (c) 2020 Thales DIS
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * IMPORTANT: This source code is intended to serve training information purposes only.
 *            Please make sure to review our IdCloud documentation, including security guidelines.
 */

package com.thalesgroup.kyc.idvconnect.helpers.communication.structures;

import com.thalesgroup.kyc.idvconnect.helpers.util.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import androidx.annotation.NonNull;

/**
 * Verification backend response.
 */
public class KYCResponse implements Serializable {

    //region Definition

    private static final long serialVersionUID = 1231945774531720461L;
    private final String mMessage;
    private final String mType;
    private final int mCode;
    private KYCDocument mDocument = null;
    private KycFace mFace = null;
    private KycLivenessResult mLivenessResult = null;
    private KycEnhancedLivenessResult mEnhancedLivenevessResult = null;

    //endregion


    //region Life Cycle

    /**
     * Creates a new {@code KYCFailedVerification} instance.
     *
     * @param response Response received from verification server.
     * @throws JSONException If value not found in passed response.
     */
    public KYCResponse(@NonNull final JSONObject response) throws JSONException {
        mCode = JsonUtil.jsonGetInt(response, "code", -1);
        mMessage = JsonUtil.jsonGetString(response,"message", "Unknown");
        mType =  JsonUtil.jsonGetString(response, "type", "Unknown");

        // All objects are optional, we might not get them from server.
        if (response.has("object")) {
            final JSONObject object = response.getJSONObject("object");
            if (object.has("document")) {
                mDocument = new KYCDocument(object.getJSONObject("document"));
            }
            if (object.has("face")) {
                mFace = new KycFace(object.getJSONObject("face"));
            }
            if (object.has("livenessResult")) {
                mLivenessResult = new KycLivenessResult(object.getJSONObject("livenessResult"));
            }
            if (object.has("enhancedLiveness")) {
                mEnhancedLivenevessResult = new KycEnhancedLivenessResult(object.getJSONObject("enhancedLiveness"));
            }
        }
    }

    //endregion

    //region Public API

    /**
     * Gets the message - details about the request.
     *
     * @return Message.
     */
    public String getMessage() {
        return mMessage;
    }

    /**
     * Gets the message in a readable format.
     *
     * @return Message in readable format.
     */
    public String getMessageReadable() {
        if (mMessage == null) {
            return "";
        }

        // Messages looks like "[5eb47d75-71f7-4b36-a273-8ddfe7e985bc] Internal service error". Strip down first ID part.
        final String search = "] "; // NOPMD
        final int index = mMessage.indexOf(search);
        if (index != -1) {
            return mMessage.substring(index + search.length());
        } else {
            return mMessage;
        }
    }

    /**
     * Gets the document type.
     *
     *  Document type:
     *  <p><ul>
     *  <li> Passport
     *  <li> ID
     *  <li> Driving license.
     *  </ul><p>
     *
     * @return Document type.
     */
    public String getType() {
        return mType;
    }

    /**
     * Gets the result code.
     *
     * @return Result code.
     */
    public int getCode() {
        return mCode;
    }

    /**
     * Gets the {@code KYCDocument}.
     *
     * @return {@code KYCDocument}.
     */
    public KYCDocument getDocument() {
        return mDocument;
    }

    /**
     * Gets the {@code KycFace}.
     *
     * @return {@code KycFace}.
     */
    public KycFace getFace() {
        return mFace;
    }

    /**
     * Gets the {@code KycLivenessResult}.
     *
     * @return {@code KycLivenessResult}.
     */
    public KycLivenessResult getLivenessResult() {
        return mLivenessResult;
    }

    /**
     * Gets the {@code KycEnhancedLivenessResult}.
     *
     * @return {@code KycEnhancedLivenessResult}.
     */
    public KycEnhancedLivenessResult getEnhancedLivenessResult() {
        return mEnhancedLivenevessResult;
    }


    //endregion

}

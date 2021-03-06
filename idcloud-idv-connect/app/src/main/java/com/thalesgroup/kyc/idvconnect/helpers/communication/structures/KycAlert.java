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

import org.json.JSONObject;

public class KycAlert {

    //region Definition

    private final String mDesc;
    private final String mDesposition;
    private final String mInformation;
    private final String mName;
    private final String mResult;

    //endregion

    //region Life Cycle

    public KycAlert(final JSONObject alert) {
        mDesc = JsonUtil.jsonGetString(alert, "Description", "Unknown");
        mDesposition = JsonUtil.jsonGetString(alert, "Disposition", "Unknown");
        mInformation = JsonUtil.jsonGetString(alert, "Information", "Unknown");
        mName = JsonUtil.jsonGetString(alert, "Name", "Unknown");
        mResult = JsonUtil.jsonGetString(alert, "Result", "Unknown");
    }

    //endregion

    //region Public API

    public String getDesc() {
        return mDesc;
    }

    public String getDesposition() {
        return mDesposition;
    }

    public String getInformation() {
        return mInformation;
    }

    public String getName() {
        return mName;
    }

    public String getResult() {
        return mResult;
    }

    //endregion
}

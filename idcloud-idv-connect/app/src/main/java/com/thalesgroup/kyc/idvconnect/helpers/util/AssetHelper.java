/*
 MIT License

 Copyright (c) 2020 Thales DIS

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 and associated
 documentation files (the "Software"), to deal in the Software without restriction, including
 without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 Software, and to
 permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or
 substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT
  SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 IN THE SOFTWARE.

 IMPORTANT: This source code is intended to serve training information purposes only.
 Please make sure to review our IdCloud documentation, including security guidelines.
 */

package com.thalesgroup.kyc.idvconnect.helpers.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Asset helper class.
 */
public class AssetHelper {

    //region Definition

    public static final String ASSET_HOW_TO_SELFIE_BAD = "how_to_selfie_bad.png";
    public static final String ASSET_HOW_TO_SELFIE_GOOD = "how_to_selfie_good.png";
    public static final String ASSET_HOW_TO_SELFIE_TUTORIAL = "kyc_face_id_tutorial.png";

    //endregion

    //region Public API

    /**
     * Retrieves the {@code Bitmap} from an asset.
     *
     * @param strName
     *         Asset name.
     * @param context
     *         Android context.
     * @return {@code Bitmap} loaded from asset.
     */
    public static Bitmap getBitmapFromAsset(final String strName, final Context context) {
        try {
            return BitmapFactory.decodeStream(context.getAssets().open(strName));
        } catch (final IOException exception) {
            return null;
        }
    }

    /**
     * Releases the {@code BitmapDrawable} from a view.
     *
     * @param view
     *         The view from which to release the {@code BitmapDrawable}.
     */
    public static void cleanUpBitmapImageView(final ImageView view) {
        if (view != null && view.getDrawable() instanceof BitmapDrawable) {
            final Drawable drawable = view.getDrawable();
            final Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                view.setImageBitmap(null);
                bitmap.recycle();
            }
        }
    }

    //endregion
}

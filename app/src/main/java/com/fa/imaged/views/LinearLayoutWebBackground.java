/*
 * Copyright (c) 2014 David Alejandro Fernández Sancho
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fa.imaged.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * This is a regular LinearLayout with a new capability: you can set a background on this view using
 * Square's Picasso, as this class implements the Target interface. Using the regular .into() method of
 * Picasso the image will be downloaded and used as background. The background is set in a callback, so
 * it runs asynchronously and all of this can be done using the UI thread.
 * <p/>
 * Sweet, I know.
 */

public class LinearLayoutWebBackground extends LinearLayout implements Target {

    public LinearLayoutWebBackground(Context context) {
        super(context);
    }

    public LinearLayoutWebBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutWebBackground(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {

        BitmapDrawable background = new BitmapDrawable(getResources(), bitmap);
        background.setColorFilter(getResources().getColor(android.R.color.transparent), PorterDuff.Mode.LIGHTEN);

        /*
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(background);
        } else {
            setBackground(background);
        } */
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(background);
        }
        else {
            setBackgroundDrawable(background);
        }
    }

    @Override
    public void onBitmapFailed(Drawable drawable) {

    }

    @Override
    public void onPrepareLoad(Drawable drawable) {

    }
}

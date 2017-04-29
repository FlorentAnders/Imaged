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

package com.fa.imaged.utils;

/**
 * Taken from here: https://gist.github.com/ryanbateman/6667995 with some changes to improve efficiency by tadfisher.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.squareup.picasso.Transformation;

public class BlurTransform implements Transformation {
    private RenderScript rs;
    private float radius;
    private boolean recycleBitmap;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public BlurTransform(Context context, float radius, boolean recycleBitmap) {
        super();
        rs = RenderScript.create(context);
        this.radius = radius;
        this.recycleBitmap = recycleBitmap;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public Bitmap transform(Bitmap bitmap) {
        // Create another bitmap that will hold the results of the filter.
        Bitmap blurredBitmap = Bitmap.createBitmap(bitmap);

        // Allocate memory for Renderscript to work with
        Allocation input;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
            input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SHARED);
        else
            input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SCRIPT);

        Allocation output = Allocation.createTyped(rs, input.getType());

        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setInput(input);

        // Set the blur radius
        script.setRadius(radius);

        // Start the ScriptIntrinisicBlur
        script.forEach(output);

        // Copy the output to the blurred bitmap
        output.copyTo(blurredBitmap);

        // Recycle the original bitmap
        if (recycleBitmap) bitmap.recycle();

        return blurredBitmap;
    }

    @Override
    public String key() {
        return "blur";
    }

}

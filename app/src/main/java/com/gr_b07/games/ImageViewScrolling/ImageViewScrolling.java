/**
 * Slot machine inspireret/kopiret dele fra EDMT dev youtube
 * https://www.youtube.com/watch?v=Ja2MEpWUyYE
 */

package com.gr_b07.games.ImageViewScrolling;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gr_b07.R;

public class ImageViewScrolling extends FrameLayout {

    int animationDuration = 150;
    ImageView currentImage, nextImage;
    int lastResult = 0, oldValue = 0, i = 0;


    IEventEnd eventEnd;

    public void setEventEnd(IEventEnd eventEnd) {
        this.eventEnd = eventEnd;
    }

    public ImageViewScrolling(Context context) {
        super(context);
        init(context);
    }

    public ImageViewScrolling(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.image_view_scrolling, this);
        currentImage = (ImageView) getRootView().findViewById(R.id.currentImage);
        nextImage = (ImageView) getRootView().findViewById(R.id.nextImage);
        nextImage.setTranslationY(getHeight());
    }

    public void setValueRandom(final int image, final int rotateCount) {

        currentImage.animate().translationY(-getHeight()).setDuration(animationDuration).start();
        nextImage.setTranslationY(nextImage.getHeight());
        nextImage.animate().translationY(0).setDuration(animationDuration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setImage(currentImage, oldValue % 6);
                currentImage.setTranslationY(0);
                if (oldValue != rotateCount) {
                    setValueRandom(image, rotateCount);
                    oldValue++;
                } else {
                    lastResult = 0;
                    oldValue = 0;
                    setImage(nextImage, image);
                    eventEnd.eventEnd(image % 6, rotateCount);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void setImage(ImageView imageView, int value) {
        if (value == Util.GROCERIES) {
            i = 0;
            imageView.setImageResource(R.drawable.groceries);
        } else if (value == Util.APPLE) {
            i = 1;
            imageView.setImageResource(R.drawable.apple);
        } else if (value == Util.CARROT) {
            i = 2;
            imageView.setImageResource(R.drawable.carrot);
        } else if (value == Util.SALAD) {
            i = 3;
            imageView.setImageResource(R.drawable.salad);
        } else if (value == Util.AUBERGINE) {
            i = 4;
            imageView.setImageResource(R.drawable.aubergine);
        } else if (value == Util.STRAWBERRY) {
            i = 5;
            imageView.setImageResource(R.drawable.strawberry);
        }
        imageView.setTag(value);
        lastResult = value;
    }

    /*public int getValue() {
        return Integer.parseInt(nextImage.getTag().toString());
    }*/

    public int getValue() {
        System.out.println(i);
        /*int value = 0;
        if (i == 0) {
            value = 0;
        } else if (nextImage.getTag().equals("1")) {
            value = 1;
        } else if (nextImage.getTag().equals("2")) {
            value = 2;
        } else if (nextImage.getTag().equals("3")) {
            value = 3;
        } else if (nextImage.getTag().equals("4")) {
            value = 4;
        } else if (nextImage.getTag().equals("5")) {
            value = 5;
        }*/
        return i;
    }
}

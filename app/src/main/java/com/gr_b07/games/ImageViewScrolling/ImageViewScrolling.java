package com.gr_b07.games.ImageViewScrolling;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gr_b07.R;

public class ImageViewScrolling extends FrameLayout {

    private static int animationDuration = 150;
    ImageView currentImage, nextImage;
    int lastResult = 0, oldValue = 0;

    IEventEnd eventEnd;

    public void setEventEnd(IEventEnd eventEnd)  {
        this.eventEnd = eventEnd;
    }

    public ImageViewScrolling(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ImageViewScrolling(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.activity_slot_machine,this);
        currentImage = (ImageView)getRootView().findViewById(R.id.currentImage);
        nextImage = (ImageView)getRootView().findViewById(R.id.nextImage);

        nextImage.setTranslationY(getHeight());
    }

    public void setValueRandom(final int image, final int rotateCout){
       currentImage.animate().translationY(-getHeight()).setDuration(animationDuration).start();
       nextImage.setTranslationY(nextImage.getHeight());
       nextImage.animate().translationY(0).setDuration(animationDuration).setListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animation) {
               
           }

           @Override
           public void onAnimationEnd(Animator animation) {
           setImage(currentImage,oldValue%6);
           currentImage.setTranslationY(0);
           if(oldValue != rotateCout){
               setValueRandom(image,rotateCout);
               oldValue++;
               }else{
               lastResult = 0;
               oldValue = 0;
               setImage(nextImage,image);
               eventEnd.eventEnd(image%6,rotateCout);
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
        if(value == Util.GROCERIES){
            imageView.setImageResource(R.drawable.groceries);
        } else if(value == Util.APPLE){
          imageView.setImageResource(R.drawable.apple);
        } else if(value == Util.CARROT){
            imageView.setImageResource(R.drawable.carrot);
        } else if(value == Util.SALAD){
            imageView.setImageResource(R.drawable.salad);
        } else if(value == Util.AUBERGINE){
            imageView.setImageResource(R.drawable.aubergine);
        } else if(value == Util.STRAWBERRY){
            imageView.setImageResource(R.drawable.strawberry);
        }
        imageView.setTag(value);
        lastResult = value;
    }

    public int getValue(){
     return Integer.parseInt(nextImage.getTag().toString());
    }
}

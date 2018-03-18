package bid.a02.a02clientproject.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import bid.a02.a02clientproject.R;

/**
 * Created by wai on 18/3/18.
 */

public class MyCustomView extends View {


    public MyCustomView(Context context, AttributeSet attrs){
        super(context, attrs);

        //paint object for drawing in onDraw
        circlePaint = new Paint();

        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.MyCustomView, 0, 0);

        try {
            //get the text and colors specified using the names in attrs.xml
            circleText = a.getString(R.styleable.MyCustomView_circleLabel);
            circleCol = a.getInteger(R.styleable.MyCustomView_circleColor, 0);//0 is default
            labelCol = a.getInteger(R.styleable.MyCustomView_labelColor, 0);
        } finally {
            a.recycle();
        }

    }


    //circle and text colors
    private int circleCol, labelCol;
    //label text
    private String circleText;
    //paint for drawing custom view
    private Paint circlePaint;

    @Override
    protected void onDraw(Canvas canvas) {
        //draw the View

        //get half of the width and height as we are working with a circle
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;

        //get the radius as half of the width or height, whichever is smaller
//subtract ten so that it has some space around it
        int radius = 0;
        if(viewWidthHalf>viewHeightHalf)
            radius=viewHeightHalf-10;
        else
            radius=viewWidthHalf-10;

        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);

        //set the paint color using the circle color specified
        circlePaint.setColor(circleCol);

        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);

        circlePaint.setColor(labelCol);

        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(50);

        //draw the text using the string attribute and chosen properties
        canvas.drawText(circleText, viewWidthHalf, viewHeightHalf, circlePaint);
    }


}

package bid.a02.a02clientproject.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import bid.a02.a02clientproject.R;

/**
 * Created by wai on 18/3/18.
 */

public class MyCustomViewWithXML extends RelativeLayout {


    public MyCustomViewWithXML(Context context, AttributeSet attrs){
        super(context, attrs);

        View view = inflate(getContext(), R.layout.view_customwithxml, null);
        addView(view);

        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.MyCustomView, 0, 0);

        labelCounter = a.getInteger(R.styleable.MyCustomView_labelCounter, 1);

    }

    public int labelCounter;




}

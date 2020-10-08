package com.whdx.base.ui.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.whdx.base.R;
import com.whdx.base.util.MathUtil;
import com.whdx.base.util.wrap.CallbackNoReturn;
import com.whdx.base.util.wrap.TextWatcherWrap;
import com.whdx.base.util.wrap.ViewUtil;

import java.math.BigDecimal;


public class AddAndSubView extends FrameLayout {

    private BigDecimal mNum = BigDecimal.ZERO;//输入框数字
    private BigDecimal step = BigDecimal.ONE;//加减步数

    private EditText mEtAddAndSub;
    private TextView mEtSub;
    private TextView mEtAdd;

    private int precision = 0;

    private CallbackNoReturn<BigDecimal> numberUpdateListener;

    public AddAndSubView(@NonNull Context context) {
        this(context, null);
    }

    public AddAndSubView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddAndSubView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        View view = View.inflate(context, R.layout.view_add_and_sub, null);
        mEtAddAndSub = view.findViewById(R.id.etAddAndSub);
        mEtSub = view.findViewById(R.id.etSub);
        mEtAdd = view.findViewById(R.id.etAdd);
        addView(view);
        mEtAddAndSub.setText("0");
        setPrecision(0);
        resetCursor("0");
        initEvent();
    }

    public void setHint(CharSequence hint){
        if(mEtAddAndSub != null){
            mEtAddAndSub.setHint(hint);
        }
    }

    public void setPrecision(int precision) {
        this.precision = precision;
        mEtAddAndSub.setFilters(new InputFilter[]{ViewUtil.get2NumPoint(precision, 10)});
    }

    private void initEvent() {

        mEtAdd.setOnClickListener(v -> {
            addNum();
        });

        mEtSub.setOnClickListener(v -> {
            subNum();
        });

       /* mEtAddAndSub.setOnClickListener(v -> {
            mEtAddAndSub.setFocusableInTouchMode(true);
            mEtAddAndSub.requestFocus();
            mEtAddAndSub.selectAll();
        });*/

        mEtAddAndSub.addTextChangedListener(new TextWatcherWrap() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                String val = s.toString();
                if (TextUtils.isEmpty(val)) {
                    val = "0";
                    mEtAddAndSub.setText("0");
                    resetCursor("0");
                }
                if(val.equals(".")){
                    val = "0";
                }
                mNum = new BigDecimal(val);
                if(numberUpdateListener != null) {numberUpdateListener.callback(mNum);}
            }
        });
    }

    public void setNumberUpdateListener(CallbackNoReturn<BigDecimal> numberUpdateListener){
        this.numberUpdateListener = numberUpdateListener;
    }

    private void subNum() {
        mNum = this.mNum.subtract(step);
        if (mNum.compareTo(BigDecimal.ZERO) < 0) {
            mNum = BigDecimal.ZERO;
        }
        mEtAddAndSub.setText(getNumberString());
        resetCursor(mEtAddAndSub.getText().toString());
    }

    public BigDecimal getNumber(){
        return mNum;
    }

    private void addNum() {
        mNum = mNum.add(step);
        mEtAddAndSub.setText(getNumberString());
        resetCursor(mEtAddAndSub.getText().toString());
    }

    /**
     * 设置输入框内容
     * @param num
     */
    public void setNum(BigDecimal num) {
        mNum = num;
//        ViewUtil.clearFocus(mEtAddAndSub);
        String text = getNumberString();
        mEtAddAndSub.setText(text);
        resetCursor(text);
    }

    private void resetCursor(String text) {
        if(mEtAddAndSub.hasFocus()){
            mEtAddAndSub.setSelection(text.length());
        }
    }

    private String getNumberString(){
        return MathUtil.doubleToFloorString(mNum.doubleValue(), precision);
    }

    /**
     * 设置加减步数
     * @param step
     */
    public void setStep(BigDecimal step) {
        this.step = step;
    }


    public void reset() {
        mEtAddAndSub.setText("");
    }
}

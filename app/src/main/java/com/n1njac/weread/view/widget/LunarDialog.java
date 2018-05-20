package com.n1njac.weread.view.widget;
/*    
 *    Created by N1njaC on 2018/5/8.
 *    email:aiai173cc@gmail.com 
 */

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.n1njac.weread.R;

public class LunarDialog extends Dialog {
    public LunarDialog(@NonNull Context context) {
        super(context, R.style.LunarDialog);
        setCanceledOnTouchOutside(true);
    }

}

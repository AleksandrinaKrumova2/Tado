package com.tado.android.installation;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.tado.C0676R;

public class InvitationReviewActivity_ViewBinding<T extends InvitationReviewActivity> implements Unbinder {
    protected T target;
    private View view2131296386;
    private View view2131296387;
    private View view2131296388;
    private View view2131296394;

    @UiThread
    public InvitationReviewActivity_ViewBinding(final T target, View source) {
        this.target = target;
        target.textviewInvitationBody = (TextView) Utils.findRequiredViewAsType(source, C0676R.id.textview_invitation_body, "field 'textviewInvitationBody'", TextView.class);
        View view = Utils.findRequiredView(source, C0676R.id.button_join_home, "field 'buttonJoinHome' and method 'onClick'");
        target.buttonJoinHome = (Button) Utils.castView(view, C0676R.id.button_join_home, "field 'buttonJoinHome'", Button.class);
        this.view2131296386 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClick(p0);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.button_reject_invitation, "field 'buttonRejectInvitation' and method 'onClick'");
        target.buttonRejectInvitation = (Button) Utils.castView(view, C0676R.id.button_reject_invitation, "field 'buttonRejectInvitation'", Button.class);
        this.view2131296388 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClick(p0);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.button_sign_out, "field 'buttonSignOut' and method 'onClick'");
        target.buttonSignOut = (Button) Utils.castView(view, C0676R.id.button_sign_out, "field 'buttonSignOut'", Button.class);
        this.view2131296394 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClick(p0);
            }
        });
        view = Utils.findRequiredView(source, C0676R.id.button_proceed_home, "field 'buttonProceed' and method 'onClick'");
        target.buttonProceed = (Button) Utils.castView(view, C0676R.id.button_proceed_home, "field 'buttonProceed'", Button.class);
        this.view2131296387 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClick(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.textviewInvitationBody = null;
        target.buttonJoinHome = null;
        target.buttonRejectInvitation = null;
        target.buttonSignOut = null;
        target.buttonProceed = null;
        this.view2131296386.setOnClickListener(null);
        this.view2131296386 = null;
        this.view2131296388.setOnClickListener(null);
        this.view2131296388 = null;
        this.view2131296394.setOnClickListener(null);
        this.view2131296394 = null;
        this.view2131296387.setOnClickListener(null);
        this.view2131296387 = null;
        this.target = null;
    }
}

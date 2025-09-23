package amc.controller;

public class ProfileNode {
    public static final int EDIT_LOG  = 1<<0;
    public static final int SHOW_CMT  = 1<<1;
    public static final int SHOW_APPT = 1<<2;
    public static final int SEND_CMT  = 1<<3;

    public final boolean isEditLogout;
    public final boolean isShowEmpComment;
    public final boolean isShowCusFeedback;
    public final boolean isSendComment;
    public final ApptNode nextApptNode;

    public ProfileNode(int options, ApptNode nextApptNode) {
        isEditLogout      = (options & EDIT_LOG ) != 0;
        isShowEmpComment  = (options & SHOW_CMT ) != 0;
        isShowCusFeedback = (options & SHOW_APPT) != 0;
        isSendComment     = (options & SEND_CMT ) != 0;
        this.nextApptNode = nextApptNode;
    }
}

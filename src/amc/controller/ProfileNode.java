package amc.controller;

public class ProfileNode {
    public static final int EDIT_LOG  = 1<<0;
    public static final int SHOW_CMT  = 1<<1;
    public static final int SHOW_APPT = 1<<2;
    public static final int SEND_CMT  = 1<<3;

    private final boolean editLogout;
    private final boolean showEmployeeCmt;
    private final boolean showCustomerAppt;
    private final boolean sendComment;
    private final ApptNode nextApptNode;

    public ProfileNode(int options, ApptNode nextApptNode) {
        editLogout       = (options & EDIT_LOG ) != 0;
        showEmployeeCmt  = (options & SHOW_CMT ) != 0;
        showCustomerAppt = (options & SHOW_APPT) != 0;
        sendComment      = (options & SEND_CMT ) != 0;
        this.nextApptNode = nextApptNode;
    }

    public boolean isEditLogout() { return editLogout; }
    public boolean isShowEmployeeCmt() { return showEmployeeCmt; }
    public boolean isShowCustomerAppt() { return showCustomerAppt; }
    public boolean isSendComment() { return sendComment; }
    public ApptNode getNextApptNode() { return nextApptNode; }
}

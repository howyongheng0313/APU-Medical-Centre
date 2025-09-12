package amc.controller;

public class ApptNode {
    public static final int SHOW_CUS = 1<<0;
    public static final int SHOW_DOC = 1<<1;
    public static final int SHOW_STF = 1<<2;

    private final boolean showCustomer;
    private final boolean showDoctor;
    private final boolean showStaff;
    private final ProfileNode cusProfileNode;
    private final ProfileNode docProfileNode;
    private final ProfileNode stfProfileNode;

    public ApptNode(
        int options,
        ProfileNode cusProfileNode,
        ProfileNode docProfileNode,
        ProfileNode stfProfileNode
    ) {
        showCustomer = (options & SHOW_CUS) != 0;
        showDoctor   = (options & SHOW_DOC) != 0;
        showStaff    = (options & SHOW_STF) != 0;
        this.cusProfileNode = cusProfileNode;
        this.docProfileNode = docProfileNode;
        this.stfProfileNode = stfProfileNode;
    }

    public boolean isShowCustomer() { return showCustomer; }
    public boolean isShowDoctor() { return showDoctor; }
    public boolean isShowStaff() { return showStaff; }
    public ProfileNode getCusProfileNode() { return cusProfileNode; }
    public ProfileNode getDocProfileNode() { return docProfileNode; }
    public ProfileNode getStfProfileNode() { return stfProfileNode; }
}

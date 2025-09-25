package amc.controller.share;

public class ApptNode {
    public static final int SHOW_CUS = 1<<0;
    public static final int SHOW_DOC = 1<<1;
    public static final int SHOW_STF = 1<<2;
    public static final int SHOW_CONSULT = 1<<3;
    public static final int SHOW_RESULT  = 1<<4;
    public static final int SHOW_PAYING  = 1<<5;

    public final boolean isShowCustomer;
    public final boolean isShowDoctor;
    public final boolean isShowStaff;
    public final ProfileNode cusProfileNode;
    public final ProfileNode docProfileNode;
    public final ProfileNode stfProfileNode;
    public final boolean isShowConsult;
    public final boolean isShowResult;
    public final boolean isShowPaying;

    public ApptNode(
        int options,
        ProfileNode cusProfileNode,
        ProfileNode docProfileNode,
        ProfileNode stfProfileNode
    ) {
        isShowCustomer = (options & SHOW_CUS) != 0;
        isShowDoctor   = (options & SHOW_DOC) != 0;
        isShowStaff    = (options & SHOW_STF) != 0;
        this.cusProfileNode = cusProfileNode;
        this.docProfileNode = docProfileNode;
        this.stfProfileNode = stfProfileNode;
        isShowConsult = (options & SHOW_CONSULT) != 0;
        isShowResult  = (options & SHOW_RESULT)  != 0;
        isShowPaying  = (options & SHOW_PAYING)  != 0;
    }
}

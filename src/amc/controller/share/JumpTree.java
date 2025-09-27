package amc.controller.share;

public class JumpTree {
    public static final ProfileNode UsrSelf = new ProfileNode(
        ProfileNode.EDIT_LOG,
        null
    );

    public static final ProfileNode DocSelf = new ProfileNode(
        ProfileNode.EDIT_LOG | ProfileNode.SHOW_CMT,
        new ApptNode(
            ApptNode.SHOW_CUS | ApptNode.SHOW_STF | ApptNode.SHOW_RESULT,
            new ProfileNode(0, null), null, null
        )
    );

    public static final ProfileNode StfSelf = new ProfileNode(
        ProfileNode.EDIT_LOG | ProfileNode.SHOW_CMT,
        new ApptNode(
            ApptNode.SHOW_CUS | ApptNode.SHOW_DOC,
            new ProfileNode(0, null), null, null
        )
    );

    private static final ProfileNode MngCustomer = new ProfileNode(
        ProfileNode.SHOW_APPT, null
    );

    private static final ProfileNode MngEmployee = new ProfileNode(
        ProfileNode.SHOW_CMT, null
    );

    public static final ProfileNode MngDoctorCmt = new ProfileNode(
        ProfileNode.SHOW_CMT,
        new ApptNode(
            ApptNode.SHOW_CUS | ApptNode.SHOW_DOC | ApptNode.SHOW_STF | ApptNode.SHOW_RESULT,
            MngCustomer, null, MngEmployee
        )
    );

    public static final ProfileNode MngStaffCmt = new ProfileNode(
        ProfileNode.SHOW_CMT,
        new ApptNode(
            ApptNode.SHOW_CUS | ApptNode.SHOW_DOC | ApptNode.SHOW_STF | ApptNode.SHOW_RESULT,
            MngCustomer, MngEmployee, null
        )
    );

    public static final ApptNode MngAppt = new ApptNode(
        ApptNode.SHOW_CUS | ApptNode.SHOW_DOC | ApptNode.SHOW_STF | ApptNode.SHOW_RESULT,
        MngCustomer, MngEmployee, MngEmployee
    );

    public static final ApptNode DocConsultation = new ApptNode(
        ApptNode.SHOW_CUS | ApptNode.SHOW_STF | ApptNode.SHOW_CONSULT | ApptNode.SHOW_RESULT,
        new ProfileNode(
            ProfileNode.SHOW_APPT,
            new ApptNode(
                ApptNode.SHOW_DOC | ApptNode.SHOW_STF | ApptNode.SHOW_RESULT,
                null, null, null
            )
        ), null, null
    );

    private static final ProfileNode CusEmployee = new ProfileNode(
        ProfileNode.SHOW_CMT | ProfileNode.SEND_CMT,
        null
    );

    public static final ApptNode CusAppt = new ApptNode(
        ApptNode.SHOW_DOC | ApptNode.SHOW_STF | ApptNode.SHOW_RESULT,
        null, CusEmployee, CusEmployee
    );
}

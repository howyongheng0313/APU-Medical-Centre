package amc.model.db_impl;

import amc.model.DbAdapter;
import amc.model.entity.Payment;
import java.util.List;

public class PaymentAdapt extends DbAdapter<Payment> {
    protected PaymentAdapt() {}

    @Override
    public Payment getMod(List<String> row) {
        Payment model = new Payment(
            row.get(0),
            row.get(1),
            Payment.Method.valueOf(row.get(2))
        );
        return model;
    }

    @Override
    public List<String> getRow(Payment model) {
        List<String> row = List.of(
            model.getPaymentId(),
            model.getAppointmentId(),
            model.getPaymentMethod().name()
        );
        return row;
    }
}

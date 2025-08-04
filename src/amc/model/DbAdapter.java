package amc.model;

import java.util.ArrayList;
import java.util.List;
import amc.controller.Convertor;

public interface DbAdapter<T> {
    public T getMod(List<String> row);
    public List<String> getRow(T model);

    public static final DbAdapter<MyModel> MYMODEL = new DbAdapter<>() {
        @Override
        public MyModel getMod(List<String> row) {
            return new MyModel(
                Convertor.DATE.convert(row.get(0)),
                Convertor.NUM.convert(row.get(1)).intValue()
            );
        }

        @Override
        public List<String> getRow(MyModel model) {
            List<String> row = new ArrayList<> ();
            row.add(Convertor.DATE.strify(model.getBirth()));
            row.add(Convertor.NUM.strify(model.getUuid()));
            return row;
        }
    };
}
